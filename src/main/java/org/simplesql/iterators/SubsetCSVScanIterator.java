package org.simplesql.iterators;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.simplesql.SimpleSQL;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.SimpleProject;

public class SubsetCSVScanIterator implements Iterator<Row>{
	private String[]fieldNames;
	private Scanner scanner;
	private String tableName;
	private URL csvFile;
	private Set<String> subsetFields;
	public SubsetCSVScanIterator(SimpleProject simpleProject) throws IOException{
		subsetFields = findSubset(simpleProject);
		tableName = simpleProject.getTable().tableName();
		this.csvFile = SimpleSQL.getSchemaResolver().getTablePath(tableName); 
				
		scanner = new Scanner(this.csvFile.openStream());
		String line = scanner.nextLine();
		fieldNames = line.split(",");
		for(int i=0;i<fieldNames.length;i++){
			fieldNames[i] = fieldNames[i].toUpperCase();
		}
	}

	private Set<String> findSubset(SimpleProject simpleProject) {
		Set<String> result = new HashSet<>();
		for(Expression<?> each:simpleProject.getColumns()){
			if(!(each instanceof Column)){
				throw new IllegalStateException("SimpleProject only supposed to"
						+ " have actually columns");
			}else{
				result.add(((Column)each).getSimpleName());
			}
		}
		return result;
	}

	@Override
	public boolean hasNext() {
		if(!scanner.hasNext()){
			scanner.close();
			return false;
		}
		return true;
	}

	@Override
	public Row next() {
		String[] values = scanner.nextLine().split(",");
		Row row = new Row(tableName);
		for(int i=0;i<values.length;i++){
			if(!subsetFields.contains(fieldNames[i])) continue;
			row.put(tableName+"."+fieldNames[i], 
					SimpleSQL.
						getSchemaResolver().
						parseValue(tableName, fieldNames[i], values[i]));
		}
		return row;
	}
	@Override
	public void reset() {
		try{
			scanner = new Scanner(csvFile.openStream());
		}catch(IOException e){
			throw new IllegalStateException("Unable to reopen "+ csvFile);
		}
	}

}
