package org.simplesql.iterators;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

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
		this.tableName = tableName.toUpperCase();
		this.resolver = resolver;
	}
	
	public Row(){
		fields = new HashMap<>();
	}
	
	public void put(String fieldName, LiteralValue value){
		fieldName = fieldName.toUpperCase();
		fields.put(fieldName, value);
	}
	
	public long getFieldAsLong(String fieldName){
		return ((LongValue)fields.get(fieldName)).evaluate(null);
	}
	
	public double getFieldAsDouble(String fieldName){
		return ((DoubleValue)fields.get(fieldName)).evaluate(null);
	}
	
	protected LiteralValue<?> getValueWithoutTable(String column){
		for(String each:fields.keySet()){
			if(!each.contains(".")) continue;
			
			if(column.equals(each.substring(each.indexOf(".")+1))) {
				return fields.get(each);
			}
		}
		return null;
	}
	
	public LiteralValue get(String fieldName){
		fieldName = fieldName.toUpperCase();
//		if(fields.containsKey(fieldName)){
			return fields.get(fieldName);
//		}else{
//			return fields.get(tableName+"."+fieldName);
//		}
	}

	public boolean containsField(String fieldName) {
		fieldName = fieldName.toUpperCase();
		return fields.containsKey(fieldName);
	}

	public Set<String> getFieldNames() {
		return fields.keySet();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String key:fields.keySet()){
			sb.append(String.format("(%s, %s)", key, fields.get(key)));
		}
		return sb.toString();
	}

	public static Row combine(Row row1, Row row2) {
		Row row = new Row();
		for(String field:row1.fields.keySet()){
			row.put(field, row1.fields.get(field));
		}

		for(String field:row2.fields.keySet()){
			row.put(field, row2.fields.get(field));
		}
		return row;
	}
}
