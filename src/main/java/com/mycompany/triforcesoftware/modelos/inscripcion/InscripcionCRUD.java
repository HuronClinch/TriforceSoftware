/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.inscripcion;

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
public class InscripcionCRUD {

    private static final String CREAR = "INSERT INTO inscripcion "
            + "(no_participante, codigo_evento, tipo_inscripcion) "
            + "VALUES (?, ?, ?)";
    private static final String LEER_TABLA = "SELECT "
            + "no_inscripcion, no_participante, codigo_evento, tipo_inscripcion "
            + "FROM inscripcion";
    private static final String LEER = "SELECT * FROM inscripcion WHERE no_inscripcion = %d";
    private static final String ACTUALIZAR = "UPDATE inscripcion SET "
            + "no_participante=?, codigo_evento=?, tipo_inscripcion=? "
            + "WHERE no_inscripcion=?";
    private static final String ELIMINAR = "DELETE FROM inscripcion WHERE no_inscripcion=?";

    public int crear(Connection connection, Inscripcion inscripcion) throws SQLException {//Crear un nuevo registro de inscripcion
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(CREAR);
            preparedStatement.setInt(1, inscripcion.getNoParticipante());
            preparedStatement.setString(2, inscripcion.getCodigoEvento());
            preparedStatement.setString(3, inscripcion.getTipoInscripcion());

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear inscripción");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }

//    public Inscripcion leer(Connection connection, int id) {//Leer una incripcion segun el id
//        Inscripcion inscripcion = null;//Crear una incripcion vacia
//        try {
//            Statement selectStatement = connection.createStatement();
//            String sql = String.format(LEER, id);//Formato para buscar inscripcion
//            ResultSet datos = selectStatement.executeQuery(sql);//Ingresar comando SQL
//
//            if (datos.next()) {
//                inscripcion = new Inscripcion(
//                        datos.getInt("no_inscripcion"),
//                        datos.getString("correo_electronico"),
//                        datos.getString("codigo_evento"),
//                        datos.getString("tipo_inscripcion"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al leer la inscripcion");
//            e.printStackTrace();
//        }
//        return inscripcion;
//    }
    public LinkedList<Inscripcion> leerTodos(Connection connection) throws SQLException {//Obtener todos las inscpciones
        LinkedList<Inscripcion> lista = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(LEER_TABLA);
            while (resultSet.next()) {
                lista.add(new Inscripcion(
                        resultSet.getInt("no_inscripcion"),
                        resultSet.getInt("no_participante"),
                        resultSet.getString("codigo_evento"),
                        resultSet.getString("tipo_inscripcion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inscripciones");
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

    public int actualizar(Connection connection, Inscripcion inscripcion) throws SQLException {//Actualizar una inscripcion
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(ACTUALIZAR);
            preparedStatement.setInt(1, inscripcion.getNoParticipante());
            preparedStatement.setString(2, inscripcion.getCodigoEvento());
            preparedStatement.setString(3, inscripcion.getTipoInscripcion());
            preparedStatement.setInt(4, inscripcion.getNoInscripcion());

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar inscripción");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }

    public int eliminar(Connection connection, int noInscripcion) throws SQLException {//Eliminar registro de inscripcion
        PreparedStatement preparedStatement = null;
        int rowsAfected = 0;
        try {
            preparedStatement = connection.prepareStatement(ELIMINAR);
            preparedStatement.setInt(1, noInscripcion);

            rowsAfected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar inscripción");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAfected;
    }
}
