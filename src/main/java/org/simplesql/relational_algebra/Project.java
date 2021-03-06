package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.resolve.SchemaResolver;


public class Project extends Relation{
	private List<Expression<?>> columns;
	private Filter filter;
	private Relation relation;
	private List<Aggregate<?>> aggregates;
	private GroupBy groupBy;
	private boolean isSubquery;
	
	public Project(){
		this(false);
	}
	
	public Project(boolean isSubquery){
		aggregates = new ArrayList<>();
		columns = new ArrayList<>();
		groupBy = null;
		this.isSubquery = isSubquery;
	}
	
	public boolean isSubquery(){
		return isSubquery;
	}
	
	public void addGroupBy(GroupBy groupBy){
		this.groupBy = groupBy;
	}
	
	public void addAggregate(Aggregate<?> aggregate){
		aggregates.add(aggregate);
	}
	
	public void addColumn(Expression<?> column){
		columns.add(column);
	}
	
	public void removeColumn(Expression<?> column){
		columns.remove(column);
	}
	
	public void setRelation(Relation relation){
		this.relation = relation;
	}
	
	public void setFilter(Filter filter){
		this.filter = filter;
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
			for(int i=0;i<aggregates.size();i++){
				
				sb.append(aggregates.get(i).toString());
				if(i!=aggregates.size()-1){
					sb.append(",");
				}
			}
		}
		
		sb.append(" FROM ");
		sb.append("(");
		sb.append(relation);
		sb.append(")");
		if(filter!=null){
			sb.append(filter);
		}
		
		if(this.groupBy!=null){
			sb.append(" "+groupBy);
		}
		return sb.toString();
	}

	public Relation getRelation() {
		return relation;
	}

	public Filter getFilter() {
		return filter;
	}
	
	public GroupBy getGroupBy(){
		return groupBy;
	}
	
	public List<Aggregate<?>> getAggregates(){
		return aggregates;
	}

	public Relation getTable(String table) {
		return relation.findTable(table);
	}
	
	public boolean resolve(SchemaResolver resolver, OutputStream output){
		boolean resolved = true;
		// resolve the data source
		resolved = resolved && relation.resolve(resolver, output);
		
		// resolve the where clause
		if(filter!=null){
			resolved = resolved && filter.resolve(relation, resolver, output);
		}
		
		// resolve the project columns
		boolean starColumn = false;
		for(Expression<?> column:columns){
			resolved = resolved && column.resolve(relation,  output);
			if(column.getFullName().equals("*")){
				starColumn = true;				
			}
		}
		
		// replace the star '*' column with all the real columns
		if(starColumn){
			this.columns.addAll(relation.getColumns());
			this.columns.removeIf(column -> column.getFullName().equals("*"));
		}
		
		// resolve the group by clause
		resolved = resolved && (groupBy==null || groupBy.resolve(relation, resolver, output));
		
		// resolve the aggregate functions
		for(Aggregate<?> aggregate:aggregates){
			resolved = resolved && aggregate.resolve(relation,  output);
		}
		return resolved;
	}

	@Override
	public List<Expression<?>> getColumns() {
		return this.columns;
	}
	
	// given a+b, return a, b, a+b
	public Set<Expression<?>> getAllColumns(){
		Set<Expression<?>> result = new HashSet<>();
		for(Expression<?> column:columns){
			Set<Expression<?>> expr = column.getReferencedColumns();
			if(expr!=null){
				result.addAll(expr);
			}
			result.add(column);
		}
		return result;
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		return relation.locateColumnBySimpleName(column);
	}

	@Override
	public Set<Expression<?>> getReferencedColumns() {
		return getReferencedColumns(true);
	}
	
	public Set<Expression<?>> getReferencedColumns(boolean projectionIncluded) {
		Set<Expression<?>> result = new HashSet<>();
		if(filter!=null){
			result.addAll(filter.getReferencedColumns());
		}
		
		if(groupBy!=null){
			result.addAll(groupBy.getReferencedColumns());
		}

		if(projectionIncluded){
			result.addAll(getAllColumns());
		}
		return result;
	}
	

	public void addColumns(List<Expression<?>> columns) {
		this.columns.addAll(columns);
	}

	@Override
	public void replaceWith(Column c1, Column c2) {
		for(Expression<?> each: getColumns()){
			each.replaceWith(c1, c2);
		}
		
		if(filter!=null){
			filter.replaceWith(c1, c2);
		}
		
		if(groupBy!=null){
			groupBy.replaceWith(c1, c2);
			for(Aggregate each:aggregates){
				each.replaceWith(c1, c2);
			}
		}
	}

}
