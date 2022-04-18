package by.webproj.carshowroom.mailsender;

import by.webproj.carshowroom.mailsender.secretkeygenerator.SecretKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSender implements MailSender{
    private static final Logger LOG = LoggerFactory.getLogger(GmailSender.class);
    private final SecretKeyGenerator secretKeyGenerator;

    public GmailSender(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

    @Override
    public void sendMessage() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

         Session session = Session.getDefaultInstance(properties, new Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication("dafae722@gmail.com","kvariufafvfdhvvh");
             }
         });
        try{
             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress("dafae722@gmail.com"));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress("gilnastya8@gmail.com"));
             message.setSubject("Секретный код для создания админ аккаунта");
             message.setText("Ваш секретный код  - " + secretKeyGenerator.generateSecretKey());
             Transport.send(message);
             LOG.info("Email was sent with success");
        }catch (MessagingException messagingException){
            LOG.error("Message dont send", messagingException);
        }
    }
}
