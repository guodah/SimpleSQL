package org.simplesql.relational_algebra;

import java.io.IOException;
import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.loggin.Logging;
import org.simplesql.resolve.SchemaResolver;
import org.simplesql.resolve.Types;

public class BooleanBinaryExpression extends BinaryExpression <Boolean>{
	public BooleanBinaryExpression(Expression<?> left, String operator, Expression<?> right){
		super(left, operator, right);
	}
	

	@Override
	public Boolean evaluate(Row ctx) {
		if(isLogicalOperator(operator)){ // AND or OR
			boolean leftBool = ((BooleanBinaryExpression)left).evaluate(ctx);
			boolean rightBool = ((BooleanBinaryExpression)right).evaluate(ctx);
			
			return operator.equals("AND")?(leftBool && rightBool):(leftBool || rightBool); 
		}else{ // comparison
			
			return compare(ctx);
		}
	}

	private boolean compare(Row ctx) {
		int compareCode = Expression.compare(left, right, ctx);
		
		switch(operator){
		case "=": return compareCode==0;
		case "<>": return compareCode!=0;
		case "<=": return compareCode<=0;
		case ">=": return compareCode>=0;
		case ">": return compareCode>0;
		case "<": return compareCode<0;
		default: throw new IllegalStateException("unsupported operator "+operator);
		}
	}

	private boolean isLogicalOperator(String operator) {
		return operator.equals("AND") || operator.equals("OR");
	}
	
	private boolean isCompareOperator(String operator){
		return operator.equals("AND") || operator.equals("OR") ||
				operator.equals(">=") || operator.equals("<=") ||
				operator.equals(">") || operator.equals("<") ||
				operator.equals("<>") || operator.equals("=") ||
				operator.equals("IS");
	}

	@Override
	public boolean resolve(Relation dataSource, OutputStream output) {
		if(!super.resolve(dataSource,  output)){
			return false;
		}
		
		if(!checkOperator(operator)){
			return false;
		}
		
		if(isLogicalOperator(operator) && (!isBoolean(left) || !isBoolean(right))){
			return false;
		}
		
		return true;
	}
	
	private boolean isBoolean(Expression<?> left) {
		return left.isBoolean();
	}


	private boolean checkOperator(String operator) {
		return this.isCompareOperator(operator) ||
				this.isLogicalOperator(operator);
	}


	public String getType(SchemaResolver resolver){
		return "BOOLEAN";
	}


	@Override
	public boolean isNumeric() {
		return false;
	}


	@Override
	public boolean isBoolean() {
		return true;
	}


	@Override
	public String getType() {
		return "BOOLEAN";
	}

}
