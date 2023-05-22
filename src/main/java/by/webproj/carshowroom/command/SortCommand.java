package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Passport;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.PassportDao;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SortCommand implements Command{
    private final RequestFactory requestFactory;
    private final PassportDao passportDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        Optional<Object> user = request.retrieveFromSession("user");
        User user1 = (User) user.get();
        List<Passport> passports = passportDao.getAllByUserId(user1.getId());
        passports.sort(Comparator.comparingInt(o -> o.getCustomer().length()));
        request.addAttributeToJsp("pass",passports);
        return requestFactory.createForwardResponse(PagePath.PASSPORTS_PAGE.getPath());
    }
}
