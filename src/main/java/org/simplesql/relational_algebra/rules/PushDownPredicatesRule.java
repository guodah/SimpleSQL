package org.simplesql.relational_algebra.rules;

import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Relation;

public class PushDownPredicatesRule extends RARule{

	public PushDownPredicatesRule(ConvertRequest request) {
		super(request);
	}

	@Override
	boolean match() {
		return request.getRANode() instanceof Project;
	}

	@Override
	void convert() {
		if(!match()) return;
		
		// process lower project operators
		Project project = (Project)request.getRANode();
		project.setRelation(process(project.getRelation()));
		
		// process itself
		ConvertRequest request = new ConvertRequest(project);
		PushDownConjunctivePredicatesRule rule = 
				new PushDownConjunctivePredicatesRule(request);
		rule.excute(); // this project's filter

	}

	private Relation process(Relation relation) {
		if(relation instanceof Project){
			ConvertRequest request = new ConvertRequest(relation);
			PushDownConjunctivePredicatesRule rule = 
					new PushDownConjunctivePredicatesRule(request);
			rule.excute(); // this project's filter
		}else if(relation instanceof Join){
			Join join = (Join)relation;
			join.setLeft(process(join.getLeft()));
			join.setRight(process(join.getRight()));
		}
		
		return relation;
	}

}
