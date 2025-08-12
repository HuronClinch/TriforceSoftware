/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.actividad;

/**
 *
 * @author huron_clinch
 */
public class Actividad {

    private String codigoActividad;
    private String codigoEvento;
    private String tipoActividad;
    private String tituloActividad;
    private String correoElectronicoPonente;
    private String horaInicio;
    private String horaFin;

    public Actividad(String codigoActividad, String codigoEvento, String tipoActividad, String tituloActividad, String correoElectronicoPonente, String horaInicio, String horaFin) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.tipoActividad = tipoActividad;
        this.tituloActividad = tituloActividad;
        this.correoElectronicoPonente = correoElectronicoPonente;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getTituloActividad() {
        return tituloActividad;
    }

    public void setTituloActividad(String tituloActividad) {
        this.tituloActividad = tituloActividad;
    }

    public String getCorreoElectronicoPonente() {
        return correoElectronicoPonente;
    }

    public void setCorreoElectronicoPonente(String correoElectronicoPonente) {
        this.correoElectronicoPonente = correoElectronicoPonente;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    
}
