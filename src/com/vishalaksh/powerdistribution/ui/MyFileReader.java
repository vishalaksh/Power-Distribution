package com.vishalaksh.powerdistribution.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.vishalaksh.powerdistribution.logic.Constants;

public class MyFileReader implements Constants {

	/**
	 * This method is suitable when the input file format is subject to change.
	 * We can specify the line number of the required data. This will read the
	 * file from starting everytime it is called.
	 * 
	 * @param file
	 * @param lineNumber
	 * @return
	 * @throws FileNotFoundException
	 */
	static String readLineFromFile(File file, int lineNumber)
			throws FileNotFoundException {

		// File file =new File(fileInput);
		Scanner scanner = new Scanner(file);
		String line = null;
		for (int i = 0; i < lineNumber; i++) {
			line = scanner.nextLine();
		}
		scanner.close();
		return line;
	}
	
	static Scanner getScannerAtLine(Scanner scn,int lineNumber ){
		
		
		for (int i = 0; i < (lineNumber-1); i++) {
			scn.nextLine();
		}
		
		return scn;
	}

}
