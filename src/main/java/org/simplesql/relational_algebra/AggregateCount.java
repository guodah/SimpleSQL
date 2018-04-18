package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;

public class AggregateCount extends Aggregate<LongValue> {
	private int count;
	public AggregateCount(Expression<?> column) {
		super(column);
		count = 0;
	}

	@Override
	public void add(LiteralValue<?> val) {
		if(val instanceof NullValue){
			return;
		}else{
			count++;
		}
	}

	@Override
	public LongValue aggregatedValue() {
		return new LongValue(count);
	}	

	
	public String toString(){
		return String.format("COUNT(%s)", column==null?"*":column);
	}

	
	public AggregateCount duplicate(){
		return new AggregateCount(column);
	}

	@Override
	public String getSimpleName() {
		return toString();
	}

	@Override
	public String getFullName() {
		return toString();
	}

	@Override
	public LongValue evaluate(Row ctx) {
		return aggregatedValue();
	}

	@Override
	public String getType() {
		return "LONG";
	}

	@Override
	public boolean isNumeric() {
		return true;
	}

	@Override
	public boolean isBoolean() {
		return false;
	}

}
