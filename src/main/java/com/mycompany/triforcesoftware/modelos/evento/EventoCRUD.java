/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.evento;

import com.mycompany.triforcesoftware.modelos.asistencia.Asistencia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class EventoCRUD {

    private static final String CREAR = "INSERT INTO evento "
            + "(codigo_evento, fecha_evento, tipo_inscripcion, titulo_evento, ubicacion, cupo_maximo) "
            + "values ('%s', '%s', '%s', '%s', '%s', %d)";
    private static final String LEER_TABLA = "SELECT * FROM evento";
    private static final String LEER = "SELECT * FROM evento WHERE codigo_evento = %s";
    private static final String ACTUALIZAR = "UPDATE evento SET "
            + "fecha_evento='%s', tipo_inscripcion='%s', titulo_evento='%s', ubicacion='%s', cupo_maximo=%d"
            + "WHERE codigo_evento='%s'";
    private static final String ELIMINAR = "DELETE FROM evento WHERE codigo_evento = '%s'";

    public void crear(Connection connection, Evento evento) {//Crear un nuevo evento
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    evento.getCodigoEvento(),
                    evento.getFechaEvento(),
                    evento.getTipoInscripcion(),
                    evento.getTituloEvento(),
                    evento.getUbicacion(),
                    evento.getCupoMaximo());//Formato para ingresar evento

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Registro evento creado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar participante");
            e.printStackTrace();
        }
    }

    public Evento leer(Connection connection, String codigoEvento) {//Leer un evento
        Evento evento = null;//Crear un evento vacio
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(LEER, codigoEvento);//Formato para buscar un evento
            ResultSet datos = insertStatement.executeQuery(sql);//Ingresar comando SQL

            if (datos.next()) {//Obtener datos del evento
                evento = new Evento(
                        datos.getString("codigo_evento"),
                        datos.getDate("fecha_evento"),
                        datos.getString("tipo_inscripcion"),
                        datos.getString("titulo_evento"),
                        datos.getString("ubicacion"),
                        datos.getInt("cupo_maximo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al el evento");
            e.printStackTrace();
        }
        return evento;
    }

    public LinkedList<Evento> leerTodos(Connection connection) {//Obtener todos los eventos
        LinkedList<Evento> lista = new LinkedList<>();
        try {
            Statement insertStatement = connection.createStatement();
            ResultSet datos = insertStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {//Obtener datos de los eventos y guardalos en LinkedList
                Evento datosEvento = new Evento(
                        datos.getString("codigo_evento"),
                        datos.getDate("fecha_evento"),
                        datos.getString("tipo_inscripcion"),
                        datos.getString("titulo_evento"),
                        datos.getString("ubicacion"),
                        datos.getInt("cupo_maximo"));

                lista.add(datosEvento);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar Evento nuevo");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Evento evento) {//Actualizar a un participante
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    evento.getCodigoEvento(),
                    evento.getFechaEvento(),
                    evento.getTipoInscripcion(),
                    evento.getTituloEvento(),
                    evento.getUbicacion(),
                    evento.getCupoMaximo());//Formato para ingresar evento 

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Evento actualizado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar Evento");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, String codigoEvento) {//Eliminar Registro asistencia
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ELIMINAR, codigoEvento);//Formato para eliminar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Evento eliminado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al Elimiar un evento");
            e.printStackTrace();
        }
    }
}
