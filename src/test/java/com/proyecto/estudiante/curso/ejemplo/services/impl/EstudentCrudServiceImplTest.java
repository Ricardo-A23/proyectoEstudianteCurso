package com.proyecto.estudiante.curso.ejemplo.services.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCrudRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudentCrudServiceImplTest {


    @Mock
    private EstudentCrudRepositoryImpl estudentRepository;

    @InjectMocks
    private EstudentCrudServiceImpl estudentCrudService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorId() throws SQLException {
        Long id =90L;
        Estudent mockEstudent = new Estudent();
        mockEstudent.setId(90L);

        when(estudentRepository.getById(id)).thenReturn(mockEstudent);

        Estudent resultado =  estudentCrudService.getById(id);

        assertNotNull(resultado);
        assertEquals(id,resultado.getId());

        verify(estudentRepository).getById(id);
    }

    @Test
    void update() throws SQLException {

        Estudent mockEstudent = new Estudent();
        mockEstudent.setId(9L);
        mockEstudent.setNombre("Estudiante");
        mockEstudent.setApellido("Apellido");
        mockEstudent.setSexo("M");
        mockEstudent.setEdad(21);
        mockEstudent.setTelefono("9141230989");
        mockEstudent.setCorreo("correo21@gmail.com");

        when(estudentRepository.getById(7L)).thenReturn(mockEstudent);
        estudentCrudService.update(mockEstudent);

    }

    @Test
    void agregar() throws SQLException {
        Estudent mockEstudent = new Estudent();
        mockEstudent.setNombre("Estudiante");
        mockEstudent.setApellido("Apellido");
        mockEstudent.setSexo("M");
        mockEstudent.setEdad(21);
        mockEstudent.setTelefono("9141230921");
        mockEstudent.setCorreo("correo@gmail.com");;

        estudentCrudService.create(mockEstudent);
    }

    @Test
    void eliminar() throws SQLException {
        Estudent mockEstudent = new Estudent();
       // mockEstudent.setId(9L);
        mockEstudent.setNombre("Estudiante");
        when(estudentRepository.getById(7L)).thenReturn(mockEstudent);

        estudentCrudService.delete(mockEstudent.getId());
    }
}