package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UpdateCarCommand implements Command{
    private static final Logger LOG  = LoggerFactory.getLogger(UpdateCarCommand.class);
    private static final String DIRECTORY = "serverforapp";
    private static final String FILE_EXTENSION = ".png";
    private final CarService carService;
    private final RequestFactory requestFactory;

    public UpdateCarCommand(CarService carService, RequestFactory requestFactory) {
        this.carService = carService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final long carId = Long.parseLong(request.getParameter("carId"));
        final String carName = request.getParameter("carName");
        final String carDescription = request.getParameter("carDescription");
        carService.updateCar(new Car.Builder().
                withCarId(carId).
                withCarName(carName).
                withCarDescription(carDescription).build());
        saveImage(request,carId);
        return requestFactory.createRedirectResponse("/controller?command=showcars");
    }
    private void saveImage(CommandRequest request, long carId) {
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + DIRECTORY;
        System.out.println(request.getServletContext().getRealPath(""));
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            for (Part part : request.getParts()) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null) {
                    part.write(uploadPath + File.separator + carId + FILE_EXTENSION);
                }
            }
        } catch (IOException | ServletException e) {
            LOG.debug(e.getMessage(), e);
        }
    }
}
