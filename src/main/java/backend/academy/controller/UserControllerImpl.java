package backend.academy.controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

public class UserControllerImpl implements UserController {

    private final InputStream inputStream;
    private final PrintStream printStream;

    public UserControllerImpl(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    @Override
    public String read(String text) {
        Scanner scanner = new Scanner(inputStream);
        printStream.print(text);
        return scanner.nextLine().trim();
    }

    @Override
    public boolean idValidNumeric(String input, int listSize) {
        try {
            int value = Integer.parseInt(input);
            return value >= 1 && value <= listSize;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String readValidatedInput(String prompt, Predicate<String> validation, String errorMessage) {
        Scanner scanner = new Scanner(inputStream);
        String input;
        do {
            printStream.print(prompt);
            input = scanner.nextLine().trim();
            if (!validation.test(input)) {
                printStream.println(errorMessage);
            }
        } while (!validation.test(input));
        return input;
    }

    @Override
    public void write(String text) {
        printStream.println(text);
    }
}
