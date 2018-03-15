package org.simplesql.relational_algebra;

public class NullValue extends LiteralValue<Object>{

	public NullValue() {
		super(true);
	}

	@Override
	public Object evaluate(Context ctx) {
		return null;
	}

}
