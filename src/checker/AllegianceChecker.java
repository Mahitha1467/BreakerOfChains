package checker;

import model.Kingdom;
import model.Message;
import util.KingdomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static util.StringUtil.ZERO;
import static util.StringUtil.getFrequencyOfChar;
import static util.StringUtil.getUniqueCharsIgnoreCase;


public class AllegianceChecker {

    public List<Kingdom> getKingdomsWithAllies(List<Message> messages) {
        Map<String, String> kingdomsWithEmblems = KingdomUtil.getExistingKingdoms();
        Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies = getKingdomsWithEmblemFrequencies(kingdomsWithEmblems);
        Map<String, List<String>> kingdomsWithAllies = checkForAllies(messages, kingdomsWithEmblemFrequencies);
        return getKingdomObject(kingdomsWithAllies);
    }

    private Map<String, Map<Character, Integer>> getKingdomsWithEmblemFrequencies(Map<String, String> kingdomsWithEmblems) {
        Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies = new HashMap<>();
        kingdomsWithEmblems.keySet()
                .forEach(kingdomName ->
                    kingdomsWithEmblemFrequencies.put(kingdomName,
                            getFrequencyOfUniqueChars(kingdomsWithEmblems.get(kingdomName)))
                );

        return kingdomsWithEmblemFrequencies;
    }

    private Map<Character, Integer> getFrequencyOfUniqueChars(String emblem) {
        Set<Character> uniqueCharsOfEmblem = getUniqueCharsIgnoreCase(emblem);
        Map<Character, Integer> frequencyOfUniqueCharsOfEmblem = new HashMap<>();

        uniqueCharsOfEmblem.forEach(uniqueChar ->
                frequencyOfUniqueCharsOfEmblem.put(uniqueChar, getFrequencyOfChar(emblem, uniqueChar))
        );

        return frequencyOfUniqueCharsOfEmblem;
    }

    private Map<String, List<String>> checkForAllies(List<Message> messages, Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies) {
        List<String> competingKingdoms = getCompetingKingdoms(messages);
        List<String> allies = new ArrayList<>();
        Map<String, List<String>> kingdomsWithAllies = initializeKingdomsWithEmptyAllies(messages);

        messages.forEach(message -> {
            String sender = message.getSender();
            String receiver = message.getReceiver();
            Map<Character, Integer> emblemFrequency = kingdomsWithEmblemFrequencies.get(receiver);

            boolean isKingdomCanSupport = isKingdomCanSupport(competingKingdoms, allies, receiver);

            if(isKingdomCanSupport && isKingdomSupports(message.getContent(), emblemFrequency)) {
                kingdomsWithAllies.get(sender).add(receiver);
                allies.add(receiver);
            }
        });

        return kingdomsWithAllies;
    }

    private List<String> getCompetingKingdoms(List<Message> messages) {
        return messages.stream()
                .map(Message::getSender)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> initializeKingdomsWithEmptyAllies(List<Message> messages) {
        Map<String, List<String>> kingdomsWithAllies = new HashMap<>();
        messages.forEach(message -> kingdomsWithAllies.put(message.getSender(), new ArrayList<>()));
        return kingdomsWithAllies;
    }

    private boolean isKingdomCanSupport(List<String> competingKingdoms, List<String> allies, String receiver) {
        return !competingKingdoms.contains(receiver) && !allies.contains(receiver);
    }

    private boolean isKingdomSupports(String message, Map<Character, Integer> emblemFrequency) {
        if(message.length() < emblemFrequency.keySet().size()) {
            return false;
        }
        return emblemFrequency.keySet()
                .stream()
                .allMatch(character ->
                        emblemFrequency.get(character).compareTo(getFrequencyOfChar(message, character)) <= ZERO
                );
    }

    private List<Kingdom> getKingdomObject(Map<String, List<String>> kingdomsWithAllies) {
        return kingdomsWithAllies.keySet()
                .stream()
                .map(kingdom -> new Kingdom(kingdom, kingdomsWithAllies.get(kingdom)))
                .collect(Collectors.toList());
    }
}
