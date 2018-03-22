package org.simplesql.relational_algebra;

import java.util.ArrayList;
import java.util.List;

public class GroupBy {
	private List<Column> columns;
	private List<Aggregate> aggregates;
	
	public GroupBy(){
		columns = new ArrayList<>();
	}
	public GroupBy(Column column){
		this();
		columns.add(column);
	}
	
	public void setAggregates(List<Aggregate> aggregates){
		this.aggregates = aggregates;
	}
	
	public List<Aggregate> getAggregates(){
		return aggregates;
	}
	
	public void addColumn(Column column){
		columns.add(column);
	}
	
	public List<Column> getColumns(){
		return columns;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("GROUP BY ");
		for(int i=0;i<columns.size();i++){
			sb.append(columns.get(i));
			if(i!=columns.size()-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
}
