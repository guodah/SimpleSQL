package org.simplesql;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.simplesql.relational_algebra.RANode;
import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.rules.ConvertRequest;
import org.simplesql.relational_algebra.rules.ProjectColumnPruneRule;
import org.simplesql.relational_algebra.rules.RARule;

public class QueryOptimizer {
	private List<Class<? extends RARule>> rules;
	private Relation root;
	public QueryOptimizer(){
		rules = new ArrayList<>();
		rules.add(ProjectColumnPruneRule.class);
		root = null;
	}
	
	public void setRoot(Relation root){
		this.root = root;
	}
	
	public Relation optimize(){
		ConvertRequest request = new ConvertRequest(root);
		for(Class<?extends RARule> rule:rules){
			RARule ruleInstance = buildRARule(rule, request);
			ruleInstance.excute();
		}
		return root;
	}
	
	private RARule buildRARule(Class<?extends RARule> rule, ConvertRequest request){
		try {
			RARule ruleInstance = rule.getConstructor(ConvertRequest.class).newInstance(request);
			return ruleInstance;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new IllegalStateException("Unable to find the RARule: "+rule);
		}
	}
}
