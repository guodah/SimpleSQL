package org.simplesql.main;

import static org.junit.Assert.*;
import static org.simplesql.relational_algebra.Utilities.parseTreeToRelAlg;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;
import org.simplesql.SimpleSQL;
import org.simplesql.iterators.Iterator;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;

public class TestMain {
	private final static String SCHEMA = "schema/test.json"; 
	
	@Before
	public void setUp() throws MalformedURLException, IOException{
		SimpleSQL.setSchema(new File(SCHEMA).toURI().toURL());
	}
	
	
	@Test
	public void testInnerJoin() throws IOException{
		List<String> result = execute("sELECT testtableA.a, testtableA.b, testtableB.a, testtableB.b FROM testtableA "+
				"inner join testtableB on testtableA.a<testtableB.a and testtableA.b>testtableB.b;");
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertEquals(new HashSet<String>(result).size(),1);
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, TESTTABLEB.A=2, TESTTABLEB.B=1}"));
	}
	
	@Test
	public void testFilter() throws IOException{
		List<String> result = execute("SELECT a, b, c, d from testtableA where a>=2 and c>=5;");
		assertNotNull(result);
		assertEquals(result.size(), 2);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, TESTTABLEA.C=8, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, TESTTABLEA.D=10}"));
	}
	
	@Test
	public void testNaturalJoin() throws IOException{
		List<String> result = execute("sELECT a, b,c,d,e,  g FROM testtableA natural join testtableB "+
				"natural join testtableC where b>3;");

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, TESTTABLEA.D=10, TESTTABLEB.E=10, TESTTABLEC.G=5}"));
	}

	@Test
	public void testSubqueryWithNaturalJoin() throws IOException{
		List<String> result = execute("sELECT a, b,c,d,e,  g FROM (select a, b,c,d from testtablea) natural join testtableB "+
				"natural join testtableC where b>3;");

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, TESTTABLEA.D=10, TESTTABLEB.E=10, TESTTABLEC.G=5}"));
	}
	
	@Test
	public void testGroupBy() throws IOException{
		List<String> result = execute("sELECT a, b, sum(c), count(*) FROM testtableA  natural join testtableB "+
				"where a>1 and b>2 GROUP BY a,b;");
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertTrue(result.contains("{COUNT(*)=4, SUM(TESTTABLEA.C)=24, TESTTABLEA.A=2, TESTTABLEA.B=3}"));
		assertTrue(result.contains("{COUNT(*)=1, SUM(TESTTABLEA.C)=5, TESTTABLEA.A=2, TESTTABLEA.B=4}"));
	}

	@Test
	public void testSubqueryNoJoin() throws IOException{
		List<String> result = execute("sELECT a, b,c,d  FROM (select a, b,c,d from testtablea);");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=1, TESTTABLEA.C=2, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=1, TESTTABLEA.C=4, TESTTABLEA.D=2}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, TESTTABLEA.C=3, TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, TESTTABLEA.C=6, TESTTABLEA.D=3}"));	
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, TESTTABLEA.C=4, TESTTABLEA.D=8}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, TESTTABLEA.C=8, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, TESTTABLEA.D=10}"));
	}
	
	@Test
	public void testExpressionsAsColumns() throws IOException{
		List<String> result = execute("select a+1, b*2+c, c+d from testtablea");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, TESTTABLEA.B * 2 + TESTTABLEA.C=4, TESTTABLEA.C + TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, TESTTABLEA.B * 2 + TESTTABLEA.C=6, TESTTABLEA.C + TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, TESTTABLEA.B * 2 + TESTTABLEA.C=7, TESTTABLEA.C + TESTTABLEA.D=9}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, TESTTABLEA.B * 2 + TESTTABLEA.C=10, TESTTABLEA.C + TESTTABLEA.D=9}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, TESTTABLEA.B * 2 + TESTTABLEA.C=10, TESTTABLEA.C + TESTTABLEA.D=12}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, TESTTABLEA.B * 2 + TESTTABLEA.C=14, TESTTABLEA.C + TESTTABLEA.D=12}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, TESTTABLEA.B * 2 + TESTTABLEA.C=13, TESTTABLEA.C + TESTTABLEA.D=15}"));
	}
	
	private List<String> execute(String sql) throws IOException {
		CharStream input = CharStreams.fromStream(new ByteArrayInputStream(sql.toUpperCase().getBytes()));
		SimpleSQLLexer lexer = new SimpleSQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleSQLParser parser = new SimpleSQLParser(tokens);

		Project project = parseTreeToRelAlg(parser);

		ProjectIterator projectIterator = IteratorBuilder.buildRelationIterator(project, false);
		return buildSet(projectIterator);
	}

	private List<String> buildSet(Iterator<Row> iterator) {
		List<String> result = new ArrayList<>();
		while(iterator.hasNext()){
			Row row = iterator.next();
			
			TreeMap<String, LiteralValue<?>> map = new TreeMap<>();
			for(String each:row.getFieldNames()){
				map.put(each, row.get(each));
			}
			result.add(map.toString());
		}
		return result;
	}

	
}
