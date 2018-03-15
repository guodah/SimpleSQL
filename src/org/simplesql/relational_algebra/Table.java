package org.simplesql.relational_algebra;

public class Table extends DataSource{
	private String tableName;
	public Table(String tableName){
		this.tableName = tableName;
	}
	public String tableName(){
		return tableName;
	}
	
	public String toString(){
		return tableName;
	}
}
