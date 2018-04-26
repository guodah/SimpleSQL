package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.SimpleSQL;
import org.simplesql.resolve.SchemaResolver;

public class Table extends Relation{
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
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		return resolver.validateTable(tableName);
	}
	@Override
	public List<Expression<?>> getColumns() {
		return new ArrayList<>(getReferencedColumns());
	}
	@Override
	public Table locateColumnBySimpleName(String column) {
		List<? extends Expression<?>> columns = getColumns();
		for(Expression<?> each: columns){
			if(((Column)each).getSimpleName().equals(column)){
				return this; 
			}
		}
		return null;
	}
	@Override
	public Set<Column> getReferencedColumns() {
		if(!SimpleSQL.getSchemaResolver().validateTable(tableName)){
			throw new IllegalStateException("Table "+tableName+" not found.");
		}
		return new HashSet<>(SimpleSQL.getSchemaResolver().findColumns(tableName));
	}
}
