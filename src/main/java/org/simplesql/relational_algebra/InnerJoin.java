package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

public class InnerJoin extends Join{
	private BooleanBinaryExpression joinCondition;


	public InnerJoin(DataSource left, DataSource right, BooleanBinaryExpression joinCondition) {
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
				&& joinCondition.resolve(this, resolver, output); 
	}

	@Override
	public List<Column> getColumns(SchemaResolver resolver) {
		List<Column> columns = left.getColumns(resolver);
		columns.addAll(right.getColumns(resolver));
		return columns;
	}
	
	public String toString(){
		return String.format("%s INNER JOIN %S ON %s", left, right, joinCondition.toString());
	}

	@Override
	public Table findColumn(String column, SchemaResolver resolver) {
		List<Column> columns = getColumns(resolver);
		Table table = null;
		for(Column each:columns){
			if(each.getColumn().equals(column)){
				if(table!=null){
					return null;
				}else{
					table = new Table(each.getTableName());
				}
			}
		}
		return table;
	}

}
