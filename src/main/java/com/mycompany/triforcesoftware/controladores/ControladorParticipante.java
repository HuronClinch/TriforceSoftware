/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.modelos.participante.Participante;
import com.mycompany.triforcesoftware.modelos.participante.ParticipanteCRUD;
import com.mycompany.triforcesoftware.utileria.Connect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ControladorParticipante {

    private Connection CONNECTION;
    private ParticipanteCRUD CRUD;
    private LinkedList<Participante> lista;

    public ControladorParticipante() {
        Connect conceccion = new Connect();
        CONNECTION = conceccion.connect();//Crear conceccion con base de datos 
        CRUD = new ParticipanteCRUD();//
        ListadoParticipantes();//Obtener el listado de las personas incritas
    }

    public boolean nuevoParticipante(String nombre, String tipo, String institucion, String correo) {//Crear participante
        try {
            Participante participante = new Participante(nombre, tipo, institucion, correo);//Crear Personaje
            CRUD.crear(CONNECTION, participante);//Agregar participante
            CONNECTION.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Eror en crear participante");
            e.printStackTrace();
            return false;
        }
    }

    private void ListadoParticipantes() {//Obtener los datos de los participantes
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<Participante> getLista() {
        return lista;
    }
}
