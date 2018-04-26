package org.simplesql.relational_algebra;

import java.util.List;
import java.util.Set;

public class LeftOuterJoin extends Join{

	public LeftOuterJoin(Relation left, Relation right) {
		super(left, right);
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		return null;
	}

	@Override
	public List<Expression<?>> getColumns() {
		return null;
	}

	@Override
	public Set<Column> getReferencedColumns() {
		// TODO Auto-generated method stub
		return null;
	}

}
