/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.triforcesoftware;

import com.mycompany.triforcesoftware.frontend.TriforcePanel;
import com.mycompany.triforcesoftware.utileria.Connect;
import com.mycompany.triforcesoftware.modelos.participante.ParticipanteCRUD;
import java.sql.Connection;

/**
 *
 * @author huron_clinch
 */
public class Triforce_Software {
    
    public static void main(String[] args) {
        TriforcePanel panel = new TriforcePanel();
        panel.setVisible(true);
        
//        Connection connection;
//        Connect connect = new Connect();
//        connection = connect.connect();

//        ParticipanteCRUD crearParicipante = new ParticipanteCRUD();
//        //Crear participante
//        Participante par = new Participante("Norman", "hola", "nuevo mundo", "PorNo@gmail.com");
//        crearParicipante.crear(connection, par);
//        //ver tabla
//        LinkedList<Participante> lista = crearParicipante.leerTodos(connection);
//        for (Participante p : lista) {
//            System.out.println("Nombre: " + p.getNombreCompleto());
//            System.out.println("Tipo: " + p.getTipoParticipante());
//            System.out.println("Institución: " + p.getInstitucionProcedencia());
//            System.out.println("Correo: " + p.getCorreoElectronico());
//            System.out.println("---------------------------");
//        }
//        //Actualizar
//        Participante par = new Participante("jose2", "ponente", "San carlos", "jose@gmail.com");
//        par.setNombreCompleto("joselin pollo");
//        crearParicipante.actualizar(connection, par);
//        //Eliminar
//        crearParicipante.eliminar(connection, "luis");
    }
}
