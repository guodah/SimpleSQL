package org.simplesql.relational_algebra;

public abstract class LiteralValue<T> extends Expression<T> {
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
}
