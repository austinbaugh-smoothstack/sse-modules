package assignment2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

enum OperationResult {
    ODD, EVEN, PRIME, PALINDROME, COMPOSITE, NON_PALINDROME
}

@FunctionalInterface
interface PerformOperation {
    boolean operation(int n);
}

public class Assignment1 {
    private static final String DEFAULT_FILE = "assignment2.1-input.txt";

    private static void applyOperations(final int number) {
        final PerformOperation isOdd = (final int n) -> n % 2 == 1;
        final PerformOperation isPrime = (final int n) -> {
            // Finds factors less than or equal to sqrt(n)
            for (int i = 2; i < Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return n != 1;
        };
        final PerformOperation isPalindrome = (final int original) -> {
            int n = original, reversed = 0, remainder;
            // Constructs reversed version of original number
            while (n != 0) {
                remainder = n % 10;
                reversed = reversed * 10 + remainder;
                n /= 10;
            }
            return original == reversed;
        };

        System.out.println(isOdd.operation(number) ? OperationResult.ODD : OperationResult.EVEN);
        System.out.println(isPrime.operation(number) ? OperationResult.PRIME : OperationResult.COMPOSITE);
        System.out
                .println(isPalindrome.operation(number) ? OperationResult.PALINDROME : OperationResult.NON_PALINDROME);
    }

    public static void main(final String[] args) throws IOException {
        final String filePath = args.length > 1 ? args[0] : DEFAULT_FILE;
        Scanner scanner = new Scanner(new FileReader(filePath));
        final int totalNumbers = scanner.nextInt();
        for (int i = 0; i < totalNumbers; i++) {
            Assignment1.applyOperations(scanner.nextInt());
        }
        scanner.close();
    }
}