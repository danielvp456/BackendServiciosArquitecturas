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
public class UseVacunacion {
    private String Id, lugarResidencia, correo;
    private boolean estadoDeVacunación;

    public UseVacunacion() {
    }
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLugarResidencia() {
        return lugarResidencia;
    }

    public void setLugarResidencia(String lugarResidencia) {
        this.lugarResidencia = lugarResidencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstadoDeVacunación() {
        return estadoDeVacunación;
    }

    public void setEstadoDeVacunación(boolean estadoDeVacunación) {
        this.estadoDeVacunación = estadoDeVacunación;
    }
    
    
}
