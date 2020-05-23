package by.epam.cafe.service.email.impl;

import by.epam.cafe.service.email.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class MailSenderImpl implements MailSender {
    private static final Logger log = LogManager.getLogger(MailSenderImpl.class);


    private static final String LOGIN = "appdemo242423k@gmail.com";
    private static final String PASSWORD = "sdfsdf2343dsfwe432_#";
    private static final String host = "smtp.gmail.com";

    private static final Properties props = System.getProperties();


    static {
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
    }

    public boolean sendRegistration(String email, String url, Locale locale, TreeMap<String, String> parameters) {
        log.debug("parameters = {}", parameters);
        try {
            locale = Locale.getDefault();
            ResourceBundle rb = ResourceBundle.getBundle("property.text", locale);
            String msg = rb.getString("email.registration.confirm");

            log.debug("msg = {}", msg);

            Session session = Session.getInstance(props);
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setSubject("Confirm registration email", "UTF-8");
            message.setText("Confirm registration email", "UTF-8");

            message.setFrom(new InternetAddress(LOGIN));
            InternetAddress toAddress = new InternetAddress(email);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Confirm registration email");
            message.setContent(message(url, msg, parameters), "text/html; charset=UTF-8");
            try (Transport transport = session.getTransport("smtps")) {
                transport.connect(host, LOGIN, PASSWORD);
                transport.sendMessage(message, message.getAllRecipients());
            }
            return true;
        } catch (MessagingException e) {
            log.debug("exception in email: ", e);
            return false;
        }
    }

    private String message(String url, String msg, TreeMap<String, String> parameters) {
        StringBuilder sb = new StringBuilder();
        //language=HTML
        sb.append("<div>").append(msg).append("</div>");
        //language=HTML
        sb.append("<form method='post' action='").append(url).append("'>");
        //language=HTML
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            sb.append("<input name='").append(entry.getKey())
                    .append("' type='hidden' value='")
                    .append(entry.getValue()).append("'>");
        }
        //language=HTML
        sb.append("<button type='submit'>Submit</button>");
//        language=HTML
        sb.append("</form>");
        return sb.toString();
    }

}
