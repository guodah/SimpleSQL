package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
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
	public String evaluate(Row ctx) {
		return toString();
	}

	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return resolver.validateColumn(columnName);
	}
	
	public String getType(SchemaResolver resolver){
		if(!resolve(resolver, null)){
			throw new IllegalStateException("Column not found");
		}
		return resolver.getType(columnName);
	}

	
	@Override
	public boolean equals(Object c){
		if(c instanceof Column)
			return columnName.equals(c.toString());
		else
			return false;
	}
}
