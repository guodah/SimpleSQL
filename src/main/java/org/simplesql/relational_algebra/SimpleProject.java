package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simplesql.resolve.SchemaResolver;

/**
 * This class is used for column pruning. A Table can be replaced with a SimpleProject
 */
public class SimpleProject extends Relation{
	private List<Column> columns;
	private Table table;
	public SimpleProject(Table table, List<Column> columns){
		this.columns = new ArrayList<>(columns);
		this.table = table;
	}
	public SimpleProject(Table table){
		columns = new ArrayList<>();
		this.table = table;
	}
	
	public void addColumn(Column column){
		columns.add(column);
	}
	
	@Override
	public boolean resolve(SchemaResolver resolver, OutputStream output) {
		if(!table.resolve(resolver, output)) return false;
		for(Expression<?> column:columns){
			if(!column.resolve(table, output))
				return false;
		}
		return true;
	}

	@Override
	public Table locateColumnBySimpleName(String column) {
		for(Expression<?> each:columns){
			if(each.getSimpleName().equals(column)){
				return table;
			}
		}
		return null;
	}

	@Override
	public List<Expression<?>> getColumns() {
		return new ArrayList<>(getReferencedColumns());
	}
	@Override
	public Set<Expression<?>> getReferencedColumns() {
		return new HashSet<>(columns);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for(int i=0;i<columns.size();i++){
			sb.append(columns.get(i));
			if(i!=columns.size()-1){
				sb.append(", ");
			}
		}		
		
		sb.append(" FROM ");
		sb.append("(");
		sb.append(table);
		sb.append(")");
		
		return sb.toString();

	}
	public Table getTable() {
		return table;
	}
	
	@Override
	public void replaceWith(Column c1, Column c2) {
		return;
	}
}
