package org.simplesql.main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.QueryOptimizer;
import org.simplesql.SimpleSQL;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.relational_algebra.Aggregate;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;
import static org.simplesql.relational_algebra.Utilities.parseTreeToRelAlg;

public class Main {
	public static void main(String args[]) throws IOException{

		SimpleSQL.setSchema(new File("schema/test.json").toURI().toURL());
		
//		execute("schema/test.json", "select a, b,c,d from testtablea where a>=2 and c<=5");
//		execute("schema/test.json", "select a, b,count(*), sum(c) from testtablea group by a, b");
		
//		execute("schema/test.json", "sELECT testtableA.a, testtableB.b FROM testtableA "+
//				"inner join testtableB on testtableA.a=testtableB.b and testtableA.b=testtableB.b;");

//		execute("schema/test.json", "sELECT a, b FROM testtableA  natural join testtableB ");
		
//		execute("schema/test.json", "sELECT a, b, sum(c), count(*) FROM testtableA  natural join testtableB "+
//				"where a>1 and b>2 GROUP BY a,b;");

//		execute("schema/test.json", "sELECT a, b,c,d,e,  g FROM testtableA natural join testtableB "+
//				"natural join testtableC where b>3;");
		
//		execute("schema/test.json", "sELECT a, b,c,d,e,  g FROM (select a, b,c,d from testtablea) natural join testtableB "+
//				"natural join testtableC where b>3;");

//		execute("schema/test.json", "sELECT a, b,c,d  FROM (select a, b,c,d from testtablea)");
		
//		execute("schema/test.json", "select a+1, b*2+c, c+d from testtablea where a+3>=2*d-1");
		
//		execute("schema/test.json", "SELECT a, b, c, d from testtableA where a>=2 and c>=5;");
//		execute("schema/test.json", "sELECT a, b, sum(c), count(*) FROM testtableA  natural join testtableB "+
//				"where a>1 and b>2 GROUP BY a,b;");
		
//		execute("schema/test.json", "select testtablea.a, e from (select * from testtablea)"
//				+ " inner join testtableB on testtableA.b = testtableB.b where c>2");

//		execute("schema/test.json", "select testtablea.a, testtableb.b from testtableA inner join "
//				+ "(select a, b, e, f from testtableb) "
//				+ "on testtableA.b = testtableB.b "
//				+"where testtablea.a>2 and testtablea.c>1 and testtableb.f<5 and testtableb.e>=2");
		
//		execute("schema/test.json", "select * from testtablea");
/*
		execute("schema/test.json", "select a,b, e, f from testtableA natural join testtableB where testtableA.a>2");

		execute("schema/test.json", 
				"select testtableA.a, testtableB.b, testtableB.e "
				+ "from "
					+ "testtableA "
					+ "inner join "
					+ "testtableB "
					+ "on testtableA.a=testtableB.a "
				+ "where testtableA.a>2");

		execute("schema/test.json", 
				"select testtableA.a, testtableB.b, testtableB.e "
				+ "from "
					+ "testtableA "
					+ "inner join "
					+ "(select * from testtableB) "
					+ "on testtableA.a=testtableB.a "
				+ "where testtableA.a>2");
*/
		/*
		execute("schema/test.json",
				"select KeyTableA.a, testtableA.b "
				+ "from testtableA inner join KeyTableA "
					+ "on testtableA.a=KeyTableA.a "
				+ "where KeyTableA.a>1");
		*/
		execute("schema/test.json",
				"select KeyTableA.a, testtableA.b "
				+ "from testtableA inner join KeyTableA "
					+ "on testtableA.a=KeyTableA.a "
					+ "inner join KeyTableB "
					+ "on testtableA.b=KeyTableB.b "
				+ "where KeyTableA.a>1 or KeyTableB.b<4");

	}

	private static void execute(String schemaPath, String sql) throws IOException {
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project project = parseTreeToRelAlg(parser);

		System.out.println(project);
		
		QueryOptimizer optimizer = SimpleSQL.prepareOptimizer();
		optimizer.setRoot(project);
		optimizer.optimize();
		
		System.out.println(project);
		
//		ProjectIterator projectIterator = IteratorBuilder.buildRelationIterator(project, false);
//		print(projectIterator);				
	}
	private static void print(ProjectIterator projectIterator) {
		List<Expression<?>> columns = projectIterator.getColumns();
		StringBuilder sb = new StringBuilder();
		for(Expression<?> each:columns){
			sb.append(each.toString()+"\t");
		}
		if(projectIterator.getAggregates()!=null){
			for(Aggregate<?> aggregate:projectIterator.getAggregates()){
				sb.append(aggregate.toString()+"\t");
			}
		}
		System.out.println(sb.toString());
		while(projectIterator.hasNext()){
			sb = new StringBuilder();
			Row row = projectIterator.next();
//			for(Expression<?> each:columns){
//				sb.append(row.get(each.toString())+"\t");
//			}
			
			
			
//			if(projectIterator.getAggregates()!=null){
//				for(Aggregate<?> aggregate:projectIterator.getAggregates()){
//					sb.append(row.get(aggregate.toString())+"\t");
//				}
//			}
			
			for(String each : row.getFieldNames()){
				sb.append(row.get(each)+"\t");
			}
			System.out.println(sb.toString());
		}
	}
}
