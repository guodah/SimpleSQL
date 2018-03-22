package org.simplesql.relational_algebra;

import java.util.ArrayList;

import java.util.List;


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
}
