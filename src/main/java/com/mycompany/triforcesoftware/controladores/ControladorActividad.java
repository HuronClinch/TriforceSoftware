/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.actividad.Actividad;
import com.mycompany.triforcesoftware.modelos.actividad.ActividadCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ControladorActividad {

    private final Connection CONNECTION;
    private final ActividadCRUD CRUD;
    private LinkedList<Actividad> lista;

    public ControladorActividad() {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new ActividadCRUD();//
        try {
            listadoEventos();//Obtener el listado de las actividades creadas
        } catch (SQLException e) {
            System.out.println("Error en lista ContoladorAsistencia");
            e.printStackTrace(System.out);
        }
    }

    public boolean nuevaActividad(Actividad actividad) {//Crear participante
        try {
            CRUD.crear(CONNECTION, actividad);//Agregar evento actividad
            CONNECTION.close();//Cerrar coneccion
            return true;
        } catch (SQLException e) {
            System.out.println("Error en crear evento");
            e.printStackTrace();
            return false;
        }
    }

    private void listadoEventos() throws SQLException {//Obtener los datos de los eventos
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<Actividad> getLista() {
        return lista;
    }
}
