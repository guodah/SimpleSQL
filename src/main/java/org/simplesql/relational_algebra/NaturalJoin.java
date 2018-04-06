package org.simplesql.relational_algebra;

import java.util.ArrayList;
import java.util.List;

import org.simplesql.resolve.SchemaResolver;

public class NaturalJoin extends Join{

	public NaturalJoin(DataSource left, DataSource right) {
		super(left, right);
	}

	public List<Column> findCommonColumns(SchemaResolver resolver){
		List<Column> leftColumns = left.getColumns(resolver);
		List<Column> rightColumns = right.getColumns(resolver);
		
		List<Column> res = new ArrayList<Column>();
		for(Column lc:leftColumns){
			if(rightColumns.contains(lc)){
				res.add(lc);
			}
		}
		return res;
	}

	@Override
	public List<Column> getColumns(SchemaResolver resolver) {
		List<Column> leftColumns = left.getColumns(resolver);
		List<Column> rightColumns = right.getColumns(resolver);
		List<Column> res = new ArrayList<Column>(leftColumns);

		for(Column rc:rightColumns){
			if(!leftColumns.contains(rc)){
				res.add(rc);
			}
		}
		return res;
	}
	
	@Override
	public String toString(){
		return String.format(" %s natural join %s", left, right);
	}
}
