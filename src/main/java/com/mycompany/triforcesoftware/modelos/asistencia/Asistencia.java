/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia;

/**
 *
 * @author huron_clinch
 */
public class Asistencia {

    private int noAsistencia;
    private String correoElectronico;
    private String codigoActividad;

    public Asistencia(String correoElectronico, String codigoActividad) {//Agregar nuevo
        this.correoElectronico = correoElectronico;
        this.codigoActividad = codigoActividad;
    }

    public Asistencia(int noAsistencia, String correoElectronico, String codigoActividad) {//Consulta
        this.noAsistencia = noAsistencia;
        this.correoElectronico = correoElectronico;
        this.codigoActividad = codigoActividad;
    }

    public int getNoAsistencia() {
        return noAsistencia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }
}
