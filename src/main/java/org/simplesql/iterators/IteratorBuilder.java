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

	
	public static ProjectIterator buildCSVProjectIterator(Project project, SchemaResolver resolver, boolean sort)
		throws IOException{
		Iterator<Row> iterator = buildDataSourceIterator(project.getDataSource(), resolver, sort);
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
			SchemaResolver resolver, boolean sort) throws IOException {
		if(dataSource instanceof Table){
			String tableName = ((Table) dataSource).tableName();
			return new CSVScanIteraor(resolver, resolver.getTablePath(tableName), tableName);
		}else if(dataSource instanceof Join){
			Join join = (Join) dataSource;
			Iterator<Row> left = buildDataSourceIterator(join.getLeft(), resolver, sort);
			Iterator<Row> right = buildDataSourceIterator(join.getRight(), resolver, sort);
			
			return JoinIteratorBuilder.
					newInstance().
					useSort(sort).
					setJoin(join).
					setDataSourceIterator(left, right).
					setResolver(resolver).build();
		}else{
			throw new IllegalStateException("unsupported type of data source");
		}
	}
}
