package checker;

import model.Kingdom;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RulerCheckerTest {

    private RulerChecker rulerChecker;

    @Before
    public void setUp() throws Exception {
        rulerChecker = new RulerChecker();
    }

    @Test
    public void shouldReturnFalseIfTheGivenStingIsEmpty() {
        assertFalse(rulerChecker.isRulerExist(new ArrayList<>()));
    }

    @Test
    public void shouldReturnFalseIfTheGivenListContainsOnlyOneKingdomButWithEmptyAllies() {
        assertFalse(rulerChecker.isRulerExist(Collections.singletonList(new Kingdom("land", new ArrayList<>()))));
    }

    @Test
    public void shouldReturnTrueIfRulerExist() {
        List<Kingdom> kingdoms = Collections.singletonList(new Kingdom("land", Collections.singletonList("air")));
        assertTrue(rulerChecker.isRulerExist(kingdoms));
    }

    @Test
    public void shouldReturnAllKingdomsNamesIfTheirAlliesNumberIsEqual() {
        List<Kingdom> kingdoms = Arrays.asList(
                new Kingdom("land", Arrays.asList("water", "space")),
                new Kingdom("air", Arrays.asList("fire", "ice"))
        );

        List<String> tiedKingdoms = rulerChecker.getTiedKingdomNames(kingdoms);

        assertEquals(Arrays.asList("land", "air"), tiedKingdoms);
    }

    @Test
    public void shouldReturnKingdomsWhichHasMaxNoOfAllies() {
        List<Kingdom> kingdoms = Arrays.asList(
                new Kingdom("land", Collections.singletonList("water")),
                new Kingdom("air", Collections.singletonList("fire")),
                new Kingdom("space", new ArrayList<>())
        );

        List<String> tiedKingdoms = rulerChecker.getTiedKingdomNames(kingdoms);

        assertEquals(Arrays.asList("land", "air"), tiedKingdoms);
    }
}
