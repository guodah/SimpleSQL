package org.simplesql.relational_algebra;

import java.io.IOException;
import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.loggin.Logging;
import org.simplesql.resolve.SchemaResolver;
import org.simplesql.resolve.Types;

public class BooleanBinaryExpression extends Expression <Boolean>{
	private String operator;
	private Expression<?> left, right;
	public BooleanBinaryExpression(Expression<?> left, String operator, Expression<?> right){
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public String toString(){
		return String.format("%s %s %s", left, operator, right);
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

	@Override
	public boolean resolve(DataSource dataSource, SchemaResolver resolver, OutputStream output) {
		boolean leftResult = left.resolve(dataSource, resolver, output);
		boolean rightResult = right.resolve(dataSource, resolver, output);
	
		if(!leftResult || !rightResult){
			return false;
		}
		
		if(!Types.isCompatible(
				left.getType(resolver), right.getType(resolver))){
			Logging.error(String.format("%s and %s are typed differently", left, right));
			return false;
		}
		
		if((operator.equals("OR") || operator.equals("AND")) && (!left.getType(resolver).equals("BOOLEAN") ||
					!right.getType(resolver).equals("BOOLEAN"))){
			Logging.error(String.format("%s and %s are not BOOLEAN type", left, right));	
		}
		
		return true;
	}
	
	public String getType(SchemaResolver resolver){
		return "BOOLEAN";
	}

}
