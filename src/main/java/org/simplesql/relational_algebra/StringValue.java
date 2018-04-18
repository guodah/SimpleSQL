package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class StringValue extends LiteralValue<String> {
	private String val;
	public StringValue(String val){
		super(false);
		this.val = val;
	}
	
	public String toString(){
		return val;
	}

	@Override
	public String evaluate(Row ctx) {
		return toString();
	}
	@Override
	public String getType() {
		return "STRING";
	}
	@Override
	public int hashCode(){
		return val.hashCode();
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof StringValue)
			return val.equals(((StringValue)o).val);
		else
			return false;
	}

	
	
	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isBoolean() {
		return false;
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
