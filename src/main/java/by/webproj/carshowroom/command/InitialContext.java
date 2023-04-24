package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.*;
import by.webproj.carshowroom.model.service.*;
import by.webproj.carshowroom.securiy.BcryptWithSaltHasherImpl;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final UserDao simplePageDao = new SimpleUserDao(hikariCPConnectionPool);
    private final UserValidator simplePageServiceValidator = new SimpleUserValidator();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();
    private final UserService simpleUserService = new SimpleUserService(simplePageServiceValidator, simplePageDao, bcryptWithSaltHasher);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();

    private final RequestDao requestDao = new SimpleRequestDao(hikariCPConnectionPool);
    private final CarRepairDao carRepairDao = new SimpleCarRepairDao(hikariCPConnectionPool);
    public Command lookup(String commandName) {

        switch (commandName) {
            case "logincmnd":
                return new LoginCommand(simpleUserService, simpleRequestFactory);
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "registration":
                return new ShowRegistrationPageCommand(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleUserService, simpleRequestFactory);
            case "createRequest":
                return new ShowAddrRequestPage(simpleRequestFactory);
            case "addreq":
                return new AddRequestCommand(simpleRequestFactory,requestDao);
            case "curRequests":
                return new ShowCurrentRequestsCommand(simpleRequestFactory,carRepairDao);
            case "completeRequests":
                return new ShowCompleteReqCommand(simpleRequestFactory,carRepairDao);
            case "createRequest1":
                return new ShowRCommand(simpleRequestFactory,requestDao);
            case "add":
                return new AddRequestCommand(simpleRequestFactory,requestDao);
            case "add1":
                return new AddReqCommand(simpleRequestFactory,requestDao,carRepairDao);
            case "createRequest2":
                return new ShowR1Command(simpleRequestFactory,carRepairDao);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }

    }
}
