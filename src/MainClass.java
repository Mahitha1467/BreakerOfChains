import Input.ConsoleInput;
import Input.InputParser;
import exception.InvalidInputException;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput consoleInput = new ConsoleInput(scanner);
        InputParser parser = new InputParser();

        String input = consoleInput.getInput();
        try {
            List<String> formattedInputs = parser.parse(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
