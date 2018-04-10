package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;

import java.util.List;

import org.simplesql.resolve.SchemaResolver;


public class Project {
	private List<Column> columns;
	private Filter filter;
	private DataSource dataSource;
	private List<Aggregate> aggregates;
	private GroupBy groupBy;
	
	public Project(){
		aggregates = new ArrayList<>();
		columns = new ArrayList<>();
		groupBy = null;
	}
	
	public void addGroupBy(GroupBy groupBy){
		this.groupBy = groupBy;
	}
	
	public void addAggregate(Aggregate aggregate){
		aggregates.add(aggregate);
	}
	
	public void addColumn(Column column){
		columns.add(column);
	}
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void setFilter(Filter filter){
		this.filter = filter;
	}
	
	public List<Column> getColumns(){
		return columns;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for(int i=0;i<columns.size();i++){
			sb.append(columns.get(i));
			if(i!=columns.size()-1){
				sb.append(", ");
			}
		}
		
		if(aggregates!=null){
			sb.append(",");
			for(int i=0;i<aggregates.size();i++){
				
				sb.append(aggregates.get(i).toString());
				if(i!=aggregates.size()-1){
					sb.append(",");
				}
			}
		}
		
		sb.append(" FROM ");
		sb.append(dataSource);
		if(filter!=null){
			sb.append(filter);
		}
		
		if(this.groupBy!=null){
			sb.append(" "+groupBy);
		}
		sb.append(";");
		return sb.toString();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public Filter getFilter() {
		return filter;
	}
	
	public GroupBy getGroupBy(){
		return groupBy;
	}
	
	public List<Aggregate> getAggregates(){
		return aggregates;
	}

	public DataSource getTable(String table) {
		return dataSource.findTable(table);
	}
	
	public boolean resolve(SchemaResolver resolver, OutputStream output){
		boolean result = true;
		
		// resolve the data source
		result = result && dataSource.resolve(resolver, output);
		
		// resolve the where clause
		if(filter!=null){
			result = result && filter.resolve(dataSource, resolver, output);
		}
		
		// resolve the project columns
		for(Column column:columns){
			result = result && column.resolve(dataSource, resolver, output);
		}
		
		// resolve the group by clause
		result = result && (groupBy==null || groupBy.resolve(dataSource, resolver, output));
		
		// resolve the aggregate functions
		for(Aggregate aggregate:aggregates){
			result = result && aggregate.resolve(dataSource, resolver, output);
		}
		return result;
	}

}
