package org.simplesql.main;

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
		execute("schema/test.json", "sql/test_aggregate.sql");
		execute("schema/test.json", "sql/test_natural_join.sql");
		execute("schema/test.json", "sql/test_agg_filter_join.sql");
	}

	private static void execute(String schemaPath, String sqlFile) throws IOException {
		CharStream input = CharStreams.fromFileName(sqlFile);
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project ra = parseTreeToRelAlg(parser, new File(schemaPath).toURI().toURL());
		System.out.println(ra);
		
		SchemaResolver resolver = new SchemaResolver(new File(schemaPath).toURI().toURL());
		ProjectIterator projectIterator = IteratorBuilder.buildCSVProjectIterator(ra, resolver);
		print(projectIterator);				
	}
/*
	private static void execute(String schemaPath, String sqlFile)throws IOException{
		CharStream input = CharStreams.fromFileName(sqlFile);
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project ra = parseTreeToRelAlg(parser, new File(schemaPath).toURI().toURL());
		System.out.println(ra);
		
		SchemaResolver resolver = new SchemaResolver(new File(schemaPath).toURI().toURL());
		ProjectIterator projectIterator = IteratorBuilder.buildCSVProjectIterator(ra, resolver);
		print(projectIterator);		
	}
*/	
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
