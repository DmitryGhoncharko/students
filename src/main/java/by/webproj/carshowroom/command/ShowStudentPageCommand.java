package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Student;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.StudentDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShowStudentPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final StudentDao studentDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String id = request.getParameter("id");
        Optional<Student> student = studentDao.getById(Long.valueOf(id));
        request.addAttributeToJsp("student",student.get());
        return requestFactory.createForwardResponse(PagePath.STUDENT_PAGE.getPath());
    }
}
