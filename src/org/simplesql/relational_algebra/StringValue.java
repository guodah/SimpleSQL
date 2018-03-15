package org.simplesql.relational_algebra;

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
}
