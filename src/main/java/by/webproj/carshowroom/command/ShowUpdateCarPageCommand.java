package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ShowUpdateCarPageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowUpdateCarPageCommand.class);
    private final CarService carService;
    private final RequestFactory requestFactory;

    public ShowUpdateCarPageCommand(CarService carService, RequestFactory requestFactory) {
        this.carService = carService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final Long carId = Long.parseLong(request.getParameter("carId"));
        Optional<Car> carFromDB = carService.findCarById(carId);
        if(carFromDB.isPresent()){
            final Car carInstance = carFromDB.get();
            request.addAttributeToJsp("car",carInstance);
            return requestFactory.createForwardResponse(PagePath.UPDATE_CAR_PAGE.getPath());
        }
        return requestFactory.createForwardResponse(PagePath.CARS_PAGE.getPath());
    }
}
