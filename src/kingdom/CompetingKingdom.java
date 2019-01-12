package kingdom;

import message.MessageGenerator;
import model.Ballot;
import model.Message;
import util.KingdomUtil;

import java.util.LinkedList;
import java.util.List;

public class CompetingKingdom {
    private String name;

    public CompetingKingdom(String name) {
        this.name = name;
    }

    public void addMessagesToBallotToSendToOtherKingdoms(MessageGenerator messageGenerator, Ballot ballot) {
        List<String> existingKingdoms = KingdomUtil.getExistingKingdoms();

        List<String> allKingdoms = new LinkedList<>(existingKingdoms);
        allKingdoms.remove(name);

        allKingdoms.forEach(kingdom -> ballot.add(getMessage(kingdom, messageGenerator)));
    }

    private Message getMessage(String kingdom, MessageGenerator messageGenerator) {
        String content = messageGenerator.generateMessage();
        return new Message(name, kingdom, content);
    }
}
