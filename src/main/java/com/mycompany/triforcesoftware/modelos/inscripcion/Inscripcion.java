/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.inscripcion;

/**
 *
 * @author huron_clinch
 */
public class Inscripcion {

    private int noInscripcion;
    private int noParticipante;
    private String codigoEvento;
    private String tipoInscripcion;

    public Inscripcion(int noInscripcion, int noParticipante, String codigoEvento, String tipoInscripcion) {
        this.noInscripcion = noInscripcion;
        this.noParticipante = noParticipante;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
    }

    public Inscripcion(int noParticipante, String codigoEvento, String tipoInscripcion) {
        this.noParticipante = noParticipante;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
    }

    public int getNoInscripcion() {
        return noInscripcion;
    }

    public int getNoParticipante() {
        return noParticipante;
    }

    public void setNoParticipante(int noParticipante) {
        this.noParticipante = noParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getTipoInscripcion() {
        return tipoInscripcion;
    }

    public void setTipoInscripcion(String tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }

}
