package com.mvcuniminuto.Mvc.Demo.Models;

public class InsertMedico {
    private String nombre;
    private String especialidad;

    public InsertMedico() {
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad(){ return especialidad;}

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

}
