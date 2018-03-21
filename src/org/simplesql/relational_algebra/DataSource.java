package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public abstract class DataSource {
	abstract public boolean resolve(SchemaResolver resolver, OutputStream output);
}
