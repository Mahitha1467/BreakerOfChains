import Input.ConsoleInput;
import Input.InputParser;
import checker.AllegianceChecker;
import checker.RulerChecker;
import kingdom.HighPriest;
import message.MessageGenerator;
import model.Ballot;
import output.Printer;

import java.util.Scanner;

public class MainClass {
    private static MessageGenerator messageGenerator;
    private static Ballot ballot;
    private static HighPriest highPriest;
    private static AllegianceChecker allegianceChecker;
    private static RulerChecker rulerChecker;
    private static Printer printer;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput consoleInput = new ConsoleInput(scanner);
        InputParser parser = new InputParser();
        messageGenerator = new MessageGenerator();
        ballot = new Ballot();
        highPriest = new HighPriest();
        allegianceChecker = new AllegianceChecker();
        rulerChecker = new RulerChecker();
        printer = new Printer();

        BreakerOfChains breakerOfChains = new BreakerOfChains(consoleInput, parser, messageGenerator, ballot,
                highPriest, allegianceChecker, rulerChecker, printer);

        breakerOfChains.findRuler();
    }

}
