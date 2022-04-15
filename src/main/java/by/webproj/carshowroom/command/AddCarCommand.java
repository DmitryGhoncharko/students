package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddCarCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(AddCarCommand.class);
    private final CarService carService;
    private final RequestFactory requestFactory;

    public AddCarCommand(CarService carService, RequestFactory requestFactory) {
        this.carService = carService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String carName = request.getParameter("carName");
        String carImage = request.getParameter("carImage");
        String carDescription = request.getParameter("carDescription");
        carService.addCar(new Car.Builder().withCarName(carName).
                withCarDescription(carDescription).
                withCarImage(carImage).build());
        return requestFactory.createForwardResponse("/controller?command=addCar");
    }
}
