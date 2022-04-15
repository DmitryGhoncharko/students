package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.CarDao;
import by.webproj.carshowroom.model.dao.SimpleCarDao;
import by.webproj.carshowroom.model.dao.SimpleUserDao;
import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.model.service.CarService;
import by.webproj.carshowroom.model.service.SimpleCarService;
import by.webproj.carshowroom.model.service.SimpleUserService;
import by.webproj.carshowroom.model.service.UserService;
import by.webproj.carshowroom.securiy.BcryptWithSaltHasherImpl;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.CarValidator;
import by.webproj.carshowroom.validator.SimpleCarValidator;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final UserDao simplePageDao = new SimpleUserDao(hikariCPConnectionPool);
    private final CarDao simpleCarDao = new SimpleCarDao(hikariCPConnectionPool);
    private final UserValidator simplePageServiceValidator = new SimpleUserValidator();
    private final CarValidator simpleCarValidator = new SimpleCarValidator();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();
    private final UserService simpleUserService = new SimpleUserService(simplePageServiceValidator, simplePageDao, bcryptWithSaltHasher);
    private final CarService simpleCarService = new SimpleCarService(simpleCarValidator, simpleCarDao);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();

    public Command lookup(String commandName) {

        switch (commandName) {
            case "logincmnd":
                return new LoginCommand(simpleUserService, simpleRequestFactory);
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "showcars":
                return new ShowCarsPageCommand(simpleCarService, simpleRequestFactory);
            case "deleteCar":
                return new DeleteCarCommand(simpleCarService, simpleRequestFactory);
            case "addCar" :
                return new AddCarCommand(simpleCarService, simpleRequestFactory);
            case "showaddcars" :
                return new ShowAddCarPageCommand(simpleRequestFactory);
                default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }

    }
}
