package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.UserService;

import java.util.List;

public class ShowUsersPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final UserService userService;

    public ShowUsersPageCommand(RequestFactory requestFactory, UserService userService) {
        this.requestFactory = requestFactory;
        this.userService = userService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<User> users = userService.findAllClients();
        request.addAttributeToJsp("users",users);
        return requestFactory.createForwardResponse(PagePath.USERS_PAGE.getPath());
    }
}
