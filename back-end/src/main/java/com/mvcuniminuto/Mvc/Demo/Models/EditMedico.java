package com.mvcuniminuto.Mvc.Demo.Models;

public class EditMedico {
    private int id;
    private String nombre;
    private String especialidad;

    public EditMedico() {
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(){
        this.especialidad = especialidad;
    }
}
