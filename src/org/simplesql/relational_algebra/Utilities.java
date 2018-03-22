package org.simplesql.relational_algebra;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Formatter;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.parse.SimpleSQLParser.ColumnContext;
import org.simplesql.parse.SimpleSQLParser.ExprContext;
import org.simplesql.parse.SimpleSQLParser.Literal_valueContext;
import org.simplesql.parse.SimpleSQLParser.ParseContext;
import org.simplesql.resolve.SchemaResolver;

public class Utilities {
	public static Project parseTreeToRelAlg(SimpleSQLParser parser, URL schema) throws MalformedURLException, IOException{
		ParseContext tree = parser.parse();
		Project project = new Project();
		findDataSource(project, tree);
		findFilter(project, tree);
		findColumns(project, tree);
		
		findAggregates(project, tree);
		findGroupBy(project, tree);
		
		if(!resolve(project, new SchemaResolver(schema), System.out)){
			return null;
		}else{
			return project;
		}
	}
	
	private static void findGroupBy(Project project, ParseContext tree) {
		if(tree.group_by()==null) return;
		
		List<ColumnContext> columns = tree.group_by().columns().column();
		GroupBy groupBy = new GroupBy();
		for(ColumnContext each:columns){
			groupBy.addColumn(new Column(project.getDataSource(), each.ANY_NAME().getText()));
		}
		groupBy.setAggregates(project.getAggregates());
		project.addGroupBy(groupBy);
	}

	private static void findAggregates(Project project, ParseContext tree) {
		List<ColumnContext> columns = tree.columns().column();
		for(ColumnContext each:columns){
			if(each.function()!=null){
				Column column = null;
				if(each.function().expr().get(0).column()!=null){
					column = new Column(project.getDataSource(), each.function().expr().get(0).column().getText());
				}else if(each.function().expr().get(0).WILDCARD()!=null){
					column = new Column(project.getDataSource(), "*");
				}
				
				project.addAggregate(findAggregateFunction(column, each.function().function_name().getText()));
			}
		}		
	}

	private static Aggregate findAggregateFunction(Column column, String text) {
		text = text.toUpperCase();
		if(text.equals("SUM")){
			return new AggregateSum(column);
		}else if(text.equals("COUNT")){
			return new AggregateCount(column);
		}else{
			throw new IllegalStateException("unsupported aggregate function");
		}
	}

	private static void findFilter(Project project, ParseContext tree){
		if(tree.WHERE()==null) return;
		Expression<?> expr = findExpression(project, tree.expr());
		if(expr instanceof BooleanBinaryExpression){
			Filter filter = new Filter((BooleanBinaryExpression)expr);
			project.setFilter(filter);
		}
	}
	
	private static Expression<?> findExpression(Project project, ExprContext context){
		if(context==null){
			return null;
		}else if(context.column()!=null){
			return new Column(project.getDataSource(), context.column().getText());
		}else if(context.literal_value()!=null){
			Literal_valueContext literal = context.literal_value();
			if(literal.NULL()!=null){
				return new NullValue();
			}else if(literal.STRING_LITERAL()!=null){
				return new StringValue(literal.STRING_LITERAL().getText());
			}else{ //literal.NUMERIC_LITERAL()!=null
				String str = literal.getText();
				
				if(str.contains(".") || str.contains("E")){
					return new DoubleValue(Double.parseDouble(str));
				}else{
					return new LongValue(Long.parseLong(str));
				}
			}
		}
		
		Expression<?> left = findExpression(project, context.expr(0));
		Expression<?> right = findExpression(project, context.expr(1));
		String operator = findOperator(context);
		return new BooleanBinaryExpression(left, operator, right);
	}
	
	private static String findOperator(ExprContext context) {
		if(context.GT()!=null){
			return ">=";
		}else if(context.LT()!=null){
			return "<";
		}else if(context.GTEQ()!=null){
			return ">=";
		}else if(context.LTEQ()!=null){
			return "<=";
		}else if(context.EQ()!=null){
			return "=";
		}else if(context.NEQ()!=null){
			return "<>";
		}else if(context.IS()!=null){
			return "is";
		}else if(context.AND()!=null){
			return "AND";
		}else if(context.OR()!=null){
			return "OR";
		}
		
		throw new IllegalStateException("Unrecognized operator");
	}

	private static void findColumns(Project project, ParseContext tree){
		List<ColumnContext> columns = tree.columns().column();
		for(ColumnContext each:columns){
			if(each.ANY_NAME()!=null){
				project.addColumn(new Column(project.getDataSource(), each.ANY_NAME().getText()));
			}
		}		
	}
	
	private static void findDataSource(Project project, ParseContext tree){
		project.setDataSource(new Table(tree.table_name().ANY_NAME().getText()));
	}
	
	public static boolean resolve(Project ra, SchemaResolver resolver, OutputStream output){
		
		
		boolean result = true;
		// resolve columns
		List<Column> columns = ra.getColumns();
		for(Column each: columns){
			if(!each.resolve(resolver, output)){
				result = false;
			}
		}
		// resolve datasource
		if(!ra.getDataSource().resolve(resolver, output)){
			result = false;
		}
		
		// resolve filter
		if(ra.getFilter()!=null && !ra.getFilter().getExpression().resolve(resolver, output)){
			result = false;
		}
		return result;
		
	}
		
	public static void main(String args[]) throws IOException{
		CharStream input = CharStreams.fromFileName("test.sql");
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);
	
		Project ra = parseTreeToRelAlg(parser, new File("tables/test.json").toURI().toURL());
		
		URL csvFile = new File("data/Testtable_groups.csv").toURI().toURL();
		SchemaResolver resolver = new SchemaResolver(new File("tables/test.json").toURI().toURL());
		ProjectIterator projectIterator = IteratorBuilder.buildSingleCSVProjectIterator(ra, resolver, csvFile, "testtable");
		System.out.printf("a\tb\tCOUNT(*)\tSUM(c)\n");
		while(projectIterator.hasNext()){
			Row row = projectIterator.next();
			System.out.printf("%d\t%d\t%d\t%d\n", row.get("a").evaluate(null), row.get("b").evaluate(null),
					row.get("COUNT(*)").evaluate(null), row.get("SUM(c)").evaluate(null));
		}
		
		System.out.println(ra);
	}
}
