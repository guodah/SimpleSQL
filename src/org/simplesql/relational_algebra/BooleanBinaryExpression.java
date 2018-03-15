package org.simplesql.relational_algebra;

public class BooleanBinaryExpression extends Expression <Boolean>{
	private String operator;
	private Expression<?> left, right;
	public BooleanBinaryExpression(Expression<?> left, String operator, Expression<?> right){
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public String toString(){
		return String.format("%s %s %s", left, operator, right);
	}

	@Override
	public Boolean evaluate(Context ctx) {
		if(isLogicalOperator(operator)){ // AND or OR
			boolean leftBool = ((BooleanBinaryExpression)left).evaluate(ctx);
			boolean rightBool = ((BooleanBinaryExpression)right).evaluate(ctx);
			
			return operator.equals("AND")?(leftBool && rightBool):(leftBool || rightBool); 
		}else{ // comparison
			
			return compare(left, right, ctx);
		}
	}

	private boolean compare(Expression<?> left, Expression<?> right, Context ctx) {
		Object leftValue = getLiteral(left, ctx);
		Object rightValue = getLiteral(right, ctx);
		
		if(leftValue==null || rightValue==null){
			return leftValue==rightValue;
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
		
		switch(operator){
		case "=": return compareCode==0;
		case "<>": return compareCode!=0;
		case "<=": return compareCode<=0;
		case ">=": return compareCode>=0;
		case ">": return compareCode>0;
		case "<": return compareCode<0;
		default: throw new IllegalStateException("unsupported operator "+operator);
		}
	}

	private boolean areSameType(Object obj1, Object obj2){
		return obj1.getClass().equals(obj2.getClass());
	}
	
	private Object getLiteral(Expression<?> expr, Context ctx){
		// both left and right should be either a literal or a column
		if(!isLiteralOrColumn(expr)){
			throw new IllegalStateException(
					"unimplemented: comparing objects other than literal or column");
		}

		Object value = null;
		if(expr instanceof Column){
			value = ctx.get(left.toString());
		}else{
			value = ((LiteralValue)left).evaluate(null);
		}
		return value;
	}
	
	private boolean isLiteralOrColumn(Expression<?> expr) {
		return (expr instanceof LiteralValue) || (expr instanceof Column);
	}

	private boolean isLogicalOperator(String operator) {
		return operator.equals("AND") || operator.equals("OR");
	}
	
	
}
