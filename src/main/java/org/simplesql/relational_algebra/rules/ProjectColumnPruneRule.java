package org.simplesql.relational_algebra.rules;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.simplesql.relational_algebra.Aggregate;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.SimpleProject;
import org.simplesql.relational_algebra.Table;

public class ProjectColumnPruneRule extends RARule{

	public ProjectColumnPruneRule(ConvertRequest request) {
		super(request);
	}

	@Override
	boolean match() {
		return request.getRANode() instanceof Project;		
	}

	@Override
	void convert() {
		if(starColumnExists()){
			// all columns need, unable to prune any column
			return;
		}
		
		// collect all the referenced columns in project, 
		// filter, aggregate, relation, and group by
		Set<Column> referencedColumns = new HashSet<>(
				request.getRANode().getReferencedColumns());
		
		/*
		 * Prune any columns not references from the relation
		 * 1. if the relation is a Table, change it to a SimpleProject, 
		 * 		leaving out unreferenced columns
		 * 2. if the relation is a Join, continue checking the joined relations
		 * 3. if the relation is a Project, prune the unreferenced columns from
		 *      the projected array of columns
		 */
		Relation relation = ((Project)request.getRANode()).getRelation();
		relation = prune(relation, referencedColumns);
		((Project)request.getRANode()).setRelation(relation);
	}

	private Relation prune(Relation relation, Set<Column> referencedColumns) {
		if(relation instanceof Table){
			return pruneTable((Table)relation,referencedColumns);
		}else if(relation instanceof Join){
			return pruneJoin((Join)relation,referencedColumns);
		}else if(relation instanceof Project){
			return pruneProject((Project)relation,referencedColumns);
		}else{
			return relation;
		}
	}

	private Relation pruneJoin(Join relation, Set<Column> referencedColumns) {
		referencedColumns.addAll(relation.getReferencedColumns());
		relation.setLeft(prune(relation.getLeft(),referencedColumns));
		relation.setRight(prune(relation.getRight(),referencedColumns));
		referencedColumns.removeAll(relation.getReferencedColumns());
		return relation;
	}

	private Relation pruneProject(Project relation, Set<Column> referencedColumns) {
		referencedColumns.addAll(relation.getReferencedColumns());
		for(Expression<?> column:new ArrayList<>(relation.getColumns())){
			if(column instanceof Column && !referencedColumns.contains(column)){
				relation.removeColumn(column);
			}
		}
		referencedColumns.removeAll(relation.getReferencedColumns());
		return relation;
	}

	private Relation pruneTable(Table relation, Set<Column> referencedColumns) {
		
		Set<Expression<?>> referenced = new HashSet<>();
		for(Expression<?> column:relation.getColumns()){
			if(referencedColumns.contains(column)){
				referenced.add(column);
			}
		}
		if(referenced.size()==relation.getColumns().size()){
			return relation; // all columns needed, no pruning
		}else{
			SimpleProject project = new SimpleProject(relation);
			for(Expression<?> column:referenced){
				project.addColumn((Column)column);
			}
			return project;
		}
	}

	private boolean starColumnExists() {
		List<Expression<?>> columns = ((Project)request.getRANode()).getColumns();
		for(Expression<?> column:columns){
			if(column.getFullName().equals("*")){
				return true;
			}
		}
		
		List<Aggregate<?>> aggregates = ((Project)request.getRANode()).getAggregates();
		for(Aggregate<?> aggregate:aggregates){
			if(aggregate.getColumn().getFullName().equals("*")){
				return true;
			}
		}
		return false;
	}
}
