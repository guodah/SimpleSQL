package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class ConditionedJoin extends Join{
	private BooleanBinaryExpression joinCondition;
	private boolean inner; // true: inner join, false: right join

	public ConditionedJoin(Relation left, Relation right, boolean inner, BooleanBinaryExpression joinCondition) {
		super(left, right);
		this.joinCondition = joinCondition;
		this.inner = inner; 
		
		if(joinCondition==null){
			throw new IllegalStateException("inner join without condition not supported");
		}
	}

	public Map<Expression<?>, Set<Expression<?>>> findAlias(){
		return joinCondition.findAlias();
	}
	
	public boolean isEquiJoin(){
		return joinCondition.isEqui();
	}
	
	public boolean isInnerJoin(){
		return inner;
	}
	
	public BooleanBinaryExpression getJoinCondition(){
		return joinCondition;
	}
	
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return super.resolve(resolver, output) 
				&& joinCondition.resolve(this, output); 
	}

	@Override
	public List<Expression<?>> getColumns() {
		List<Expression<?>> columns = new ArrayList<>(left.getColumns());
		columns.addAll(right.getColumns());
		return columns;
	}
	
	public String toString(){
		return String.format("(%s) INNER JOIN (%s) ON %s", left, right, joinCondition.toString());
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		List<Expression<?>> columns = getColumns();
		Table table = null;
		for(Expression<?> each:columns){
			Table temp = each.locateColumn(column);
			if(table!=null){
				if(temp!=null){
					return null;
				}
			}else{
				table = temp;
			}
		}
		return table;
	}

	public boolean evaluateJoinCondition(Row row1, Row row2){
		Row superRow = Row.combine(row1, row2);
		return this.joinCondition.evaluate(superRow);
	}
	
	@Override
	public Set<Column> getReferencedColumns(){
		return joinCondition.getReferencedColumns();
	}
}
