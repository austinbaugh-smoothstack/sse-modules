/*
 * File info:
 *   Without arguments passed, this file will concatenate the
 *   string in DEFAULT_ADDITION to the file DEFAULT_FILE_PATH.
 *   With an optional single argument, a different output file can be specified
 *   With an optional second argument, a different string can be specified
 *   
 * Instructions:
 *   Write a Java program to append text to an existing file.
 */

import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class Assignment2 {
	final static String DEFAULT_FILE_PATH = "assignment-2-output.txt";
	final static String DEFAULT_ADDITION = "concatenated string\n";
	
	public static void main(String[] args) throws IOException {
		final String path = args.length > 0 ? args[0] : DEFAULT_FILE_PATH;
		// args = args[1::]
		if(args.length > 1) {
			args = Arrays.copyOfRange(args, 1, args.length - 1);
		}
		final String addition = args.length > 1 ? String.join(" ", args) : DEFAULT_ADDITION;
		
		final FileWriter writer = new FileWriter(path, true);
		writer.write(addition);
		writer.close();
	}
}