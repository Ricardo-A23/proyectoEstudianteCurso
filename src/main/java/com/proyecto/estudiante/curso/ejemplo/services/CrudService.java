package com.proyecto.estudiante.curso.ejemplo.services;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<T> {

    List<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
    void create(T t) throws SQLException;
    void update(T t ) throws SQLException;
    void delete(Long id) throws SQLException;
}
