package com.proyecto.estudiante.curso.ejemplo.models;

public class Estudent {

        private Long id;
        private String nombre;
        private String apellido;
        private Integer edad;
        private String sexo;
        private String telefono;
        private String correo;

        public Estudent() {
        }

        public Estudent(String nombre, String apellido, Integer edad, String sexo, String telefono, String correo) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.sexo = sexo;
            this.telefono = telefono;
            this.correo = correo;
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

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public Integer getEdad() {
            return edad;
        }

        public void setEdad(Integer edad) {
            this.edad = edad;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        @Override
        public String toString() {
            return "id: " + id +
                    " | nombre: " + nombre +
                    " | apellido: " + apellido +
                    " | edad: " + edad +
                    " | sexo: " + sexo +
                    " | telefono: " + telefono +
                    " | correo: " + correo;
        }
    }

