package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;


public abstract class LiteralValue<T extends Comparable<T>> extends Expression<Comparable<T>> {
	private boolean isNull;

	
	public LiteralValue(boolean isNull){
		this.isNull = isNull;
	}
	
	public boolean isNull(){
		return isNull;
	}
	
	public String toString(){
		return isNull?"null":"";
	}
	
	public boolean resolve(Relation dataSource, SchemaResolver resolver, OutputStream output){
		return true;
	}
}
