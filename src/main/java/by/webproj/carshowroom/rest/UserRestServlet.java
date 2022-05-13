package by.webproj.carshowroom.rest;

import by.webproj.carshowroom.command.InitialContext;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.SimpleUserDao;
import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.model.service.SimpleUserService;
import by.webproj.carshowroom.model.service.UserService;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/user/*")
public class UserRestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestServlet.class);
    private final InitialContext initialContext = new InitialContext();
    private final UserService userService = initialContext.getSimpleUserService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Starting GET request ...");
        String pathInfo = req.getPathInfo();
        String[] parts = pathInfo.split("/");
        String param = parts[1];

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Starting POST request ...");
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getRequestURI();
        if (pathInfo.equals("/user/login") || pathInfo.equals("/user/login/")){
            final String login = req.getParameter("login");
            final String password = req.getParameter("password");
            System.out.println(login);
            System.out.println(password);
            final Optional<User> user = userService.authenticateIfClient(login,password);
            if(user.isPresent()){
                resp.setStatus(202);
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(String.valueOf(user.get().getUserId()));
                return;
            }
        }else if(pathInfo.equals("/user/registration")||pathInfo.equals("/user/registration/")){
            final String login = req.getParameter("login");
            final String password = req.getParameter("password");
            final boolean userAdded = userService.addUserAsClient(login,password);
            final Optional<User> user = userService.authenticateIfClient(login,password);
            if(userAdded){
                if(user.isPresent()){
                    resp.setStatus(201);
                    PrintWriter printWriter = resp.getWriter();
                    printWriter.write(String.valueOf(user.get().getUserId()));
                    return;
                }
            }
        }

        resp.setStatus(401);
    }

}
