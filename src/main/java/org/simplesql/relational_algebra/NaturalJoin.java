package org.simplesql.relational_algebra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.resolve.SchemaResolver;

public class NaturalJoin extends ConditionedJoin{

	public NaturalJoin(Relation left, Relation right) {
		super(left, right, true, findCondition(left, right));
	}

	private static BooleanBinaryExpression findCondition(
			Relation left, Relation right) {
		List<? extends Expression<?>> leftColumns = left.getColumns();
		List<? extends Expression<?>> rightColumns = right.getColumns();
		
		BooleanBinaryExpression result = null;
		for(Expression<?> lc:leftColumns){
			for(Expression<?> rc:rightColumns){
				if(lc.getSimpleName().equals(rc.getSimpleName())){
					if(result==null){
						result = new BooleanBinaryExpression(lc, "=", rc);
					}else{
						result = new BooleanBinaryExpression(result, "AND", 
								new BooleanBinaryExpression(lc, "=", rc));
					}
					break;
				}
			}
		}
		return result;
	}

	public List<Expression<?>> findCommonColumns(){
		List<? extends Expression<?>> leftColumns = left.getColumns();
		List<? extends Expression<?>> rightColumns = right.getColumns();
		
		List<Expression<?>> res = new ArrayList<Expression<?>>();
		for(Expression<?> lc:leftColumns){
			for(Expression<?> rc:rightColumns){
				if(lc.getSimpleName().equals(rc.getSimpleName())){
					res.add(lc);
					break;
				}
			}
		}
		return res;
	}

	@Override
	public List<Expression<?>> getColumns() {
		List<? extends Expression<?>> leftColumns = left.getColumns();
		List<? extends Expression<?>> rightColumns = right.getColumns();
		List<Expression<?>> res = new ArrayList<>(leftColumns);

		for(Expression<?> rc:rightColumns){
			if(!leftColumns.contains(rc)){
				res.add(rc);
			}
		}
		return res;
	}
	
	@Override
	public String toString(){
		return String.format(" (%s) NATURAL JOIN (%s)", left, right);
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		List<? extends Expression<?>> leftColumns = left.getColumns();
		for(Expression<?> each:leftColumns){
			Table table = each.locateColumn(column);
			if(table!=null){
				return table;
			}
		}

		List<? extends Expression<?>> rightColumns = right.getColumns();
		for(Expression<?> each:rightColumns){
			Table table = each.locateColumn(column);
			if(table!=null){
				return table;
			}
		}
		return null;
	}

	@Override
	public Set<Expression<?>> getReferencedColumns() {
		List<Expression<?>> commonColumns = findCommonColumns();
		Set<String> commonSimpleNames = new HashSet<>();
		for(Expression<?> each:commonColumns){
			commonSimpleNames.add(each.getSimpleName());
		}

		Set<Expression<?>> result = new HashSet<>();
		for(Expression<?> each:left.getColumns()){
			if(each instanceof Column && 
					commonSimpleNames.contains(each.getSimpleName())){
				result.add((Column)each);
			}
		}
		for(Expression<?> each:right.getColumns()){
			if(each instanceof Column && 
					commonSimpleNames.contains(each.getSimpleName())){
				result.add((Column)each);
			}
		}
		return result;
	}
}
