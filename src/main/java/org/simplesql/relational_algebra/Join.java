package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

abstract public class Join extends DataSource{

	protected DataSource left;
	protected DataSource right;
	
	public Join(DataSource left, DataSource right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return left.resolve(resolver, output) && right.resolve(resolver, output);
	}
	
	public static Join defineJoin(DataSource left, DataSource right, String joinMethod){
		joinMethod = joinMethod.toUpperCase();
		if(joinMethod.equals("NATURAL")){
			return new NaturalJoin(left, right);
		}else{
			throw new IllegalStateException("unsupported join: "+joinMethod);
		}
	}
	
	public DataSource getLeft(){
		return left;
	}
	
	public DataSource getRight(){
		return right;
	}
}
