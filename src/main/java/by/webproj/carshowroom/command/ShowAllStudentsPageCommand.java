package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Student;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.StudentDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowAllStudentsPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final StudentDao studentDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        List<Student> students = studentDao.getAll();
        request.addAttributeToJsp("students",students);
        return requestFactory.createForwardResponse(PagePath.STUDENTS_PAGE.getPath());
    }
}
