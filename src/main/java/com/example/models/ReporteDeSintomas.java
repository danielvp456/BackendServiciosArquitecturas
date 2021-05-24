/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author Mauricio
 */
@NoSql(dataFormat = DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class ReporteDeSintomas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Field(name = "_id")
    String id;
    
    
    String  fecha, hora, paciente, listaDeSíntomas;

    public ReporteDeSintomas() {
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
