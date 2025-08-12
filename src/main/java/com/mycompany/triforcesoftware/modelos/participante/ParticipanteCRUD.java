/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.participante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ParticipanteCRUD {

    private static final String CREAR = "INSERT INTO participante "
            + "(nombre_completo, tipo_participante, institucion_procedencia, correo_electronico) "
            + "values ('%s', '%s', '%s', '%s')";
    private static final String LEER_TABLA = "SELECT * FROM participante";
    private static final String LEER = "SELECT * FROM participante WHERE correo_electronico = '%s'";
    private static final String ACTUALIZAR = "UPDATE participante SET "
            + "nombre_completo = '%s', tipo_participante = '%s', institucion_procedencia = '%s' "
            + "WHERE correo_electronico = '%s'";
    private static final String ELIMINAR = "DELETE FROM participante WHERE correo_electronico = '%s'";

    public void crear(Connection connection, Participante participante) {//Crear un nuevo participante
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    participante.getNombreCompleto(),
                    participante.getTipoParticipante(),
                    participante.getInstitucionProcedencia(),
                    participante.getCorreoElectronico());//Formato para ingresar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Participante creado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }

    public Participante leer(Connection connection, String correo) {//Leer un participante
        Participante participante = null;//Crear participante vacio
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(LEER, correo);//Formato para buscar participante 
            ResultSet datos = insertStatement.executeQuery(sql);//Ingresar comando SQL

            if (datos.next()) {
                participante = new Participante(
                        datos.getString("correo_electronico"),
                        datos.getString("nombre_completo"),
                        datos.getString("tipo_participante"),
                        datos.getString("institucion_procedencia"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer participante");
            e.printStackTrace();
        }
        return participante;
    }

    public LinkedList<Participante> leerTodos(Connection connection) {//Obtener todos los participantes
        LinkedList<Participante> lista = new LinkedList<>();
        try {
            Statement insertStatement = connection.createStatement();
            ResultSet datos = insertStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {
                Participante datosParticipante = new Participante(
                        datos.getString("correo_electronico"),
                        datos.getString("nombre_completo"),
                        datos.getString("tipo_participante"),
                        datos.getString("institucion_procedencia"));

                lista.add(datosParticipante);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar participantes");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Participante participante) {//Actualizar a un participante
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    participante.getNombreCompleto(),
                    participante.getTipoParticipante(),
                    participante.getInstitucionProcedencia(),
                    participante.getCorreoElectronico());//Formato para ingresar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Participante actualizado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, String correo) {//Eliminar participante
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(ELIMINAR, correo);//Formato para eliminar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Participante eliminado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }
}
