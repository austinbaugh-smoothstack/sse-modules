package lms.service;

import java.util.Scanner;

class ScannerUtil {
    
    private static final Scanner scanner = new Scanner(System.in);
    static String getInput() {
        String input;
        do {
            input = scanner.nextLine();
        } while(input.isEmpty());
        return input;
    }
}
