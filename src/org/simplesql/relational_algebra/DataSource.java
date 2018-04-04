package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

public abstract class DataSource {
	abstract public boolean resolve(SchemaResolver resolver, OutputStream output);
	abstract public List<Column> getColumns(SchemaResolver resolver);
}
