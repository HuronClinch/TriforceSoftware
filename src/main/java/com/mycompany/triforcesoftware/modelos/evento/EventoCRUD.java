/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class EventoCRUD {

    private static final String CREAR = "INSERT INTO evento "
            + "(codigo_evento, fecha_evento, tipo_evento, titulo_evento, ubicacion, cupo_maximo, costo_inscripcion) "
            + "VALUES (?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?, ?, ?)";
    private static final String LEER_TABLA = "UPDATE evento SET "
            + "fecha_evento=STR_TO_DATE(?, '%d/%m/%Y'), tipo_evento=?, titulo_evento=?, ubicacion=?, cupo_maximo=?, costo_inscripcion=? "
            + "WHERE codigo_evento=?";
    private static final String ACTUALIZAR = "UPDATE evento SET "
            + "fecha_evento=STR_TO_DATE(?, '%d/%m/%Y'), tipo_evento=?, titulo_evento=?, ubicacion=?, cupo_maximo=?, costo_inscripcion=? "
            + "WHERE codigo_evento=?";
    private static final String ELIMINAR = "DELETE FROM evento WHERE codigo_evento=?";

    private final SimpleDateFormat FORMATO_ENTRADA = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat FORMATO_SQL = new SimpleDateFormat("yyyy-MM-dd");

    public int crear(Connection connection, Evento evento) throws SQLException {//crear nuevo evento
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(CREAR);//Formato para los campos
            preparedStatement.setString(1, evento.getCodigoEvento());
            preparedStatement.setString(2, evento.getFechaEvento());
            preparedStatement.setString(3, evento.getTipoEvento());
            preparedStatement.setString(4, evento.getTituloEvento());
            preparedStatement.setString(5, evento.getUbicacion());
            preparedStatement.setInt(6, evento.getCupoMaximo());
            preparedStatement.setDouble(7, evento.getCostoInscripcion());

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear evento");
            e.printStackTrace(System.out);
        } finally {
            preparedStatement.close();
        }
        return rowsAfected;
    }

    public LinkedList<Evento> leerTodos(Connection connection) throws SQLException {//Obtener todos los eventos
        LinkedList<Evento> lista = new LinkedList<>();//Crear una lista nula
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(LEER_TABLA);//Ingresar formato

            while (resultSet.next()) {//Obtener datos de los eventos y guardalos en LinkedList
                lista.add(new Evento(
                        resultSet.getString("codigo_evento"),
                        resultSet.getString("fecha_evento"),
                        resultSet.getString("tipo_evento"),
                        resultSet.getString("titulo_evento"),
                        resultSet.getString("ubicacion"),
                        resultSet.getInt("cupo_maximo"),
                        resultSet.getDouble("costo_inscripcion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar eventos");
            e.printStackTrace(System.out);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return lista;
    }

    public int actualizar(Connection connection, Evento evento) throws SQLException {//Actualizar a un participante
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(ACTUALIZAR);//Ingresar formato
            preparedStatement.setString(1, evento.getFechaEvento());
            preparedStatement.setString(2, evento.getTipoEvento());
            preparedStatement.setString(3, evento.getTituloEvento());
            preparedStatement.setString(4, evento.getUbicacion());
            preparedStatement.setInt(5, evento.getCupoMaximo());
            preparedStatement.setDouble(6, evento.getCostoInscripcion());
            preparedStatement.setString(7, evento.getCodigoEvento());

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar evento");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }

    public int eliminar(Connection connection, String codigoEvento) throws SQLException {//Eliminar Registro asistencia
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(ELIMINAR);//Ingresar formato
            preparedStatement.setString(1, codigoEvento);

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar evento");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }
}
