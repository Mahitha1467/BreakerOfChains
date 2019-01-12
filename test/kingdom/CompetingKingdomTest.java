package kingdom;

import message.MessageGenerator;
import model.Ballot;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.KingdomUtil;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({KingdomUtil.class, CompetingKingdom.class})
public class CompetingKingdomTest {
    @Mock
    private MessageGenerator messageGenerator;

    @Mock
    private Ballot ballot;

    private CompetingKingdom competingKingdom;
    private String kingdomName = "land";

    @Before
    public void setUp() throws Exception {
        competingKingdom = new CompetingKingdom(kingdomName);
    }

    @Test
    public void shouldAddMessageToBallotToSendToOtherKingdoms() throws Exception {
        mockStatic(KingdomUtil.class);
        List<String> allKingdoms = Arrays.asList("air", "land", "water");
        Message message1 = new Message(kingdomName, "air", "qwert");
        Message message2 = new Message(kingdomName, "water", "zxcvbb");

        when(KingdomUtil.getExistingKingdomsNames()).thenReturn(allKingdoms);
        when(messageGenerator.generateMessage())
                .thenReturn("qwert")
                .thenReturn("zxcvbb");
        whenNew(Message.class).withArguments(kingdomName, "air", "qwert").thenReturn(message1);
        whenNew(Message.class).withArguments(kingdomName, "water", "zxcvbb").thenReturn(message2);

        competingKingdom.addMessagesToBallotToSendToOtherKingdoms(messageGenerator, ballot);

        verifyStatic(times(1));
        KingdomUtil.getExistingKingdomsNames();
        verify(messageGenerator, times(2)).generateMessage();
        verify(ballot, times(1)).add(message1);
        verify(ballot, times(1)).add(message2);
    }
}
