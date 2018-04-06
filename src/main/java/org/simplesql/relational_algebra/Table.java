package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

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
	@Override
	public List<Column> getColumns(SchemaResolver resolver) {
		if(!resolver.validateTable(tableName)){
			throw new IllegalStateException("Table "+tableName+" not found.");
		}
		return resolver.findColumns(tableName);
	}
}
