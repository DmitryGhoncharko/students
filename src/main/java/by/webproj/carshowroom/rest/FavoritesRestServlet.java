package by.webproj.carshowroom.rest;

import by.webproj.carshowroom.command.InitialContext;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.entity.Favorites;
import by.webproj.carshowroom.model.service.FavoritesService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/favorites/*")
public class FavoritesRestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FavoritesRestServlet.class);
    private final InitialContext initialContext = new InitialContext();
    private final FavoritesService favoritesService = initialContext.getFavoritesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Starting POST request ...");
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getRequestURI();
        if (pathInfo.equals("/favorites/") || pathInfo.equals("/favorites")) {
            final String userId = req.getParameter("userId");
            System.out.println(userId);
            List<Car> cars = favoritesService.findFavoritesCarByUserId(Long.parseLong(userId));
            String json = new Gson().toJson(cars);
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(json);
            return;
        } else if (pathInfo.equals("/favorites/add") || pathInfo.equals("/favorites/add/")) {
            String userId = req.getParameter("userId");
            String carId = req.getParameter("carId");
            System.out.println(userId);
            System.out.println(carId);
            int favoritesId = favoritesService.addToFavorites(Long.parseLong(userId), Long.parseLong(carId));
            if (favoritesId != -1) {
                resp.setStatus(201);
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(String.valueOf(favoritesId));
                return;
            }
        } else if (pathInfo.equals("/favorites/delete") || pathInfo.equals("/favorites/delete/")) {
            String favorite_id = req.getParameter("id");
            boolean isDeleted = favoritesService.deleteById(Long.parseLong(favorite_id));
            if (isDeleted) {
                resp.setStatus(201);
                return;
            }
        }
        resp.setStatus(401);
    }
}
