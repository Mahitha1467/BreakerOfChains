package checker;

import model.Kingdom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.StringUtil.ZERO;

public class RulerChecker {
    private static final int ONE = 1;

    public boolean isRulerExist(List<Kingdom> kingdoms) {
        List<Kingdom> tiedKingdoms = getTiedKingdoms(kingdoms);
        return tiedKingdoms.size() == ONE && tiedKingdoms.get(ZERO).getAllies().size() >= ONE;
    }

    public List<String> getTiedKingdomNames(List<Kingdom> kingdoms) {
        List<Kingdom> tiedKingdoms = getTiedKingdoms(kingdoms);
        return tiedKingdoms.stream()
                .map(Kingdom::getName)
                .collect(Collectors.toList());
    }

    private List<Kingdom> getTiedKingdoms(List<Kingdom> kingdoms) {
        int maxCount = ZERO;
        List<Kingdom> tiedKingdoms = new ArrayList<>();

        for (Kingdom kingdom : kingdoms) {
            if (kingdom.getAllies().size() > maxCount) {
                tiedKingdoms = new ArrayList<>();
                tiedKingdoms.add(kingdom);
                maxCount = kingdom.getAllies().size();
            } else if(kingdom.getAllies().size() == maxCount) {
                tiedKingdoms.add(kingdom);
            }
        }

        return tiedKingdoms;
    }

    public Kingdom getRuler(List<Kingdom> kingdoms) {
        return getTiedKingdoms(kingdoms).get(ZERO);
    }
}
