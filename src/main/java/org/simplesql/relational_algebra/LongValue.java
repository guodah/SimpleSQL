package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class LongValue extends NumericValue<Long>{
	
	public static final LongValue ZERO = new LongValue(0L);
	
	private long val;
	public LongValue(long val){
		this.val = val;
	}
	
	public String toString(){
		return ""+val;
	}

	@Override
	public Long evaluate(Row ctx) {
		return val;
	}
	@Override
	public String getType() {
		return "LONG";
	}

	@Override
	public int hashCode(){
		return new Long(val).hashCode();
	}

	
	@Override
	public boolean equals(Object o){
		if(o instanceof LongValue)
			return val==((LongValue)o).val;
		else
			return false;
	}

}
