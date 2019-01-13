package util;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {
    public static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String EMPTY_STRING = "";

    public static int getFrequencyOfChar(String string, Character character) {
        int frequency = ZERO;
        string = string.toLowerCase();
        char lowerCaseChar = Character.toLowerCase(character);

        while(string.length() > ZERO) {
            int firstIndex = string.indexOf(lowerCaseChar);
            int lastIndex = string.lastIndexOf(lowerCaseChar);

            if(firstIndex >= ZERO) {
                frequency++;
            }
            if(firstIndex >= ZERO && lastIndex != firstIndex) {
                frequency++;
                string = string.substring(firstIndex + 1, lastIndex);
            } else {
                string = "";
            }
        }

        return frequency;
    }

    public static Set<Character> getUniqueCharsIgnoreCase(String string) {
        String lowerCaseString = string.toLowerCase();
        int length = lowerCaseString.length();
        Set<Character> unique = new HashSet<>();

        for(int i = 0; i< length; i++) {
            unique.add(lowerCaseString.charAt(i));
        }

        return unique;
    }

    public static String getCapitalize(String string) {
        if(string == null || EMPTY_STRING.equals(string)) {
            return EMPTY_STRING;
        }

        char firstCharToUpperCase = Character.toUpperCase(string.charAt(ZERO));

        StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder.replace(ZERO, ZERO + ONE, Character.toString(firstCharToUpperCase)).toString();
    }

    public static String removeFirstAndLastChars(String string) {
        if(string == null || EMPTY_STRING.equals(string) || string.length() < 2) {
            return EMPTY_STRING;
        }

        StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder
                .replace(ZERO, ZERO + ONE, "")
                .replace(stringBuilder.length() - ONE, stringBuilder.length(), "")
                .toString();
    }
}
