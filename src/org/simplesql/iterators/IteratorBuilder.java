package org.simplesql.iterators;

import java.io.IOException;
import java.net.URL;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;

public class IteratorBuilder {
	public static ProjectIterator buildSingleCSVProjectIterator(Project project, 
			SchemaResolver resolver, URL csvFile, String table) throws IOException{
		
		CSVScanIteraor csvIterator = new CSVScanIteraor(resolver, csvFile, table);
		Iterator<Row> iterator = csvIterator;
		if(project.getFilter()!=null){
			FilterIterator filterIterator = new FilterIterator(csvIterator, project.getFilter());
			iterator = filterIterator;
		}
		
		if(project.getGroupBy()!=null){
			iterator = new GroupByIterator(project.getGroupBy(), iterator);
		}
		
		return new ProjectIterator(project, iterator);
	}
}
