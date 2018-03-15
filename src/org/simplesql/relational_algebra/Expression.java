package org.simplesql.relational_algebra;

/*
expr: 
     literal_value  
   | column
   | expr ('>' | '<' | '=' | '<>' | '>=' | '<=' | 'is') expr
   | expr OR expr
   | expr AND expr

   ;*/
public abstract class Expression <T>{
	
	abstract public T evaluate(Context ctx);
}
