package org.simplesql.relational_algebra;

import java.util.List;

public class LeftOuterJoin extends Join{

	public LeftOuterJoin(Relation left, Relation right) {
		super(left, right);
	}

	@Override
	public Table locateColumn(String column) {
		return null;
	}

	@Override
	public List<Expression<?>> getColumns() {
		return null;
	}

}
