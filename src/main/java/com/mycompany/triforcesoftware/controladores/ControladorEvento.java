/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.evento.Evento;
import com.mycompany.triforcesoftware.modelos.evento.EventoCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ControladorEvento {

    private final Connection CONNECTION;
    private final EventoCRUD CRUD;
    private LinkedList<Evento> lista;

    public ControladorEvento() throws SQLException {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new EventoCRUD();//
        listadoEventos();//Obtener el listado de los eventos creados
    }

    public boolean nuevoEvento(Evento evento) {//Crear participante
        try {
            CRUD.crear(CONNECTION, evento);//Agregar evento nuevo
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

    public LinkedList<Evento> getLista() {
        return lista;
    }
}
