package org.simplesql.iterators;

import java.io.IOException;
import java.net.URL;
import org.simplesql.relational_algebra.Project;
import org.simplesql.resolve.SchemaResolver;

public class IteratorBuilder {
	public static ProjectIterator buildSingleCSVProjectIterator(Project project, 
			SchemaResolver resolver, URL csvFile, String table) throws IOException{
		CSVScanIteraor csvIterator = new CSVScanIteraor(resolver, csvFile, table);
		if(project.getFilter()!=null){
			FilterIterator filterIterator = new FilterIterator(csvIterator, project.getFilter());
			return new ProjectIterator(project, filterIterator);
		}else{
			return new ProjectIterator(project, csvIterator);
		}
	}
}
