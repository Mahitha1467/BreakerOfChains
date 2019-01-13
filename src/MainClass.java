import Input.ConsoleInput;
import Input.InputParser;
import checker.AllegianceChecker;
import checker.RulerChecker;
import exception.InvalidInputException;
import kingdom.CompetingKingdom;
import kingdom.HighPriest;
import message.MessageGenerator;
import model.Ballot;
import model.Kingdom;
import model.Message;
import output.Printer;

import java.util.List;
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

        printer.printRulerDetails(new Kingdom());
        String input = consoleInput.getInput();
        try {
            List<String> competingKingdoms = parser.parse(input);
            declareRuler(competingKingdoms);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void declareRuler(List<String> competingKingdoms) {
        int ballotRound = 1;
        while(competingKingdoms.size() > 0) {
            List<Kingdom> kingdoms = startBallotProcess(competingKingdoms);
            printer.printBallotResults(kingdoms, ballotRound);
            if (rulerChecker.isRulerExist(kingdoms)) {
                printer.printRulerDetails(rulerChecker.getRuler(kingdoms));
                break;
            }
            competingKingdoms = rulerChecker.getTiedKingdomNames(kingdoms);
            ballotRound++;
        }
    }

    private static List<Kingdom> startBallotProcess(List<String> formattedInputs) {
        formattedInputs.forEach(input -> {
            CompetingKingdom competingKingdom = new CompetingKingdom(input);
            competingKingdom.addMessagesToBallotToSendToOtherKingdoms(messageGenerator, ballot);
        });
        List<Message> randomSixMessages = highPriest.getMessagesFromBallot(ballot);
        return allegianceChecker.getKingdomsWithAllies(randomSixMessages);
    }
}
