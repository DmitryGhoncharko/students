package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.PassportDao;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class AddPassportCommand implements Command{
    private final RequestFactory requestFactory;
    private final PassportDao passportDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        Optional<Object>  user = request.retrieveFromSession("user");
        User user1 = (User) user.get();
        String customer = request.getParameter("customer");
        String org = request.getParameter("org");
        String ruk = request.getParameter("ruk");
        String gen = request.getParameter("gen");
        String tex = request.getParameter("tex");
        String ots = request.getParameter("ots");
        passportDao.add(user1.getId(),customer,org,ruk,gen,tex,ots);
        return requestFactory.createRedirectResponse("/controller?command=addPass");
    }
}
