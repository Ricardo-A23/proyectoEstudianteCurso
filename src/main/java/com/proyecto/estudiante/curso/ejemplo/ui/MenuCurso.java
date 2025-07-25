package com.proyecto.estudiante.curso.ejemplo.ui;

import com.proyecto.estudiante.curso.ejemplo.models.Course;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.CourseCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.impl.CourseCrudServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCurso {

    public static void menuCurso(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        CourseCrudServiceImpl courseCrudService = new CourseCrudServiceImpl(new CourseCrudRepositoryImpl());
        do {
            System.out.println("Listado de opciones" +
                    "\n1. Listar Cursos" +
                    "\n2. Buscar por id" +
                    "\n3. Agregar Curso" +
                    "\n4. Modificar Curso" +
                    "\n5. Eliminar Curso" +
                    "\n6. Regresar\n");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> listar(courseCrudService);

                case 2 -> buscarPorId(sc, courseCrudService);

                case 3 -> agregar(sc, courseCrudService);

                case 4 -> modificar(sc, courseCrudService);

                case 5 -> eliminar(sc, courseCrudService);

                case 6 -> System.out.println("Regresando al menu principal");

                default -> System.out.println("Opcion no permitida");
            }
        }while (opcion != 6);
    }

    private static void listar(CourseCrudServiceImpl courseCrudService) {
        System.out.println("Listado de cursos");
        try {
            courseCrudService.getAll().forEach(System.out::println);
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error al listar los cursos" +e.getMessage());
        }
    }


    private static void buscarPorId(Scanner sc, CourseCrudServiceImpl courseCrudService) {
        System.out.println("Ingrese el id del curso a buscar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        Long id = sc.nextLong();
        try {
            System.out.println(courseCrudService.getById(id));
        } catch (SQLException e) {
            System.out.println("Error al buscar el curso de id "+id + " " +e.getMessage());
        }
    }
    
    private static void agregar(Scanner sc, CourseCrudServiceImpl courseCrudService) {
        Course curso = new Course();
        System.out.println("Ingres el nombre del curso");
        sc.nextLine(); //limpiamos el buffer 
        curso.setNombre(sc.nextLine());
        try {
            courseCrudService.create(curso);
        } catch (SQLException e) {
            System.out.println("Error al crear el curso de " + e.getMessage());
        }
    }
    
    private static void modificar(Scanner sc, CourseCrudServiceImpl courseCrudService) {
        Course curso;
        curso = new Course();
        System.out.println("Ingrese el id del curso a modicar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        curso.setId(sc.nextLong());
        System.out.println("Ingres el nombre nuevo del curso");
        while(!sc.hasNextLine()){
            System.out.println("Debe ingresar un nombre valido");
            sc.nextLine();
        }
        curso.setNombre(sc.nextLine());
        try {
            courseCrudService.update(curso);
        } catch (SQLException e) {
            System.out.println("Error al modificar el curso " + e.getMessage());
        }
    }
    
    
    private static void eliminar(Scanner sc, CourseCrudServiceImpl courseCrudService) {
        System.out.println("Ingrese el id del curso a eliminar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        Long idEliminar = sc.nextLong();
        try {
            courseCrudService.delete(idEliminar);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el curso " + e.getMessage());
        }
    }
    
}
