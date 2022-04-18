package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowSecretCodePageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowSecretCodePageCommand.class);
    private final RequestFactory requestFactory;

    public ShowSecretCodePageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.SECRETCODE_PAGE.getPath());
    }
}
