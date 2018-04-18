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
		return NumericValue.perform(operator, (NumericValue<?>)leftValue, (NumericValue<?>)rightValue);
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
	
	
}
