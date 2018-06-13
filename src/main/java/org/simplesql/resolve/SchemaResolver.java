
package org.simplesql.resolve;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private Map<String, Set<String>> primaryKeys;
	private Map<String, Map<String, String[]>> foreignKeys;
	private Map<String, URL> paths;
	ObjectMapper objectMapper;
	
	public SchemaResolver(URL url) throws IOException{
		schema = new HashMap<>();
		paths = new HashMap<>();
		objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(url);
		
		primaryKeys = new HashMap<>();
		foreignKeys = new HashMap<>();
		
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
			}
			
			// read primary key
			if(tables.get(i).get("primary_key")!=null){
				primaryKeys.put(table, new HashSet<String>());
				ArrayNode primaryKeyNodes = (ArrayNode)tables.get(i).get("primary_key");
				for(int j=0;j<primaryKeyNodes.size();j++){
					String column = primaryKeyNodes.get(j).get("column").asText().toUpperCase();
					if(!schema.get(table).containsKey(column)){
						throw new IllegalStateException("primary key does not exist as a column");
					}
					primaryKeys.get(table).add(column);
				}
			}
			
			// read foreign key
			if(tables.get(i).get("foreign_keys")!=null){
				foreignKeys.put(table, new HashMap<>());
				ArrayNode foreignKeyNodes = (ArrayNode)tables.get(i).get("foreign_keys");
				for(int j=0;j<foreignKeyNodes.size();j++){
					String column = foreignKeyNodes.get(j).get("column").asText().toUpperCase();
					if(!schema.get(table).containsKey(column)){
						throw new IllegalStateException("foreign key does not exist as a column");
					}
					String referencedTable = foreignKeyNodes.get(j).get("references").
							get("table").asText().toUpperCase();
					String referencedColumn = foreignKeyNodes.get(j).get("references").
							get("column").asText().toUpperCase();
					foreignKeys.get(table).put(column, new String[]{referencedTable, referencedColumn});
				}
			}
		}
	}

	public boolean isPrimaryKey(String table, String... columns){
		for(String each: columns){
			if(!validateColumn(table, each)){
				return false;
			}
		}
		
		return primaryKeys.containsKey(table) &&
				primaryKeys.get(table).equals(
						new HashSet<String>(Arrays.asList(columns)));
	}
	
	public boolean validateTable(String table){
		table = table.toUpperCase();
		return schema.containsKey(table);
	}

	public boolean validateColumn(String table, String column){
		if(table==null){
			return validateColumn(column);
		}
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
		if(tableName==null){
			return getType(columnName);
		}
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
			columns.add(new Column(tableName, columnName));
		}
		return columns;
	}

	public URL getTablePath(String tableName) {
		tableName = tableName.toUpperCase();
		return paths.get(tableName);
	}

	public boolean isForeignKey(String table1, String column1, 
			String table2, String column2) {
		if(!foreignKeys.containsKey(table1))
			return false;
		
		if(!foreignKeys.get(table1).containsKey(column1)){
			return false;
		}
		
		String [] reference = foreignKeys.get(table1).get(column1);
		return reference[0].equals(table2) && reference[1].equals(column2);
	}

	public Set<String> getTables() {
		return this.schema.keySet();
	}

	public Set<String> getColumns(String table) {
		return schema.get(table).keySet();
	}

}
