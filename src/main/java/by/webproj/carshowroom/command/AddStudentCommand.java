package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.StudentDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddStudentCommand implements Command{
    private final RequestFactory requestFactory;
    private final StudentDao studentDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError, DaoException {
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String mark = request.getParameter("mark");
        studentDao.add(name,group, Integer.valueOf(mark));
        return requestFactory.createRedirectResponse("/controller?command=all");
    }
}
