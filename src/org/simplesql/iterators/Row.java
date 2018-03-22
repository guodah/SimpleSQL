package org.simplesql.iterators;

import java.util.HashMap;

import java.util.Map;

import org.simplesql.relational_algebra.DoubleValue;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.LongValue;
import org.simplesql.resolve.SchemaResolver;

public class Row {
	private Map<String, LiteralValue> fields;
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
	
	public void put(String fieldName, LiteralValue value){
		fields.put(fieldName, value);
	}
	public long getFieldAsLong(String fieldName){
		return ((LongValue)fields.get(fieldName)).evaluate(null);
	}
	public double getFieldAsDouble(String fieldName){
		return ((DoubleValue)fields.get(fieldName)).evaluate(null);
	}
	public LiteralValue get(String fieldName){
		return fields.get(fieldName);
	}

	public boolean containsField(String fieldName) {
		return fields.containsKey(fieldName);
	}
}
