package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.loggin.Logging;
import org.simplesql.resolve.SchemaResolver;

/**
 * Considered operators are +, -, *, /
 * 
 * @author Dahai Guo
 *
 */
public class NumericBinaryExpression extends BinaryExpression <LiteralValue<?>>{

	public NumericBinaryExpression(Expression<?> left, String operator, Expression<?> right){
		super(left, operator, right);
	}
	

	@Override
	public LiteralValue<?> evaluate(Row ctx) {
		NumericValue<?> leftValue = wrap(left.evaluate(ctx));
		NumericValue<?> rightValue = wrap(right.evaluate(ctx));
		return perform(operator, (NumericValue<?>)leftValue, (NumericValue<?>)rightValue);
	}

	private NumericValue<?> wrap(Object value) {
		if(value instanceof NumericValue<?>){
			return (NumericValue<?>)value;
		}else if(value instanceof Long){
			return new LongValue((Long)value);
		}else if(value instanceof Double){
			return new DoubleValue((Double)value);
		}else{
			throw new IllegalStateException("unrecognized datatype: "+value.getClass());
		}
	}


	@Override
	public boolean resolve(Relation relation, OutputStream output) {
		if(!super.resolve(relation,  output)){
			return false;
		}
		
		if(!checkOperator(operator)){
			return false;
		}
		
		if(!isNumeric(left) || !isNumeric(right)){
			return false;
		}
		
		return true;

	}

	private boolean isNumeric(Expression<?> left) {
		return left.isNumeric();
	}


	@Override
	public String getType() {
		String leftType = left.getType();
		String rightType = right.getType();
		if(leftType.equals("DOUBLE") || rightType.equals("DOUBLE")){
			return "DOUBLE";
		}else{
			return "LONG";
		}
	}

	private boolean checkOperator(String operator){
		return operator.equals("+") || operator.equals("-") ||
				operator.equals("*") || operator.equals("/");
	}


	@Override
	public boolean isNumeric() {
		return true;
	}


	@Override
	public boolean isBoolean() {
		return false;
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
