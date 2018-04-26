package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

class Null implements Comparable<Null>{
	@Override
	public int compareTo(Null o) {
		return 0;
	}
	
	@Override
	public String toString(){
		return "NULL";
	}
}

public class NullValue extends LiteralValue<Null>{

	public final static NullValue NULL= new NullValue();
	
	public NullValue() {
		super(true);
	}

	@Override
	public Null evaluate(Row ctx) {
		return null;
	}
	@Override
	public String getType() {
		return "NULL";
	}

	@Override
	public int hashCode(){
		return 0;
	}

	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public boolean isBoolean() {
		return false;
	}
	

	@Override
	public String getSimpleName() {
		return "NULL";
	}

	@Override
	public String getFullName() {
		return "NULL";
	}
	@Override
	public Set<Column> getReferencedColumns() {
		return null;
	}	

}
