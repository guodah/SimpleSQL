package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public class Table extends DataSource{
	private String tableName;
	public Table(String tableName){
		this.tableName = tableName;
	}
	public String tableName(){
		return tableName;
	}
	
	public String toString(){
		return tableName;
	}
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return resolver.validateTable(tableName);
	}
}
