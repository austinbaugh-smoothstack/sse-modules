/*
 * File info:
 *   Without arguments passed, this file will print the number of occurrances
 *   of DEFAULT_CHAR in the contents of DEFAULT_FILE_PATH;
 *   With an optional single argument, a different input file can be specified
 *   With an optional second argument, a different character can be counted
 *   
 * Instructions:
 *    Write a Java program that counts the number of times a particular
 *    character, such as 'e', appears in a file. The character can be
 *    specified at the command line. 
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Assignment3 {
	private final static String DEFAULT_FILE_PATH = "assignment-3-input.txt";
	private final static char DEFAULT_CHAR = 'e';
	
	public static void main(final String[] args) throws IOException {
		final String path = args.length > 0 ? args[0] : DEFAULT_FILE_PATH;
		final char character = args.length > 1 ? args[1].charAt(0) : DEFAULT_CHAR;
		final BufferedReader reader = new BufferedReader(new FileReader(path));
		
		int totalOccurances = 0;
		// The current character being compared from the file
		int c;
		while((c = reader.read()) != -1) {
			if((char) c == character) {
				totalOccurances++;
			}
		}
		reader.close();
		System.out.println(
			"Total number of occurances of character " +
			"'" + character + "' " +
			"in file \"" + path + "\":\n" +
			totalOccurances
		);
	}
}