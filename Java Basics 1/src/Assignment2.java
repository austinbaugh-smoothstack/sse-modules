/*
 * Instructions:
 *   * User is asked to guess a number 1-100. (Program chooses randomly.)
 *   * If the guess is within 10 of the correct answer, plus or minus, output
 *     the correct answer and exit. (If the answer is 63, user must guess 53-73.)
 *   * If the number is not within 10 of the correct answer, ask the user to
 *     keep guessing.
 *   * If the user does not succeed within 5 attempts, display, "Sorry," along
 *     with the answer and exit.
 */

import java.util.Scanner;

public class Assignment2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int randNum = (int) (Math.random() * 100 + 1);
        int inputNum, numTries = 0;

        do {
            if (numTries == 5) {
                System.out.println("Sorry,");
                break;
            }

            System.out.print("Guess a number between 1-100: ");
            inputNum = scanner.nextInt();
            numTries++;
        } while (10 < Math.abs(randNum - inputNum));

        System.out.println("The number was " + randNum);
        scanner.close();
    }
}
