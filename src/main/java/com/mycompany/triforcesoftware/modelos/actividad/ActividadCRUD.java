/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ActividadCRUD {

    private static final String CREAR = "INSERT INTO actividad "
            + "(codigo_actividad, codigo_evento, tipo_actividad, titulo_actividad, correo_electronico_ponente, hora_inicio, hora_fin, cupo_maximo) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LEER_TABLA = "SELECT * FROM actividad";

    public int crear(Connection connection, Actividad actividad) throws SQLException {//Crear una nueva actividad
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            preparedStatement = connection.prepareStatement(CREAR);
            preparedStatement.setString(1, actividad.getCodigoActividad());
            preparedStatement.setString(2, actividad.getCodigoEvento());
            preparedStatement.setString(3, actividad.getTipoActividad());
            preparedStatement.setString(4, actividad.getTituloActividad());
            preparedStatement.setString(5, actividad.getCorreoElectronicoPonente());
            preparedStatement.setTime(6, actividad.getHoraInicio());
            preparedStatement.setTime(7, actividad.getHoraFin());
            preparedStatement.setInt(8, actividad.getCupoMaximo());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar la actividad");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAffected;
    }

    public LinkedList<Actividad> leerTodos(Connection connection) throws SQLException {//Obtener todas las actividades
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Actividad> lista = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement(LEER_TABLA);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Actividad actividad = new Actividad(
                        resultSet.getString("codigo_actividad"),
                        resultSet.getString("codigo_evento"),
                        resultSet.getString("tipo_actividad"),
                        resultSet.getString("titulo_actividad"),
                        resultSet.getString("correo_electronico_ponente"),
                        resultSet.getTime("hora_inicio"),
                        resultSet.getTime("hora_fin"),
                        resultSet.getInt("cupo_maximo")
                );
                lista.add(actividad);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer actividades");
            e.printStackTrace(System.out);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return lista;
    }
}
