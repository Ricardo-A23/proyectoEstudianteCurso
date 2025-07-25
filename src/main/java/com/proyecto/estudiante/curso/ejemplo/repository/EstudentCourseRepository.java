package com.proyecto.estudiante.curso.ejemplo.repository;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.models.EstudentCourse;

import java.sql.SQLException;
import java.util.List;

public interface EstudentCourseRepository {

    List<EstudentCourse> findAll() throws SQLException;

    List<Estudent> findEstudentByCourseId(Long id) throws SQLException;

    List<Course> findCourseByStudentId(Long id) throws SQLException;

    void add(Long idEstudiante, Long idCurso) throws SQLException;

    void delete(Long idEstudiante, Long idCurso) throws SQLException;

    void update(Long idEstudiante, Long idCurso, Long idCursoNuevo) throws SQLException;

    boolean existsByEstudentAndCurso(Long idEstudiante, Long idCurso) throws SQLException;
}
