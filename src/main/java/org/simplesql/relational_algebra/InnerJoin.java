package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

public class InnerJoin extends Join{
	private BooleanBinaryExpression joinCondition;


	public InnerJoin(Relation left, Relation right, BooleanBinaryExpression joinCondition) {
		super(left, right);
		this.joinCondition = joinCondition;
		
		if(joinCondition==null){
			throw new IllegalStateException("inner join without condition not supported");
		}
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
		List<Expression<?>> columns = left.getColumns();
		columns.addAll(right.getColumns());
		return columns;
	}
	
	public String toString(){
		return String.format("%s INNER JOIN %S ON %s", left, right, joinCondition.toString());
	}

	@Override
	public Table locateColumn(String column) {
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
}
