package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class LongValue extends LiteralValue<Long>{
	private long val;
	public LongValue(long val){
		super(false);
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
	public String getType(SchemaResolver resolver) {
		return "LONG";
	}

}
