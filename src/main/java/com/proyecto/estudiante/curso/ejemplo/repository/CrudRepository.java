package com.proyecto.estudiante.curso.ejemplo.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {

    List<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void create(T t) throws SQLException;
    void update(T t ) throws SQLException;
    void delete(Long id) throws SQLException;
    void setConn(Connection conn) throws SQLException;
}
