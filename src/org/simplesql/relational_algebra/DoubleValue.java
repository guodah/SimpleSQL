package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class DoubleValue extends LiteralValue<Double>{
	private double val;
	public DoubleValue(double val){
		super(false);
		this.val = val;
	}
	
	public String toString(){
		return ""+val;
	}

	@Override
	public Double evaluate(Row ctx) {
		return val;
	}

	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return true;
	}

	@Override
	public String getType(SchemaResolver resolver) {
		return "DOUBLE";
	}
}
