package com.proyecto.estudiante.curso.ejemplo.models;

public class Course {

    private Long id;
    private String nombre;

    public Course() {
    }

    public Course(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre ;
    }
}
