package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.CarRepair;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarRepairDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowR1Command implements Command{
    private final RequestFactory requestFactory;
    private final CarRepairDao carRepairDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        List<CarRepair> carRepairs= carRepairDao.getAll();
        request.addAttributeToJsp("c",carRepairs);
        return requestFactory.createForwardResponse(PagePath.REQ_R.getPath());
    }
}
