package org.simplesql.iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.LiteralValue;
import org.simplesql.relational_algebra.NaturalJoin;
import org.simplesql.resolve.SchemaResolver;

class SortKey implements Comparable<SortKey>{
	private List<LiteralValue<?>> lst;
	
	public SortKey(List<LiteralValue<?>> lst){
		this.lst = lst;
	}
	
	@Override
	public int compareTo(SortKey key) {
		if(this.lst.size()!=key.lst.size()){
			throw new IllegalStateException("two lists are not equally long");
		}
		
		for(int i=0;i<this.lst.size();i++){
			int compareCode = Expression.compare(this.lst.get(i), key.lst.get(i), null);
			if(compareCode!=0){
				return compareCode;
			}
		}
		return 0;
	}
	
	@Override
	public boolean equals(Object key){
		return (key instanceof SortKey) &&
			compareTo((SortKey) key)==0;
	}
	
	@Override
	public int hashCode(){
		return lst.toString().hashCode();
	}
	
	@Override 
	public String toString(){
		return lst.toString();
	}
}
public class NaturalJoinSortIterator extends JoinIterator{
	private List<Column> commonColumns;
	private java.util.Iterator<Row> joinResultIterator;
	private TreeMap<SortKey, List<Row>> buckets;
	private Iterator<Row> left, right;
	private SchemaResolver resolver;
	public NaturalJoinSortIterator(NaturalJoin operator, Iterator<Row> left, 
				Iterator<Row> right, SchemaResolver resolver) {
		super(operator, left, right, resolver);
		this.left = left;
		this.right = right;
		this.resolver = resolver;
		commonColumns = operator.findCommonColumns(resolver);
		buckets = new TreeMap<>();
		List <Row> joinResult = new ArrayList<>();
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
		List<Row> result = new ArrayList<>();
		List<Row> rows = new ArrayList<>();
		SortKey key = getKey(row);
		if(buckets.containsKey(key)){
			rows = buckets.get(key);
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
		for(Column each:commonColumns){
			if(each.getColumn().equals(field)){
				return true;
			}
		}
		return false;
	}

	private void buildBuckets() {
		while(left.hasNext()){
			Row row = left.next();
			SortKey key = getKey(row);
			if(!buckets.containsKey(key)){
				buckets.put(key, new ArrayList<>());
			}
//			System.out.println(buckets.containsKey(key));
			buckets.get(key).add(row);
		}
	}
	
	private SortKey getKey(Row row) {
		List<LiteralValue<?>> values = new ArrayList<>();
		for(Column each:commonColumns){
			values.add(row.getValueWithoutTable(each.getColumn()));
		}
		return new SortKey(values);
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
