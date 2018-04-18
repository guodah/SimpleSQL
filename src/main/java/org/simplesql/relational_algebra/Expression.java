package org.simplesql.relational_algebra;

import java.io.OutputStream;

import org.simplesql.iterators.Row;
import org.simplesql.resolve.SchemaResolver;

/*
expr: 
     literal_value  
   | column
   | expr ('>' | '<' | '=' | '<>' | '>=' | '<=' | 'is') expr
   | expr OR expr
   | expr AND expr

   ;*/
public abstract class Expression <T>{
	protected boolean resolved;
	
	public boolean isResolved(){
		return resolved;
	}
	
	abstract public String getSimpleName();
	abstract public String getFullName();
	
	abstract public T evaluate(Row ctx);
	
	abstract public boolean resolve(Relation dataSource, OutputStream output);

	abstract public String getType();
	
	public Table locateColumn(String column){
		if(this instanceof LiteralValue){
			return null;
		}else{
			throw new IllegalStateException("unsupported operation");
		}
	}
	
	public static int compare(Expression<?> left, Expression<?> right, Row ctx) {
		Object leftValue = getLiteral(left, ctx);
		Object rightValue = getLiteral(right, ctx);
		
		if(leftValue==null && rightValue==null){
			return 0;
		}else if(leftValue!=null && rightValue==null){
			return 1;
		}else if(rightValue!=null && leftValue==null){
			return -1;
		}
		
		// as of now (March 15) only null, long, double and String are supported
		int compareCode = 0;
		if(areSameType(leftValue, rightValue)){
			compareCode = ((Comparable)leftValue).compareTo(rightValue);
		}else if(leftValue instanceof String || rightValue instanceof String){
			throw new IllegalStateException("uncomparable data types");
		}else{
			if(leftValue instanceof Long && rightValue instanceof Double){
				compareCode = ((Long)leftValue > (Double)rightValue)?1:-1;
			}else if(leftValue instanceof Double && rightValue instanceof Long){
				compareCode = ((Double)leftValue > (Long)rightValue)?1:-1;
			}else{
				throw new IllegalStateException("uncomparable data types");
			}
		}
		
		return compareCode;
	}

	private static boolean areSameType(Object obj1, Object obj2){
		return obj1.getClass().equals(obj2.getClass());
	}
	
	private static Object getLiteral(Expression<?> expr, Row ctx){
		// both left and right should be either a literal or a column
		if(!isLiteralOrColumn(expr)){
			throw new IllegalStateException(
				"unimplemented: comparing objects other than literal or column");
		}

		Object value = null;
		if(expr instanceof Column){
			value = ctx.get(expr.getFullName()).evaluate(null);
		}else{
			value = ((LiteralValue)expr).evaluate(null);
		}
		return value;
	}
	
	
	
	private static boolean isLiteralOrColumn(Expression<?> expr) {
		return (expr instanceof LiteralValue) || (expr instanceof Column);
	}

	abstract public boolean isNumeric();

	abstract public boolean isBoolean();

}
