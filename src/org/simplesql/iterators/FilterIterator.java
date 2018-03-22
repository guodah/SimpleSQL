package org.simplesql.iterators;

import org.simplesql.relational_algebra.Filter;

public class FilterIterator implements Iterator<Row>{
	private Iterator<Row> downStream;
	private Filter filter;
	private Row nextRow;
	public FilterIterator(Iterator<Row> downStream, Filter filter){
		this.downStream = downStream;
		this.filter = filter;
	}
	
	@Override
	public boolean hasNext() {
		nextRow = null;
		while(downStream.hasNext()){
			nextRow = downStream.next();
			if(filter.evaluate(nextRow)){
				return true;
			}else{
				nextRow = null;
			}
		}
		return false;
	}

	@Override
	public Row next() {
		return nextRow;
	}

}
