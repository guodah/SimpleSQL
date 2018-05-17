package org.simplesql.relational_algebra.rules;

import java.util.Set;

import org.simplesql.SimpleSQL;
import org.simplesql.relational_algebra.BooleanBinaryExpression;
import org.simplesql.relational_algebra.Column;
import org.simplesql.relational_algebra.ConditionedJoin;
import org.simplesql.relational_algebra.Expression;
import org.simplesql.relational_algebra.Join;
import org.simplesql.relational_algebra.Project;
import org.simplesql.relational_algebra.Relation;
import org.simplesql.relational_algebra.Table;
import org.simplesql.resolve.SchemaResolver;

public class JoinSimplicationRule extends RARule{
	private Project project;
	private ConditionedJoin join;
	private Column primaryKey;
	private Column foreignKey;
	
	public JoinSimplicationRule(ConvertRequest request) {
		super(request);
		project = null;
		join = null;
	}
	
	@Override
	boolean match() {
		if(!(request.getRANode() instanceof Project)){
			return false;
		}
		
		project = (Project) request.getRANode();
		join = match(project.getRelation());
		if(join==null) return false;
		
		Set<Expression<?>> columns = project.getReferencedColumns();
		for(Expression<?> column:columns){
			if(fromSameTable(column, primaryKey) && !column.equals(primaryKey)){
				return false;
			}
		}
		return true;
	}

	private boolean fromSameTable(Expression<?> column1, Column column2) {
		if(!(column1 instanceof Column)) return false;
		
		return ((Column)column1).getTableName().equals(primaryKey.getTableName());
	}

	private ConditionedJoin match(Relation relation) {
		if(relation instanceof Join){
			Join join = (Join) relation;
			if(check(join)){
				return (ConditionedJoin) relation;
			}
			
			ConditionedJoin result=match(join.getLeft());
			if(result!=null){
				return result;
			}
			return match(join.getRight());
		}else {
			return null;
		}
	}

	private boolean check(Join join) {
		if(!(join instanceof ConditionedJoin) ||
				!(join.getLeft() instanceof Table) ||
				!(join.getRight() instanceof Table)){
			return false;
		}
		
		BooleanBinaryExpression on = ((ConditionedJoin)join).getJoinCondition();
		if(!(on.getLeft() instanceof Column) || !(on.getRight() instanceof Column)
				|| !on.isEqui()){
			return false;
		}
		
		Column left = (Column) on.getLeft(), right = (Column) on.getRight();
		if(isPrimaryKey(left) && referneces(right, left)){
			primaryKey = left;
			foreignKey = right;
			return true;
		}
				
		if(isPrimaryKey(right) && referneces(left, right)){
			primaryKey = right;
			foreignKey = left;
			return true;
		}
		
		return false;
	}

	private boolean referneces(Column column1, Column column2) {
		SchemaResolver resolver = SimpleSQL.getSchemaResolver();
		return resolver.isForeignKey(column1.getTableName(), column1.getSimpleName(),
				column2.getTableName(), column2.getSimpleName());
	}

	private boolean isPrimaryKey(Column column) {
		SchemaResolver resolver = SimpleSQL.getSchemaResolver();
		return resolver.isPrimaryKey(column.getTableName(), column.getSimpleName());
	}

	@Override
	void convert() {
		replacePrimaryKey();
		removePrimaryKeyTable();
		
		if(match()) convert();
	}

	private void removePrimaryKeyTable() {
		project.setRelation(remove((Join) project.getRelation()));
	}

	private Relation remove(Join relation) {
		if(relation==join){
			if(join.getLeft().toString().equals(primaryKey.getTableName())){
				return join.getRight();
			}else{
				return join.getLeft();
			}
		}else if(relation instanceof Join){
			if(((Join)relation).getLeft()==join){
				((Join) relation).setLeft(remove(join));
			}else if(((Join)relation).getLeft()==join){
				((Join) relation).setLeft(remove(join));
			}
		}
		return relation;
	}

	private void replacePrimaryKey() {
		project.replaceWith(primaryKey, foreignKey);
	}

}
