package org.simplesql.relational_algebra;

import java.util.Set;

public interface RANode {
	Set<Expression<?>> getReferencedColumns();
	void replaceWith(Column c1, Column c2);
}
