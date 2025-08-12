/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia.pagos;

/**
 *
 * @author huron_clinch
 */
public class Pago {

    private int noPago;
    private String correoElectronico;
    private String codigoEvento;
    private String metodoPago;
    private double monto;
    

    public Pago() {
    }

    public Pago(int noPago, String correoElectronico, String codigoEvento, String metodoPago, double monto) {
        this.noPago = noPago;
        this.correoElectronico = correoElectronico;
        this.codigoEvento = codigoEvento;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    public int getNoPago() {
        return noPago;
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
