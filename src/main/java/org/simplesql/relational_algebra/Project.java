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
	private boolean topProject;
	
	public Project(){
		this(false);
	}
	
	public Project(boolean topProject){
		aggregates = new ArrayList<>();
		columns = new ArrayList<>();
		groupBy = null;
		this.topProject = topProject;
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
		boolean result = true;
		// resolve the data source
		result = result && relation.resolve(resolver, output);
		
		// resolve the where clause
		if(filter!=null){
			result = result && filter.resolve(relation, resolver, output);
		}
		
		// resolve the project columns
		for(Expression<?> column:columns){
			result = result && column.resolve(relation,  output);
		}
		
		// resolve the group by clause
		result = result && (groupBy==null || groupBy.resolve(relation, resolver, output));
		
		// resolve the aggregate functions
		for(Aggregate<?> aggregate:aggregates){
			result = result && aggregate.resolve(relation,  output);
		}
		return result;
	}

	@Override
	public List<Expression<?>> getColumns() {
		return this.columns;
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		return relation.locateColumnBySimpleName(column);
	}

	@Override
	public Set<Column> getReferencedColumns() {
		Set<Column> result = new HashSet<>();
		if(filter!=null){
			result.addAll(filter.getReferencedColumns());
		}
		
		if(groupBy!=null){
			result.addAll(groupBy.getReferencedColumns());
		}

		if(topProject){
			for(Expression<?> column:columns){
				Set<Column> expr = column.getReferencedColumns();
				if(expr!=null){
					result.addAll(expr);
				}
			}
		}
		return result;
	}

}
