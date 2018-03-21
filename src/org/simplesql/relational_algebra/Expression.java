package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

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
	
	abstract public boolean resolve(SchemaResolver resolver, OutputStream output);

	abstract public String getType(SchemaResolver resolver);
}
