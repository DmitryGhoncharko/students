package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowRegistrationPageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowRegistrationPageCommand.class);
    private final RequestFactory requestFactory;

    public ShowRegistrationPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.REGISTRATION_PAGE.getPath());
    }
}
