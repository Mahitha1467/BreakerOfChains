package util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void shouldReturnZeroIfTheGivenStringIsEmpty() {
        int frequency = StringUtil.getFrequencyOfChar("", 'a');

        assertEquals(0, frequency);
    }

    @Test
    public void shouldReturnOneWhenTheGivenCharExistsOnlyOnceInTheGivenString() {
        int frequency = StringUtil.getFrequencyOfChar("abcd", 'a');

        assertEquals(1, frequency);
    }

    @Test
    public void shouldReturnThreeIfCharExistsThreeTimesInTheGivenString() {
        int frequency = StringUtil.getFrequencyOfChar("abcdarefa", 'a');

        assertEquals(3, frequency);
    }

    @Test
    public void shouldReturnFrequencyByIgnoringCase() {
        int frequency = StringUtil.getFrequencyOfChar("abcDAarefA", 'a');

        assertEquals(4, frequency);
    }

    @Test
    public void shouldReturnAllTheCharsWhenThereIsNoCharRepeats() {
        String input = "adobe";
        Set<Character> expected = new HashSet<>();
        expected.add('a');
        expected.add('d');
        expected.add('o');
        expected.add('b');
        expected.add('e');

        Set<Character> uniqueChars = StringUtil.getUniqueCharsIgnoreCase(input);

        assertEquals(expected.size(), uniqueChars.size());
        assertEquals(expected, uniqueChars);
    }

    @Test
    public void shouldReturnOnlyUniqueCharsWhenThereIsCharRepeats() {
        String input = "adobe ad";
        Set<Character> expected = new HashSet<>();
        expected.add('a');
        expected.add('d');
        expected.add('o');
        expected.add('b');
        expected.add('e');
        expected.add(' ');

        Set<Character> uniqueChars = StringUtil.getUniqueCharsIgnoreCase(input);

        assertEquals(expected.size(), uniqueChars.size());
        assertEquals(expected, uniqueChars);
    }

    @Test
    public void shouldIgnoreCaseWhileCheckingForTheCharRepeat() {
        String input = "adobeAD";
        Set<Character> expected = new HashSet<>();
        expected.add('a');
        expected.add('d');
        expected.add('o');
        expected.add('b');
        expected.add('e');

        Set<Character> uniqueChars = StringUtil.getUniqueCharsIgnoreCase(input);

        assertEquals(expected.size(), uniqueChars.size());
        assertEquals(expected, uniqueChars);
    }

    @Test
    public void shouldReturnEmptyStringIfTheGivenStringIsNull() {
        assertEquals("", StringUtil.getCapitalize(""));
    }

    @Test
    public void shouldReturnEmptyStringIfTheGivenStringIsEmpty() {
        assertEquals("", StringUtil.getCapitalize(""));
    }

    @Test
    public void shouldReturnByCapitalizingTheGivenString() {
        assertEquals("Arohi", StringUtil.getCapitalize("arohi"));
    }

    @Test
    public void shouldReturnEmptyIfTheGivenStringIsNull() {
        assertEquals("", StringUtil.removeFirstAndLastChars(null));
    }

    @Test
    public void shouldReturnEmptyIfTheGivenStringIsEmpty() {
        assertEquals("", StringUtil.removeFirstAndLastChars(""));
    }

    @Test
    public void shouldReturnStringByRemovingFirstAndLastCharsIfTheGivenStringLengthIsMoreThan2() {
        assertEquals("bc", StringUtil.removeFirstAndLastChars("abcd"));
    }
}
