package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteCarCommand implements Command{
    private static final Logger LOG  = LoggerFactory.getLogger(DeleteCarCommand.class);
    private final CarService carService;
    private final RequestFactory requestFactory;

    public DeleteCarCommand(CarService carService, RequestFactory requestFactory) {
        this.carService = carService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final long carId = Long.parseLong(request.getParameter("carId"));
        carService.deleteCar(carId);
        return requestFactory.createRedirectResponse("/controller?command=showcars");
    }
}
