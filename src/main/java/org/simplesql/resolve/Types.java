package org.simplesql.resolve;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Types {
	private static Map<String, Set<String>> compatibleTypes;
	private static Map<String, String> representingTypes;
	static {
		compatibleTypes = new HashMap<>();
		Set<String> numericTypes = new HashSet<>();
		numericTypes.add("INT");
		numericTypes.add("LONG");
		numericTypes.add("INTEGER");
		numericTypes.add("DOUBLE");
		compatibleTypes.put("INT", numericTypes);
		compatibleTypes.put("LONG", numericTypes);
		compatibleTypes.put("INTEGER", numericTypes);
		
		Set<String> booleanType = new HashSet<>();
		booleanType.add("BOOLEAN");
		compatibleTypes.put("BOOLEAN", booleanType);
		
		representingTypes = new HashMap<>();
		representingTypes.put("INTEGER", "LONG");
		representingTypes.put("LONG", "LONG");
		representingTypes.put("INT", "LONG");
		representingTypes.put("FLOAT", "DOUBLE");
		representingTypes.put("DOUBLE", "DOUBLE");
	}
	
	public static boolean isCompatible(String type1, String type2){
		type1 = type1.toUpperCase();
		type2 = type2.toUpperCase();
		return compatibleTypes.get(type1).contains(type2);
	}

	public static String getRepresentingType(String type) {
		return representingTypes.get(type);
	}
}
