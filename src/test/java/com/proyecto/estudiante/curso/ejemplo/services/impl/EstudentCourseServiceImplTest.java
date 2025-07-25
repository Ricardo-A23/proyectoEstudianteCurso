package com.proyecto.estudiante.curso.ejemplo.services.impl;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.CourseCrudRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EstudentCourseServiceImplTest {

    @Mock
    private CourseCrudRepositoryImpl courseCrudRepository;

    @InjectMocks
    CourseCrudServiceImpl courseCrudService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void update() throws SQLException {

        Course courseMock = new Course();

        when(courseCrudRepository.getById(1L)).thenReturn(courseMock);
        assertThrows(NumberFormatException.class, () -> courseCrudService.update(courseMock));
        //courseCrudService.update(courseMock);
    }
}