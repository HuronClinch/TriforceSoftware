/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.asistencia.pagos.Pago;
import com.mycompany.triforcesoftware.modelos.asistencia.pagos.PagoCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class ControladorPago {

    private final Connection CONNECTION;
    private final PagoCRUD CRUD;
    private LinkedList<Pago> lista;

    public ControladorPago() throws SQLException {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new PagoCRUD();//
        listadoEventos();//Obtener el listado de los eventos creados
    }

    public boolean nuevoEvento(Pago pago) {//Crear participante
        try {
            CRUD.crear(CONNECTION, pago);//Agregar evento nuevo
            CONNECTION.close();//Cerrar coneccion
            return true;
        } catch (SQLException e) {
            System.out.println("Error Crear pago");
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarPago(String correo, String codigoEvento) {//Comprobar si ya existe el correo ingresado 
        for (Pago pago : lista) {
            if (pago.getCorreoElectronico().equals(correo) && pago.getCodigoEvento().equals(codigoEvento)) {//Comprobar si ya esta ingresado el pago
                JOptionPane.showMessageDialog(null, "ya existe el pago al evento");
                return false;
            }
        }
        return true;
    }

    private void listadoEventos() throws SQLException {//Obtener los datos de los eventos
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<Pago> getLista() {
        return lista;
    }
}
