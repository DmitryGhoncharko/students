package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.mailsender.GmailSender;
import by.webproj.carshowroom.mailsender.MailSender;
import by.webproj.carshowroom.mailsender.secretkeycache.SecretKeyCache;
import by.webproj.carshowroom.mailsender.secretkeycache.SynchronizedArrayListBasedSecretKeyCache;
import by.webproj.carshowroom.mailsender.secretkeygenerator.SecretKeyGenerator;
import by.webproj.carshowroom.mailsender.secretkeygenerator.SecretKeyGeneratorBasedOnPassayLibrary;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.*;
import by.webproj.carshowroom.model.service.*;
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
    private final SecretKeyCache synchronizedArrayListBasedSecretKeyCache = new SynchronizedArrayListBasedSecretKeyCache();
    private final SecretKeyGenerator secretKeyGeneratorBasedOnPassayLibrary = new SecretKeyGeneratorBasedOnPassayLibrary(synchronizedArrayListBasedSecretKeyCache);
    private final MailSender javaxMailSender = new GmailSender(secretKeyGeneratorBasedOnPassayLibrary);
    private final UserService simpleUserService = new SimpleUserService(simplePageServiceValidator, simplePageDao, bcryptWithSaltHasher, synchronizedArrayListBasedSecretKeyCache);
    private final CarService simpleCarService = new SimpleCarService(simpleCarValidator, simpleCarDao);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();
    private final FavoritesDao favoritesDao = new SimpleFavoritesDao(hikariCPConnectionPool);
    private final FavoritesService favoritesService = new SimpleFavoritesService(favoritesDao);

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
            case "addCar":
                return new AddCarCommand(simpleCarService, simpleRequestFactory);
            case "showaddcars":
                return new ShowAddCarPageCommand(simpleRequestFactory);
            case "sekretkeypage":
                return new ShowSecretCodePageCommand(simpleRequestFactory);
            case "getsecretkey":
                return new SendSecretKeyCommand(javaxMailSender, simpleRequestFactory);
            case "registration":
                return new ShowRegistrationPageCommand(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleUserService, simpleRequestFactory);
            case "updateCar":
                return new ShowUpdateCarPageCommand(simpleCarService, simpleRequestFactory);
            case "updateCarCommand":
                return new UpdateCarCommand(simpleCarService, simpleRequestFactory);
            case "showUsers":
                return new ShowUsersPageCommand(simpleRequestFactory, simpleUserService);
            case "showfavorites":
                return new ShowUserFavoritesCommand(simpleRequestFactory, favoritesService);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }

    }

    public UserService getSimpleUserService() {
        return simpleUserService;
    }

    public CarService getSimpleCarService() {
        return simpleCarService;
    }

    public FavoritesService getFavoritesService(){
        return favoritesService;
    }
}
