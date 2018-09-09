package com.citco.email.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AbtractFileDao {

	abstract String getLocation();
	
	protected void appendTextToFile(String str) throws IOException {
		FileWriter fileWriter = new FileWriter(getLocation(),true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(str);
		fileWriter.close();
	}
	
	protected void appendTextToFile(ArrayList<String> array) throws IOException {
		FileWriter fileWriter = new FileWriter(getLocation());
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(String.join("", array));
		fileWriter.close();
	}
	
	protected void readFileLineByLine(IFileReaderCallback callback) throws FileNotFoundException {
		File file = new File(getLocation());
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) {
			callback.onLineRead(scan.nextLine() + "\n");
		}
		scan.close();	
	}
}