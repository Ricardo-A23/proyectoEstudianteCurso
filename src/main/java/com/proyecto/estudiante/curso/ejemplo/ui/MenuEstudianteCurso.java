package com.proyecto.estudiante.curso.ejemplo.ui;


import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.CourseCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCourseRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.EstudentCourseService;
import com.proyecto.estudiante.curso.ejemplo.services.impl.EstudentCourseServiceImpl;
import com.proyecto.estudiante.curso.ejemplo.services.impl.EstudentCrudServiceImpl;

import java.sql.SQLException;

import java.util.Scanner;

public class MenuEstudianteCurso {

    public static void menuEstudianteCurso(){

        Scanner sc = new Scanner(System.in);
        int opcion;

        EstudentCourseService estudentCourseService = new EstudentCourseServiceImpl(
                new EstudentCourseRepositoryImpl(), new EstudentCrudRepositoryImpl(), new CourseCrudRepositoryImpl()
        );

        do {
            System.out.println("Listado de opciones" +
                    "\n1. Listar" +
                    "\n2. Busar Estudiante Por Curso" +
                    "\n3. Buscar Curso Por estudiante" +
                    "\n4. Agregar estudiante a curso" +
                    "\n5. Modificar estudiante de curso" +
                    "\n6. Eliminar estudiante de curso" +
                    "\n7. Regresar\n");
            opcion = sc.nextInt();

            switch(opcion){

                case 1 -> listar(estudentCourseService);

                case 2 -> buscarCursoPorEstudianteId(sc,estudentCourseService);

                case 3 -> buscarEstudiantePorCursoId(sc, estudentCourseService);

                case 4 -> agregar(sc, estudentCourseService);

                case 5 -> modificar(sc, estudentCourseService);

                case 6 -> eliminar(sc, estudentCourseService);

                case 7 -> System.out.println("Regresando al menu principal");

                default -> System.out.println("Opcion no permitida");
            }


        }while (opcion != 7);
    }

    private static void listar(EstudentCourseService estudentCourseService) {
        System.out.println("Listado de estudiantes con cursos");
        try {
            estudentCourseService.getAll().forEach(System.out::println);
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error al listar los estudiante " +e.getMessage());
        }
    }

    private static void buscarCursoPorEstudianteId(Scanner sc, EstudentCourseService estudentCourseService){
        System.out.println("Lista de cursos por estudiante");
        System.out.println("Ingrese el id del estudiante");

        while(!sc.hasNextLine()){
            System.out.println("Ingrese un id valido.");
        }
        Long idEstudiante = sc.nextLong();

        try {
            estudentCourseService.findCourseByStudentId(idEstudiante).forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error al buscar los cursos " +e.getMessage());
        }
    }

    private static void buscarEstudiantePorCursoId(Scanner sc, EstudentCourseService estudentCourseService) {
        System.out.println("Lista de estudiantes por curso");
        System.out.println("Ingrese el id del estudiante a buscar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        Long id = sc.nextLong();
        try {
            estudentCourseService.findEstudentByCourseId(id).forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Error al buscar los estudiantes " +e.getMessage());
        }
    }

    private static void agregar(Scanner sc, EstudentCourseService estudentCourseService) {
        System.out.println("Ingrese el id del estudiante");
        Long idEstudiante = sc.nextLong();

        System.out.println("Ingrese el id del curso");
        Long idCurso = sc.nextLong();
        try {
            estudentCourseService.create(idEstudiante,idCurso);
        } catch (SQLException e) {
            System.out.println("Error al crear la asignacion del curso " + e.getMessage());
        }
    }

    private static void modificar(Scanner sc, EstudentCourseService estudentCourseService) {
        System.out.println("Ingrese el id del estudiante");
        Long idEstudiante = sc.nextLong();

        System.out.println("Ingrese el id del curso");
        Long idCurso = sc.nextLong();

        System.out.println("Ingrese el id del curso nuevo");
        Long idCursoNuevo = sc.nextLong();

        try {
            estudentCourseService.update(idEstudiante,idCurso, idCursoNuevo);
        } catch (SQLException e) {
            System.out.println("Error al modificar la asignacion del curso " + e.getMessage());
        }
    }


    private static void eliminar(Scanner sc, EstudentCourseService estudentCourseService) {
        System.out.println("Ingrese el id del estudiante a eliminar");
        Long idEliminar = sc.nextLong();

        System.out.println("Ingrese el id del curso");
        Long idCurso = sc.nextLong();

        try {
            estudentCourseService.delete(idEliminar, idCurso);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el estudiante del curso " + e.getMessage());
        }
    }



}
