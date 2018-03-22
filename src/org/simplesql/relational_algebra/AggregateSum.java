package org.simplesql.relational_algebra;

public class AggregateSum extends Aggregate{

	private LiteralValue sum;
	private int count;
	public AggregateSum(Column column) {
		super(column);
		sum = null;
	}

	@Override
	public void add(LiteralValue val) {
		if(sum==null){
			sum = val;
			count = 1;
		}else{
			if(val instanceof NullValue){
				return;
			}else if(val instanceof LongValue){
				sum = new LongValue(((LongValue)sum).evaluate(null)+((LongValue)val).evaluate(null));
				count++;
			}else{
				throw new IllegalStateException("Unsupported type for aggregate");
			}
		}
	}

	@Override
	public LiteralValue aggregatedValue() {
		if(sum instanceof LongValue){
			return new LongValue(((LongValue)sum).evaluate(null));
		}else{
			throw new IllegalStateException("Unsupported type for aggregate");
		}
	}	
	
	public String toString(){
		return String.format("SUM(%s)", column);
	}


	public AggregateSum duplicate(){
		return new AggregateSum(column);
	}
}
