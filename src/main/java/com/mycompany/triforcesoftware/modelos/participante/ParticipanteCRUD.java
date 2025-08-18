/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.participante;

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
public class ParticipanteCRUD {

    private static final String CREAR = "INSERT INTO participante "
            + "(correo_electronico, nombre_completo, tipo_participante, institucion_procedencia) "
            + "VALUES (?, ?, ?, ?)";
    private static final String LEER_TABLA = "SELECT "
            + "correo_electronico, nombre_completo, tipo_participante, institucion_procedencia "
            + "FROM participante";

    public int crear(Connection connection, Participante participante) throws SQLException {//Crear un nuevo participante
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;

        try {
            preparedStatement = connection.prepareStatement(CREAR);//Ingresar comando SQL
            preparedStatement.setString(1, participante.getCorreoElectronico());
            preparedStatement.setString(2, participante.getNombreCompleto());
            preparedStatement.setString(3, participante.getTipoParticipante());
            preparedStatement.setString(4, participante.getInstitucionProcedencia());//Formato para ingresar participante

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear participante");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }

    public LinkedList<Participante> leerTodos(Connection connection) throws SQLException {//Obtener todos los participantes
        LinkedList<Participante> lista = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (resultSet.next()) {
                lista.add(new Participante(
                        resultSet.getString("nombre_completo"),
                        resultSet.getString("tipo_participante"),
                        resultSet.getString("institucion_procedencia"),
                        resultSet.getString("correo_electronico")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar participantes");
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
