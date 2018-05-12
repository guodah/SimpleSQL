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

**May 1, 2018**: added code for pushing down predicates. Right now, it is only able to push down predicates on a conjunctive condition on the where clause. Below is an example. 

Suppose an SQL statement:
	
	select testtablea.a, testtableb.b 
	from testtableA inner join (select a, b, e, f from testtableB) 
	on testtableA.b = testtableB.b 
	where testtableA.a>2 and testtableA.c>1 and 
					testtableB.f<5 and testtableB.e>=2
					
The where clause is a conjunctive condition. The sub-conditions can be partitioned to two groups **testtableA.a>2 and testtableA.c>1** and **testtableB.f<5 and testtableB.e>=2**. They can be pushed down to the two joined tables testtableA and testtableB. Below is the rewritten SQL statement in which the previous where clause is split into two where clauses.

	SELECT TESTTABLEA.A, TESTTABLEB.B 
	FROM 
		((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.D, TESTTABLEA.C 
		FROM (TESTTABLEA) 
		WHERE TESTTABLEA.A > 2 AND TESTTABLEA.C > 1) 
	INNER JOIN 
		(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F 
		FROM (TESTTABLEB) 
		WHERE TESTTABLEB.F < 5 AND TESTTABLEB.E >= 2) 
	ON TESTTABLEA.B = TESTTABLEB.B)
 
If performing both predicate push-down and column pruning, the rewritten query becomes the following where TESTTABLEA.D AND TESTTABLEB.A are pruned.

	SELECT TESTTABLEA.A, TESTTABLEB.B 
	FROM 
		((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.C 
		FROM (TESTTABLEA) 
		WHERE TESTTABLEA.A > 2 AND TESTTABLEA.C > 1) 
	INNER JOIN 
		(SELECT TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F 
		FROM (TESTTABLEB) 
		WHERE TESTTABLEB.F < 5 AND TESTTABLEB.E >= 2) 
	ON TESTTABLEA.B = TESTTABLEB.B)


**May 3, 2018**: Added code to enable '\*' as the "column" name at the select clause, such as **select \* from testtableA** or in subquery like the following:

	// Table testtablea (a, b, c, d)  
	// Table testtableb (a, b, e, f)  
	select testtablea.a, e 
	from 
		(select * from testtablea)
	inner join 
		testtableB 
	on testtableA.b = testtableB.b 
	where c>2 
	
The previous query statement will be converted to the following (after pushing down predicates and pruning columns)

	SELECT TESTTABLEA.A, TESTTABLEB.E 
	FROM 
		((SELECT TESTTABLEA.B, TESTTABLEA.A, TESTTABLEA.C 
		FROM (TESTTABLEA) 
		WHERE TESTTABLEA.C > 2) 
	INNER JOIN 
		(SELECT TESTTABLEB.B, TESTTABLEB.E 
		FROM (TESTTABLEB)) 
	ON TESTTABLEA.B = TESTTABLEB.B)


**May 12, 2018**: added code for using transitive condition to narrow down the considered rows in joined relations. For example, the following SQL statement:

	// testtableA (a,b,c,d)
	// testtableB (a,b,e,f)
	select a,b, e, f 
	from testtableA natural join testtableB 
	where testtableA.a>2 

Because the natural join is to match the rows in testtableA and testtableB using columns a and b, the where condition **testtableA.a>2** also implies **testtableB.a>2**. Therefore, the previous SQL statement can be optimized to the following where the "where" condition is pushed down to the joined tables:

				SELECT TESTTABLEA.A, TESTTABLEA.B, TESTTABLEB.E, TESTTABLEB.F 
				FROM ( 
					SELECT TESTTABLEA.B, TESTTABLEA.A 
					FROM (TESTTABLEA) 
					WHERE TESTTABLEA.A > 2) 
				NATURAL JOIN 
					(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E, TESTTABLEB.F 
					FROM (TESTTABLEB) 
					WHERE TESTTABLEB.A > 2))

The next example considers inner join and subquery:

	// testtableA (a,b,c,d)
	// testtableB (a,b,e,f)
	select testtableA.a, testtableB.b, testtableB.e 
	from 
		testtableA 
	inner join 
		(select * from testtableB) 
	on testtableA.a=testtableB.a 
	where testtableA.a>2

This statement can be optimized to the following:

	SELECT TESTTABLEA.A, TESTTABLEB.B, TESTTABLEB.E 
	FROM (
		(SELECT TESTTABLEA.A 
		FROM (TESTTABLEA) 
		WHERE TESTTABLEA.A > 2) 
	INNER JOIN 
		(SELECT TESTTABLEB.A, TESTTABLEB.B, TESTTABLEB.E 
		FROM (TESTTABLEB) 
		WHERE TESTTABLEB.A > 2) 
	ON TESTTABLEA.A = TESTTABLEB.A)


**PLAN**:
* add code for join simplication
* implement predicate push down for join conditions
* add count distinct
* add semi join
