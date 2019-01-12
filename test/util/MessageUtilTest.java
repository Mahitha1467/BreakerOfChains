package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageUtilTest {

    @Test
    public void shouldReturnTheMessageForTheGivenIndex() {
        assertEquals("Go risk it all", MessageUtil.getMessage(4));
    }

    @Test
    public void shouldReturnEmptyIfTheGivenIndexIsMoreThanListSize() {
        assertEquals("", MessageUtil.getMessage(100));
    }

    @Test
    public void shouldReturnTheSizeOfTheMessageList() {
        assertEquals(25, MessageUtil.getMessageListSize());
    }
}
