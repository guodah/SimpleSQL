package org.simplesql;

import static org.simplesql.relational_algebra.Utilities.parseTreeToRelAlg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.simplesql.iterators.Iterator;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;

public class SimpleSQL {
	private  static SchemaResolver resolver = null;
	public static void setSchema(URL schema) throws IOException{
		resolver = new SchemaResolver(schema);
	}
	
	public static SchemaResolver getSchemaResolver(){
		return resolver;
	}
	
	private static Iterator executeQuery(String sql) throws IOException {
		if(resolver==null){
			throw new IllegalStateException("schema not set");
		}
		
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project project = parseTreeToRelAlg(parser);

		ProjectIterator projectIterator = IteratorBuilder.buildRelationIterator(
				project, false);
		return projectIterator;
	}
}
