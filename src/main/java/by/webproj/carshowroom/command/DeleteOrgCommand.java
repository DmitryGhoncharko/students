package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.OrgDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteOrgCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrgDao orgDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String id = request.getParameter("id");
        orgDao.remove(Long.valueOf(id));
        return requestFactory.createRedirectResponse("/controller?command=all");
    }
}
