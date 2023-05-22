package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.PassportDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdatePageCommand implements Command{
    private final RequestFactory requestFactory;
    private final PassportDao passportDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String customer = request.getParameter("customer");
        String org = request.getParameter("org");
        String ruk = request.getParameter("ruk");
        String gen = request.getParameter("gen");
        String tex = request.getParameter("tex");
        String ots = request.getParameter("ots");
        Optional<Object> id = request.retrieveFromSession("id");
        Integer id1 = Integer.valueOf((String) id.get());
        passportDao.update(Long.valueOf(id1),customer,org,ruk,gen,tex,ots);
        return requestFactory.createRedirectResponse("/controller?command=show");
    }
}
