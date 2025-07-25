package com.proyecto.estudiante.curso.ejemplo.ui;


import com.proyecto.estudiante.curso.ejemplo.models.Estudent;
import com.proyecto.estudiante.curso.ejemplo.repository.impl.EstudentCrudRepositoryImpl;
import com.proyecto.estudiante.curso.ejemplo.services.impl.EstudentCrudServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuEstudiante {

    public static void menuEstudiante(){

        Scanner sc = new Scanner(System.in);
        int opcion;
        EstudentCrudServiceImpl estudentCrudService = new EstudentCrudServiceImpl(new EstudentCrudRepositoryImpl());
        do {
            System.out.println("Listado de opciones" +
                    "\n1. Listar Estudiantes" +
                    "\n2. Buscar por id" +
                    "\n3. Agregar Estudiante" +
                    "\n4. Modificar Estudiante" +
                    "\n5. Eliminar Estudiante" +
                    "\n6. Regresar\n");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> listar(estudentCrudService);

                case 2 -> buscarPorId(sc, estudentCrudService);

                case 3 -> agregar(sc, estudentCrudService);

                case 4 -> modificar(sc, estudentCrudService);

                case 5 -> eliminar(sc, estudentCrudService);

                case 6 -> System.out.println("Regresando al menu principal");

                default -> System.out.println("Opcion no permitida");
            }
        }while (opcion != 6);
    }

    private static void listar(EstudentCrudServiceImpl estudentCrudService) {
        System.out.println("Listado de estudiante");
        try {
            estudentCrudService.getAll().forEach(System.out::println);
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error al listar los estudiante " +e.getMessage());
        }
    }


    private static void buscarPorId(Scanner sc, EstudentCrudServiceImpl estudentCrudService) {
        System.out.println("Ingrese el id del estudiante a buscar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        Long id = sc.nextLong();
        try {
            System.out.println(estudentCrudService.getById(id));
        } catch (SQLException e) {
            System.out.println("Error al buscar el estudiante de id "+id + " " +e.getMessage());
        }
    }

    private static void agregar(Scanner sc, EstudentCrudServiceImpl estudentCrudService) {

        Estudent estudent =  new Estudent();
        rellenarDatos(sc, estudent);
        try {
            estudentCrudService.create(estudent);
        } catch (SQLException e) {
            System.out.println("Error al crear el estudiante de " + e.getMessage());
        }
    }

    private static void modificar(Scanner sc, EstudentCrudServiceImpl estudentCrudService) {

        Estudent estudent = new Estudent();
        System.out.println("Ingrese el id del estudiante a modicar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        estudent.setId(sc.nextLong());

        rellenarDatos(sc,  estudent);

        try {
            estudentCrudService.update(estudent);
        } catch (SQLException e) {
            System.out.println("Error al modificar el estudiante " + e.getMessage());
        }
    }


    private static void eliminar(Scanner sc, EstudentCrudServiceImpl estudentCrudService) {
        System.out.println("Ingrese el id del estudiante a eliminar");
        while(!sc.hasNextLong()){
            System.out.println("Debe ingresar un id valido");
            sc.next();
        }
        Long idEliminar = sc.nextLong();
        try {
            estudentCrudService.delete(idEliminar);
        } catch (SQLException e) {
            System.out.println("Error al eliminar el estudiante " + e.getMessage());
        }
    }


    private static Estudent rellenarDatos(Scanner sc, Estudent estudent) {
        sc.nextLine();
        System.out.println("Ingrese el nombre");
        estudent.setNombre(sc.nextLine());
        System.out.println("Ingrese los apellidos");
        estudent.setApellido(sc.nextLine());
        System.out.println("Ingrese el sexo");
        estudent.setSexo(sc.nextLine());
        System.out.println("Ingrese el numero de telefono");
        estudent.setTelefono(sc.nextLine());
        System.out.println("Ingrese el email");
        estudent.setCorreo(sc.nextLine());

        System.out.println("Ingrese la edad");
        estudent.setEdad(sc.nextInt());

        return estudent;
    }
}
