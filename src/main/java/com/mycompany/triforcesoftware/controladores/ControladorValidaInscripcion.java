/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.validainscripcion.ValidarInscripcion;
import com.mycompany.triforcesoftware.modelos.validainscripcion.ValidarInscripcionCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class ControladorValidaInscripcion {

    private final Connection CONNECTION;
    private final ValidarInscripcionCRUD CRUD;
    private LinkedList<ValidarInscripcion> lista;

    public ControladorValidaInscripcion() {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new ValidarInscripcionCRUD();//
        try {
            listadoEventos();//Obtener el listado de las validaciones creadas
        } catch (SQLException e) {
            System.out.println("Error al lisatr validacion inscripcion");
            e.printStackTrace(System.out);
        }
    }

    public boolean nuevoEvento(ValidarInscripcion validarInscripcion) {//Crear validacion
        try {
            CRUD.crear(CONNECTION, validarInscripcion);//Agregar nueva validacion
            JOptionPane.showMessageDialog(null, "Validacion de inscripcion correctamente");

            CONNECTION.close();//Cerrar coneccion
            return true;
        } catch (SQLException e) {
            System.out.println("Error en inscripcion");
            e.printStackTrace();
            return false;
        }
    }

    private void listadoEventos() throws SQLException {//Obtener los datos de los eventos
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<ValidarInscripcion> getLista() {
        return lista;
    }
}
