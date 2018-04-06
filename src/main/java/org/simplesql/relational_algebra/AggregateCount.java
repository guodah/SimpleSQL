package org.simplesql.relational_algebra;

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
}
