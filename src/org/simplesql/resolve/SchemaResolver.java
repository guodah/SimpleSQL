package org.simplesql.resolve;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.DoubleValue;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.LongValue;
import org.simplesql.relational_algebra.StringValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class SchemaResolver {
	private Map<String, Map<String, Map<String,String>>> schema;
	private Map<String, URL> paths;
	ObjectMapper objectMapper;
	
	public SchemaResolver(URL url) throws IOException{
		schema = new HashMap<>();
		paths = new HashMap<>();
		objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(url);
		
		ArrayNode tables = (ArrayNode)root;
		for(int i=0;i<tables.size();i++){
			String table = tables.get(i).get("table").asText().toUpperCase();
			String path = tables.get(i).get("path").asText();
			paths.put(table, new File(path).toURI().toURL());
			schema.put(table, new HashMap<>());
			ArrayNode columns = (ArrayNode)tables.get(i).get("columns");
			for(int j=0;j<columns.size();j++){
				String field = columns.get(j).get("field").asText().toUpperCase();
				String type = columns.get(j).get("type").asText().toUpperCase();
				String defaultVal = columns.get(j).get("default").asText();
				if(defaultVal.equals("null") || defaultVal.equals("NULL")){
					defaultVal = null;
				}
				
				schema.get(table).put(field, new HashMap<>());
				schema.get(table).get(field).put("type", type.toUpperCase());
				schema.get(table).get(field).put("default", defaultVal==null?"NULL":defaultVal.toUpperCase());
				
			//	System.out.printf("table=%s, field=%s, type=%s, default=%s\n",  table, field, type, defaultVal);
			}
		}
	}

	public boolean validateTable(String table){
		table = table.toUpperCase();
		return schema.containsKey(table);
	}

	public boolean validateColumn(String table, String column){
		table = table.toUpperCase();
		column = column.toUpperCase();
		return schema.containsKey(table) && schema.get(table).containsKey(column);
	}
	
	public  boolean validateColumn(String column){
		column = column.toUpperCase();
		for(String table:schema.keySet()){
			if(validateColumn(table, column)){
				return true;
			}
		}
		return false;
	}
	
	public boolean validateColumn(String table, String column, String type){
		table = table.toUpperCase();
		column = column.toUpperCase();
		type = type.toUpperCase();
		return schema.containsKey(table) && schema.get(table).containsKey(column)
				&& isCompatile(type, schema.get(table).get(column).get("type"));
	}

	private boolean isCompatile(String type1, String type2) {
		return Types.isCompatible(type1, type2);
	}

	public String getType(String columnName){
		columnName = columnName.toUpperCase();
		for(String table:schema.keySet()){
			if(schema.get(table).containsKey(columnName)){
				return schema.get(table).get(columnName).get("type");
			}
		}
		return null;
	}
	
	public String getType(String tableName, String columnName) {
		tableName = tableName.toUpperCase();
		columnName = columnName.toUpperCase();
		if(!validateColumn(tableName, columnName)){
			throw new IllegalStateException("column not founed");
		}
		
		return schema.get(tableName).get(columnName).get("type");
	}

	public LiteralValue parseValue(String tableName, String columnName, String value) {
		tableName = tableName.toUpperCase();
		columnName = columnName.toUpperCase();
		if(!validateColumn(tableName, columnName)){
			throw new IllegalStateException("column not found");
		}
		
		String type = Types.getRepresentingType(schema.get(tableName).get(columnName).get("type"));
		if(type.equals("LONG")){
			return new LongValue(Long.parseLong(value));
		}else if(type.equals("DOUBLE")){
			return new DoubleValue(Double.parseDouble(value));
		}else{//string
			return new StringValue(value);
		}
	}

	public List<Column> findColumns(String tableName) {
		tableName = tableName.toUpperCase();
		if(!this.schema.containsKey(tableName)){
			return null;
		}
		
		List<Column> columns = new ArrayList<>();
		for(String columnName:schema.get(tableName).keySet()){
			columns.add(new Column(columnName));
		}
		return columns;
	}

	public URL getTablePath(String tableName) {
		tableName = tableName.toUpperCase();
		return paths.get(tableName);
	}

}
