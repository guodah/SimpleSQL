package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class NullValue extends LiteralValue<Object>{

	public NullValue() {
		super(true);
	}

	@Override
	public Object evaluate(Row ctx) {
		return null;
	}
	@Override
	public String getType(SchemaResolver resolver) {
		return "NULL";
	}

}
