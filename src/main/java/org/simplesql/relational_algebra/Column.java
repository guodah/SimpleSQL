package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class Column extends Expression<String>{
	private String columnName;
	private String tableName;
	public Column(String columnName){
		this(null, columnName);
		tableName = null;
	}
	
	public Column(String tableName, String columnName){
		this.tableName = tableName;
		this.columnName = columnName;		
	}
	
	public String toString(){
		if(tableName==null){
			return columnName;
		}else{
			return tableName+"."+columnName;
		}
	}

	@Override
	public String evaluate(Row ctx) {
		return toString();
	}

	// 1. If the column already specifies a table, check if the table is part of the data source
	// 2. If the column does not specify a table, check data source contains a table that contains
	//    the column exclusively, then bind the column to that table
	@Override
	public boolean resolve(Relation dataSource, SchemaResolver resolver, OutputStream output) {
		if(resolved) return true;
		
		if(tableName!=null){
			resolved = dataSource.findTable(tableName)!=null && 
					resolver.validateColumn(tableName, columnName);
		}else{
			Table table = dataSource.locateColumn(columnName, resolver);
			tableName = table.tableName();
			resolved = (table!=null);
		}
		return resolved;
	}
	
	public String getType(SchemaResolver resolver){
		if(!isResolved()){
			throw new IllegalStateException("Column unsolved");
		}
		return resolver.getType(tableName, columnName);
	}

	
	@Override
	public boolean equals(Object c){
		if(c instanceof Column)
			return columnName.equals(c.toString());
		else
			return false;
	}

	public String getTableName() {
		return tableName;
	}

	public String getColumn(){
		return columnName;
	}
	
}
