package assignment2;

import java.util.Arrays;

public class Assignment5 {
    private static boolean groupSumClump(int currentSum, int input[], int goalSum) {
        if (currentSum == goalSum) {
            return true;
        } else if (currentSum > goalSum) {
            return false;
        }

        int firstPrevIndex = 0, numOccurances = 1;
        for (int i = 1; i <= input.length; i++) {
            if (i != input.length && input[firstPrevIndex] == input[i]) {
                numOccurances++;
            } else {
                final int numToRemove = input[firstPrevIndex];
                int inputWithoutPrev[] = new int[input.length - numOccurances];
                // Copy everything before the adjacent duplicates
                System.arraycopy(input, 0, inputWithoutPrev, 0, firstPrevIndex);
                // Copy everything after the adjacent duplicates
                System.arraycopy(input, firstPrevIndex + numOccurances, inputWithoutPrev, firstPrevIndex,
                        input.length - firstPrevIndex - numOccurances);
                if (Assignment5.groupSumClump(currentSum + numToRemove * numOccurances, inputWithoutPrev, goalSum)) {
                    return true;
                }
                if (i != input.length) {
                    if (numOccurances > 1) {
                        numOccurances = 1;
                    }
                    firstPrevIndex = i;
                }
            }
        }
        return false;
    }

    public static void main(final String[] _args) {
        final int input1[] = { 2, 4, 8 };
        System.out.println("Array:");
        System.out.println(Arrays.toString(input1));
        System.out.println("Result:");
        System.out.println(Assignment5.groupSumClump(0, input1, 10));
        System.out.println();

        final int input2[] = { 1, 2, 4, 8, 1 };
        System.out.println("Array:");
        System.out.println(Arrays.toString(input2));
        System.out.println("Result:");
        System.out.println(Assignment5.groupSumClump(0, input2, 14));
        System.out.println();

        final int input3[] = { 2, 4, 4, 8 };
        System.out.println("Array:");
        System.out.println(Arrays.toString(input3));
        System.out.println("Result:");
        System.out.println(Assignment5.groupSumClump(0, input3, 14));
        System.out.println();
    }
}