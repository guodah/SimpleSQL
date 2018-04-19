package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.SimpleSQL;
import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class Column extends Expression<LiteralValue>{
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
	public LiteralValue evaluate(Row ctx) {
		if(ctx==null || !ctx.containsField(getFullName())){
			return NullValue.NULL;
		}else {
			return ctx.get(getFullName());
		}
	}

	// 1. If the column already specifies a table, check if the table is part of the data source
	// 2. If the column does not specify a table, check data source contains a table that contains
	//    the column exclusively, then bind the column to that table
	@Override
	public boolean resolve(Relation dataSource,  OutputStream output) {
		if(resolved || isWildCard()) return true;
		
		if(tableName!=null){
			resolved = dataSource.findTable(tableName)!=null && 
					SimpleSQL.getSchemaResolver().validateColumn(tableName, columnName);
		}else{
			Table table = dataSource.locateColumn(columnName);
			tableName = table.tableName();
			resolved = (table!=null);
		}
		return resolved;
	}
	
	private boolean isWildCard() {
		return columnName.equals("*");
	}
	
	@Override
	public Table locateColumn(String column){
		if(column.equals(columnName)){
			return tableName==null?null:new Table(tableName);
		}else{
			return null;
		}
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


	@Override
	public String getSimpleName() {
		return columnName;
	}
	
	@Override
	public String getFullName(){
		if(tableName==null){
			return columnName;
		}else{
			return tableName+"."+columnName;
		}
	}

	@Override
	public boolean isNumeric() {
		String type = getType();
		return type.equals("LONG") || type.equals("DOUBLE") || type.equals("INTEGER");
	}

	@Override
	public boolean isBoolean() {
		throw new IllegalStateException("unsupported operation");
	}

	@Override
	public String getType() {
		return SimpleSQL.getSchemaResolver().getType(tableName, columnName);
	}	
}
