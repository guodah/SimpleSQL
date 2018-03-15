package org.simplesql.relational_algebra;

public class DoubleValue extends LiteralValue<Double>{
	private double val;
	public DoubleValue(double val){
		super(false);
		this.val = val;
	}
	
	public String toString(){
		return ""+val;
	}

	@Override
	public Double evaluate(Context ctx) {
		return val;
	}

}
