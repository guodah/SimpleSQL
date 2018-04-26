package org.simplesql.iterators;

import java.io.IOException;

import java.net.URL;

import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.SimpleProject;
import org.simplesql.SimpleSQL;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.NaturalJoin;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Table;
import org.simplesql.resolve.SchemaResolver;

public class IteratorBuilder {

	
	public static ProjectIterator buildRelationIterator(Project project, boolean sort)
			throws IOException{
		Iterator<Row> iterator = buildRelationIterator(project.getRelation(), sort);
		if(project.getFilter()!=null){
			FilterIterator filterIterator = new FilterIterator(iterator, project.getFilter());
			iterator = filterIterator;
		}
			
		if(project.getGroupBy()!=null){
			iterator = new GroupByIterator(project.getGroupBy(), iterator);
		}
			
		return new ProjectIterator(project, iterator);	
	}


	private static Iterator<Row> buildRelationIterator(Relation relation,  boolean sort) throws IOException {
		if(relation instanceof Table){
			String tableName = ((Table) relation).tableName();
			return new CSVScanIteraor(SimpleSQL.getSchemaResolver().getTablePath(tableName), tableName);
		}else if(relation instanceof Join){
			Join join = (Join) relation;
			Iterator<Row> left = buildRelationIterator(join.getLeft(), sort);
			Iterator<Row> right = buildRelationIterator(join.getRight(), sort);
			
			return JoinIteratorBuilder.
					newInstance().
					useSort(sort).
					setJoin(join).
					setDataSourceIterator(left, right).build();
		}else if(relation instanceof Project){
			return buildRelationIterator((Project)relation, sort);
		}else if(relation instanceof SimpleProject){
			return new SubsetCSVScanIterator((SimpleProject)relation);
		}else{
			throw new IllegalStateException("unsupported type of data source");
		}
	}
}
