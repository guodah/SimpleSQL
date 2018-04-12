package org.simplesql.main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.relational_algebra.Aggregate;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;
import static org.simplesql.relational_algebra.Utilities.parseTreeToRelAlg;

public class Main {
	public static void main(String args[]) throws IOException{
		execute("schema/test.json", "sELECT testtableA.a, testtableB.b FROM testtableA "+
				"inner join testtableB on testtableA.a=testtableB.b and testtableA.b=testtableB.b;");

		execute("schema/test.json", "sELECT a, b, sum(c), count(*) FROM testtableA  natural join testtableB "+
				"where a>1 and b>2 GROUP BY a,b;");

		execute("schema/test.json", "sELECT a, b,c,d,e,  g FROM testtableA natural join testtableB "+
				"natural join testtableC where a>1;");

	}

	private static void execute(String schemaPath, String sql) throws IOException {
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project project = parseTreeToRelAlg(parser, new File(schemaPath).toURI().toURL());
		System.out.println(project);
		
		SchemaResolver resolver = new SchemaResolver(new File(schemaPath).toURI().toURL());
		ProjectIterator projectIterator = IteratorBuilder.buildCSVProjectIterator(project, resolver, false);
		print(projectIterator);				
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
