package by.webproj.carshowroom.mailsender;

import by.webproj.carshowroom.mailsender.secretkeygenerator.SecretKeyGenerator;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaxMailSender implements MailSender{
    private static final Logger LOG = LoggerFactory.getLogger(JavaxMailSender.class);
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_MAIL_SENDER_FILE_NAME = "mailsender.properties";
    private static final String PROPERTIES_TO_MESSAGE_SEND = "ms.to";
    private static final String PROPERTIES_FROM_MESSAGE_SEND = "ms.from";
    private static final String PROPERTIES_MAIL_SERVER_HOST = "ms.host";
    private static final String TO_MESSAGE_SEND;
    private static final String FROM_MESSAGE_SEND;
    private static final String MAIL_SERVER_HOST;
    private final SecretKeyGenerator secretKeyGenerator;

    public JavaxMailSender(SecretKeyGenerator secretKeyGenerator) {
        this.secretKeyGenerator = secretKeyGenerator;
    }

    static {
        try (InputStream inputStream = HikariCPConnectionPool.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_MAIL_SENDER_FILE_NAME)) {
            PROPERTIES.load(inputStream);
            TO_MESSAGE_SEND = PROPERTIES.getProperty(PROPERTIES_TO_MESSAGE_SEND);
            FROM_MESSAGE_SEND = PROPERTIES.getProperty(PROPERTIES_FROM_MESSAGE_SEND);
            MAIL_SERVER_HOST = PROPERTIES.getProperty(PROPERTIES_MAIL_SERVER_HOST);
        }catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException", e);
            throw new RuntimeException("FileNotFoundException", e);
        } catch (IOException e) {
            LOG.error("IOException", e);
            throw new RuntimeException("IOException", e);
        }
        LOG.info("Mail sender initialize correctly");
    }
    @Override
    public void sendMessage() {
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", MAIL_SERVER_HOST);
        final Session session = Session.getDefaultInstance(properties);
        try{
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_MESSAGE_SEND));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_MESSAGE_SEND));
            message.setSubject("Секретный код для создания админ аккаунта");
            message.setText("Ваш секретный код  - " + secretKeyGenerator.generateSecretKey());
            Transport.send(message);
            LOG.info("Email was sent with success");
        }catch (MessagingException messagingException){
            LOG.error("Message dont send", messagingException);
        }
    }
}
