package org.simplesql.relational_algebra;

import java.io.OutputStream;
import java.util.List;

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
public abstract class Expression <T> implements RANode{
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
		Object leftValue = getValue(left, ctx);
		Object rightValue = getValue(right, ctx);
		
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
	
	private static Object getValue(Expression<?> expr, Row ctx){
/*
		// both left and right should be either a literal or a column
		if(!isLiteralOrColumn(expr)){
			throw new IllegalStateException(
				"unimplemented: comparing objects other than literal or column");
		}

		Object value = null;
		if(expr instanceof Column){
			
//			value = ctx.get(expr.getFullName()).evaluate(null);
			value = expr.evaluate(ctx);
		}else{
//			value = ((LiteralValue)expr).evaluate(null);
			value = expr.evaluate(ctx);
		}
		return value;
*/
		Object value = expr.evaluate(ctx);
		if(value instanceof LiteralValue<?>){
			return ((LiteralValue) value).evaluate(ctx);
		}else{
			return value;
		}
	}
	

	abstract public boolean isNumeric();

	abstract public boolean isBoolean();

	abstract public boolean isSimple();
	
	abstract public List<Table> getReferencedTables();

}
