package model.email;

import java.util.*;
import java.io.Serializable;

import javax.mail.*;
import javax.mail.internet.*;

@SuppressWarnings("serial")
public class MailSender implements Serializable{

    private final String USERNAME = "carpnd2018@gmail.com";
    private final String PASSWORD = "grupob2018";

    public void send(String to, String subject, String body) {
        try {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

   
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (Exception e) {
           
        }
    }
}
