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

    private String correoElectronico;
    private String codigoEvento;
    private String tipoInscripcion;

    public Inscripcion(String correoElectronico, String codigoEvento, String tipoInscripcion) {
        this.correoElectronico = correoElectronico;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
