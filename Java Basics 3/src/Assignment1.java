/*
 * File info:
 *   This file acts similar to ls -R
 *   A file path can be passed as an argument to list the directory
 *   contents of. Without an argument, the contents of the current
 *   directory will be listed.
 *   
 * Instructions:
 *   Write a Java program to get a list of all file/directory names
 *   (including in subdirectories) under a given directory. 
 */

import java.io.File;
import java.io.IOException;

public class Assignment1 {
	/**
	 * Recurisvely lists directory contents
	 * 
	 * @param file File whose name will be printed, along with its contents if it is a directory
	 * @param depth How many subdirectories have been recursed down
	 */
	private static void ls(final File file, final int depth) {
		for(int i = 0; i < depth; i++) {
			System.out.print('\t');
		}
		
		System.out.println(file.getName());
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				ls(subFile, depth + 1);
			}
		}
	}
	
	public static void main(final String[] args) throws IOException {
		final String path = args.length == 0 ? System.getProperty("user.dir") : args[0];
		ls(new File(path), 0);
	}
}