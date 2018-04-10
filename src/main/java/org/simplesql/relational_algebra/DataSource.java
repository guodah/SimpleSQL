package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

public abstract class DataSource {
	abstract public boolean resolve(SchemaResolver resolver, OutputStream output);
	abstract public List<Column> getColumns(SchemaResolver resolver);
	
	public Table findTable(String table){
		return findTable(this, table);
	}
	
	private Table findTable(DataSource dataSource, String table) {
		if(dataSource instanceof Table){
			if(dataSource.toString().equals(table)){
				return (Table)dataSource;
			}else{
				return null;
			}
		}else if(dataSource instanceof Join){
			Table left = findTable(((Join) dataSource).getLeft(), table);
			if(left!=null) return left;
			Table right = findTable(((Join) dataSource).getRight(), table);
			if(right!=null) return right;
		}
		return null;
	}
	
	abstract public Table findColumn(String column, SchemaResolver resolver) ;
	/*
	public List<Table> findColumn(String column, SchemaResolver resolver) {
		List<Table> tables = new ArrayList<>();
		findColumn(this, column, resolver, tables);
		return tables;
	}
	
	private void findColumn(DataSource dataSource, String column, SchemaResolver resolver, List<Table> tables) {
		if(dataSource instanceof Table){
			if(resolver.validateColumn(((Table)dataSource).tableName(), column)){
				tables.add((Table)dataSource);
			}
		}else if(dataSource instanceof Join){
			findColumn(((Join)dataSource).getLeft(), column, resolver, tables);
			findColumn(((Join)dataSource).getRight(), column, resolver, tables);
		}
	}
	*/
}
