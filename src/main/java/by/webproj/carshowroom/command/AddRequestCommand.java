package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Request;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.RequestDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddRequestCommand implements Command{
    private final RequestFactory requestFactory;
    private final RequestDao requestDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String reqName = request.getParameter("requestName");
        String reqDesc = request.getParameter("requestDescription");
        Long userId = Long.valueOf(request.getParameter("userId"));
        requestDao.add(new Request.Builder().
                withUser(new User.Builder().withUserId(userId).build()).
                withName(reqName).
                withDescription(reqDesc).
                build());
        return requestFactory.createRedirectResponse(PagePath.INDEX_PATH.getPath());
    }
}
