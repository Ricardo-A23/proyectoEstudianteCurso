package com.proyecto.estudiante.curso.ejemplo.services.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.repository.CrudRepository;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.CrudService;
import com.proyecto.estudiante.curso.ejemplo.util.ConnectionBd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EstudentCrudServiceImpl implements CrudService<Estudent> {

    private CrudRepository<Estudent> estudentRepository;

    public EstudentCrudServiceImpl(EstudentCrudRepositoryImpl estudentRepository) {
        this.estudentRepository = estudentRepository;
    }

    @Override
    public List<Estudent> getAll() throws SQLException {
        try (Connection conn = ConnectionBd.getConnection()) {
            estudentRepository.setConn(conn);
            return estudentRepository.getAll();
        }
    }

    @Override
    public Estudent getById(Long id) throws SQLException {
        if ((id == null) || (id <= 0)) {
            throw new IllegalArgumentException("El id no puede ser nulo ni menor o igual a cero.");
        }
        try (Connection conn = ConnectionBd.getConnection()) {
            estudentRepository.setConn(conn);
            return estudentRepository.getById(id);
        }
    }

    @Override
    public void create(Estudent estudent) throws SQLException {
        validacionEstudiante(estudent);
        try (Connection conn = ConnectionBd.getConnection()) {
            estudentRepository.setConn(conn);
            estudentRepository.create(estudent);
        }
    }

    @Override
    public void update(Estudent estudent) throws SQLException {
        validacionEstudiante(estudent);
        if (estudent.getId() == null || estudent.getId() <= 0) {
            throw new IllegalArgumentException("El id del estudiante no puede ser nulo ni menor o igual a cero.");
        }

        try (Connection conn = ConnectionBd.getConnection()) {
            estudentRepository.setConn(conn);
            Estudent estudentVerificar = estudentRepository.getById(estudent.getId());

            if(estudentVerificar == null){
                throw new IllegalArgumentException("El estudiante no existe");
            }
            estudentRepository.update(estudent);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del estudiante es obligatorio.");
        }
        try(Connection conn = ConnectionBd.getConnection()) {
            estudentRepository.setConn(conn);

            Estudent estudentVerificar = estudentRepository.getById(id);

            if(estudentVerificar == null){
                throw new IllegalArgumentException("El estudiante no existe");
            }
                estudentRepository.delete(id);
        }
    }


    private void validacionEstudiante(Estudent estudent) {
        if (estudent == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }

        if (estudent.getNombre() == null || estudent.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }

        if (estudent.getApellido() == null || estudent.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio.");
        }

        if (estudent.getEdad() == null || estudent.getEdad() < 0 || estudent.getEdad() > 120) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y 120 años.");
        }

        if (estudent.getSexo() == null || (!estudent.getSexo().equalsIgnoreCase("M") && !estudent.getSexo().equalsIgnoreCase("F"))) {
            throw new IllegalArgumentException("El sexo debe ser 'M' o 'F'.");
        }

        if (estudent.getTelefono() == null || estudent.getTelefono().trim().isEmpty() || (estudent.getTelefono().trim().length() != 10)) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }

        if (estudent.getCorreo() == null || estudent.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }

        if (!estudent.getCorreo().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
        }

    }
}
