package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class Filter implements RANode{
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
		return String.format(" WHERE %s", expression);
	}
	
	public boolean evaluate(Row row){
		return expression.evaluate(row);
	}
	
	public boolean resolve(Relation dataSource, SchemaResolver resolver, 
			OutputStream output){
		return expression.resolve(dataSource, output);
	}

	@Override
	public Set<Column> getReferencedColumns() {
		return expression.getReferencedColumns();
	}
}
