package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarRepairDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowCompleteReqCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarRepairDao carRepairDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        Optional<Object> userOp = request.retrieveFromSession("user");
        User user = (User) userOp.get();
        List<CarRepair> carRepairs = carRepairDao.selectByUserIdWhereStatusComplete(user.getId());
        request.addAttributeToJsp("rep",carRepairs);
        return requestFactory.createForwardResponse(PagePath.V_REQUEST.getPath());
    }
}
