package org.simplesql.relational_algebra;

import java.util.Set;

public interface RANode {
	Set<Column> getReferencedColumns();
}
