package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public abstract class Aggregate{
	protected Column column;

	public Aggregate(Column column){
		this.column = column;
	}
	
	public Column getColumn(){
		return column;
	}
	
	abstract public Aggregate duplicate();
	abstract public void add(LiteralValue val);
	abstract public LiteralValue aggregatedValue();

	public boolean resolve(Relation dataSource, SchemaResolver resolver, OutputStream output){
		return column.resolve(dataSource, resolver, output); 
	}
	
}
