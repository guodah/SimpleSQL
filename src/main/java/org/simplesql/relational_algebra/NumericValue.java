package org.simplesql.relational_algebra;

abstract public class NumericValue<T extends Comparable<T>> extends LiteralValue<T>{

	public NumericValue() {
		super(false);
	}

	@Override
	public boolean isNumeric() {
		return true;
	}

	@Override
	public boolean isBoolean() {
		return false;
	}

	@Override
	public String getSimpleName() {
		return toString();
	}

	@Override
	public String getFullName() {
		return toString();
	}

	public static NumericValue perform(String operator, NumericValue left, NumericValue right){
		if(operator.equals("+")){
			return add(left, right);
		}else if(operator.equals("-")){
			return subtract(left, right);
		}else if(operator.equals("*")){
			return multiply(left, right);
		}else if(operator.equals("/")){
			return divide(left, right);
		}else{
			throw new IllegalStateException("unregconized operator: " + operator);
		}
	}

	private static NumericValue add(NumericValue left, NumericValue right) {
		if(left instanceof DoubleValue || right instanceof DoubleValue){
			return new DoubleValue((Double)(left.evaluate(null))+((Double)right.evaluate(null)));
		}else{
			return new LongValue(((Long)left.evaluate(null))+((Long)right.evaluate(null)));
		}
	}
	private static NumericValue subtract(NumericValue left, NumericValue right) {
		if(left instanceof DoubleValue || right instanceof DoubleValue){
			return new DoubleValue((Double)(left.evaluate(null))-((Double)right.evaluate(null)));
		}else{
			return new LongValue(((Long)left.evaluate(null))-((Long)right.evaluate(null)));
		}
	}
	private static NumericValue multiply(NumericValue left, NumericValue right) {
		if(left instanceof DoubleValue || right instanceof DoubleValue){
			return new DoubleValue((Double)(left.evaluate(null))*((Double)right.evaluate(null)));
		}else{
			return new LongValue(((Long)left.evaluate(null))*((Long)right.evaluate(null)));
		}
	}
	private static NumericValue divide(NumericValue left, NumericValue right) {
		if(left instanceof DoubleValue || right instanceof DoubleValue){
			return new DoubleValue((Double)(left.evaluate(null))/((Double)right.evaluate(null)));
		}else{
			return new LongValue(((Long)left.evaluate(null))/((Long)right.evaluate(null)));
		}
	}
}
