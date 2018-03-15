## SimpleSQL

This is a project for me to experience the process of **parsing**, **compiling**, and **executing** SQL statements.   

###Updates:  
**March 13, 2018**: Using [ANTLR v4](http://www.antlr.org/download.html), defined a simple SQL grammar, supporting basic **"select columns from table where boolean_expression"** SQL statements. It does not support subqueries.

**March 14, 2018**: Wrote Java code that converts an SQL parse time to relational algebra tree. It is in Utilities.parseTreeToRelAlg in org.simplesql.relational_algebra. It has worked on a few examples, while still needs more testing. Also explored Orc. 

**March 15, 2018**: Wrote Java code for the evaluate methods in Expression and subclasses (BooleanBinaryExpression, LiteralValue, LongValue, DoubleValue, StringValue and NulValue. 
