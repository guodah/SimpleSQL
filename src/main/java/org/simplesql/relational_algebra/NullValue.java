package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

class Null implements Comparable<Null>{
	@Override
	public int compareTo(Null o) {
		return 0;
	}
}

public class NullValue extends LiteralValue<Null>{

	public final static NullValue NULL= new NullValue();
	
	public NullValue() {
		super(true);
	}

	@Override
	public Null evaluate(Row ctx) {
		return null;
	}
	@Override
	public String getType(SchemaResolver resolver) {
		return "NULL";
	}

	@Override
	public int hashCode(){
		return 0;
	}
}
