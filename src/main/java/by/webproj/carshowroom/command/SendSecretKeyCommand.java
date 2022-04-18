package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.mailsender.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSecretKeyCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(SendSecretKeyCommand.class);
    private final MailSender mailSender;
    private final RequestFactory requestFactory;

    public SendSecretKeyCommand(MailSender mailSender, RequestFactory requestFactory) {
        this.mailSender = mailSender;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        mailSender.sendMessage();
        return requestFactory.createRedirectResponse("/controller?command=registration");
    }
}
