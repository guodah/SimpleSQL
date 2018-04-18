package org.simplesql.relational_algebra;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.SimpleSQL;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.parse.SimpleSQLParser.ColumnContext;
import org.simplesql.parse.SimpleSQLParser.ExprContext;
import org.simplesql.parse.SimpleSQLParser.FunctionContext;
import org.simplesql.parse.SimpleSQLParser.Literal_valueContext;
import org.simplesql.parse.SimpleSQLParser.ParseContext;
import org.simplesql.parse.SimpleSQLParser.RelationContext;
import org.simplesql.resolve.SchemaResolver;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Utilities {
	@SuppressWarnings("unchecked")
	private static Set<String> AGGREGATE_FUNCTIONS = new HashSet<String>(Arrays.asList(
				new String[]{"COUNT", "SUM", "AVERAGE", "MAX", "MIN"}));
	
	
	public static Project parseTreeToRelAlg(SimpleSQLParser parser) throws MalformedURLException, IOException{
		ParseContext tree = parser.parse();
		return findProject(tree, SimpleSQL.getSchemaResolver());
	}

	private static Project findProject(ParseContext tree,SchemaResolver resolver){
		Project project = new Project();
		findDataSource(project, tree, resolver);
		findFilter(project, tree);
		findColumns(project, tree);
		findGroupBy(project, tree);
				
		if(!project.resolve(resolver, System.out)){
			return null;
		}else{
			return project;
		}
		
	}

	private static boolean isAggregateFunction(String function){
		return AGGREGATE_FUNCTIONS.contains(function);
	}
	
	private static Function<?> findFunction(FunctionContext functionContext){
		String function = functionContext.function_name().getText();
		if(isAggregateFunction(function)){
			Expression<?> column = findExpr(functionContext.expr().get(0));
			return AggregateBuilder.
					newInstance().
					setColumn(column).
					setFunction(function).
					build();
		}else{
			throw new IllegalStateException("function unsupported: "+function);
		}
	}
	
	private static Expression<?> findExpr(ExprContext expr){
		if(expr.function()!=null){
			return findFunction(expr.function());
		}else if(expr.column()!=null){
			return findColumn(expr.column());
		}else if(expr.literal_value()!=null){
			return findLiteralValue(expr.literal_value());
		}else if(expr.add_sub()!=null || expr.mul_div()!=null 
				|| expr.compare_operator()!=null || expr.AND()!=null || expr.OR()!=null){
			return findBinaryExpression(expr);
		}else if(expr.getText().equals("*")){
			return new Column("*");
		}else{
			throw new IllegalStateException("unregconized expression: "+expr.getText());
		}
	}
	
	private static Expression<?> findLiteralValue(Literal_valueContext literal) {
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

	private static void findGroupBy(Project project, ParseContext tree) {
		if(tree.group_by()==null) return;
		
		List<ExprContext> columns = tree.group_by().columns().expr();
		GroupBy groupBy = new GroupBy();
		for(ExprContext each:columns){
			groupBy.addColumn(findColumn(each.column()));
		}
		groupBy.setAggregates(project.getAggregates());
		project.addGroupBy(groupBy);
	}


	private static void findFilter(Project project, ParseContext tree){
		if(tree.WHERE()==null) return;
		Expression<?> expr = findExpr(tree.expr());
		if(expr instanceof BooleanBinaryExpression){
			Filter filter = new Filter((BooleanBinaryExpression)expr);
			project.setFilter(filter);
		}
	}
	
	private static Expression<?> findBinaryExpression(ExprContext context){
		
		Expression<?> left = findExpr( context.expr(0));
		Expression<?> right = findExpr( context.expr(1));
		
		if(context.add_sub()!=null || context.mul_div()!=null){
			return new NumericBinaryExpression(left, findOperator(context), right);
		}else{
			return new BooleanBinaryExpression(left, findOperator(context), right);
		}
	}
	
	private static String findOperator(ExprContext context) {
		if(context.add_sub()!=null){
			if(context.add_sub().ADD()!=null){
				return "+";
			}else if(context.add_sub().SUB()!=null){
				return "-";
			}else{
				throw new IllegalStateException("unrecognized numeric operator: "+context.getText());
			}
		}else if(context.mul_div()!=null){
			if(context.mul_div().MUL()!=null){
				return "*";
			}else if(context.mul_div().DIV()!=null){
				return "/";
			}else{
				throw new IllegalStateException("unrecognized numeric operator: "+context.getText());
			}
		}else if(context.compare_operator()!=null){
			if(context.compare_operator().GT()!=null){
				return ">";
			}else if(context.compare_operator().LT()!=null){
				return "<";
			}else if(context.compare_operator().GTEQ()!=null){
				return ">=";
			}else if(context.compare_operator().LTEQ()!=null){
				return "<=";
			}else if(context.compare_operator().EQ()!=null){
				return "=";
			}else if(context.compare_operator().NEQ()!=null){
				return "<>";
			}else if(context.compare_operator().IS()!=null){
				return "is";
			}else{
				throw new IllegalStateException("unrecognized compare operator: "+context.getText());
			}
		}else if(context.AND()!=null){
			return "AND";
		}else if(context.OR()!=null){
			return "OR";
		}else{
			throw new IllegalStateException("unrecognized operator: "+context.getText());
		}
	}

	private static void findColumns(Project project, ParseContext tree){
		List<ExprContext> columns = tree.columns().expr();
		
		for(ExprContext each:columns){
			Expression<?> column = findExpr(each);
			if(column==null){
				continue;
			}else if(column instanceof Aggregate){
				project.addAggregate((Aggregate)column);				
			}else {
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
		}else{
			throw new IllegalStateException("unsupport column");
		}
	}
	
	private static void findDataSource(Project project, ParseContext tree, SchemaResolver resolver){
		Relation dataSource = findDataSource(tree.relation(), resolver);
		project.setRelation(dataSource);
	}
	
	private static Relation findDataSource(RelationContext relationContext, SchemaResolver resolver) {
		if(relationContext.table_name()!=null){
			return new Table(relationContext.table_name().getText());
		}else if(relationContext.join_operator()!=null){
			List<RelationContext> dataSources = relationContext.relation();
			Relation left = findDataSource( dataSources.get(0), resolver);
			Relation right = findDataSource( dataSources.get(1), resolver);
			
			Expression<?> joinCondition = null;
			if(relationContext.join_condition()!=null){
				joinCondition = findExpr(relationContext.join_condition().expr());
			}

			String joinType = relationContext.join_operator().join_type().getText(); 
			if(joinType.equals("NATURAL") || joinType.equals("INNER") && 
					joinCondition instanceof BooleanBinaryExpression){
				return Join.defineJoin(left, right, relationContext.join_operator().join_type().getText(), 
						(BooleanBinaryExpression)joinCondition);
			}else{
				throw new IllegalStateException("unsupported join condition");
			}
		}else if(relationContext.parse()!=null){
			return findProject(relationContext.parse(), resolver);
		}else{
			throw new IllegalStateException("unsupported data source: "+relationContext.getText());
		}
	}
}
