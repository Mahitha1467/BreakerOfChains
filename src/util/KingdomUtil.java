package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KingdomUtil {
    private static List<String> existingKingdomsNames = Arrays.asList("air", "land", "ice", "space", "water", "fire");

    private static Map<String, String> existingKingdoms = new HashMap<>();

    static {
        existingKingdoms.put("land", "panda");
        existingKingdoms.put("water", "octopus");
        existingKingdoms.put("ice", "mammoth");
        existingKingdoms.put("air", "owl");
        existingKingdoms.put("fire", "dragon");
    }

    public static List<String> getExistingKingdomsNames() {
        return existingKingdomsNames;
    }

    public static Map<String, String> getExistingKingdoms() {
        return existingKingdoms;
    }
}
