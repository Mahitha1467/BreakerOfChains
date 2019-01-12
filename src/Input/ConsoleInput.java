package Input;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;
    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput() {
        System.out.println("Enter the kingdoms competing to be the Ruler");
        return scanner.nextLine();
    }
}
