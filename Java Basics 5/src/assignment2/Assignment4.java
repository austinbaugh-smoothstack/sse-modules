package assignment2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Assignment4 {
    private static String[] noX(String input[]) {
        return Arrays.asList(input).stream().map(str -> str.replace("x", "")).collect(Collectors.toList())
                .toArray(String[]::new);
    }

    public static void main(final String[] _args) {
        final String inputs[][] = { { "ax", "bb", "cx" }, { "xxax", "xbxbx", "xxcx" }, { "x" } };
        for (final String input[] : inputs) {
            System.out.println("Original Array:");
            System.out.println(Arrays.toString(input));
            System.out.println("New Array:");
            System.out.println(Arrays.toString(Assignment4.noX(input)));
            System.out.println();
        }
    }
}