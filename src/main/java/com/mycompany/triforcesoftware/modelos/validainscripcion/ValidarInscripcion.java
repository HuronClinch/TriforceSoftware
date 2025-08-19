/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.validainscripcion;

/**
 *
 * @author huron_clinch
 */
public class ValidarInscripcion {

    private String correoElectronico;
    private String codigoEvento;

    public ValidarInscripcion(String correoElectronico, String codigoEvento) {
        this.correoElectronico = correoElectronico;
        this.codigoEvento = codigoEvento;
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
    
}
