package com.proyecto.estudiante.curso.ejemplo.services.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.models.EstudentCourse;
import com.proyecto.estudiante.curso.ejemplo.repository.CrudRepository;
import com.proyecto.estudiante.curso.ejemplo.repository.EstudentCourseRepository;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.CourseCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCourseRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.EstudentCourseService;
import com.proyecto.estudiante.curso.ejemplo.util.ConnectionBd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EstudentCourseServiceImpl implements EstudentCourseService {

    private EstudentCourseRepository estudentCourseRepository;
    private CrudRepository<Estudent>  estudentCrudRepository;
    private CrudRepository<Course> courseCrudRepository;

    public EstudentCourseServiceImpl(EstudentCourseRepositoryImpl  estudentCourseRepository,
            EstudentCrudRepositoryImpl estudentCrudRepository, CourseCrudRepositoryImpl  courseCrudRepository) {
        this.estudentCourseRepository = estudentCourseRepository;
        this.estudentCrudRepository = estudentCrudRepository;
        this.courseCrudRepository = courseCrudRepository;
    }

    @Override
    public List<EstudentCourse> getAll() throws SQLException {
        try(Connection conn = ConnectionBd.getConnection()){
            estudentCourseRepository.setConn(conn);
            return estudentCourseRepository.findAll();
        }
    }


    @Override
    public List<Estudent> findEstudentByCourseId(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }

        try(Connection conn = ConnectionBd.getConnection()){
            estudentCourseRepository.setConn(conn);
            courseCrudRepository.setConn(conn);

            Course buscarCurso = courseCrudRepository.getById(id);
            if(buscarCurso == null){
                throw new IllegalArgumentException("El curso no existe");
            }
            return estudentCourseRepository.findEstudentByCourseId(id);
        }
    }

    @Override
    public List<Course> findCourseByStudentId(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        try(Connection conn = ConnectionBd.getConnection()){
            estudentCourseRepository.setConn(conn);
            estudentCrudRepository.setConn(conn);

            Estudent e = estudentCrudRepository.getById(id);
            if(e == null){
                throw new IllegalArgumentException("El estudiante no existe");
            }

            return estudentCourseRepository.findCourseByStudentId(id);
        }
    }

    @Override
    public boolean existsByEstudentAndCurso(Long idEstudiante, Long idCurso) throws SQLException {
        validacionIdEstudianteYCurso(idEstudiante, idCurso);
        try(Connection conn = ConnectionBd.getConnection()){
            estudentCourseRepository.setConn(conn);
            return estudentCourseRepository.existsByEstudentAndCurso(idEstudiante, idCurso);
        }
    }

    @Override
    public void create(Long idEstudiante, Long idCurso) throws SQLException {
        validacionIdEstudianteYCurso(idEstudiante, idCurso);

        if (estudentCourseRepository.existsByEstudentAndCurso(idEstudiante, idCurso)) {
            throw new IllegalArgumentException("El estudiante ya esta inscrito en el curso");
        }

        try(Connection conn = ConnectionBd.getConnection()) {
            estudentCourseRepository.setConn(conn);
            estudentCourseRepository.add(idEstudiante, idCurso);

        }
    }

    @Override
    public void update(Long idEstudiante, Long idCurso, Long idCursoNuevo) throws SQLException {
        validacionIdEstudianteYCurso(idEstudiante, idCurso);

        if (idCursoNuevo == null || idCursoNuevo <= 0) {
            throw new IllegalArgumentException("Id del curso nuevo invalido");
        }

        try(Connection conn = ConnectionBd.getConnection()) {
            estudentCourseRepository.setConn(conn);
            estudentCrudRepository.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Estudent estudiante = estudentCrudRepository.getById(idEstudiante);
            Course curso =  courseCrudRepository.getById(idCurso);
            Course cursoNuevo = courseCrudRepository.getById(idCursoNuevo);
            if (estudiante == null) {
                throw new IllegalArgumentException("El estudiante no existe");
            }
            if(curso == null){
                throw new IllegalArgumentException("El curso no existe");
            }
            if(cursoNuevo == null){
                throw new IllegalArgumentException("El curso con el id " + idCursoNuevo + " no existe");
            }
            if(estudentCourseRepository.existsByEstudentAndCurso(idEstudiante, idCursoNuevo)) {
                throw new IllegalArgumentException("El estudiante ya existe en el curso");
            }
            try {
                estudentCourseRepository.update(idEstudiante, idCurso, idCursoNuevo);
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    @Override
    public void delete(Long idEstudiante, Long idCurso) throws SQLException {
        validacionIdEstudianteYCurso(idEstudiante, idCurso);

        try (Connection conn = ConnectionBd.getConnection()) {
            conn.setAutoCommit(false);
            estudentCourseRepository.setConn(conn);

            if (!estudentCourseRepository.existsByEstudentAndCurso(idEstudiante, idCurso)) {
                throw new IllegalArgumentException("El estudiante no está inscrito en el curso");
            }

            try {
                estudentCourseRepository.delete(idEstudiante, idCurso);
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }



    private void validacionIdEstudianteYCurso(Long  idEstudiante, Long idCurso) {
        if (idEstudiante == null || idEstudiante <= 0) {
            throw new IllegalArgumentException("Id del estudiante invalido");
        }
        if (idCurso == null || idCurso <= 0) {
            throw new IllegalArgumentException("Id del curso invalido");
        }
    }


}
