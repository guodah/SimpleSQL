package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
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
	protected boolean resolved;
	
	public boolean isResolved(){
		return resolved;
	}
	
	abstract public T evaluate(Row ctx);
	
	abstract public boolean resolve(DataSource dataSource, SchemaResolver resolver, OutputStream output);

	abstract public String getType(SchemaResolver resolver);
}
