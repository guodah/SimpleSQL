package org.simplesql.relational_algebra;

abstract public class NumericValue<T extends Comparable<T>> extends LiteralValue<T>{

	public NumericValue() {
		super(false);
	}

	@Override
	public boolean isNumeric() {
		return true;
	}

	@Override
	public boolean isBoolean() {
		return false;
	}

	@Override
	public String getSimpleName() {
		return toString();
	}

	@Override
	public String getFullName() {
		return toString();
	}

}
