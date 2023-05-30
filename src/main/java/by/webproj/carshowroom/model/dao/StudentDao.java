package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Student;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    boolean add(String name, String group,Integer mark) throws DaoException;

    boolean remove(Long id) throws DaoException;

    List<Student> getAll() throws DaoException;

    boolean update(Long id, String name, String group, Integer mark) throws DaoException;

    Optional<Student> getById(Long id) throws DaoException;
}
