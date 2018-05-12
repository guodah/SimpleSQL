package org.simplesql.main;

import static org.junit.Assert.*;
import static org.simplesql.relational_algebra.Utilities.parseTreeToRelAlg;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;
import org.simplesql.QueryOptimizer;
import org.simplesql.SimpleSQL;
import org.simplesql.iterators.Iterator;
import org.simplesql.iterators.IteratorBuilder;
import org.simplesql.iterators.ProjectIterator;
import org.simplesql.iterators.Row;
import org.simplesql.parse.SimpleSQLLexer;
import org.simplesql.parse.SimpleSQLParser;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.rules.ProjectColumnPruneRule;
import org.simplesql.relational_algebra.rules.PushDownPredicatesRule;
import org.simplesql.relational_algebra.rules.TransitiveConditionRule;
import org.simplesql.resolve.SchemaResolver;

public class TestMain {
	private final static String SCHEMA = "schema/test.json"; 
	private QueryOptimizer optimizer;
	@Before
	public void setUp() throws MalformedURLException, IOException{
		SimpleSQL.setSchema(new File(SCHEMA).toURI().toURL());
		optimizer = SimpleSQL.newOptimizer();
	}
	
	@Test
	public void testLeftOuterJoin1() throws IOException{
		List<String> result = execute(
				"sELECT testtableA.a, testtableA.b, "
				+ "testtableB.a, testtableB.b"+
				" FROM testtableA left join testtableB on testtableA.a<testtableB.a"
				+ " and testtableA.b>testtableB.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 4);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=1, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=2, TESTTABLEA.B=3, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=2, TESTTABLEA.B=4, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 1);
	}

	@Test
	public void testLeftOuterJoin2() throws IOException{
		List<String> result = execute(
				"sELECT testtableA.a, testtableA.b, "
				+ "testtableB.a, testtableB.b FROM testtableA left outer join "
				+ "testtableB on testtableA.a<testtableB.a and testtableA.b>testtableB.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 4);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=1, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=2, TESTTABLEA.B=3, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=2, TESTTABLEA.B=4, "
								+ "TESTTABLEB.A=null, TESTTABLEB.B=null}").intValue(), 1);
	}

