/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.asistencia.Asistencia;
import com.mycompany.triforcesoftware.modelos.asistencia.AsistenciaCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class ContoladorAsistencia {

    private final Connection CONNECTION;
    private final AsistenciaCRUD CRUD;
    private LinkedList<Asistencia> lista;

    public ContoladorAsistencia() {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new AsistenciaCRUD();//
        try {
            listadoEventos();//Obtener el listado de las validaciones creadas
        } catch (SQLException e) {
            System.out.println("Error en lista ContoladorAsistencia");
            e.printStackTrace(System.out);
        }
    }

    public boolean nuevaAsistencia(Asistencia asistencia) {//Crear validacion
        try {
            CRUD.crear(CONNECTION, asistencia);//Agregar nueva asistencia
            JOptionPane.showMessageDialog(null, "Creacion de asistencia, exitosa");

            CONNECTION.close();//Cerrar coneccion
            return true;
        } catch (SQLException e) {
            System.out.println("Error en inscripcion");
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarAsistencia(String correo, String codigoActividad) {//Comprobar si ya existe el correo ingresado 
        for (Asistencia asistencia : lista) {
            if (asistencia.getCorreoElectronico().equals(correo) && asistencia.getCodigoActividad().equals(codigoActividad)) {//Comprobar si ya esta ingresado el pago
                JOptionPane.showMessageDialog(null, "ya existe la asistencia ");
                return false;
            }
        }
        return true;
    }

    private void listadoEventos() throws SQLException {//Obtener los datos de los eventos
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<Asistencia> getLista() {
        return lista;
    }
}
