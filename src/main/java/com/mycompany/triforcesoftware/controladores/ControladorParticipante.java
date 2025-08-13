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

    public void nuevoParticipante(String campo1, String campo2, String campo3, String campo4) {//Crear participante
        try {
            Participante participante = new Participante(campo1, campo2, campo3, campo4);//Crear Personaje
            CRUD.crear(CONNECTION, participante);//Agregar participante
            CONNECTION.close();
        } catch (SQLException e) {
            System.out.println("Eror en crear participante");
            e.printStackTrace();
        }
    }

    private void ListadoParticipantes() {//Obtener los datos de los participantes
        lista = CRUD.leerTodos(CONNECTION);
    }

    public LinkedList<Participante> getLista() {
        return lista;
    }
}
