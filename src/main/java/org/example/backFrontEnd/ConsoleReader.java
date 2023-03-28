package org.example.backFrontEnd;

import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }
    public KeyValuePair parseInput() {
        return StringDivider.divideString(scanner.nextLine());
    }
    public KeyValuePair parseInput(String lineHint) {
        System.out.println(lineHint);
        return StringDivider.divideString(scanner.nextLine());
    }
}
