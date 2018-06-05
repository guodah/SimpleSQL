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
import org.simplesql.relational_algebra.rules.JoinSimplicationRule;
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
	public void testStarColumn_1() throws IOException{
		List<String> result = execute("select * from table_A");
		assertNotNull(result);
		assertEquals(result.size(), 7);
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=1, TABLE_A.C=2, TABLE_A.D=4}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=1, TABLE_A.C=4, TABLE_A.D=2}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=2, TABLE_A.C=3, TABLE_A.D=6}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=2, TABLE_A.C=6, TABLE_A.D=3}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=3, TABLE_A.C=4, TABLE_A.D=8}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=3, TABLE_A.C=8, TABLE_A.D=4}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=4, TABLE_A.C=5, TABLE_A.D=10}"));
	}

	@Test
	public void testFilter() throws IOException{
		List<String> result = execute(
				"SELECT a, b, c, d "
				+ "from table_A "
				+ "where a>=2 and c>=5;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 2);
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=3, TABLE_A.C=8, TABLE_A.D=4}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=4, TABLE_A.C=5, TABLE_A.D=10}"));
	}

	@Test
	public void testInnerJoin() throws IOException{
		List<String> result = execute(
				"sELECT table_A.a, table_A.b, table_B.a, table_B.b "
				+ "FROM table_A inner join table_B "
				+ "on table_A.a<table_B.a and table_A.b>table_B.b;");
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertEquals(new HashSet<String>(result).size(),1);
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=2, TABLE_B.A=2, TABLE_B.B=1}"));
	}
		
	@Test
	public void testSubqueryWithNaturalJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d,e,  g "
				+ "FROM (select a, b,c,d from table_a) natural join table_B "
				+ "natural join table_C "
				+ "where b>3;"
		);

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=4, TABLE_A.C=5, TABLE_A.D=10, TABLE_B.E=10, TABLE_C.G=5}"));
	}

	@Test
	public void testExpressionsAsColumns() throws IOException{
		List<String> result = execute("select a+1, b*2+c, c+d from table_a");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		assertTrue(result.contains("{TABLE_A.A + 1=2, TABLE_A.B * 2 + TABLE_A.C=4, TABLE_A.C + TABLE_A.D=6}"));
		assertTrue(result.contains("{TABLE_A.A + 1=2, TABLE_A.B * 2 + TABLE_A.C=6, TABLE_A.C + TABLE_A.D=6}"));
		assertTrue(result.contains("{TABLE_A.A + 1=2, TABLE_A.B * 2 + TABLE_A.C=7, TABLE_A.C + TABLE_A.D=9}"));
		assertTrue(result.contains("{TABLE_A.A + 1=2, TABLE_A.B * 2 + TABLE_A.C=10, TABLE_A.C + TABLE_A.D=9}"));
		assertTrue(result.contains("{TABLE_A.A + 1=3, TABLE_A.B * 2 + TABLE_A.C=10, TABLE_A.C + TABLE_A.D=12}"));
		assertTrue(result.contains("{TABLE_A.A + 1=3, TABLE_A.B * 2 + TABLE_A.C=14, TABLE_A.C + TABLE_A.D=12}"));
		assertTrue(result.contains("{TABLE_A.A + 1=3, TABLE_A.B * 2 + TABLE_A.C=13, TABLE_A.C + TABLE_A.D=15}"));
	}
	
	@Test 
	public void testNumericExpressionInFilter()throws IOException{
		List<String> result = execute(
				"select a+1, b*2+c, c+d "
				+ "from table_a "
				+ "where a+3>=2*d-1"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TABLE_A.A + 1=2, TABLE_A.B * 2 + TABLE_A.C=6, TABLE_A.C + TABLE_A.D=6}"));
	}
	
	@Test
	public void testGroupBy() throws IOException{
		List<String> result = execute(
				"sELECT a, b, sum(c), count(*) "
				+ "FROM table_A  natural join table_B "
				+ "where a>1 and b>2 "
				+ "GROUP BY a,b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(),2);
		assertTrue(result.contains("{COUNT(*)=4, SUM(TABLE_A.C)=24, TABLE_A.A=2, TABLE_A.B=3}"));
		assertTrue(result.contains("{COUNT(*)=1, SUM(TABLE_A.C)=5, TABLE_A.A=2, TABLE_A.B=4}"));
	}

	@Test
	public void testColumnPruneWithExpression() throws IOException{
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize("select a from (select a, b, a+b from table_A)");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A FROM (SELECT TABLE_A.A FROM (TABLE_A))");
	}

	@Test
	public void testColumnPruneWithSubquery() throws IOException{
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select table_a.a, e "
				+ "from "
					+ "(select a, b, c,d from table_a) "
				+ "inner join "
					+ "table_B "
				+ "on table_A.b = table_B.b "
				+ "where c>2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A, TABLE_B.E "
				+ "FROM ("
					+ "(SELECT TABLE_A.A, TABLE_A.B, TABLE_A.C FROM (TABLE_A)) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.B, TABLE_B.E FROM (TABLE_B)) "
				+ "ON TABLE_A.B = TABLE_B.B) "
				+ "WHERE TABLE_A.C > 2");
	}

	@Test
	public void testPredicatePushDown() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		String optimizedSQL = optimize(
				"select table_a.a, table_b.b "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "table_B "
				+ "on table_A.b = table_B.b "
				+"where table_a.a>2 and table_a.c>1 and "
					+ "table_b.f<5 and table_b.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A, TABLE_B.B "
				+ "FROM "
					+ "((SELECT TABLE_A.B, TABLE_A.C, TABLE_A.D, TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2 AND TABLE_A.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.A, TABLE_B.B, TABLE_B.E, TABLE_B.F "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.F < 5 AND TABLE_B.E >= 2) "
				+ "ON TABLE_A.B = TABLE_B.B)");
	}

	@Test
	public void testTransitiveConditionWithInnerJoin() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize(
				"select table_A.a, table_B.b, table_B.e "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "table_B "
				+ "on table_A.a=table_B.a "
				+ "where table_A.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TABLE_A.A, TABLE_B.B, TABLE_B.E "
				+ "FROM ("
					+ "(SELECT TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.A, TABLE_B.B, TABLE_B.E "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.A > 2) "
				+ "ON TABLE_A.A = TABLE_B.A)"));
	}

	@Test
	public void testJoinSimplication() throws IOException{
		optimizer.addRule(JoinSimplicationRule.class);
		String optimizedSQL = optimize(
				"select KeyTable_A.a, KeyTable_B.b "
				+ "from table_A inner join KeyTable_A "
					+ "on table_A.a=KeyTable_A.a "
					+ "inner join KeyTable_B "
					+ "on table_A.b=KeyTable_B.b "
				+ "where KeyTable_A.a>1 or KeyTable_B.b<4");
		assertTrue(optimizedSQL.equals(
				"SELECT TABLE_A.A, TABLE_A.B "
				+ "FROM (TABLE_A) "
				+ "WHERE TABLE_A.A > 1 OR TABLE_A.B < 4"));
	}

	
	@Test
	public void testLeftOuterJoin1() throws IOException{
		List<String> result = execute(
				"sELECT Table_A.a, Table_A.b, "
				+ "Table_B.a, Table_B.b"+
				" FROM Table_A left join Table_B on Table_A.a<Table_B.a"
				+ " and Table_A.b>Table_B.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 4);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=1, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=2, TABLE_B.A=2, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=2, TABLE_A.B=3, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=2, TABLE_A.B=4, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 1);
	}

	@Test
	public void testLeftOuterJoin2() throws IOException{
		List<String> result = execute(
				"sELECT Table_A.a, Table_A.b, "
				+ "Table_B.a, Table_B.b FROM Table_A left outer join "
				+ "Table_B on Table_A.a<Table_B.a and Table_A.b>Table_B.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 4);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=1, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=2, TABLE_B.A=2, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=2, TABLE_A.B=3, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=2, TABLE_A.B=4, TABLE_B.A=null, TABLE_B.B=null}").intValue(), 1);
	}

	@Test
	public void testRightOuterJoin1() throws IOException{
		List<String> result = execute(
				"sELECT table_A.a, table_A.b, table_B.a, table_B.b "
				+ "FROM table_A right join table_B "
				+ "on table_A.a<table_B.a and table_A.b>table_B.b;");

		assertNotNull(result);
		assertEquals(result.size(), 9);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 5);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=1, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=1, TABLE_B.B=2}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=2, TABLE_B.B=3}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=2, TABLE_B.A=2, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=2, TABLE_B.B=4}").intValue(), 1);
	}

	@Test
	public void testRightOuterJoin2() throws IOException{
		List<String> result = execute(
				"sELECT table_A.a, table_A.b, table_B.a, table_B.b "
				+ "FROM table_A right outer join table_B "
				+ "on table_A.a<table_B.a and table_A.b>table_B.b;"
		);
		
		assertNotNull(result);
		assertEquals(result.size(), 9);
		
		Map<String, Integer> counts = count(result);
		
		assertEquals(counts.size(), 5);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=1, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=1, TABLE_B.B=2}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=2, TABLE_B.B=3}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=1, TABLE_A.B=2, TABLE_B.A=2, TABLE_B.B=1}").intValue(), 2);
		assertEquals(counts.get("{TABLE_A.A=null, TABLE_A.B=null, TABLE_B.A=2, TABLE_B.B=4}").intValue(), 1);
	}
	
	@Test
	public void testNaturalJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d,e,  g "
				+ "FROM table_A natural join table_B "+
									"natural join table_C "
				+ "where b>3;"
		);

		assertNotNull(result);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=4, TABLE_A.C=5, TABLE_A.D=10, TABLE_B.E=10, TABLE_C.G=5}"));
	}



	@Test
	public void testSubqueryNoJoin() throws IOException{
		List<String> result = execute(
				"sELECT a, b,c,d  "
				+ "FROM (select a, b,c,d from table_a);");
		
		assertNotNull(result);
		assertEquals(result.size(), 7);
		
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=1, TABLE_A.C=2, TABLE_A.D=4}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=1, TABLE_A.C=4, TABLE_A.D=2}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=2, TABLE_A.C=3, TABLE_A.D=6}"));
		assertTrue(result.contains("{TABLE_A.A=1, TABLE_A.B=2, TABLE_A.C=6, TABLE_A.D=3}"));	
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=3, TABLE_A.C=4, TABLE_A.D=8}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=3, TABLE_A.C=8, TABLE_A.D=4}"));
		assertTrue(result.contains("{TABLE_A.A=2, TABLE_A.B=4, TABLE_A.C=5, TABLE_A.D=10}"));
	}

	

	@Test
	public void testColumnPrune() throws IOException{
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select table_a.a "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "table_B "
				+ "on table_A.b = table_B.b");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A "
				+ "FROM ("
					+ "(SELECT TABLE_A.B, TABLE_A.A FROM (TABLE_A)) "
					+ "INNER JOIN "
					+ "(SELECT TABLE_B.B FROM (TABLE_B)) "
					+ "ON TABLE_A.B = TABLE_B.B)");
	}
		
	@Test
	public void testPredicatePushDownWithSubquery() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		String optimizedSQL = optimize(
				"select table_a.a, table_b.b "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "(select a, b, e, f from table_b) "
				+ "on table_A.b = table_B.b "
				+"where table_a.a>2 and table_a.c>1 and "
					+ "table_b.f<5 and table_b.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A, TABLE_B.B "
				+ "FROM "
					+ "((SELECT TABLE_A.B, TABLE_A.C, TABLE_A.D, TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2 AND TABLE_A.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.A, TABLE_B.B, TABLE_B.E, TABLE_B.F "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.F < 5 AND TABLE_B.E >= 2) "
				+ "ON TABLE_A.B = TABLE_B.B)");
	}

	@Test
	public void testPredicatePushDownAndColumnPrune() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize(
				"select table_a.a, table_b.b "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "(select a, b, e, f from table_b) "
				+ "on table_A.b = table_B.b "
				+"where table_a.a>2 and table_a.c>1 and "
					+ "table_b.f<5 and table_b.e>=2");
		assertNotNull(optimizedSQL);
		assertEquals(optimizedSQL, 
				"SELECT TABLE_A.A, TABLE_B.B "
				+ "FROM "
					+ "((SELECT TABLE_A.B, TABLE_A.C, TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2 AND TABLE_A.C > 1) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.B, TABLE_B.E, TABLE_B.F "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.F < 5 AND TABLE_B.E >= 2) "
				+ "ON TABLE_A.B = TABLE_B.B)");
	}
	
	
	@Test
	public void testStarColumn_2() throws IOException{
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		String optimizedSQL = optimize("select table_a.a, e from (select * from table_a)"
				+ " inner join table_B on table_A.b = table_B.b where c>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TABLE_A.A, TABLE_B.E "
				+ "FROM "
					+ "((SELECT TABLE_A.B, TABLE_A.C, TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.C > 2) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.B, TABLE_B.E "
					+ "FROM (TABLE_B)) "
					+ "ON TABLE_A.B = TABLE_B.B)"));
	}
	
	@Test
	public void testTransitiveConditionWithNaturalJoin() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize("select a,b, e, f "
				+ "from table_A natural join table_B "
				+ "where table_A.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TABLE_A.A, TABLE_A.B, TABLE_B.E, TABLE_B.F "
				+ "FROM ( "
					+ "(SELECT TABLE_A.B, TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2) "
				+ "NATURAL JOIN "
					+ "(SELECT TABLE_B.A, TABLE_B.B, TABLE_B.E, TABLE_B.F "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.A > 2))"));
	}


	@Test
	public void testTransitiveConditionWithSubquery() throws IOException{
		optimizer.addRule(TransitiveConditionRule.class);
		optimizer.addRule(PushDownPredicatesRule.class);
		optimizer.addRule(ProjectColumnPruneRule.class);
		
		String optimizedSQL = optimize(
				"select table_A.a, table_B.b, table_B.e "
				+ "from "
					+ "table_A "
				+ "inner join "
					+ "(select * from table_B) "
				+ "on table_A.a=table_B.a "
				+ "where table_A.a>2");
		assertTrue(optimizedSQL.equals(
				"SELECT TABLE_A.A, TABLE_B.B, TABLE_B.E "
				+ "FROM ("
					+ "(SELECT TABLE_A.A "
					+ "FROM (TABLE_A) "
					+ "WHERE TABLE_A.A > 2) "
				+ "INNER JOIN "
					+ "(SELECT TABLE_B.A, TABLE_B.B, TABLE_B.E "
					+ "FROM (TABLE_B) "
					+ "WHERE TABLE_B.A > 2) "
				+ "ON TABLE_A.A = TABLE_B.A)"));
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
