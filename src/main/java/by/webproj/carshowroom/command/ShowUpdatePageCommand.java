package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Passport;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.PassportDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShowUpdatePageCommand implements Command{
    private final RequestFactory requestFactory;
    private final PassportDao passportDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String id = request.getParameter("id");
        Optional<Passport> passport = passportDao.getById(Long.valueOf(id));
        Passport passport1 = passport.get();
        request.addToSession("id",id);
        Passport data = Passport.builder().
                id(Long.valueOf(id)).
                customer(passport1.getCustomer()).
                org(passport1.getOrg()).
                ruk(passport1.getRuk()).
                gen(passport1.getGen()).
                tex(passport1.getTex()).
                ots(passport1.getOts()).
                userId(passport1.getUserId()).
                build();
        request.addAttributeToJsp("pass",data);
        return requestFactory.createForwardResponse(PagePath.PASSPORT_PAGE.getPath());
    }
}
