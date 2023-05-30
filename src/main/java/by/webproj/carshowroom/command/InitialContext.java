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
    private final StudentDao studentDao = new SimpleStudentDao(hikariCPConnectionPool);
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
            case "about":
                return new ShowAboutPageCommand(simpleRequestFactory);
            case "student":
                return new ShowAddStudentPageCommand(simpleRequestFactory);
            case "all":
                return new ShowAllStudentsPageCommand(simpleRequestFactory,studentDao);
            case "del":
                return new DeleteStudentCommand(simpleRequestFactory,studentDao);
            case "update":
                return new ShowStudentPageCommand(simpleRequestFactory,studentDao);
            case "updateS":
                return new UpdateCommand(simpleRequestFactory,studentDao);
            case "addStudent":
                return new AddStudentCommand(simpleRequestFactory,studentDao);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }
    }
}
