package util;

import java.util.Arrays;
import java.util.List;

public class KingdomUtil {
    private static List<String> existingKingdoms = Arrays.asList("air", "land", "ice", "space", "water", "fire");

    public static List<String> getExistingKingdoms() {
        return existingKingdoms;
    }
}
