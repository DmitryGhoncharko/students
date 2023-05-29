package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.OrgDao;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateDataCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrgDao orgDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String date = request.getParameter("date");
        orgDao.update(Long.valueOf(id),name,desc, Date.valueOf(date));
        return requestFactory.createRedirectResponse("/controller?command=all");
    }
}
