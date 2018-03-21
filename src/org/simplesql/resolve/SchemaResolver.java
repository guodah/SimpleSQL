package org.simplesql.resolve;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class SchemaResolver {
	private Map<String, Map<String, Map<String,String>>> database;
	ObjectMapper objectMapper;
	
	public SchemaResolver(URL url) throws IOException{
		database = new HashMap<>();
		
		objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(url);
		
		ArrayNode tables = (ArrayNode)root;
		for(int i=0;i<tables.size();i++){
			String table = tables.get(i).get("table").asText().toUpperCase();
			database.put(table, new HashMap<>());
			ArrayNode columns = (ArrayNode)tables.get(i).get("columns");
			for(int j=0;j<columns.size();j++){
				String field = columns.get(j).get("field").asText().toUpperCase();
				String type = columns.get(j).get("type").asText().toUpperCase();
				String defaultVal = columns.get(j).get("default").asText();
				if(defaultVal.equals("null") || defaultVal.equals("NULL")){
					defaultVal = null;
				}
				
				database.get(table).put(field, new HashMap<>());
				database.get(table).get(field).put("type", type.toUpperCase());
				database.get(table).get(field).put("default", defaultVal==null?"NULL":defaultVal.toUpperCase());
				
				System.out.printf("table=%s, field=%s, type=%s, default=%s\n",  table, field, type, defaultVal);
			}
		}
	}

	public boolean validateTable(String table){
		table = table.toUpperCase();
		return database.containsKey(table);
	}

	public boolean validateColumn(String table, String column){
		table = table.toUpperCase();
		column = column.toUpperCase();
		return database.containsKey(table) && database.get(table).containsKey(column);
	}
	
	public boolean validateColumn(String table, String column, String type){
		table = table.toUpperCase();
		column = column.toUpperCase();
		type = type.toUpperCase();
		return database.containsKey(table) && database.get(table).containsKey(column)
				&& isCompatile(type, database.get(table).get(column).get("type"));
	}

	private boolean isCompatile(String type1, String type2) {
		return Types.isCompatible(type1, type2);
	}

	public String getType(String tableName, String columnName) {
		tableName = tableName.toUpperCase();
		columnName = columnName.toUpperCase();
		if(!validateColumn(tableName, columnName)){
			throw new IllegalStateException("column not founed");
		}
		
		return database.get(tableName).get(columnName).get("type");
	}
	
	
}