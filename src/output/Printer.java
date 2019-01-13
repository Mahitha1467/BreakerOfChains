package output;

import model.Kingdom;
import util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import static util.StringUtil.getCapitalize;

public class Printer {

    public void printRulerDetails(Kingdom kingdom) {
        System.out.println("Who is the ruler of Southeros?");
        System.out.println(kingdom.getName() == null ? "None" : getCapitalize(kingdom.getName()));
        System.out.println("Allies of Ruler?");
        System.out.println(kingdom.getAllies() == null ? "None" : getFormattedAllies(kingdom.getAllies()));
    }

    private String getFormattedAllies(List<String> allies) {
        String capitalizedString = allies.stream()
                .map(StringUtil::getCapitalize)
                .collect(Collectors.toList())
                .toString();
        return StringUtil.removeFirstAndLastChars(capitalizedString);
    }

    public void printBallotResults(List<Kingdom> kingdoms, int ballotRound) {
        System.out.println(String.format("Results after round %s ballot count", ballotRound));
        kingdoms.forEach(kingdom -> {
            System.out.println(
                    String.format("Allies for %s : %s",
                            getCapitalize(kingdom.getName()),
                            kingdom.getAllies().size()
                    )
            );
        });
    }
}
