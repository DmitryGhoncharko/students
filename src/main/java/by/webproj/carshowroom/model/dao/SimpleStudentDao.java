package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Student;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SimpleStudentDao implements StudentDao{
    private final ConnectionPool connectionPool;

    @Override
    public boolean add(String name, String group, Integer mark) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into student(name, gr, mark) values (?,?,?)")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,group);
            preparedStatement.setInt(3,mark);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("error",e);
            throw new DaoException("error",e);
        }
    }

    @Override
    public boolean remove(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from  student where id = ?")){
            preparedStatement.setLong(1,id);
            return  preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }

    @Override
    public List<Student> getAll() throws DaoException {
        List<Student> students = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select  id, name, gr, mark from student")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Student student = Student.builder().
                        id(resultSet.getLong(1)).
                        name(resultSet.getString(2)).
                        group(resultSet.getString(3)).
                        mark(resultSet.getInt(4)).
                        build();
                students.add(student);
            }
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
        return students;
    }

    @Override
    public boolean update(Long id, String name, String group, Integer mark) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update  student set name = ?, gr = ?, mark = ? where id = ?")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,group);
            preparedStatement.setInt(3,mark);
            preparedStatement.setLong(4,id);
            return preparedStatement.execute();
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
    }

    @Override
    public Optional<Student> getById(Long id) throws DaoException {
        try(Connection connection=  connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select id, name, gr, mark from student where id = ?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(
                        Student.builder().
                                id(resultSet.getLong(1)).
                                name(resultSet.getString(2)).
                                group(resultSet.getString(3)).
                                mark(resultSet.getInt(4)).
                                build()
                );
            }
        }catch (SQLException e){
            log.error("err",e);
            throw new DaoException("err",e);
        }
        return Optional.empty();
    }
}
