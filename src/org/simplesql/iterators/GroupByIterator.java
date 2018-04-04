package org.simplesql.iterators;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import org.simplesql.relational_algebra.Aggregate;
import org.simplesql.relational_algebra.Column;

import org.simplesql.relational_algebra.GroupBy;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.LongValue;
import org.simplesql.relational_algebra.NullValue;


public class GroupByIterator implements Iterator<Row> {
	private Iterator<Row> downStream;
	private GroupBy groupBy;
	private Map<Object, Object> map;
	private java.util.Iterator<Row> rowIterator;
	
		
	public GroupByIterator(GroupBy groupBy, Iterator<Row> downStream){
		this.groupBy = groupBy;
		this.downStream = downStream;
		map = new HashMap<Object, Object>();
		buildGroups();
		findRows();		
	}
	
	
	private void findRows() {
		List<Row> rows = new ArrayList<>();
		navigate(map, map.keySet(), rows, new ArrayList<Object>());
		rowIterator = rows.iterator();
	}


	private void navigate(Map<Object, Object> map, Set<Object> keySet, List<Row> rows, ArrayList<Object> path) {
		for(Object key:keySet){
			Object val = map.get(key);
			path.add(key);
			if(map.get(key) instanceof Map){
				navigate((Map)val, ((Map)val).keySet(), rows, path);
			}else{
//				System.out.println(path);
				
				List<Aggregate> aggregates = (List<Aggregate>) val;
				
				List<Column> columns = groupBy.getColumns();
				
				Row row = new Row();
				for(int i=0;i<path.size();i++){
					row.put(columns.get(i).toString(), (LiteralValue) path.get(i));
				}
				
				for(int i=0;i<aggregates.size();i++){
					row.put(aggregates.get(i).toString(), aggregates.get(i).aggregatedValue());
				}
				rows.add(row);
			}
			path.remove(path.size()-1);
		}
	}


	private void buildGroups() {
		List<Column> columns = groupBy.getColumns();
		List<Aggregate> buckets = groupBy.getAggregates();
		
		while(downStream.hasNext()){
			Row row = downStream.next();
			buildBuckets(columns, row, buckets);
		}
	}

	private void buildBuckets(List<Column> columns, Row row, List<Aggregate> buckets) {
		Map<Object, Object> tmap = map;
		for(int i=0;i<columns.size();i++){
			String field = columns.get(i).toString();
			Object value = row.get(field);
			
			if(!tmap.containsKey(value)){
				if(i==columns.size()-1){
					buckets = duplicate(buckets);
					tmap.put(value, buckets);
				}else{
					Map<Object, Object> vmap = new HashMap<Object, Object>();
					tmap.put(value, vmap);
					tmap = vmap;
				}
			}else{
				if(i==columns.size()-1){
					buckets = (List<Aggregate>) tmap.get(value);
				}else{
					tmap = (Map<Object, Object>) tmap.get(value);
				}
			}
		}
		
		for(int i=0;i<buckets.size();i++){
			String field = buckets.get(i).getColumn().toString();
			if(field.equals("*"))
				buckets.get(i).add(NullValue.NULL);
			else{
				buckets.get(i).add(row.get(field));
			}
		}
	}

	List<Aggregate> duplicate(List<Aggregate> aggregates){
		List<Aggregate> res = new ArrayList<Aggregate>();
		for(Aggregate each:aggregates){
			res.add(each.duplicate());
		}
		return res;
	}

	@Override
	public boolean hasNext() {
		if(map.size()==0) return false;
		
		return rowIterator.hasNext();
	}

	@Override
	public Row next() {
		return rowIterator.next();
	}


	@Override
	public void reset() {
		downStream.reset();
		map = new HashMap<Object, Object>();
		buildGroups();
		findRows();		
	}
}
