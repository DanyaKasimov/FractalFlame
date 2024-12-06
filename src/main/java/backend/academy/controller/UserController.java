package backend.academy.controller;

import java.util.function.Predicate;

public interface UserController {

    String read(String text);

    void write(String text);

    boolean idValidNumeric(String input, int listSize);

    String readValidatedInput(String prompt, Predicate<String> validation, String errorMessage);
}
