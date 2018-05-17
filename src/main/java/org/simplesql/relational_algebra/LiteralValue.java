package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;


public abstract class LiteralValue<T extends Comparable<T>> extends Expression<Comparable<T>> {
	private boolean isNull;

	
	public LiteralValue(boolean isNull){
		this.isNull = isNull;
	}
	
	public boolean isNull(){
		return isNull;
	}
	
	public String toString(){
		return isNull?"null":"";
	}
	
	public boolean resolve(Relation dataSource, OutputStream output){
		return true;
	}
	
	@Override
	public boolean isSimple(){
		return true;
	}
	
	@Override
	public List<Table> getReferencedTables(){
		return new ArrayList<>();
	}
	

	@Override
	public boolean containsLiterals() {
		return true;
	}
	@Override
	public void replaceWith(Column c1, Column c2) {
	}	

}
