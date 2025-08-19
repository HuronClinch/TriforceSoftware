/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            + "VALUES (?, ?)";

    private static final String LEER_TABLA = "SELECT "
            + "correo_electronico, codigo_actividad FROM asistencia";

    public int crear(Connection connection, Asistencia asistencia) throws SQLException {//Crear un nuevo registro de asistencia
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try {
            preparedStatement = connection.prepareStatement(CREAR);//Ingresar comando SQL
            preparedStatement.setString(1, asistencia.getCorreoElectronico());
            preparedStatement.setString(2, asistencia.getCodigoActividad());//Formato para ingresar asistencia

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear asistencia");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAffected;
    }

    public LinkedList<Asistencia> leerTodos(Connection connection) throws SQLException {//Obtener todos los participantes
        LinkedList<Asistencia> lista = new LinkedList<>();//Crear una lista nueva
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (resultSet.next()) {
                lista.add(new Asistencia(
                        resultSet.getString("correo_electronico"),
                        resultSet.getString("codigo_actividad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar asistencias");
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
}
