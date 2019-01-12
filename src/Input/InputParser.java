package Input;

import exception.InvalidInputException;
import util.KingdomUtil;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private final static String EMPTY_STRING = "";
    private final static String INPUT_SEPARATOR = " ";

    public List<String> parse(String input) throws InvalidInputException {
        if(input == null || EMPTY_STRING.equals(input)) {
            throw new InvalidInputException("Input cannot be null/empty");
        }

        List<String> competingKingdoms = Arrays.asList(input.toLowerCase().split(INPUT_SEPARATOR));
        if(!KingdomUtil.getExistingKingdoms().containsAll(competingKingdoms)) {
            throw new InvalidInputException("Given input should contain only existing kingdoms. Existing kingdoms are" +
                    " 'Air, Land, Ice, Water, Space, Fire' and expected format is 'Air Space'");
        }

        return competingKingdoms;
    }
}
