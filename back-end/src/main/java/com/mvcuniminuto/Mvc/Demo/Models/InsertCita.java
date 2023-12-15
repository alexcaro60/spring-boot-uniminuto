package com.mvcuniminuto.Mvc.Demo.Models;

import java.util.Date;

public class InsertCita {
    private Date fecha;
    private int IdMedico;

    public InsertCita() {
    }

    // Getters y Setters

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(int IdMedico) {
        this.IdMedico = IdMedico;
    }
}
