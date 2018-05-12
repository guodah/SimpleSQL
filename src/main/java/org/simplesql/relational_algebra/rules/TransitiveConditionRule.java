package org.simplesql.relational_algebra.rules;

import java.util.Map;
import java.util.Set;

import org.simplesql.relational_algebra.ConditionedJoin;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.Filter;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Relation;

public class TransitiveConditionRule extends RARule{

	public TransitiveConditionRule(ConvertRequest request) {
		super(request);
	}

	@Override
	boolean match() {
		if(!(request.getRANode() instanceof Project)){
			return false;
		}
		
		Project project = (Project) request.getRANode();
		if(!(project.getRelation() instanceof ConditionedJoin)
				|| project.getFilter()==null){
			return false;
		}
		
		return ((ConditionedJoin)project.getRelation()).isEquiJoin();
	}

	@Override
	void convert() {
		if(!match()) return;
		
		ConditionedJoin join = (ConditionedJoin) 
					((Project)request.getRANode()).getRelation();
		process(join.getLeft());
		process(join.getRight());
		
		Filter filter = ((Project)request.getRANode()).getFilter();
		Map<Expression<?>, Set<Expression<?>>> alias = join.findAlias();
		filter.findTransitiveCondition(alias);
	}

	private void process(Relation relation) {
		// process possible subquery
		TransitiveConditionRule rule = new TransitiveConditionRule(
							new ConvertRequest(relation));
		rule.convert();
	}

}
