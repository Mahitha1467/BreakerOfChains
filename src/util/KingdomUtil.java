package util;

import java.util.Arrays;
import java.util.List;

public class KingdomUtil {
    private static List<String> existingKingdomsNames = Arrays.asList("air", "land", "ice", "space", "water", "fire");

    public static List<String> getExistingKingdomsNames() {
        return existingKingdomsNames;
    }
}
