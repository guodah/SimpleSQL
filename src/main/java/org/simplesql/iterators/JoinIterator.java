package org.simplesql.iterators;

import java.util.Map;

import org.simplesql.relational_algebra.Join;
import org.simplesql.resolve.SchemaResolver;

abstract public class JoinIterator implements Iterator<Row>{
	protected Iterator<Row> left, right;
	protected Join operator;
	protected SchemaResolver resolver;

	public JoinIterator(Join operator, Iterator<Row> left, Iterator<Row> right){
		this.left = left;
		this.right = right;
		this.operator = operator;
		this.resolver = resolver;
	}

	@Override
	public void reset() {
		left.reset();
		right.reset();
	}
}
