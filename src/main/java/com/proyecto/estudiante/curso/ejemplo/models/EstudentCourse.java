package com.proyecto.estudiante.curso.ejemplo.models;

public class EstudentCourse {


    private Long id;
    private Estudent estudiante;
    private Course curso;

    public EstudentCourse() {
    }

    public EstudentCourse(Estudent estudiante, Course curso) {
        this.estudiante = estudiante;
        this.curso = curso;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudent getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudent estudiante) {
        this.estudiante = estudiante;
    }

    public Course getCurso() {
        return curso;
    }

    public void setCurso(Course curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "id=" + id +
                "\nestudiante=" + estudiante.toString() +
                "\ncurso=" + curso.toString() + "\n";
    }
}
