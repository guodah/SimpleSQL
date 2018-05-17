package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class Filter implements RANode{
	private BooleanBinaryExpression expression;
	
	public Filter(BooleanBinaryExpression expression){
		setExpression(expression);
	}
	
	public Filter(){
		
	}
	
	public void findTransitiveCondition(Map<Expression<?>, Set<Expression<?>>> alias){
		expression.findTransitiveCondition(alias);
	}
	
	public void setExpression(BooleanBinaryExpression expression){
		this.expression = expression;
	}
	
	public BooleanBinaryExpression getExpression(){
		return expression;
	}
	
	public String toString(){
		return String.format(" WHERE %s", expression);
	}
	
	public boolean evaluate(Row row){
		return expression.evaluate(row);
	}
	
	public boolean resolve(Relation dataSource, SchemaResolver resolver, 
			OutputStream output){
		return expression.resolve(dataSource, output);
	}

	@Override
	public Set<Expression<?>> getReferencedColumns() {
		return expression.getReferencedColumns();
	}
	
	/*
	 * Attempts to split the expression based on the reference tables. Partitioning is
	 * impossible if
	 * 1) any condition references more than one table
	 * 2) any logic operator is not AND
	 */
	public Map<String, Filter> simplePartition(){
		if(!expression.isConjunctiveAndSimple()) return null;
		
		Map<String, List<BooleanBinaryExpression>> partitions = new HashMap<>();
		traverse(expression, partitions);
		Map<String, Filter> result = new HashMap<>();
		for(String table:partitions.keySet()){
			result.put(table, makeFilter(partitions.get(table)));
		}
		return result;
	}

	private Filter makeFilter(List<BooleanBinaryExpression> list) {
		BooleanBinaryExpression condition = makeCondition(list);
		return new Filter(condition);
	}

	private BooleanBinaryExpression makeCondition(List<BooleanBinaryExpression> list) {
		BooleanBinaryExpression result = list.get(0);
		for(int i=1;i<list.size();i++){
			result = new BooleanBinaryExpression(result, "AND",list.get(i));
		}
		return result;
	}

	private void traverse(BooleanBinaryExpression expression, 
			Map<String, List<BooleanBinaryExpression>> partitions) {
		if(expression.isSinglePredicate()){
			Table table = expression.getReferencedTables().get(0);
			if(!partitions.containsKey(table.tableName())){
				partitions.put(table.tableName(), new ArrayList<>());
			}
			partitions.get(table.tableName()).add(expression);
		}else{
			traverse((BooleanBinaryExpression) expression.getLeft(), partitions);
			traverse((BooleanBinaryExpression) expression.getRight(), partitions);
		}
	}

	public void AND(Filter filter) {
		if(filter==null || filter.getExpression()!=null){
			return;
		}
		expression = new BooleanBinaryExpression(expression, "AND", filter.getExpression());
	}

	@Override
	public void replaceWith(Column c1, Column c2) {
		expression.replaceWith(c1, c2);
	}
	
	
}
