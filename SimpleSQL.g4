grammar SimpleSQL;

@header {
    package org.simplesql.parse;
}

// root rule
parse : SELECT columns FROM table_name (WHERE expr)? ';'; 

columns: column (',' column)*;
column: ANY_NAME;

table_name: ANY_NAME;

expr: 
     literal_value  
   | column
   | expr (GTEQ | NEQ | EQ | GT | LTEQ | LT | IS ) expr
   | expr AND expr
   | expr OR expr

   ;

literal_value
 : NUMERIC_LITERAL
 | STRING_LITERAL
 | NULL
 ;
 
NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

EQ : '=';
NEQ: '<>';
LTEQ: '<=';
LT: '<';
GTEQ: '>=';
GT: '>';

IS: I S ;
SELECT: S E L E C T ;
FROM: F R O M ;
WHERE: W H E R E ;
AND: A N D ;
OR: O R ;
NULL: N U L L;
ANY_NAME : [a-zA-Z]+ ; 

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];


WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines, \r (Windows)
