package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowAddCarPageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowAddCarPageCommand.class);
    private final RequestFactory requestFactory;

    public ShowAddCarPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.ADD_CAR_PAGE.getPath());
    }
}
