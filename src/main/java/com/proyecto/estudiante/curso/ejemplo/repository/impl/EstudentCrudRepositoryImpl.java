package com.proyecto.estudiante.curso.ejemplo.repository.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudentCrudRepositoryImpl implements CrudRepository<Estudent>{

    private Connection conn;

    public EstudentCrudRepositoryImpl() {
    }

    public EstudentCrudRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Estudent> getAll() throws SQLException {
        List<Estudent> list = new ArrayList<>();
        try(Statement pst = conn.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * FROM estudiantes")){
            while(rs.next()){
                Estudent e = getEstudent(rs);
                
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public Estudent getById(Long id) throws SQLException {
        Estudent e = null;
        try(PreparedStatement pst = conn.prepareStatement("SELECT * FROM estudiantes WHERE id=?")){
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if(rs.next()){
                    e = getEstudent(rs);
                }
            }
        }
        return e;
    }

    @Override
    public void create(Estudent estudent) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement
                ("INSERT INTO estudiantes (nombre, apellidos, edad, sexo, telefono, correo) VALUES(?,?,?,?,?,?)")){
            createEstudent(estudent, pst);
            pst.executeUpdate();
        }
    }

    @Override
    public void update(Estudent estudent) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement
                ("UPDATE estudiantes SET nombre=?, apellidos=?, edad=?, sexo=?, telefono=?, correo=? WHERE id=?")){
            createEstudent(estudent, pst);
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try(PreparedStatement pst = conn.prepareStatement("DELETE FROM estudiante WHERE id=?")){
            pst.setLong(1, id);
            pst.executeUpdate();
        }
    }


    //metodos de reutilizacion de codigo

    private static Estudent getEstudent(ResultSet rs) throws SQLException {
        Estudent e = new Estudent();
        e.setId(rs.getLong("id"));
        e.setNombre(rs.getString("nombre"));
        e.setApellido(rs.getString("apellidos"));
        e.setEdad(rs.getInt("edad"));
        e.setSexo(rs.getString("sexo"));
        e.setTelefono(rs.getString("telefono"));
        e.setCorreo(rs.getString("correo"));
        return e;
    }
    private static void createEstudent(Estudent estudiante, PreparedStatement pst) throws SQLException {
        pst.setString(1, estudiante.getNombre());
        pst.setString(2, estudiante.getApellido());
        pst.setInt(3, estudiante.getEdad());
        pst.setString(4, estudiante.getSexo());
        pst.setString(5, estudiante.getTelefono());
        pst.setString(6, estudiante.getCorreo());
    }

}
