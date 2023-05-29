package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.OrgDao;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.Optional;

@RequiredArgsConstructor
public class AddOrgCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrgDao orgDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String date = request.getParameter("date");
        Optional<Object> userObject = request.retrieveFromSession("user");
        User user = (User) userObject.get();
        orgDao.add(name,desc, Date.valueOf(date),user.getId());
        return requestFactory.createRedirectResponse("/controller?command=all");
    }
}
