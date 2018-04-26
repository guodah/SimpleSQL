package org.simplesql.relational_algebra.rules;


public abstract class RARule {
	@SuppressWarnings("unused")
	protected ConvertRequest request;
	public RARule(ConvertRequest request){
		this.request = request;
	}
	public void excute(){
		if(match()) convert();
	}
	abstract boolean match();
	abstract void convert();
	
	
}
