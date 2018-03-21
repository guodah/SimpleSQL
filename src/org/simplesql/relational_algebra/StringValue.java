package org.simplesql.relational_algebra;

import java.io.OutputStream;

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
	public String evaluate(Context ctx) {
		return toString();
	}
	@Override
	public String getType(SchemaResolver resolver) {
		return "STRING";
	}

}
