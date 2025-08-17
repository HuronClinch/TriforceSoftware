/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import com.mycompany.triforcesoftware.modelos.inscripcion.InscripcionCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ControladorInscripcion {

    private final Connection CONNECTION;
    private final InscripcionCRUD CRUD;
    private LinkedList<Inscripcion> lista;

    public ControladorInscripcion() throws SQLException {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new InscripcionCRUD();//
        listadoEventos();//Obtener el listado de los inscripciones creadas
    }

    public boolean nuevoEvento(Inscripcion inscripcion) {//Crear inscripcion
        try {
            CRUD.crear(CONNECTION, inscripcion);//Agregar evento nuevo
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

    public LinkedList<Inscripcion> getLista() {
        return lista;
    }
}
