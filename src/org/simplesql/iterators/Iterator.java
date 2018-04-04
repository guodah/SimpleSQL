package org.simplesql.iterators;

public interface Iterator<T> {
	void reset();
	boolean hasNext();
	T next();
}	
