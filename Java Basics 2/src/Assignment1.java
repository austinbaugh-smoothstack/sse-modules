/*
 * Instructions:
 *    Take multiple values from the command line and show the result
 *    of adding all of them.
 */

import java.util.Scanner;

public class Assignment1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nextNum, sum = 0;
		do {
			System.out.print("Input number to add or 0 to get the sum: ");
			nextNum = scanner.nextInt();
			sum += nextNum;
		} while(nextNum != 0);
		System.out.println("Total sum: " + sum);
		
		scanner.close();
	}
}
