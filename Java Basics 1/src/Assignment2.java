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
