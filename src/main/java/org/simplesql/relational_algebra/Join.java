package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.resolve.SchemaResolver;

abstract public class Join extends Relation implements RANode{

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
	
	public static Join defineJoin(Relation left, Relation right, String joinMethod, 
			BooleanBinaryExpression expression){
		joinMethod = joinMethod.toUpperCase();
		if(joinMethod.equals("NATURAL")){
			return new NaturalJoin(left, right);
		}else if(joinMethod.equals("INNER")){
			return new ConditionedJoin(left, right, true, expression);
		}else if(joinMethod.equals("LEFT")){
			return new ConditionedJoin(left, right, false, expression);
		}else if(joinMethod.equals("RIGHT")){
			return new ConditionedJoin(right, left, false, expression);
		}else {
			throw new IllegalStateException("unsupported join: "+joinMethod);
		}
	}
	
	public Relation getLeft(){
		return left;
	}
	
	public Relation getRight(){
		return right;
	}
	

	public void setLeft(Relation left) {
		this.left = left;
	}

	public void setRight(Relation right) {
		this.right = right;
	}

}
