package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Request;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CarRepairDao;
import by.webproj.carshowroom.model.dao.RequestDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowRCommand implements Command{
    private final RequestFactory requestFactory;
    private final RequestDao requestDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        List<Request> requests = requestDao.getAll();
        request.addAttributeToJsp("r",requests);
        return requestFactory.createForwardResponse(PagePath.REQ_N.getPath());
    }
}
