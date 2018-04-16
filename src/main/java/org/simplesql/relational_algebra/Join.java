package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

abstract public class Join extends Relation{

	protected Relation left;
	protected Relation right;
	
	public Join(Relation left, Relation right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return left.resolve(resolver, output) && right.resolve(resolver, output);
	}
	
	public static Join defineJoin(Relation left, Relation right, String joinMethod, BooleanBinaryExpression expression){
		joinMethod = joinMethod.toUpperCase();
		if(joinMethod.equals("NATURAL")){
			return new NaturalJoin(left, right);
		}else if(joinMethod.equals("INNER")){
			return new InnerJoin(left, right, expression);
		}else{
			throw new IllegalStateException("unsupported join: "+joinMethod);
		}
	}
	
	public Relation getLeft(){
		return left;
	}
	
	public Relation getRight(){
		return right;
	}
	
}
