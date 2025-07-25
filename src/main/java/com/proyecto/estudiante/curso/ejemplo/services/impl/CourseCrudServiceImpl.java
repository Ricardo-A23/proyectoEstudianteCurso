package com.proyecto.estudiante.curso.ejemplo.services.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.CourseCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.CrudService;
import com.proyecto.estudiante.curso.ejemplo.util.ConnectionBd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseCrudServiceImpl implements CrudService<Course> {

    private CourseCrudRepositoryImpl courseRepository;

    public CourseCrudServiceImpl(CourseCrudRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() throws SQLException{
        try (Connection conn = ConnectionBd.getConnection()) {
            courseRepository.setConn(conn);
            return courseRepository.getAll();
        }
    }

    @Override
    public Course getById(Long id) throws SQLException{
        if((id == null) || (id <= 0)) {
            throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero.");
        }
        try (Connection conn = ConnectionBd.getConnection()) {
            courseRepository.setConn(conn);
            return courseRepository.getById(id);
        }
    }

    @Override
    public void create(Course course)throws SQLException {
        if((course.getNombre()) == null || (course.getNombre().equals(""))){
            throw new  IllegalArgumentException("El nombre del curso no puede ser nulo ni vacio.");
        }
        try (Connection conn = ConnectionBd.getConnection()) {
            courseRepository.setConn(conn);
            courseRepository.create(course);
        }
    }

    @Override
    public void update(Course course)throws SQLException{
        if((course.getNombre() == null) || (course.getNombre().equals(""))){
            throw new IllegalArgumentException("El nombre del curso es requerido.");
        }

        if ((course.getId() == null) || (course.getId() <= 0)){
            throw new IllegalArgumentException("El id del curso no puede ser nulo ni menor o igual a cero.");
        }

        try (Connection conn = ConnectionBd.getConnection()) {
            courseRepository.setConn(conn);
            Course existeCurso = courseRepository.getById(course.getId());
            if(existeCurso == null){
                throw new IllegalArgumentException("El curso no existe");
            }


            courseRepository.update(course);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        if ((id == null) || (id <= 0)){
            throw new IllegalArgumentException("El id del curso no puede ser nulo ni menor o igual a cero.");
        }
        try (Connection conn = ConnectionBd.getConnection()) {
            courseRepository.setConn(conn);
            courseRepository.delete(id);
        }
    }
}
