/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.validainscripcion;

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
public class ValidarInscripcionCRUD {

    private static final String CREAR = "INSERT INTO validar_inscripcion "
            + "(correo_electronico, codigo_evento) VALUES (?, ?)";

    private static final String LEER_TABLA = "SELECT "
            + "correo_electronico, codigo_evento FROM validar_inscripcion";

    public int crear(Connection connection, ValidarInscripcion validarInscripcion) throws SQLException {
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try {
            preparedStatement = connection.prepareStatement(CREAR);
            preparedStatement.setString(1, validarInscripcion.getCorreoElectronico());
            preparedStatement.setString(2, validarInscripcion.getCodigoEvento());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear validación de inscripción");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAffected;
    }

    public LinkedList<ValidarInscripcion> leerTodos(Connection connection) throws SQLException {
        LinkedList<ValidarInscripcion> lista = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(LEER_TABLA);

            while (resultSet.next()) {
                lista.add(new ValidarInscripcion(
                        resultSet.getString("correo_electronico"),
                        resultSet.getString("codigo_evento")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar validaciones de inscripción");
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
