package message;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.MessageUtil;

import java.util.Random;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageUtil.class, MessageGenerator.class})
public class MessageGeneratorTest {
    @Mock
    private Random random;

    private MessageGenerator messageGenerator;

    @Before
    public void setUp() throws Exception {
        messageGenerator = new MessageGenerator();
    }

    @Test
    public void shouldReturnTheMessage() throws Exception {
        mockStatic(MessageUtil.class);

        whenNew(Random.class).withNoArguments().thenReturn(random);
        when(MessageUtil.getMessageListSize()).thenReturn(5);
        when(random.nextInt(5)).thenReturn(3);
        when(MessageUtil.getMessage(3)).thenReturn("qwert");

        String message = messageGenerator.generateMessage();

        Assert.assertEquals("qwert", message);
    }
}
