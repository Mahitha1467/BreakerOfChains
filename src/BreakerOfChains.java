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

public class BreakerOfChains {
    private MessageGenerator messageGenerator;
    private Ballot ballot;
    private HighPriest highPriest;
    private AllegianceChecker allegianceChecker;
    private RulerChecker rulerChecker;
    private Printer printer;
    private ConsoleInput consoleInput;
    private InputParser parser;

    public BreakerOfChains(ConsoleInput consoleInput, InputParser parser, MessageGenerator messageGenerator,
                           Ballot ballot, HighPriest highPriest, AllegianceChecker allegianceChecker,
                           RulerChecker rulerChecker, Printer printer) {
        this.consoleInput = consoleInput;
        this.parser = parser;
        this.messageGenerator = messageGenerator;
        this.ballot = ballot;
        this.highPriest = highPriest;
        this.allegianceChecker = allegianceChecker;
        this.rulerChecker = rulerChecker;
        this.printer = printer;
    }

    void findRuler() {
        printer.printRulerDetails(new Kingdom());
        String input = consoleInput.getInput();
        try {
            List<String> competingKingdoms = parser.parse(input);
            declareRuler(competingKingdoms);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private void declareRuler(List<String> competingKingdoms) {
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

    private List<Kingdom> startBallotProcess(List<String> formattedInputs) {
        formattedInputs.forEach(input -> {
            CompetingKingdom competingKingdom = new CompetingKingdom(input);
            competingKingdom.addMessagesToBallotToSendToOtherKingdoms(messageGenerator, ballot);
        });
        List<Message> randomSixMessages = highPriest.getMessagesFromBallot(ballot);
        return allegianceChecker.getKingdomsWithAllies(randomSixMessages);
    }
}
