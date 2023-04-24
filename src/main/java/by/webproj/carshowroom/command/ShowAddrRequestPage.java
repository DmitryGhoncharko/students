package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.dao.RequestDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowAddrRequestPage implements Command{
    private final RequestFactory requestFactory;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.ADD_REQUEST_PAGE.getPath());
    }
}
