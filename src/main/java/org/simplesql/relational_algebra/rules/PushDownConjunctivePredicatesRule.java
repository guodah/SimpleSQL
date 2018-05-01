package org.simplesql.relational_algebra.rules;

import java.util.Map;

import org.simplesql.relational_algebra.BooleanBinaryExpression;
import org.simplesql.relational_algebra.Filter;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.Table;

public class PushDownConjunctivePredicatesRule extends RARule{

	private Map<String, Filter> filters;
	public PushDownConjunctivePredicatesRule(ConvertRequest request) {
		super(request);
		filters = null;
	}

	@Override
	boolean match() {
		if(!(request.getRANode() instanceof Project)){
			return false;
		}
		
		Project project = (Project) request.getRANode();
		if(!(project.getRelation() instanceof Join)){
			return false;
		}
		
		if(project.getFilter()==null){
			return false;
		}
		
		filters = project.getFilter().simplePartition();
		if(filters==null) return false;
		
		return true;
	}

	@Override
	void convert() {
		Project project = ((Project) request.getRANode());
		Relation relation = project.getRelation();
		project.setRelation(process(relation));
		project.setFilter(null);
	}

	private Relation process(Relation relation) {
		// 1. if the joined relation is a table, convert it to 
		//	a project with table and filter
		// 2. if the joined relation is a subquery (project), scan its 
		//	relation with the filters
		//		a) if the project is based on a single table, merge the filter with
		//			its existing filter
		//		b) otherwise, scan its relation
		// 3. if the joined relation is another join, scan its left and right
		//	relations

		if(relation instanceof Table){
			Project project = new Project();
			project.setRelation(relation);
			project.addColumns(relation.getColumns());
			project.setFilter(filters.get(((Table)relation).tableName()));
			return project;
		}else if(relation instanceof Project){
			Project project = (Project) relation;
			return pushProject(project);
		}else if(relation instanceof Join){
			Join join = (Join) relation;
			join.setLeft(process(join.getLeft()));
			join.setRight(process(join.getRight()));
			return join;
		}
		throw new IllegalStateException("unrecognized relation");
	}

	private Relation pushProject(Project project) {
		if(project.getRelation() instanceof Table){
			String tableName = ((Table)project.getRelation()).tableName();
			if(project.getFilter()!=null){
				project.getFilter().AND(filters.get(tableName));
			}else{
				project.setFilter(filters.get(tableName));
			}
		}else{
			project.setRelation(process(project.getRelation()));
		}
		return project;
	}
}
