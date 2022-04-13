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
import by.webproj.carshowroom.validator.CarValidator;
import by.webproj.carshowroom.validator.SimpleCarValidator;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final UserDao simplePageDao = new SimpleUserDao(hikariCPConnectionPool);
    private final CarDao carDao = new SimpleCarDao(hikariCPConnectionPool);
    private final UserValidator simplePageServiceValidator = new SimpleUserValidator();
    private final CarValidator carValidator = new SimpleCarValidator();
    private final UserService pageService = new SimpleUserService(simplePageServiceValidator,simplePageDao);
    private final CarService carService = new SimpleCarService(carValidator, carDao);
    private final RequestFactory requestFactory = new SimpleRequestFactory();

    public Command lookup(String commandName) {

               switch (commandName){
                   default: return new ShowMainPageCommand(requestFactory);
               }




    }
}
