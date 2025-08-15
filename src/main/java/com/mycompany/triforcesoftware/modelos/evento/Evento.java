/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.evento;

import java.sql.Date;

/**
 *
 * @author huron_clinch
 */
public class Evento {

    private String codigoEvento;
    private String fechaEvento; // Formato dd/MM/yyyy
    private String tipoEvento;
    private String tituloEvento;
    private String ubicacion;
    private int cupoMaximo;
    private double costoInscripcion;

    public Evento(String codigoEvento, String fechaEvento, String tipoEvento, String tituloEvento, String ubicacion, int cupoMaximo, double costoInscripcion) {
        this.codigoEvento = codigoEvento;
        this.fechaEvento = fechaEvento;
        this.tipoEvento = tipoEvento;
        this.tituloEvento = tituloEvento;
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.costoInscripcion = costoInscripcion;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public double getCostoInscripcion() {
        return costoInscripcion;
    }

    public void setCostoInscripcion(double costoInscripcion) {
        this.costoInscripcion = costoInscripcion;
    }

}
