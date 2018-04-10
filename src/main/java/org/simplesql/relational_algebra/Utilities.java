package org.simplesql.relational_algebra;

import java.io.ByteArrayInputStream;
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
import org.simplesql.parse.SimpleSQLParser.Data_sourceContext;
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
				
		if(!project.resolve(new SchemaResolver(schema), System.out)){
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
			groupBy.addColumn(findColumn(each));
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
					column = findColumn(each.function().expr().get(0).column());
				}else if(each.function().expr().get(0).WILDCARD()!=null){
					column = new Column("*");
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
		Expression<?> expr = findExpression(tree.expr());
		if(expr instanceof BooleanBinaryExpression){
			Filter filter = new Filter((BooleanBinaryExpression)expr);
			project.setFilter(filter);
		}
	}
	
	private static Expression<?> findExpression(ExprContext context){
		if(context==null){
			return null;
		}else if(context.column()!=null){
			return findColumn(context.column());
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
		
		Expression<?> left = findExpression( context.expr(0));
		Expression<?> right = findExpression( context.expr(1));
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
			Column column = findColumn(each);
			if(column!=null){
				project.addColumn(column);
			}
		}		
	}
	
	private static Column findColumn(ColumnContext each){
		if(each.ANY_NAME()!=null){
			String column = each.ANY_NAME().getText();
			return new Column(column);
		}else if(each.table_column()!=null){
			String table = each.table_column().ANY_NAME().get(0).getText();
			String column = each.table_column().ANY_NAME().get(1).getText();
			return new Column(table, column);		
		}else if(each.function()!=null){
			return null;
		}else{
			throw new IllegalStateException("unsupport column");
		}
	}
	
	private static void findDataSource(Project project, ParseContext tree){
		DataSource dataSource = findDataSource(tree.data_source());
		project.setDataSource(dataSource);
	}
	
	private static DataSource findDataSource(Data_sourceContext data_sourceContext) {
		if(data_sourceContext.table_name()!=null){
			return new Table(data_sourceContext.table_name().getText());
		}else if(data_sourceContext.join_operator()!=null){
			List<Data_sourceContext> dataSources = data_sourceContext.data_source();
			DataSource left = findDataSource( dataSources.get(0));
			DataSource right = findDataSource( dataSources.get(1));
			
			Expression<?> joinCondition = null;
			if(data_sourceContext.join_condition()!=null){
				joinCondition = findExpression(data_sourceContext.join_condition().expr());
			}

			String joinType = data_sourceContext.join_operator().join_type().getText(); 
			if(joinType.equals("NATURAL") || joinType.equals("INNER") && 
					joinCondition instanceof BooleanBinaryExpression){
				return Join.defineJoin(left, right, data_sourceContext.join_operator().join_type().getText(), 
						(BooleanBinaryExpression)joinCondition);
			}else{
				throw new IllegalStateException("unsupported join condition");
			}
		}else{
			throw new IllegalStateException("unsupported data source: "+data_sourceContext.getText());
		}
	}


	public static void main(String args[]) throws IOException{
		test("schema/test.json", "sELECT testtableA.a, testtableB.b FROM testtableA "+
					"inner join testtableB on testtableA.a=testtableB.a and testtableA.b=testtableB.b;");

		test("schema/test.json", "sELECT a, b, sum(c), count(*) FROM testtableA  natural join testtableB "+
					"where a>1 and b<5 GROUP BY a,b;");

		test("schema/test.json", "sELECT a, b,c,d,e,  g FROM testtableA natural join testtableB "+
					"natural join testtableC;");
	}

	private static void test(String schemaPath, String sql)throws IOException{
//		CharStream input = CharStreams.fromFileName(sqlFile);
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project ra = parseTreeToRelAlg(parser, new File(schemaPath).toURI().toURL());
		System.out.println(ra);
		
//		SchemaResolver resolver = new SchemaResolver(new File(schemaPath).toURI().toURL());
//		ProjectIterator projectIterator = IteratorBuilder.buildCSVProjectIterator(ra, resolver);
//		print(projectIterator);		
	}
	
	private static void print(ProjectIterator projectIterator) {
		List<Column> columns = projectIterator.getColumns();
		StringBuilder sb = new StringBuilder();
		for(Column each:columns){
			sb.append(each.toString()+"\t");
		}
		if(projectIterator.getAggregates()!=null){
			for(Aggregate aggregate:projectIterator.getAggregates()){
				sb.append(aggregate.toString()+"\t");
			}
		}
		System.out.println(sb.toString());
		while(projectIterator.hasNext()){
			sb = new StringBuilder();
			Row row = projectIterator.next();
			for(Column each:columns){
				sb.append(row.get(each.toString())+"\t");
			}		
			if(projectIterator.getAggregates()!=null){
				for(Aggregate aggregate:projectIterator.getAggregates()){
					sb.append(row.get(aggregate.toString())+"\t");
				}
			}
			System.out.println(sb.toString());
		}
	}
}
