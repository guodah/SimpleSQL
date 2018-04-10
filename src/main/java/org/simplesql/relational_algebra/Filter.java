package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

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
	
	public boolean evaluate(Row row){
		return expression.evaluate(row);
	}
	
	public boolean resolve(DataSource dataSource, SchemaResolver resolver, OutputStream output){
		return expression.resolve(dataSource, resolver, output);
	}
}
