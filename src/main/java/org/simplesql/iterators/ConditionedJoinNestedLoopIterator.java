package org.simplesql.iterators;

import java.util.ArrayList;
import java.util.List;

import org.simplesql.relational_algebra.ConditionedJoin;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.NullValue;
import org.simplesql.resolve.SchemaResolver;

// right now, it performs nested-loop
public class ConditionedJoinNestedLoopIterator extends JoinIterator{
	private List<Row> leftRows;
	private List<Row> rightRows;
	private java.util.Iterator<Row> joinResultIterator;
	public ConditionedJoinNestedLoopIterator(ConditionedJoin operator, 
				Iterator<Row> left, Iterator<Row> right) {
		super(operator, left, right);
		
		// loading the entirety of left and right
		leftRows = new ArrayList<>();
		rightRows = new ArrayList<>();
		
		List<Row> joinResult = new ArrayList<>();
		
		while(left.hasNext()){
			leftRows.add(left.next());
		}
		while(right.hasNext()){
			rightRows.add(right.next());
		}
		
		for(Row row1:leftRows){
			boolean matched = false;
			for(Row row2:rightRows){
				if(operator.evaluateJoinCondition(row1, row2)){
					joinResult.add(Row.combine(row1, row2));
					matched = true;
				}
			}
			
			if(!matched && !operator.isInnerJoin()){
				for(Expression<?> each:operator.getRight().getColumns()){
					row1.put(each.getFullName(), NullValue.NULL);
				}
				joinResult.add(row1);
			}
		}
		
		joinResultIterator = joinResult.iterator();
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
