package org.simplesql.relational_algebra;

public class LongValue extends LiteralValue<Long>{
	private long val;
	public LongValue(long val){
		super(false);
		this.val = val;
	}
	
	public String toString(){
		return ""+val;
	}

	@Override
	public Long evaluate(Context ctx) {
		return val;
	}
}
