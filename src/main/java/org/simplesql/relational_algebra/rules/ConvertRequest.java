package org.simplesql.relational_algebra.rules;

import org.simplesql.relational_algebra.RANode;

public class ConvertRequest {
	private RANode raNode;
	public ConvertRequest(RANode raNode){
		this.raNode = raNode;
	}
	
	public RANode getRANode(){
		return raNode;
	}
}
