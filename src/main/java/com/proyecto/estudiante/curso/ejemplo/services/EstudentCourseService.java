package com.proyecto.estudiante.curso.ejemplo.services;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.models.EstudentCourse;
import com.proyecto.estudiante.curso.ejemplo.repository.CrudRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EstudentCourseService {

    List<EstudentCourse> getAll() throws SQLException;

    void create(Long idEstudiante, Long idCurso)throws SQLException;

    void delete(Long idEstudiante, Long idCurso)throws SQLException;

    void update(Long idEstudiante, Long idCurso, Long idCursoNuevo)throws SQLException;

    List<Estudent> findEstudentByCourseId(Long id)throws SQLException;

    List<Course> findCourseByStudentId(Long id)throws SQLException;

    boolean existsByEstudentAndCurso(Long idEstudiante, Long idCurso) throws SQLException;


}
