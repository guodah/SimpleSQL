package org.simplesql.iterators;

import java.util.ArrayList;
import java.util.List;

import org.simplesql.relational_algebra.InnerJoin;
import org.simplesql.relational_algebra.Join;
import org.simplesql.resolve.SchemaResolver;

// right now, it performs nested-loop
public class InnerJoinNestedLoopIterator extends JoinIterator{
	private List<Row> leftRows;
	private List<Row> rightRows;
	private List<Row> resultRows;
	public InnerJoinNestedLoopIterator(InnerJoin operator, Iterator<Row> left, Iterator<Row> right, SchemaResolver resolver) {
		super(operator, left, right, resolver);
		
		// loading the entirety of left and right
		leftRows = new ArrayList<>();
		rightRows = new ArrayList<>();
		
		while(left.hasNext()){
			leftRows.add(left.next());
		}
		while(right.hasNext()){
			rightRows.add(left.next());
		}
		
		for(Row row1:leftRows){
			for(Row row2:rightRows){
				
			}
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Row next() {
		// TODO Auto-generated method stub
		return null;
	}

}
