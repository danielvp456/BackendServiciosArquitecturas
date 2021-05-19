/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

/**
 *
 * @author 57311
 */
public class ReporteDeSintomasDTO {
    String id;
     
    String  fecha, hora, paciente, listaDeSíntomas;

    public ReporteDeSintomasDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getListaDeSíntomas() {
        return listaDeSíntomas;
    }

    public void setListaDeSíntomas(String listaDeSíntomas) {
        this.listaDeSíntomas = listaDeSíntomas;
    }
    
    
}
