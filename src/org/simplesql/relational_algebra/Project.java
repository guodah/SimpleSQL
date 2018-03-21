package org.simplesql.relational_algebra;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private List<Column> columns;
	private Filter filter;
	private DataSource dataSource;
	
	public Project(){
		columns = new ArrayList<>();
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
		sb.append(" FROM ");
		sb.append(dataSource);
		if(filter!=null){
			sb.append(filter);
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
}
