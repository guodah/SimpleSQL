package org.simplesql.iterators;

import org.simplesql.relational_algebra.DataSource;
import org.simplesql.relational_algebra.InnerJoin;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.NaturalJoin;
import org.simplesql.resolve.SchemaResolver;

public class JoinIteratorBuilder {
	private boolean sort;
	public JoinIteratorBuilder useSort(boolean sort){
		this.sort = sort;
		return this;
	}
	
	private Join join;
	public JoinIteratorBuilder setJoin(Join join){
		this.join = join;
		return this;
	}
	
	private Iterator<Row> left, right;
	public JoinIteratorBuilder setDataSourceIterator(Iterator<Row> left, Iterator<Row> right) {
		this.left = left;
		this.right = right;
		return this;
	}
	
	private SchemaResolver resolver;
	public JoinIteratorBuilder setResolver(SchemaResolver resolver){
		this.resolver = resolver;
		return this;
	}
	public static JoinIteratorBuilder newInstance() {
		return new JoinIteratorBuilder();
	}
	public JoinIterator build() {
		if(join instanceof NaturalJoin){
			if(sort) {
				return new NaturalJoinSortIterator((NaturalJoin)join, left, right, resolver);
			}else {
				return new NaturalJoinHashItrator((NaturalJoin)join, left, right, resolver);
			}
		}else if(join instanceof InnerJoin){
			return new InnerJoinNestedLoopIterator((InnerJoin)join, left, right, resolver);
		}else {
			throw new IllegalStateException("unsupported join "+join.getClass());
		}
	}
	
}
