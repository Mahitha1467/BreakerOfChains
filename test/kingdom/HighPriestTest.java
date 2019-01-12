package kingdom;

import model.Ballot;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HighPriest.class)
public class HighPriestTest {
    @Mock
    private Random random;

    private HighPriest highPriest;

    @Before
    public void setUp() throws Exception {
        highPriest = new HighPriest();
    }

    @Test
    public void shouldGetAllTheMessagesFromBallotIfTheBallotSizeIsBelow6() {
        Message message1 = new Message("land", "air", "qwert");
        Message message2 = new Message("land", "water", "zxcvb");
        Ballot ballot = new Ballot();
        ballot.add(message1);
        ballot.add(message2);

        List<Message> messagesFromBallot = highPriest.getMessagesFromBallot(ballot);

        assertEquals(Arrays.asList(message1, message2), messagesFromBallot);
    }

    @Test
    public void shouldGetAllTheMessagesFromBallotIfTheBallotSizeIsEqualTo6() {
        Message message1 = new Message("land", "air", "qwert");
        Message message2 = new Message("land", "water", "zxcvb");
        Message message3 = new Message("land", "fire", "zxcvb1");
        Message message4 = new Message("air", "space", "zxcvb2");
        Message message5 = new Message("air", "ice", "zxcvb34");
        Message message6 = new Message("air", "water", "zxcvb56");
        Ballot ballot = new Ballot();
        ballot.add(message1);
        ballot.add(message2);
        ballot.add(message3);
        ballot.add(message4);
        ballot.add(message5);
        ballot.add(message6);

        List<Message> messagesFromBallot = highPriest.getMessagesFromBallot(ballot);

        assertEquals(Arrays.asList(message1, message2, message3, message4, message5, message6), messagesFromBallot);
    }

    @Test
    public void shouldGetRandom6MessagesFromTheBallot() throws Exception {
        Message message1 = new Message("land", "air", "qwert");
        Message message2 = new Message("land", "water", "zxcvbqw");
        Message message3 = new Message("land", "fire", "zxcvbas");
        Message message4 = new Message("air", "land", "zxcvbdf");
        Message message5 = new Message("air", "water", "zxcvbrt");
        Message message6 = new Message("air", "fire", "zxcvbyu");
        Message message7 = new Message("water", "land", "zxcvbiu");
        Message message8 = new Message("water", "air", "zxcvbjh");

        Ballot ballot = new Ballot();
        ballot.add(message1);
        ballot.add(message2);
        ballot.add(message3);
        ballot.add(message4);
        ballot.add(message5);
        ballot.add(message6);
        ballot.add(message7);
        ballot.add(message8);

        whenNew(Random.class).withNoArguments().thenReturn(random);
        when(random.nextInt(8))
                .thenReturn(4)
                .thenReturn(6)
                .thenReturn(2)
                .thenReturn(4)
                .thenReturn(5)
                .thenReturn(7)
                .thenReturn(0);


        List<Message> messagesFromBallot = highPriest.getMessagesFromBallot(ballot);

        assertEquals(6, messagesFromBallot.size());
        assertEquals(Arrays.asList(message1, message3, message5, message6, message7, message8), messagesFromBallot);
    }
}
