package util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class KingdomUtilTest {

    @Test
    public void shouldGetAllKingdomsWithTheirEmblems() {
        Map<String, String> expectedKingdoms = new HashMap<>();
        expectedKingdoms.put("land", "panda");
        expectedKingdoms.put("water", "octopus");
        expectedKingdoms.put("ice", "mammoth");
        expectedKingdoms.put("air", "owl");
        expectedKingdoms.put("fire", "dragon");
        expectedKingdoms.put("space", "gorilla");

        Map<String, String> existingKingdoms = KingdomUtil.getExistingKingdoms();

        assertEquals(expectedKingdoms, existingKingdoms);
    }
}
