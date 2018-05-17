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
import org.simplesql.relational_algebra.RANode;
import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.rules.JoinSimplicationRule;
import org.simplesql.relational_algebra.rules.ProjectColumnPruneRule;
import org.simplesql.relational_algebra.rules.PushDownPredicatesRule;
import org.simplesql.relational_algebra.rules.TransitiveConditionRule;
import org.simplesql.resolve.SchemaResolver;

public class SimpleSQL {
	private  static SchemaResolver resolver = null;
	public static void setSchema(URL schema) throws IOException{
		resolver = new SchemaResolver(schema);
	}
	
	public static SchemaResolver getSchemaResolver(){
		return resolver;
	}
	
	public static QueryOptimizer newOptimizer(){
		return new QueryOptimizer();
	}

	public static QueryOptimizer prepareOptimizer(){
		QueryOptimizer optimizer = new QueryOptimizer();
		optimizer.addRule(JoinSimplicationRule.class);
//		optimizer.addRule(TransitiveConditionRule.class);
//		optimizer.addRule(PushDownPredicatesRule.class);
//		optimizer.addRule(ProjectColumnPruneRule.class);
		return optimizer;
	}
	
	public static Relation optimize(Relation relation){
		QueryOptimizer optimizer = new QueryOptimizer();
		optimizer.setRoot(relation);
		return optimizer.optimize();
	}
	
	public static Iterator<Row> executeQuery(String sql) throws IOException {
		Project project = findProjectNode(sql);
		ProjectIterator projectIterator = IteratorBuilder.buildRelationIterator(
				project, false);
		return projectIterator;
	}
	
	public static Project findProjectNode(String sql) throws IOException{
		if(resolver==null){
			throw new IllegalStateException("schema not set");
		}
		
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project project = parseTreeToRelAlg(parser);
		return project;
	}
}
