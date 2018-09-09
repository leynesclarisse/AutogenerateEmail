package com.citco.email.utils;


public class Validation {
	public boolean validInput(String input) {
		try {
			Double.parseDouble(input);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}	
}
