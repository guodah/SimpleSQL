package org.simplesql.iterators;

import java.util.HashMap;
import java.util.Map;

import org.simplesql.resolve.SchemaResolver;

public class Row {
	private Map<String, Object> fields;
	private String tableName;
	private SchemaResolver resolver;
	
	public Row(String tableName, SchemaResolver resolver){
		this();
		this.tableName = tableName;
		this.resolver = resolver;
	}
	
	public Row(){
		fields = new HashMap<>();
	}
	
	public void put(String fieldName, Object value){
		fields.put(fieldName, value);
	}
	public long getFieldAsLong(String fieldName){
		return ((Long)fields.get(fieldName)).longValue();
	}
	public double getFieldAsDouble(String fieldName){
		return ((Double)fields.get(fieldName)).doubleValue();
	}
	public Object get(String fieldName){
		return fields.get(fieldName);
	}

	public boolean containsField(String fieldName) {
		return fields.containsKey(fieldName);
	}
}
