package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public abstract class Aggregate<T extends LiteralValue> extends Function<T>
		implements RANode{
	protected Expression<?> column;

	public Aggregate(Expression<?> column){
		this.column = column;
	}
	
	public Expression<?> getColumn(){
		return column;
	}
	
	abstract public Aggregate<?> duplicate();
	abstract public void add(LiteralValue<?> val);
	abstract public T aggregatedValue();

	@Override
	public boolean containsLiterals(){
		return column.containsLiterals();
	}
	
	public boolean resolve(Relation relation, OutputStream output){
		return column.resolve(relation, output); 
	}
	
	@Override
	public Set<Expression<?>> getReferencedColumns(){
		return column.getReferencedColumns();		
	}
	
	@Override
	public boolean isSimple(){
		return column.isSimple();
	}
	
	@Override
	public List<Table> getReferencedTables(){
		return column.getReferencedTables();
	}
	
	@Override
	public void replaceWith(Column c1, Column c2) {
		this.column.replaceWith(c1, c2);
	}

}
