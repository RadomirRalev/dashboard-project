package core.providers;

import core.contracts.Reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    private final Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}