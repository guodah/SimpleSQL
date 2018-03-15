package org.simplesql.relational_algebra;

import java.io.IOException;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.parse.SimpleSQLParser.ColumnContext;
import org.simplesql.parse.SimpleSQLParser.ExprContext;
import org.simplesql.parse.SimpleSQLParser.Literal_valueContext;
import org.simplesql.parse.SimpleSQLParser.ParseContext;

public class Utilities {
	public static Project parseTreeToRelAlg(SimpleSQLParser parser){
		ParseContext tree = parser.parse();
		Project project = new Project();
		findColumns(project, tree);
		findDataSource(project, tree);
		findFilter(project, tree);
		return project;
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
			return new Column(context.column().getText());
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
		
		Expression<?> left = findExpression(context.expr(0));
		Expression<?> right = findExpression(context.expr(1));
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
			project.addColumn(new Column(each.ANY_NAME().getText()));
		}		
	}
	
	private static void findDataSource(Project project, ParseContext tree){
		project.setDataSource(new Table(tree.table_name().ANY_NAME().getText()));
	}
	
	public static void main(String args[]) throws IOException{
		CharStream input = CharStreams.fromFileName("test3.sql");
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);
		Project ra = parseTreeToRelAlg(parser);
		System.out.println(ra);
	}
}
