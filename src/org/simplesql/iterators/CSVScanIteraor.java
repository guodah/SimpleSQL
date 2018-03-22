package org.simplesql.iterators;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.simplesql.resolve.SchemaResolver;

public class CSVScanIteraor implements ScanIterator{
	
	private String[]fieldNames;
	private Scanner scanner;
	private SchemaResolver resolver;
	private String tableName;
	public CSVScanIteraor(SchemaResolver resolver, URL csvFile, String tableName) throws IOException{
		scanner = new Scanner(csvFile.openStream());
		String line = scanner.nextLine();
		fieldNames = line.split(",");
		this.tableName = tableName;		
		this.resolver = resolver;
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
		Row row = new Row(tableName, resolver);
		for(int i=0;i<values.length;i++){
			row.put(fieldNames[i], resolver.parseValue(tableName, fieldNames[i], values[i]));
		}
		return row;
	}

	
}
