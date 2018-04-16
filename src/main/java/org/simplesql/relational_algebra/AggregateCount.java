package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public class AggregateCount extends Aggregate {
	private int count;
	public AggregateCount(Column column) {
		super(column);
		count = 0;
	}

	@Override
	public void add(LiteralValue val) {
		if(val instanceof NullValue && !column.toString().equals("*")){
			return;
		}else{
			count++;
		}
	}

	@Override
	public LiteralValue aggregatedValue() {
		return new LongValue(count);
	}	

	
	public String toString(){
		return String.format("COUNT(%s)", column==null?"*":column);
	}

	
	public AggregateCount duplicate(){
		return new AggregateCount(column);
	}

	@Override
	public boolean resolve(Relation dataSource, SchemaResolver resolver, OutputStream output) {
		return true;
	}
}
