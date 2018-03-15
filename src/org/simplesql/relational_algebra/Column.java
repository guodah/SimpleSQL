package org.simplesql.relational_algebra;

public class Column extends Expression<String>{
	private String columnName;
	public Column(String columnName){
		this.columnName = columnName;
	}
	
	public String toString(){
		return columnName;
	}

	@Override
	public String evaluate(Context ctx) {
		// ctx unused
		return toString();
	}
}
