package checker;

import model.Kingdom;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.KingdomUtil;
import util.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({KingdomUtil.class, StringUtil.class})
public class AllegianceCheckerTest {
    private AllegianceChecker allegianceChecker;

    @Before
    public void setUp() throws Exception {
        mockStatic(KingdomUtil.class);
        mockStatic(StringUtil.class);

        allegianceChecker = new AllegianceChecker();

        Map<String, String> kingdomsWithEmblems = new HashMap<>();
        kingdomsWithEmblems.put("land", "panda");
        kingdomsWithEmblems.put("air", "owl");
        kingdomsWithEmblems.put("water", "octopus");
        kingdomsWithEmblems.put("ice", "mammoth");

        when(KingdomUtil.getExistingKingdoms()).thenReturn(kingdomsWithEmblems);
        setUpMocksForEmblemUniqueChars();
        setUpMocksForFrequency();
    }

    @Test
    public void shouldReturnKingdomsWithTheSupportingKingdoms() {
        Message message1 = new Message("land", "water", "asoctopusasp");
        Message message2 = new Message("air", "ice", "alamaadfmmothoioi");

        when(StringUtil.getFrequencyOfChar("asoctopusasp", 'o')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("asoctopusasp", 'c')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("asoctopusasp", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("asoctopusasp", 'p')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("asoctopusasp", 'u')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("asoctopusasp", 's')).thenReturn(3);

        when(StringUtil.getFrequencyOfChar("alamaadfmmothoioi", 'm')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("alamaadfmmothoioi", 'a')).thenReturn(4);
        when(StringUtil.getFrequencyOfChar("alamaadfmmothoioi", 'o')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("alamaadfmmothoioi", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("alamaadfmmothoioi", 'h')).thenReturn(1);

        List<Kingdom> kingdomsWithAllies = allegianceChecker.getKingdomsWithAllies(Arrays.asList(message1, message2));

        verifyStatic(times(1));
        KingdomUtil.getExistingKingdoms();
        verifyMocksForEmblemUniqueChars();
        verifyMocksForFrequency();

        assertEquals(2, kingdomsWithAllies.size());
        assertEquals(1, kingdomsWithAllies.get(0).getAllies().size());
        assertEquals("water", kingdomsWithAllies.get(0).getAllies().get(0));
        assertEquals(1, kingdomsWithAllies.get(1).getAllies().size());
        assertEquals("ice", kingdomsWithAllies.get(1).getAllies().get(0));
    }

    @Test
    public void shouldReturnKingdomsWithEmptyAlliesIfTheMessageNotContainReceivingKingdomEmblem() {
        Message message1 = new Message("land", "water", "qwer");
        Message message2 = new Message("air", "ice", "zxcv");

        List<Kingdom> kingdomsWithAllies = allegianceChecker.getKingdomsWithAllies(Arrays.asList(message1, message2));

        verifyStatic(times(1));
        KingdomUtil.getExistingKingdoms();
        verifyMocksForEmblemUniqueChars();
        verifyMocksForFrequency();

        assertEquals(2, kingdomsWithAllies.size());
        assertEquals(0, kingdomsWithAllies.get(0).getAllies().size());
        assertEquals(0, kingdomsWithAllies.get(1).getAllies().size());
    }

    @Test
    public void shouldNotSupportIfTheReceiverIsAlsoCompetingToBeTheRuler() {
        Message message1 = new Message("land", "air", "qwert");
        Message message2 = new Message("air", "land", "octopus");

        List<Kingdom> kingdomsWithAllies = allegianceChecker.getKingdomsWithAllies(Arrays.asList(message1, message2));

        verifyStatic(times(1));
        KingdomUtil.getExistingKingdoms();
        verifyMocksForEmblemUniqueChars();
        verifyMocksForFrequency();

        assertEquals(2, kingdomsWithAllies.size());
        assertEquals(0, kingdomsWithAllies.get(0).getAllies().size());
        assertEquals(0, kingdomsWithAllies.get(1).getAllies().size());
    }

    @Test
    public void shouldSupportOnlyOnceThoughKingdomReceivesCorrectSecondMessage() {
        Message message1 = new Message("land", "water", "octopusqwas");
        Message message2 = new Message("air", "water", "octopus");

        when(StringUtil.getFrequencyOfChar("octopusqwas", 'o')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("octopusqwas", 'c')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopusqwas", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopusqwas", 'p')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("octopusqwas", 'u')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopusqwas", 's')).thenReturn(2);

        List<Kingdom> kingdomsWithAllies = allegianceChecker.getKingdomsWithAllies(Arrays.asList(message1, message2));

        verifyStatic(times(1));
        KingdomUtil.getExistingKingdoms();
        verifyMocksForEmblemUniqueChars();
        verifyMocksForFrequency();

        assertEquals(2, kingdomsWithAllies.size());
        assertEquals(1, kingdomsWithAllies.get(0).getAllies().size());
        assertEquals("water", kingdomsWithAllies.get(0).getAllies().get(0));
        assertEquals(0, kingdomsWithAllies.get(1).getAllies().size());
    }

    private void setUpMocksForEmblemUniqueChars() {
        Set<Character> owlUniqueChars = new HashSet<>(Arrays.asList('o', 'w', 'l'));
        Set<Character> pandaUniqueChars = new HashSet<>(Arrays.asList('p', 'a', 'n', 'd'));
        Set<Character> octopusUniqueChars = new HashSet<>(Arrays.asList('o', 'c', 't', 'p', 'u', 's'));
        Set<Character> mammothUniqueChars = new HashSet<>(Arrays.asList('m', 'a', 'o', 't', 'h'));

        when(StringUtil.getUniqueCharsIgnoreCase("owl")).thenReturn(owlUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("panda")).thenReturn(pandaUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("octopus")).thenReturn(octopusUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("mammoth")).thenReturn(mammothUniqueChars);
    }

    private void setUpMocksForFrequency() {
        when(StringUtil.getFrequencyOfChar("owl", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("owl", 'w')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("owl", 'l')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("panda", 'p')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("panda", 'a')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("panda", 'n')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("panda", 'd')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("octopus", 'o')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("octopus", 'c')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopus", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopus", 'p')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopus", 'u')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octopus", 's')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("mammoth", 'm')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("mammoth", 'a')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 'h')).thenReturn(1);
    }

    private void verifyMocksForEmblemUniqueChars() {
        verifyStatic(times(1));
        StringUtil.getUniqueCharsIgnoreCase("owl");
        verifyStatic(times(1));
        StringUtil.getUniqueCharsIgnoreCase("panda");
        verifyStatic(times(1));
        StringUtil.getUniqueCharsIgnoreCase("octopus");
        verifyStatic(times(1));
        StringUtil.getUniqueCharsIgnoreCase("mammoth");
    }

    private void verifyMocksForFrequency() {
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("owl", 'o');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("owl", 'w');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("owl", 'l');

        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("panda", 'p');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("panda", 'a');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("panda", 'n');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("panda", 'd');

        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 'o');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 'c');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 't');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 'p');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 'u');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("octopus", 's');

        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("mammoth", 'm');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("mammoth", 'a');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("mammoth", 'o');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("mammoth", 't');
        verifyStatic(times(1));
        StringUtil.getFrequencyOfChar("mammoth", 'h');
    }
}
