package com.proyecto.estudiante.curso.ejemplo.repository.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.models.EstudentCourse;
import com.proyecto.estudiante.curso.ejemplo.repository.EstudentCourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudentCourseRepositoryImpl implements EstudentCourseRepository {

    private Connection conn;

    public EstudentCourseRepositoryImpl() {
    }

    public EstudentCourseRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<EstudentCourse> findAll() throws SQLException {
        List<EstudentCourse> lista = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement
                    ("SELECT e.*, c.id as idCurso, c.nombre as nombreCurso, ec.id as idRegistro" +
                            "FROM estudiante_curso as ec " +
                            "INNER JOIN estudiantes as e on ec.estudiante_id = e.id " +
                            "INNER JOIN cursos as c ON ec.curso_id = c.id")) {

            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                EstudentCourse estudianteCurso = new EstudentCourse();
                estudianteCurso.setId(rs.getLong("idRegistro"));

                Estudent estudiante = getEstudiante(rs);

                Course curso = new Course();
                curso.setId(rs.getLong("idCurso"));
                curso.setNombre(rs.getString("nombreCurso"));

                estudianteCurso.setEstudiante(estudiante);
                estudianteCurso.setCurso(curso);

                lista.add(estudianteCurso);
            }
        }
        return lista;
    }

    @Override
    public List<Estudent> findEstudentByCourseId(Long id) throws SQLException {
        List<Estudent> estudiantes = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement
                ("SELECT ec.id as idRegistro, e.* " +
                    "FROM estudiante_curso as ec " +
                    "INNER JOIN estudiantes as e on(ec.estudiante_id = e.id) WHERE ec.curso_id = ?")) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Estudent estudiante = getEstudiante(rs);
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    @Override
    public List<Course> findCourseByStudentId(Long id) throws SQLException {
        List<Course> cursos = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement("SELECT c.* " +
                    "FROM estudiante_curso as ec " +
                    "INNER JOIN cursos as c on(ec.curso_id = c.id) WHERE ec.estudiante_id = ?")) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Course curso = new Course();
                curso.setId(rs.getLong("id"));
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso);
            }
        }
        return cursos;
    }

    @Override
    public boolean existsByEstudentAndCurso(Long idEstudiante, Long idCurso) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement
                ("SELECT * FROM estudiante_curso WHERE idEstudiante = ? AND idCurso = ?")) {
            pst.setLong(1, idEstudiante);
            pst.setLong(2, idCurso);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
        }


    @Override
    public void add(Long idEstudiante, Long idCurso) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement
                     ("INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (?,?)")) {
            pst.setLong(1, idEstudiante);
            pst.setLong(2, idCurso);
            pst.executeUpdate();
        }
    }

    @Override
    public void update(Long idEstudiante, Long idCursoActual, Long idCursoNuevo) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement
                ("UPDATE estudiante_curso SET curso_id=? WHERE estudiante_id=? AND curso_id=?")) {
            pst.setLong(1, idCursoNuevo);
            pst.setLong(2, idEstudiante);
            pst.setLong(3, idCursoActual);
            pst.executeUpdate();
        }
    }


    @Override
    public void delete(Long idEstudiante, Long idCurso) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement
                ("DELETE FROM estudiante_curso WHERE estudiante_id=? AND curso_id=?")) {
            pst.setLong(1, idEstudiante);
            pst.setLong(2, idCurso);
            pst.executeUpdate();
        }
    }


    private static Estudent getEstudiante(ResultSet rs) throws SQLException {
        Estudent estudiante = new Estudent();
        estudiante.setId(rs.getLong("id"));
        estudiante.setNombre(rs.getString("nombre"));
        estudiante.setApellido(rs.getString("apellidos"));
        estudiante.setEdad(rs.getInt("edad"));
        estudiante.setSexo(rs.getString("sexo"));
        estudiante.setTelefono(rs.getString("telefono"));
        estudiante.setCorreo(rs.getString("correo"));
        return estudiante;
    }
}
