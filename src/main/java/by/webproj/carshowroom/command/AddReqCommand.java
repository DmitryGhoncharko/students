package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.entity.Request;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarRepairDao;
import by.webproj.carshowroom.model.dao.RequestDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AddReqCommand implements Command{
    private final RequestFactory requestFactory;
    private final RequestDao requestDao;
    private final CarRepairDao carRepairDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Double cost = Double.valueOf(request.getParameter("cost"));
        Long id = Long.valueOf(request.getParameter("id"));
        Request data = requestDao.getAll().stream().filter(x -> x.getId().equals(id)).findFirst().get();
        requestDao.removeByUserId(id);
        carRepairDao.add(new CarRepair.Builder().withComplete(false).withUser(new User.Builder().withUserId(data.getUser().getId()).build()).withDescription(desc).withName(name).withCost(cost).build());
        return requestFactory.createRedirectResponse(PagePath.INDEX_PATH.getPath());
    }
}
