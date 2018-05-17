package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class DoubleValue extends NumericValue<Double>{
	private double val;
	public DoubleValue(double val){
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
	public String getType() {
		return "DOUBLE";
	}
	
	@Override
	public int hashCode(){
		return new Double(val).hashCode();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof DoubleValue)
			return val==((DoubleValue)o).val;
		else
			return false;
	}
	@Override
	public Set<Expression<?>> getReferencedColumns() {
		return null;
	}


}
