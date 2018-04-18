package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.loggin.Logging;
import org.simplesql.resolve.SchemaResolver;
import org.simplesql.resolve.Types;

abstract public class BinaryExpression<T> extends Expression<T>{
	protected String operator;
	protected Expression<?> left, right;
	public BinaryExpression(Expression<?> left, String operator, Expression<?> right){
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public String toString(){
		return String.format("%s %s %s", left, operator, right);
	}
	@Override
	public boolean resolve(Relation dataSource, OutputStream output) {
		boolean leftResult = left.resolve(dataSource,  output);
		boolean rightResult = right.resolve(dataSource,  output);
	
		if(!leftResult || !rightResult){
			return false;
		}
		
		if(!Types.isCompatible(
				left.getType(), right.getType())){
			Logging.error(String.format("%s and %s are typed differently", left, right));
			return false;
		}
		return true;
	}

	@Override
	public String getSimpleName() {
		return toString();
	}

	@Override
	public String getFullName() {
		return toString();
	}

}
