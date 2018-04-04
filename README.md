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
