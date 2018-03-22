package org.simplesql.iterators;

import java.util.HashSet;
import java.util.Set;

import org.simplesql.relational_algebra.Aggregate;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Project;

public class ProjectIterator implements Iterator<Row>{

	private Project project;
	private Iterator<Row> downStream;
	
	public ProjectIterator(Project project, Iterator<Row> downStream){
		this.project = project;
		this.downStream = downStream;
	}
	
	@Override
	public boolean hasNext() {
		return downStream.hasNext();
	}

	@Override
	public Row next() {
		Row row = downStream.next();
		Row res = new Row();
		
		for(Column each:project.getColumns()){
			String columnName = each.evaluate(null);
			if(row.containsField(columnName)){
				res.put(each.evaluate(null), row.get(columnName));
			}
		}
		
		for(Aggregate each:project.getAggregates()){
			String columnName = each.toString();
			if(row.containsField(columnName)){
				res.put(columnName, row.get(columnName));
			}
		}
		
		return res;
	}

}
