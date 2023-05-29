package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Org;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.OrgDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowAllOrgsPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrgDao orgDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        Optional<Object> userObject = request.retrieveFromSession("user");
        User user = (User) userObject.get();
        List<Org> orgs = orgDao.getAllByUserId(user.getId());
        request.addAttributeToJsp("orgs",orgs);
        return requestFactory.createForwardResponse(PagePath.ORGS_PAGE.getPath());
    }
}
