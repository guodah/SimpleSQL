package org.simplesql.iterators;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.simplesql.SimpleSQL;
import org.simplesql.resolve.SchemaResolver;

public class CSVScanIteraor implements ScanIterator{
	
	private String[]fieldNames;
	private Scanner scanner;
	private String tableName;
	private URL csvFile;
	public CSVScanIteraor(URL csvFile, String tableName) throws IOException{
		this.csvFile = csvFile; 
		this.tableName = tableName;		

		scanner = new Scanner(this.csvFile.openStream());
		String line = scanner.nextLine();
		fieldNames = line.split(",");
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
			row.put(tableName+"."+fieldNames[i], SimpleSQL.getSchemaResolver().parseValue(tableName, fieldNames[i], values[i]));
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
