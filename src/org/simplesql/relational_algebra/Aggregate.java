package org.simplesql.relational_algebra;

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
	
}
