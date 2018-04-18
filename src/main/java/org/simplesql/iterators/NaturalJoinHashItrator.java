package org.simplesql.iterators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.NaturalJoin;
import org.simplesql.resolve.SchemaResolver;

/**
 * Loads the left data source to the memory first and build a hash map
 */
public class NaturalJoinHashItrator extends JoinIterator {
	private java.util.Iterator<Row> joinResultIterator;
	private Map<Object, Object> map;
	private List<Expression<?>> commonColumns;
	public NaturalJoinHashItrator(NaturalJoin operator, Iterator<Row> left, Iterator<Row> right) {
		super(operator, left, right);
		map = new HashMap<>();
		commonColumns = operator.findCommonColumns();
		List<Row> joinResult = new ArrayList<Row>();
		if(commonColumns.size()!=0){
			buildBuckets();
			findJoinResult(joinResult);
		}
		joinResultIterator = joinResult.iterator();
	}

	private void findJoinResult(List<Row> joinResult) {
		while(right.hasNext()){
			List<Row> rows = join(right.next());
			if(rows.size()!=0){
				joinResult.addAll(rows);
			}
		}
	}

	private List<Row> join(Row row) {
		Map<Object, Object> tmap = map;
		List<Row> rows = null;
		List<Row> result = new ArrayList<>();
		for(int i=0;i<commonColumns.size();i++){
			String column = commonColumns.get(i).getSimpleName();
			
			Object value = row.getValueWithoutTable(column);
			
			if(!tmap.containsKey(value)){
				return result;
			}else{
				if(i==commonColumns.size()-1){
					rows = (List<Row>)(tmap.get(value));
				}else{
					tmap = (Map<Object, Object>)tmap.get(value);
				}
			}
		}
		
		for(Row each:rows){
			result.add(join(each, row));
		}
		return result;
	}

	private Row join(Row row1, Row row2) {
		Row row = new Row();
		for(String field:row1.getFieldNames()){
			row.put(field, row1.get(field));
		}
		
		for(String field:row2.getFieldNames()){
			if(!isACommonColumn(field)){
				row.put(field, row2.get(field));
			}
		}
		return row;
	}
	private boolean isACommonColumn(String field) {
		int dotPosition = field.indexOf('.');
		if(dotPosition<0){
			throw new IllegalStateException("row header does not contain table name");
		}
		field = field.substring(dotPosition+1);
		for(Expression<?> each:commonColumns){
			if(each.getSimpleName().equals(field)){
				return true;
			}
		}
		return false;
	}

	
	private void buildBuckets(){
		while(left.hasNext()){
			buildBuckets(left.next());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buildBuckets(Row row) {
		Map<Object, Object> tmap = map;
		
		for(int i=0;i<commonColumns.size();i++){
			String column = commonColumns.get(i).getSimpleName();
			Object value = row.getValueWithoutTable(column);
		
			if(!tmap.containsKey(value)){
				if(i==commonColumns.size()-1){
					List<Row> rows = new ArrayList<>();
					rows.add(row);
					tmap.put(value, rows);
				}else{
					Map<Object, Object> vmap = new HashMap<Object, Object>();
					tmap.put(value, vmap);
					tmap = vmap;
				}
			}else{
				if(i==commonColumns.size()-1){
					((List<Row>)tmap.get(value)).add(row);
				}else{
					tmap = (Map<Object, Object>) tmap.get(value);
				}
			}
		}
	}


	@Override
	public boolean hasNext() {
		return joinResultIterator.hasNext();
	}

	@Override
	public Row next() {
		return joinResultIterator.next();
	}

}
