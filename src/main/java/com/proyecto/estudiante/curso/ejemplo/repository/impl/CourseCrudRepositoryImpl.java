package com.proyecto.estudiante.curso.ejemplo.repository.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseCrudRepositoryImpl implements CrudRepository<Course> {

    private Connection conn;
    public CourseCrudRepositoryImpl() {
    }

    public CourseCrudRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        List<Course> cursos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cursos")){

            while(rs.next()){
                Course curso = getCourse(rs);
                cursos.add(curso);
            }

        }
        return cursos;
    }

    @Override
    public Course getById(Long id) throws SQLException {
        Course curso = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cursos WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    curso = getCourse(rs);
                }
            }
        }
        return curso;
    }

    @Override
    public void create(Course curso) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement("INSERT INTO cursos (nombre) VALUES (?)")){
            pst.setString(1, curso.getNombre());
            pst.executeUpdate();
        }
    }

    @Override
    public void update(Course curso) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement("UPDATE cursos SET nombre = ? WHERE id=?")){
            pst.setString(1, curso.getNombre());
            pst.setLong(2, curso.getId());
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement("DELETE FROM cursos WHERE id=?")){
            pst.setLong(1, id);
            pst.executeUpdate();
        }
    }


    private static Course getCourse(ResultSet rs) throws SQLException {
        Course curso = new Course();
        curso.setId(rs.getLong("id"));
        curso.setNombre(rs.getString("nombre"));
        return curso;
    }
}
