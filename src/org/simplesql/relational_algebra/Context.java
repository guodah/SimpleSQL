package org.simplesql.relational_algebra;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private Map<String, Object> tuple;
	public Context(){
		tuple = new HashMap<>();
	}
	
	public void put(String key, Object val){
		tuple.put(key,  val);
	}
	
	public Object get(String key){
		return tuple.get(key);
	}
	
	public boolean contains(String key){
		return tuple.containsKey(key);
	}
}
