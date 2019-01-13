package util;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {
    public static final int ZERO = 0;

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
}
