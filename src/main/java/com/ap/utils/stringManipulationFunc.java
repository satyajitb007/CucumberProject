package com.ap.utils;

public class stringManipulationFunc {
	
	public static double getDouble_value_from_String(String input) {
		String modifiedText = input.replaceAll("[^0-9.]+", "");
		double value = Double.parseDouble(modifiedText);
		return value;
	}
	
	public static int String_to_integer(String input) {
		int data = Integer.parseInt(input);
		return data;
	}

}
