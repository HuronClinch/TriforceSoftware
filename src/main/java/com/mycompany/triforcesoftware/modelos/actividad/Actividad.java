/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.actividad;

import java.sql.Time;

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
    private Time horaInicio;
    private Time horaFin;
    private int cupoMaximo;

    public Actividad(String codigoActividad, String codigoEvento, String tipoActividad, String tituloActividad, String correoElectronicoPonente, Time horaInicio, Time horaFin, int cupoMaximo) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.tipoActividad = tipoActividad;
        this.tituloActividad = tituloActividad;
        this.correoElectronicoPonente = correoElectronicoPonente;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupoMaximo = cupoMaximo;
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
}
