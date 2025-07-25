package com.proyecto.estudiante.curso.ejemplo.ui;

import java.util.Scanner;

public class MenuPrincipal {

    public static void menuPrincipal(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Listado de opciones" +
                    "\n1. Estudiante" +
                    "\n2. Curso" +
                    "\n3. Inscribir estudiante a curso" +
                    "\n4. Salir\n");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> MenuEstudiante.menuEstudiante();
                case 2 -> MenuCurso.menuCurso();
                case 3 -> MenuEstudianteCurso.menuEstudianteCurso();
                case 4 -> System.out.println("Saliendo del programa...");
            }
        }while (opcion != 4);

    }
}
