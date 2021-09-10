package assignment2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Assignment2 {
    private static Integer[] rightDigit(Integer input[]) {
        return Arrays.asList(input).stream().map(num -> num % 10).collect(Collectors.toList()).toArray(Integer[]::new);
    }

    public static void main(final String[] _args) {
        final Integer inputs[][] = { { 1, 22, 93 }, { 16, 8, 886, 8, 1 }, { 10, 0 } };
        for (final Integer input[] : inputs) {
            System.out.println("Original Array:");
            System.out.println(Arrays.toString(input));
            System.out.println("New Array:");
            System.out.println(Arrays.toString(Assignment2.rightDigit(input)));
            System.out.println();
        }
    }
}