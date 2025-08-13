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

/**
 *
 * @author huron_clinch
 */
public class ControladorParticipante {

    private Connection connection;

    public ControladorParticipante() {
        connection = (Connection) new Connect();
    }

    public void nuevoParticipante(String campo1, String campo2, String campo3, String campo4) {//Crear participante
        try {
            Participante participante = new Participante(campo1, campo2, campo3, campo4);//Crear Personaje

            ParticipanteCRUD crearParicipante = new ParticipanteCRUD();
            crearParicipante.crear(connection, participante);//Agregar participante
            connection.close();
        } catch (SQLException e) {
            System.out.println("Eroro en crear participante");
            e.printStackTrace();
        }
    }
}