	@Test
	public void testRightOuterJoin1() throws IOException{
		List<String> result = execute(
				"sELECT testtableA.a, testtableA.b, testtableB.a, testtableB.b "
				+ "FROM testtableA right join testtableB "
				+ "on testtableA.a<testtableB.a and testtableA.b>testtableB.b;");

		assertNotNull(result);
		assertEquals(result.size(), 9);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 5);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=1, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=1, TESTTABLEB.B=2}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=3}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=4}").intValue(), 1);
	}

	@Test
	public void testRightOuterJoin2() throws IOException{
		List<String> result = execute(
				"sELECT testtableA.a, testtableA.b, testtableB.a, testtableB.b "
				+ "FROM testtableA right outer join testtableB "
				+ "on testtableA.a<testtableB.a and testtableA.b>testtableB.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 9);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 5);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=1, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=1, TESTTABLEB.B=2}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=3}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=1}").intValue(), 2);
		assertEquals(counts.get("{TESTTABLEA.A=null, TESTTABLEA.B=null, "
								+ "TESTTABLEB.A=2, TESTTABLEB.B=4}").intValue(), 1);
	}
	
	@Test
	public void testInnerJoin() throws IOException{
		List<String> result = execute(
				"sELECT testtableA.a, testtableA.b, testtableB.a, testtableB.b "
				+ "FROM testtableA inner join testtableB "
				+ "on testtableA.a<testtableB.a and testtableA.b>testtableB.b;");
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertEquals(new HashSet<String>(result).size(),1);
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
									+ "TESTTABLEB.A=2, TESTTABLEB.B=1}"));
	}
	
	@Test
	public void testFilter() throws IOException{
		List<String> result = execute(
				"SELECT a, b, c, d "
				+ "from testtableA "
				+ "where a>=2 and c>=5;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 2);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, "
									+ "TESTTABLEA.C=8, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, "
									+ "TESTTABLEA.C=5, TESTTABLEA.D=10}"));
	}
	
	@Test
	public void testNaturalJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d,e,  g "
				+ "FROM testtableA natural join testtableB "+
									"natural join testtableC "
				+ "where b>3;"
		);

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, "
									+ "TESTTABLEA.D=10, TESTTABLEB.E=10, TESTTABLEC.G=5}"));
	}

	@Test
	public void testSubqueryWithNaturalJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d,e,  g "
				+ "FROM (select a, b,c,d from testtablea) natural join testtableB "+
								"natural join testtableC "
								+ "where b>3;"
		);

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, "
									+ "TESTTABLEA.D=10, TESTTABLEB.E=10, TESTTABLEC.G=5}"));
	}
	
	@Test
	public void testGroupBy() throws IOException{
		List<String> result = execute(
				"sELECT a, b, sum(c), count(*) "
				+ "FROM testtableA  natural join testtableB "+
				"where a>1 and b>2 GROUP BY a,b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertTrue(result.contains("{COUNT(*)=4, SUM(TESTTABLEA.C)=24, "
									+ "TESTTABLEA.A=2, TESTTABLEA.B=3}"));
		assertTrue(result.contains("{COUNT(*)=1, SUM(TESTTABLEA.C)=5, "
									+ "TESTTABLEA.A=2, TESTTABLEA.B=4}"));
	}

	@Test
	public void testSubqueryNoJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d  "
				+ "FROM (select a, b,c,d from testtablea);");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=1, "
									+ "TESTTABLEA.C=2, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=1, "
									+ "TESTTABLEA.C=4, TESTTABLEA.D=2}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
									+ "TESTTABLEA.C=3, TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A=1, TESTTABLEA.B=2, "
									+ "TESTTABLEA.C=6, TESTTABLEA.D=3}"));	
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, "
									+ "TESTTABLEA.C=4, TESTTABLEA.D=8}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=3, "
									+ "TESTTABLEA.C=8, TESTTABLEA.D=4}"));
		assertTrue(result.contains("{TESTTABLEA.A=2, TESTTABLEA.B=4, "
									+ "TESTTABLEA.C=5, TESTTABLEA.D=10}"));
	}
	
	@Test
	public void testExpressionsAsColumns() throws IOException{
		List<String> result = execute("select a+1, b*2+c, c+d from testtablea");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=4, "
									+ "TESTTABLEA.C + TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=6, "
									+ "TESTTABLEA.C + TESTTABLEA.D=6}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=7, "
									+ "TESTTABLEA.C + TESTTABLEA.D=9}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=10, "
									+ "TESTTABLEA.C + TESTTABLEA.D=9}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=10, "
									+ "TESTTABLEA.C + TESTTABLEA.D=12}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=14, "
									+ "TESTTABLEA.C + TESTTABLEA.D=12}"));
		assertTrue(result.contains("{TESTTABLEA.A + 1=3, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=13, "
									+ "TESTTABLEA.C + TESTTABLEA.D=15}"));
	}
	
	@Test 
	public void testNumericExpressionInFilter()throws IOException{
		List<String> result = execute(
				"select a+1, b*2+c, c+d "
				+ "from testtablea "
				+ "where a+3>=2*d-1"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TESTTABLEA.A + 1=2, "
									+ "TESTTABLEA.B * 2 + TESTTABLEA.C=6, "
									+ "TESTTABLEA.C + TESTTABLEA.D=6}"));
	}
	
	@Test
	public void testColumnPruneWithSubquery() throws IOException{
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select testtablea.a, e "
				+ "from "
					+ "(select a, b, c,d from testtablea) "
				+ "inner join "
					+ "testtableB "
				+ "on testtableA.b = testtableB.b where c>2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TESTTABLEA.A, TESTTABLEB.E "
				+ "FROM ("
					+ "(SELECT TESTTABLEA.A, TESTTABLEA.B, TESTTABLEA.C FROM (TESTTABLEA)) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.B, TESTTABLEB.E FROM (TESTTABLEB)) "
				+ "ON TESTTABLEA.B = TESTTABLEB.B) "
				+ "WHERE TESTTABLEA.C > 2");
	}

	@Test
	public void testColumnPrune() throws IOException{
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select testtablea.a "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "testtableB "
				+ "on testtableA.b = testtableB.b");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TESTTABLEA.A "
				+ "FROM ("
					+ "(SELECT TESTTABLEA.B, TESTTABLEA.A FROM (TESTTABLEA)) "
					+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.B FROM (TESTTABLEB)) "
					+ "ON TESTTABLEA.B = TESTTABLEB.B)");
	}
	
	@Test
	public void testPredicatePushDown() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		String optimizedSQL = optimize(
				"select testtablea.a, testtableb.b "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "testtableB "
				+ "on testtableA.b = testtableB.b "
				+"where testtablea.a>2 and testtablea.c>1 and "
					+ "testtableb.f<5 and testtableb.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TESTTABLEA.A, TESTTABLEB.B "
				+ "FROM "
					+ "((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.D, TESTTABLEA.C "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2 AND TESTTABLEA.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.F < 5 AND TESTTABLEB.E >= 2) "
				+ "ON TESTTABLEA.B = TESTTABLEB.B)");
	}
	
	@Test
	public void testPredicatePushDownWithSubquery() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		String optimizedSQL = optimize(
				"select testtablea.a, testtableb.b "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "(select a, b, e, f from testtableb) "
				+ "on testtableA.b = testtableB.b "
				+"where testtablea.a>2 and testtablea.c>1 and "
					+ "testtableb.f<5 and testtableb.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TESTTABLEA.A, TESTTABLEB.B "
				+ "FROM "
					+ "((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.D, TESTTABLEA.C "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2 AND TESTTABLEA.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.F < 5 AND TESTTABLEB.E >= 2) "
				+ "ON TESTTABLEA.B = TESTTABLEB.B)");
	}

	@Test
	public void testPredicatePushDownAndColumnPrune() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select testtablea.a, testtableb.b "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "(select a, b, e, f from testtableb) "
				+ "on testtableA.b = testtableB.b "
				+"where testtablea.a>2 and testtablea.c>1 and "
					+ "testtableb.f<5 and testtableb.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TESTTABLEA.A, TESTTABLEB.B "
				+ "FROM "
					+ "((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.C "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2 AND TESTTABLEA.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.F < 5 AND TESTTABLEB.E >= 2) "
				+ "ON TESTTABLEA.B = TESTTABLEB.B)");
	}
	
	@Test
	public void testStarColumn_1() throws IOException{
		List<String> result = execute("select * from testtableA");
		assertNotNull(result);
		assertEquals(result.size(), 7);
		assertTrue(result.contains(
				"{TESTTABLEA.A=1, TESTTABLEA.B=1, TESTTABLEA.C=2, TESTTABLEA.D=4}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=1, TESTTABLEA.B=1, TESTTABLEA.C=4, TESTTABLEA.D=2}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=1, TESTTABLEA.B=2, TESTTABLEA.C=3, TESTTABLEA.D=6}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=1, TESTTABLEA.B=2, TESTTABLEA.C=6, TESTTABLEA.D=3}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=2, TESTTABLEA.B=3, TESTTABLEA.C=4, TESTTABLEA.D=8}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=2, TESTTABLEA.B=3, TESTTABLEA.C=8, TESTTABLEA.D=4}"));
		assertTrue(result.contains(
				"{TESTTABLEA.A=2, TESTTABLEA.B=4, TESTTABLEA.C=5, TESTTABLEA.D=10}"));
	}
	
	@Test
	public void testStarColumn_2() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize("select testtablea.a, e from (select * from testtablea)"
				+ " inner join testtableB on testtableA.b = testtableB.b where c>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TESTTABLEA.A, TESTTABLEB.E "
				+ "FROM "
					+ "((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.C "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.C > 2) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.B, TESTTABLEB.E "
					+ "FROM (TESTTABLEB)) "
				+ "ON TESTTABLEA.B = TESTTABLEB.B)"));
	}
	
	@Test
	public void testTransitiveConditionWithNaturalJoin() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize("select a,b, e, f "
				+ "from testtableA natural join testtableB "
				+ "where testtableA.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TESTTABLEA.A, TESTTABLEA.B, TESTTABLEB.E, TESTTABLEB.F "
				+ "FROM ( "
					+ "(SELECT TESTTABLEA.B, TESTTABLEA.A "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2) "
				+ "NATURAL JOIN "
					+ "(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.A > 2))"));
	}

	@Test
	public void testTransitiveConditionWithInnerJoin() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize(
				"select testtableA.a, testtableB.b, testtableB.e "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "testtableB "
				+ "on testtableA.a=testtableB.a "
				+ "where testtableA.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TESTTABLEA.A, TESTTABLEB.B, TESTTABLEB.E "
				+ "FROM ("
					+ "(SELECT TESTTABLEA.A "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.A > 2) "
				+ "ON TESTTABLEA.A = TESTTABLEB.A)"));
	}

	@Test
	public void testTransitiveConditionWithSubquery() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize(
				"select testtableA.a, testtableB.b, testtableB.e "
				+ "from "
					+ "testtableA "
				+ "inner join "
					+ "(select * from testtableB) "
				+ "on testtableA.a=testtableB.a "
				+ "where testtableA.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TESTTABLEA.A, TESTTABLEB.B, TESTTABLEB.E "
				+ "FROM ("
					+ "(SELECT TESTTABLEA.A "
					+ "FROM (TESTTABLEA) "
					+ "WHERE TESTTABLEA.A > 2) "
				+ "INNER JOIN "
					+ "(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E "
					+ "FROM (TESTTABLEB) "
					+ "WHERE TESTTABLEB.A > 2) "
				+ "ON TESTTABLEA.A = TESTTABLEB.A)"));
	}
	
	private String optimize(String sql) throws IOException{
		Project project = SimpleSQL.findProjectNode(sql);
		optimizer.setRoot(project);
		return optimizer.optimize().toString();
	}
	
	private List<String> execute(String sql) throws IOException {
		Iterator<Row> iterator = SimpleSQL.executeQuery(sql);
		return buildList(iterator);
	}

	private List<String> buildList(Iterator<Row> iterator) {
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

	private Map<String, Integer> count(List<String> result) {
		Map<String, Integer> counts = new HashMap<>();
		for(String each:result){
			counts.put(each, counts.getOrDefault(each, 0)+1);
		}
		return counts;
	}	
}
