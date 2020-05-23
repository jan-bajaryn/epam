package by.epam.cafe.service.email.impl;

import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

public class MailSenderImplTest {
    private final MailSenderImpl mailSenderImpl = new MailSenderImpl();

    @Test
    public void testSendRegistration() {
        boolean result = mailSenderImpl.sendRegistration("andreybarencov@gmail.com", "hahahahahaha", Locale.getDefault(), parameters);
        assertTrue(result);
    }
}