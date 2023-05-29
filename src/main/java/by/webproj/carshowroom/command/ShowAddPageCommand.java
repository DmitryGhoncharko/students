package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.OrgDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowAddPageCommand implements Command{
    private final RequestFactory requestFactory;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        return requestFactory.createForwardResponse(PagePath.ADD_PAGE.getPath());
    }
}
