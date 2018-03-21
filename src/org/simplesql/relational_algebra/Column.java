package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.resolve.SchemaResolver;

public class Column extends Expression<String>{
	private String columnName;
	private DataSource dataSource;
	public Column(String columnName){
		this(null, columnName);
	}
	
	public Column(DataSource dataSource, String columnName){
		this.dataSource = dataSource;
		this.columnName = columnName;		
	}
	
	public String toString(){
		return columnName;
	}

	@Override
	public String evaluate(Context ctx) {
		// ctx unused
		return toString();
	}

	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		if(dataSource instanceof Table){
			return resolver.validateColumn(((Table) dataSource).tableName(), columnName);
		}
		
		throw new IllegalStateException("unsupported input");
	}
	
	public String getType(SchemaResolver resolver){
		if(!resolve(resolver, null)){
			throw new IllegalStateException("Column not found");
		}
		if(dataSource instanceof Table){
			return resolver.getType(((Table) dataSource).tableName(), columnName);
		}
		throw new IllegalStateException("unsupported type");
	}

}
