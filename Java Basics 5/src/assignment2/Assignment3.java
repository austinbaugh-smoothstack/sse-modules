package assignment2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Assignment3 {
    private static Integer[] doubling(Integer input[]) {
        return Arrays.asList(input).stream().map(num -> num * 2).collect(Collectors.toList()).toArray(Integer[]::new);
    }

    public static void main(final String[] _args) {
        final Integer inputs[][] = { { 1, 2, 3 }, { 6, 8, 6, 8, -1 }, {} };
        for (final Integer input[] : inputs) {
            System.out.println("Original Array:");
            System.out.println(Arrays.toString(input));
            System.out.println("New Array:");
            System.out.println(Arrays.toString(Assignment3.doubling(input)));
            System.out.println();
        }
    }
}