package by.epam.cafe.service.my;

import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmails {

    private static final String LOGIN = "appdemo242423k@gmail.com";
    private static final String PASSWORD = "sdfsdf2343dsfwe432_#";

    @Test
    public void abc() throws MessagingException {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.username", LOGIN);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");
        message.setSubject("aaa", "UTF-8");
        message.setText("BBBBBBBBBBB", "UTF-8");

        try {
            message.setFrom(new InternetAddress(LOGIN));
            InternetAddress toAddress = new InternetAddress("andreybarencov@gmail.com");
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject("some subject");
            message.setText("body");
            Transport transport = session.getTransport("smtps");
            transport.connect(host, LOGIN, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ignored) {
        }
    }
}