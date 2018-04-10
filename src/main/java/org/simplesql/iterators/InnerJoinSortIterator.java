package org.simplesql.iterators;

import org.simplesql.relational_algebra.Join;
import org.simplesql.resolve.SchemaResolver;

public class InnerJoinSortIterator extends JoinIterator{

	public InnerJoinSortIterator(Join operator, Iterator<Row> left, Iterator<Row> right, SchemaResolver resolver) {
		super(operator, left, right, resolver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Row next() {
		// TODO Auto-generated method stub
		return null;
	}
}
