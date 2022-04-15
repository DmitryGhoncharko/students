package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShowCarsPageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowCarsPageCommand.class);
    private final CarService carService;
    private final RequestFactory requestFactory;

    public ShowCarsPageCommand(CarService carService, RequestFactory requestFactory) {
        this.carService = carService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final List<Car> carList = carService.getCars();
        request.addAttributeToJsp("cars",carList);
        return requestFactory.createForwardResponse(PagePath.CARS_PAGE.getPath());
    }
}
