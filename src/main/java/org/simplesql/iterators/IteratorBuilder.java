package org.simplesql.iterators;

import java.io.IOException;

import java.net.URL;

import org.simplesql.relational_algebra.DataSource;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.NaturalJoin;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Table;
import org.simplesql.resolve.SchemaResolver;

public class IteratorBuilder {

	
	public static ProjectIterator buildCSVProjectIterator(Project project, SchemaResolver resolver)
		throws IOException{
		Iterator<Row> iterator = buildDataSourceIterator(project.getDataSource(), resolver);
		if(project.getFilter()!=null){
			FilterIterator filterIterator = new FilterIterator(iterator, project.getFilter());
			iterator = filterIterator;
		}
		
		if(project.getGroupBy()!=null){
			iterator = new GroupByIterator(project.getGroupBy(), iterator);
		}
		
		return new ProjectIterator(project, iterator);
		
	}

	private static Iterator<Row> buildDataSourceIterator(DataSource dataSource, 
			SchemaResolver resolver) throws IOException {
		if(dataSource instanceof Table){
			String tableName = ((Table) dataSource).tableName();
			return new CSVScanIteraor(resolver, resolver.getTablePath(tableName), tableName);
		}else if(dataSource instanceof Join){
			Join join = (Join) dataSource;
			Iterator<Row> left = buildDataSourceIterator(join.getLeft(), resolver);
			Iterator<Row> right = buildDataSourceIterator(join.getRight(), resolver);
			
			if(join instanceof NaturalJoin){
				return new NaturalJoinItrator((NaturalJoin)join, left, right, resolver);
			}else{
				throw new IllegalStateException("unsupported type of join");
			}
		}else{
			throw new IllegalStateException("unsupported type of data source");
		}
	}
}
