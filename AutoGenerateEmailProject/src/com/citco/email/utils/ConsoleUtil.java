package com.citco.email.utils;


import java.util.Scanner;

public class ConsoleUtil {
	Scanner scanner = new Scanner(System.in);
	
	public String readLine() {
		return scanner.nextLine();
	}
	
	public void printLn(String message,Object ... arg) {
		System.out.println(String.format(message, arg));
	}
	
	public void print(String message,Object ... arg) {
		System.out.print(String.format(message, arg));
	}
	
	public void err(String message,Object ... arg) {
		System.err.print(String.format(message, arg));
	}
	
	public boolean checkIfNum(String str) {
		try {
			Double.parseDouble(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}