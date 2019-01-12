package input;


import Input.InputParser;
import exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.KingdomUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KingdomUtil.class)
public class InputParserTest {
    private InputParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new InputParser();
    }

    @Test
    public void shouldThrowExceptionWhenTheGivenStringIsNull() {
        try {
            parser.parse("");
        } catch (InvalidInputException e) {
            assertEquals("Input cannot be null/empty", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenTheGivenStringIsEmpty() {
        try {
            parser.parse("");
        } catch (InvalidInputException e) {
            assertEquals("Input cannot be null/empty", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfTheGivenInputKingdomDoesNotExist() {
        try {
            parser.parse("Air Space1");
        } catch (InvalidInputException e) {
            assertEquals("Given input should contain only existing kingdoms. Existing kingdoms are " +
                    "'Air, Land, Ice, Water, Space, Fire' and expected format is 'Air Space'", e.getMessage());
        }
    }

    @Test
    public void shouldReturnListOfInputKingdomsIfTheGivenFormatIsCorrect() throws InvalidInputException {
        List<String> formatterInput = parser.parse("Air space");
        assertEquals(Arrays.asList("air", "space"), formatterInput);
    }
}
