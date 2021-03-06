package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

public abstract class Relation implements RANode{
	abstract public boolean resolve(SchemaResolver resolver, OutputStream output);
	
	
	public Table findTable(String table){
		return findTable(this, table);
	}
	
	private Table findTable(Relation dataSource, String table) {
		if(dataSource instanceof Table){
			if(dataSource.toString().equals(table)){
				return (Table)dataSource;
			}else{
				return null;
			}
		}else if(dataSource instanceof Join){
			Table left = findTable(((Join) dataSource).getLeft(), table);
			if(left!=null) return left;
			Table right = findTable(((Join) dataSource).getRight(), table);
			if(right!=null) return right;
		}else if(dataSource instanceof Project){ // subquery
			return findTable(((Project)dataSource).getRelation(), table);
		}
		return null;
	}
	
	abstract public Table locateColumnBySimpleName(String column) ;


	abstract public List<Expression<?>> getColumns();
	
}
