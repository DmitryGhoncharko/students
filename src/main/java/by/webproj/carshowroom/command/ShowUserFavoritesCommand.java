package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.FavoritesService;

import java.util.List;

public class ShowUserFavoritesCommand implements Command{
    private final RequestFactory requestFactory;
    private final FavoritesService favoritesService;

    public ShowUserFavoritesCommand(RequestFactory requestFactory, FavoritesService favoritesService) {
        this.requestFactory = requestFactory;
        this.favoritesService = favoritesService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long userId = Long.parseLong(request.getParameter("userId"));
        List<Car> cars = favoritesService.findFavoritesCarByUserId(userId);
        request.addAttributeToJsp("cars",cars);
        return requestFactory.createForwardResponse(PagePath.USER_FAVORITES_PAGE.getPath());
    }
}
