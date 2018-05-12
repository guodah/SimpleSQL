package org.simplesql.relational_algebra;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.simplesql.iterators.Row;
import org.simplesql.loggin.Logging;
import org.simplesql.resolve.SchemaResolver;
import org.simplesql.resolve.Types;

public class BooleanBinaryExpression extends BinaryExpression <Boolean>{
	public BooleanBinaryExpression(Expression<?> left, String operator, Expression<?> right){
		super(left, operator, right);
	}
	
	public void findTransitiveCondition(Map<Expression<?>, Set<Expression<?>>> alias){
		if(!isSimple() || ! containsLiterals()){
			return;
		}

		Expression<?> expr = (left instanceof LiteralValue)?right:left;
		boolean exprOnLeft = expr==left;
		Expression<?> value = (left instanceof LiteralValue)?left:right;
		if(!alias.containsKey(expr)){
			return;
		}
		
		addConditions(exprOnLeft, value, alias.get(expr));
	}
	

	private void addConditions(boolean exprOnLeft, 
				Expression<?> value, 
				Set<Expression<?>> set) {
		BooleanBinaryExpression addition = null;
		for(Expression<?> expr:set){
			BooleanBinaryExpression next = (exprOnLeft)
					?new BooleanBinaryExpression(expr, operator, value)
					:new BooleanBinaryExpression(value, operator, expr);

			addition = (addition==null)
						?next
						:new BooleanBinaryExpression(addition, "AND", next);
		}

		BooleanBinaryExpression self = new BooleanBinaryExpression(left, operator, right);
		left = self;
		right = addition;
		operator = "AND";
	}

	@Override
	public Boolean evaluate(Row ctx) {
		if(isLogicalOperator(operator)){ // AND or OR
			boolean leftBool = ((BooleanBinaryExpression)left).evaluate(ctx);
			boolean rightBool = ((BooleanBinaryExpression)right).evaluate(ctx);
			
			return operator.equals("AND")?(leftBool && rightBool):(leftBool || rightBool); 
		}else{ // comparison
			
			return compare(ctx);
		}
	}

	private boolean compare(Row ctx) {
		int compareCode = Expression.compare(left, right, ctx);
		
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

	private boolean isLogicalOperator(String operator) {
		return operator.equals("AND") || operator.equals("OR");
	}
	
	private boolean isCompareOperator(String operator){
		return operator.equals("AND") || operator.equals("OR") ||
				operator.equals(">=") || operator.equals("<=") ||
				operator.equals(">") || operator.equals("<") ||
				operator.equals("<>") || operator.equals("=") ||
				operator.equals("IS");
	}

	@Override
	public boolean resolve(Relation dataSource, OutputStream output) {
		if(!super.resolve(dataSource,  output)){
			return false;
		}
		
		if(!checkOperator(operator)){
			return false;
		}
		
		if(isLogicalOperator(operator) && (!isBoolean(left) || !isBoolean(right))){
			return false;
		}
		
		return true;
	}
	
	private boolean isBoolean(Expression<?> left) {
		return left.isBoolean();
	}


	private boolean checkOperator(String operator) {
		return this.isCompareOperator(operator) ||
				this.isLogicalOperator(operator);
	}


	public String getType(SchemaResolver resolver){
		return "BOOLEAN";
	}


	@Override
	public boolean isNumeric() {
		return false;
	}


	@Override
	public boolean isBoolean() {
		return true;
	}


	@Override
	public String getType() {
		return "BOOLEAN";
	}
	
	public boolean isEqui(){
		if(operator.equals("=")){
			return true;
		}else if(operator.equals("OR")){
			return false;
		}else if(operator.equals("AND")){
			return isEqui(left) && isEqui(right);
		}else{
			return false;
		}
	}
	
	public Map<Expression<?>, Set<Expression<?>>> findAlias(){
		Map<Expression<?>, Set<Expression<?>>> result =
				new HashMap<Expression<?>, Set<Expression<?>>>();
		findAlias(result);
		return result;
	}
	
	private void findAlias(Map<Expression<?>, Set<Expression<?>>> result) {
		if(operator.equals("=")){
			if(!result.containsKey(left)){
				result.put(left, new HashSet<Expression<?>>());
			}
			if(!result.containsKey(right)){
				result.put(right, new HashSet<Expression<?>>());
			}
			
			result.get(left).add(right);
			result.get(right).add(left);
		}else if(operator.equals("AND") || operator.equals("OR")){
			((BooleanBinaryExpression)left).findAlias(result);
			((BooleanBinaryExpression)right).findAlias(result);
		}
	}


	private boolean isEqui(Expression<?> left) {
		if(left instanceof BooleanBinaryExpression){
			return ((BooleanBinaryExpression) left).isEqui();
		}else{
			return false;
		}
	}


	/**
	 * Checks the Boolean expression is a conjunctive condition, like
	 * A and B and C. In addition, each predicate (A, B or C) only addresses data
	 * from one table.  
	 * 
	 * This method is check if the predicates in this Boolean expression can be 
	 * pushed down to individual tables.
	 *   
	 * @return
	 */
	public boolean isConjunctiveAndSimple(){
		if(operator.equals("OR")) return false;
		else if(operator.equals("AND")){
			boolean result = false;
			if(left instanceof BooleanBinaryExpression){
				result = ((BooleanBinaryExpression)left).isConjunctiveAndSimple();
			}else{
				result = left.isSimple();
			}
			
			if(right instanceof BooleanBinaryExpression){
				result = result || ((BooleanBinaryExpression)right).
										isConjunctiveAndSimple();
			}else{
				result = right.isSimple();
			}
			return result;
		}else{
			return this.isSimple();
		}
		
	}


	public boolean isSinglePredicate() {
		return !isLogicalOperator(operator);
	}
}
