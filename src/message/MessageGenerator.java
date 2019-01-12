package message;

import util.MessageUtil;

import java.util.Random;

public class MessageGenerator {

    public String generateMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(MessageUtil.getMessageListSize());
        return MessageUtil.getMessage(randomIndex);
    }
}
