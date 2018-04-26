## SimpleSQL

This is a project for me to experience the process of **parsing**, **compiling**, and **executing** SQL statements. 

###Updates:  
**March 13, 2018**: Using [ANTLR v4](http://www.antlr.org/download.html), defined a simple SQL grammar, supporting basic **"select columns from table where boolean_expression"** SQL statements. It does not support subqueries.

**March 14, 2018**: Added Java code that converts an SQL parse time to relational algebra tree. It is in Utilities.parseTreeToRelAlg in org.simplesql.relational_algebra. It has worked on a few examples, while still needs more testing. Also explored Orc. 

**March 15, 2018**: Added Java code for the evaluate methods in Expression and subclasses (BooleanBinaryExpression, LiteralValue, LongValue, DoubleValue, StringValue and NulValue.

**March 20, 2018**: Added a resolver to check if 1) columns all exist and 2) expressions have mismatched types. (not much testing performed)

**March 22, 2018**: Added iterators for project, filter and CSV scan. Right now, the code can compile, resolve, and execute a very simple SQL query (single table and no subqueries). (not much testing performed)

**March 28, 2018**: Added code for two aggregate functions (sum and count) and the "group by" clause. (not much testing performed.)

**April 4, 2018**: Added code for natural join. (not much testing performed.)

**April 10, 2018**: Rewrote the code for resolver. Now the resolver is able to locate columns without specifying a table. After being resolved, each column identifies a table name and column name. I believe this needs to be done before implementing inner join.

**April 11, 2018**: Added code for natural join based on sorting and inner join with join condition based on nested loops.

**April 16, 2018**: Added code for basic uncorrelated subqueries. 

**April 18, 2018**: Added code for supporting expressions as columns, like "select a+1, b*2+c, c+d from testtablea"

**April 19, 2018**: Added code for left and right outer join.

**April 26, 2018**: Added code for column pruning. Thus far, I understand column pruning as an optimizing process to remove columns from select clause if these columns are not referenced in the enclosing structure. For example, given the following SQL statement:
	
	// Table testtablea (a, b, c, d)  
	// Table testtableb (a, b, e, f)  
	select testtablea.a  
	from testtableA inner join testtableB   
	on testtableA.b = testtableB.b  

In this statement, only columns a and b from testtableA and column b from testtableB are needed. So the joined tables can be replaced with select clauses as follows:

	SELECT TESTTABLEA.A   
	FROM (  
		SELECT TESTTABLEA.B, TESTTABLEA.A FROM (TESTTABLEA)   
		INNER JOIN  
		SELECT TESTTABLEB.B FROM (TESTTABLEB)   
		ON TESTTABLEA.B = TESTTABLEB.B)

The next example shows pruning columns in a subquerry. It also contains a where clause:

	select testtablea.a, e
	from (select a, b, c,d from testtablea) inner join testtableB
	on testtableA.b = testtableB.b where c>2

In the subquery, columns d is unused. From testtableB, columns a and f are unused. So they can be pruned.

	SELECT TESTTABLEA.A, TESTTABLEB.E
	FROM (
		SELECT TESTTABLEA.A, TESTTABLEA.B, TESTTABLEA.C FROM (TESTTABLEA) 
		INNER JOIN 
		SELECT TESTTABLEB.B, TESTTABLEB.E FROM (TESTTABLEB)
		ON TESTTABLEA.B = TESTTABLEB.B)
	WHERE TESTTABLEA.C > 2

  
**PLAN**:
* implement predicate push down
* add count distinct
* add semi join
