/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia;

import com.mycompany.triforcesoftware.modelos.participante.Participante;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class AsistenciaCRUD {

    private static final String CREAR = "INSERT INTO asistencia "
            + "(correo_electronico, codigo_actividad) "
            + "values ('%s', '%s')";
    private static final String LEER_TABLA = "SELECT * FROM asistencia";
    private static final String LEER = "SELECT * FROM asistencia WHERE no_asistencia = %d";
    private static final String ACTUALIZAR = "UPDATE asistencia SET "
            + "correo_electronico = '%s', codigo_actividad = '%s' "
            + "WHERE no_asistencia = %d";
    private static final String ELIMINAR = "DELETE FROM asistencia WHERE no_asistencia = %d";

    public void crear(Connection connection, Asistencia asistencia) {//Crear un nuevo registro de asistencia
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    asistencia.getCorreoElectronico(),
                    asistencia.getCodigoActividad());//Formato para ingresar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Registro asistencia creado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }

    public Asistencia leer(Connection connection, int id) {//Leer un participante
        Asistencia asistencia = null;//Crear un registro nulo de asistencia 
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(LEER, id);//Formato para buscar una registro de asistencia
            ResultSet datos = insertStatement.executeQuery(sql);//Ingresar comando SQL

            if (datos.next()) {//Obtener datos de asistencia
                asistencia = new Asistencia(
                        datos.getInt("no_asistencia"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_actividad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer participante");
            e.printStackTrace();
        }
        return asistencia;
    }

    public LinkedList<Asistencia> leerTodos(Connection connection) {//Obtener todos los participantes
        LinkedList<Asistencia> lista = new LinkedList<>();
        try {
            Statement insertStatement = connection.createStatement();
            ResultSet datos = insertStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {//Obtener datos de asistnecia y guardalos en LinkedList
                Asistencia datosParticipante = new Asistencia(
                        datos.getInt("no_asistencia"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_actividad"));

                lista.add(datosParticipante);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar participantes");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Asistencia asistencia) {//Actualizar a un participante
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    asistencia.getCorreoElectronico(),
                    asistencia.getCodigoActividad(),
                    asistencia.getNoAsistencia());//Formato para ingresar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Registro asistencia actualizado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, String correo) {//Eliminar Registro asistencia
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ELIMINAR, correo);//Formato para eliminar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Registro asistencia eliminado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al eliminar evento");
            e.printStackTrace();
        }
    }
}
