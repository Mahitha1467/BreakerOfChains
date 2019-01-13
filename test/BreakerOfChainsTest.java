import Input.ConsoleInput;
import Input.InputParser;
import checker.AllegianceChecker;
import checker.RulerChecker;
import kingdom.CompetingKingdom;
import kingdom.HighPriest;
import message.MessageGenerator;
import model.Ballot;
import model.Kingdom;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import output.Printer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BreakerOfChains.class)
public class BreakerOfChainsTest {
    @Mock
    private MessageGenerator messageGenerator;

    @Mock
    private Ballot ballot;

    @Mock
    private HighPriest highPriest;

    @Mock
    private AllegianceChecker allegianceChecker;

    @Mock
    private RulerChecker rulerChecker;

    @Mock
    private Printer printer;

    @Mock
    private ConsoleInput consoleInput;

    @Mock
    private InputParser parser;

    @Mock
    private Kingdom kingdom;

    @Mock
    private CompetingKingdom competingKingdom;

    private BreakerOfChains breakerOfChains;

    @Before
    public void setUp() throws Exception {
        breakerOfChains = new BreakerOfChains(consoleInput, parser, messageGenerator, ballot, highPriest,
                allegianceChecker, rulerChecker, printer);
    }

    @Test
    public void shouldRunBallotProcessTillRulerFind() throws Exception {
        String input = "Air Land";
        List<String> inputs = Arrays.asList("air", "land");
        Message message1 = new Message("air", "water", "octopus1");
        Message message2 = new Message("air", "space", "rwerwer");
        Message message3 = new Message("air", "ice", "mammoth23");
        Message message4 = new Message("land", "ice", "ladlsdkdl");
        Message message5 = new Message("land", "space", "gorillasf");
        Message message6 = new Message("land", "fire", "dragon1llk");

        Message message21 = new Message("air", "water", "octopus1");
        Message message22 = new Message("air", "space", "rwerwer");
        Message message23 = new Message("air", "ice", "mamoth23");
        Message message24 = new Message("land", "ice", "ladlsdkdl");
        Message message25 = new Message("land", "space", "gorillasf");
        Message message26 = new Message("land", "fire", "dragon1llk");

        List<Message> firstRandom6Messages = Arrays.asList(message1, message2, message3, message4, message5, message6);
        List<Message> secondRandom6Messages = Arrays.asList(message21, message22, message23, message24, message25, message26);
        List<Kingdom> firstKingdomsWithAllies = Arrays.asList(
          new Kingdom("air", Arrays.asList("water", "ice")),
          new Kingdom("land", Arrays.asList("space", "fire"))
        );
        List<Kingdom> secondRoundKingdomsWithAllies = Arrays.asList(
          new Kingdom("air", Collections.singletonList("water")),
          new Kingdom("land", Arrays.asList("space", "fire"))
        );

        whenNew(Kingdom.class).withNoArguments().thenReturn(kingdom);
        when(consoleInput.getInput()).thenReturn(input);
        when(parser.parse(input)).thenReturn(inputs);
        whenNew(CompetingKingdom.class).withArguments("air").thenReturn(competingKingdom);
        whenNew(CompetingKingdom.class).withArguments("land").thenReturn(competingKingdom);
        doNothing().when(competingKingdom).addMessagesToBallotToSendToOtherKingdoms(messageGenerator, ballot);
        when(highPriest.getMessagesFromBallot(ballot))
                .thenReturn(firstRandom6Messages)
                .thenReturn(secondRandom6Messages);
        when(allegianceChecker.getKingdomsWithAllies(firstRandom6Messages)).thenReturn(firstKingdomsWithAllies);
        when(allegianceChecker.getKingdomsWithAllies(secondRandom6Messages)).thenReturn(secondRoundKingdomsWithAllies);
        when(rulerChecker.isRulerExist(firstKingdomsWithAllies)).thenReturn(false);
        when(rulerChecker.isRulerExist(secondRoundKingdomsWithAllies)).thenReturn(true);
        when(rulerChecker.getTiedKingdomNames(firstKingdomsWithAllies)).thenReturn(inputs);
        when(rulerChecker.getRuler(secondRoundKingdomsWithAllies)).thenReturn(kingdom);
        doNothing().when(printer).printRulerDetails(kingdom);

        breakerOfChains.findRuler();

        verifyNew(Kingdom.class, times(1)).withNoArguments();
        verifyNew(CompetingKingdom.class, times(2)).withArguments("air");
        verifyNew(CompetingKingdom.class, times(2)).withArguments("land");
        verify(consoleInput, times(1)).getInput();
        verify(parser, times(1)).parse(input);
        verify(competingKingdom, times(4)).addMessagesToBallotToSendToOtherKingdoms(messageGenerator, ballot);
        verify(highPriest, times(2)).getMessagesFromBallot(ballot);
        verify(allegianceChecker, times(1)).getKingdomsWithAllies(firstRandom6Messages);
        verify(allegianceChecker, times(1)).getKingdomsWithAllies(secondRandom6Messages);
        verify(rulerChecker, times(1)).isRulerExist(firstKingdomsWithAllies);
        verify(rulerChecker, times(1)).isRulerExist(secondRoundKingdomsWithAllies);
        verify(rulerChecker, times(1)).getTiedKingdomNames(firstKingdomsWithAllies);
        verify(rulerChecker, times(1)).getRuler(secondRoundKingdomsWithAllies);
        verify(printer, times(2)).printRulerDetails(kingdom);
    }
}
