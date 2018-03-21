package org.simplesql.relational_algebra;

public class Filter {
	private BooleanBinaryExpression expression;
	
	public Filter(BooleanBinaryExpression expression){
		setExpression(expression);
	}
	
	public Filter(){
		
	}
	
	public void setExpression(BooleanBinaryExpression expression){
		this.expression = expression;
	}
	
	public BooleanBinaryExpression getExpression(){
		return expression;
	}
	
	public String toString(){
		return String.format(" where %s", expression);
	}
}
