package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public abstract class Aggregate<T extends LiteralValue> extends Function<T>{
	protected Expression<?> column;

	public Aggregate(Expression<?> column){
		this.column = column;
	}
	
	public Expression<?> getColumn(){
		return column;
	}
	
	abstract public Aggregate<?> duplicate();
	abstract public void add(LiteralValue<?> val);
	abstract public T aggregatedValue();

	public boolean resolve(Relation relation, OutputStream output){
		return column.resolve(relation, output); 
	}
	
}
