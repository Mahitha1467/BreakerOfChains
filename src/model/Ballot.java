package model;

import java.util.ArrayList;
import java.util.List;

public class Ballot {
    List<Message> messages = new ArrayList<>();

    public void add(Message message) {
        messages.add(message);
    }
}
