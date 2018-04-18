package org.simplesql.relational_algebra;

public class AggregateBuilder {
	private AggregateBuilder builder;
	public static AggregateBuilder newInstance(){
		return new AggregateBuilder();
	}
	
	private String function;
	private Expression<?> column;
	
	public AggregateBuilder setFunction(String function){
		this.function = function;
		return this;
	}
	
	public AggregateBuilder setColumn(Expression<?> expr){
		column = expr;
		return this;
	}
	
	public Aggregate<?> build(){
		if(function.equals("SUM")){
			return new AggregateSum(column);
		}else if(function.equals("COUNT")){
			return new AggregateCount(column);
		}else{
			throw new IllegalStateException("unsupported aggregate function: "+function);
		}
	}
}
