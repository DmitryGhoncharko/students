package by.webproj.carshowroom.rest;

import by.webproj.carshowroom.command.InitialContext;
import by.webproj.carshowroom.entity.Car;
import by.webproj.carshowroom.model.service.CarService;
import com.google.gson.Gson;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cars/*")
public class CarsRestServlet extends HttpServlet {
    private final InitialContext initialContext = new InitialContext();
    private final CarService carService = initialContext.getSimpleCarService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pathInfo = req.getRequestURI();
        System.out.println(pathInfo);
        if(pathInfo.equals("/cars")||pathInfo.equals("/cars/")){
            resp.setContentType("application/json; charset=UTF-8");
            final List<Car> cars = carService.getCars();
            String json = new Gson().toJson(cars);
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
