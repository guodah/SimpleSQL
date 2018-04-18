package org.simplesql.relational_algebra;

import org.simplesql.iterators.Row;

public class AggregateSum extends Aggregate<NumericValue<?>>{

	private LiteralValue<?> sum;
	public AggregateSum(Expression<?> column) {
		super(column);
		sum = null;
	}

	@Override
	public void add(LiteralValue<?> val) {
		if(sum==null){
			sum = val;
		}else{
			if(val instanceof NullValue){
				return;
			}else if(val instanceof LongValue){
				sum = new LongValue(((LongValue)sum).evaluate(null)+((LongValue)val).evaluate(null));
			}else{
				throw new IllegalStateException("Unsupported type for aggregate");
			}
		}
	}

	@Override
	public NumericValue<?> aggregatedValue() {
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

	@Override
	public String getSimpleName() {
		return toString();
	}

	@Override
	public String getFullName() {
		return toString();
	}

	@Override
	public NumericValue<?> evaluate(Row ctx) {
		return this.aggregatedValue();
	}

	@Override
	public String getType() {
		if(sum instanceof LongValue){
			return "LONG";
		}else{
			throw new IllegalStateException("Unsupported type for aggregate");
		}
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
