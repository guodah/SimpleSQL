package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.resolve.SchemaResolver;

public class GroupBy implements RANode{
	private List<Column> columns;
	private List<Aggregate<?>> aggregates;
	
	
	
	public GroupBy(){
		columns = new ArrayList<>();
	}
	public GroupBy(Column column){
		this();
		columns.add(column);
	}
	
	public void setAggregates(List<Aggregate<?>> list){
		this.aggregates = list;
	}
	
	public List<Aggregate<?>> getAggregates(){
		return aggregates;
	}
	
	public void addColumn(Column column){
		columns.add(column);
	}
	
	public List<Column> getColumns(){
		return columns;
	}
	
	public boolean resolve(Relation dataSource, SchemaResolver resolver, 
			OutputStream output){
		for(Column column:columns){
			if(!column.resolve(dataSource, output)){
				return false;
			}
		}
		
		for(Aggregate aggregate:aggregates){
			if(!aggregate.resolve(dataSource, output)){
				return false;
			}
		}
		return true;
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
	
	@Override
	public Set<Expression<?>> getReferencedColumns(){
		return new HashSet<>(columns);
	}
	@Override
	public void replaceWith(Column c1, Column c2) {
		for(Expression<?> expression:aggregates){
			expression.replaceWith(c1, c2);
		}
		
		for(Column each:columns){
			each.replaceWith(c1, c2);
		}
	}
}
