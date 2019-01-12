package kingdom;

import model.Ballot;
import model.Message;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class HighPriest {
    private final static int MAX_MESSAGE_COUNT = 6;

    public List<Message> getMessagesFromBallot(Ballot ballot) {
        List<Message> messages = ballot.getMessages();
        int ballotSize = messages.size();

        if (ballotSize <= MAX_MESSAGE_COUNT) {
            return messages;
        }

        Set<Integer> randomIndexes = getRandomIndexes(ballotSize);
        return randomIndexes.stream()
                .map(messages::get)
                .collect(Collectors.toList());
    }

    private Set<Integer> getRandomIndexes(int ballotSize) {
        Random random = new Random();
        Set<Integer> randomIndexes = new HashSet<>();

        while (randomIndexes.size() < MAX_MESSAGE_COUNT) {
            randomIndexes.add(random.nextInt(ballotSize));
        }

        return randomIndexes;
    }
}
