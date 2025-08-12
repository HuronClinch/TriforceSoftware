/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.inscripcion;

import java.sql.Connection;
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
            + "(correo_electronico, codigo_evento, tipo_inscripcion) "
            + "VALUES ('%s', '%s', '%s')";
    private static final String LEER_TABLA = "SELECT * FROM inscripcion";
    private static final String LEER = "SELECT * FROM inscripcion WHERE no_inscripcion = %d";
    private static final String ACTUALIZAR = "UPDATE inscripcion SET "
            + "correo_electronico='%s', codigo_evento='%s', tipo_inscripcion='%s' "
            + "WHERE no_inscripcion=%d";
    private static final String ELIMINAR = "DELETE FROM inscripcion WHERE no_inscripcion = %d";

    public void crear(Connection connection, Inscripcion inscripcion) {//Crear un nuevo registro de inscripcion
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    inscripcion.getCorreoElectronico(),
                    inscripcion.getCodigoEvento(),
                    inscripcion.getTipoInscripcion());//Formato para ingresar registro inscripcion

            insertStatement.executeUpdate(sql);
            System.out.println("SQL ejecutado (Registro inscripcion creado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar inscripcion");
            e.printStackTrace();
        }
    }

    public Inscripcion leer(Connection connection, int id) {//Leer una incripcion segun el id
        Inscripcion inscripcion = null;//Crear una incripcion vacia
        try {
            Statement selectStatement = connection.createStatement();
            String sql = String.format(LEER, id);//Formato para buscar inscripcion
            ResultSet datos = selectStatement.executeQuery(sql);//Ingresar comando SQL

            if (datos.next()) {
                inscripcion = new Inscripcion(
                        datos.getInt("no_inscripcion"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_evento"),
                        datos.getString("tipo_inscripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la inscripcion");
            e.printStackTrace();
        }
        return inscripcion;
    }

    public LinkedList<Inscripcion> leerTodos(Connection connection) {//Obtener todos las inscpciones
        LinkedList<Inscripcion> lista = new LinkedList<>();
        try {
            Statement selectStatement = connection.createStatement();
            ResultSet datos = selectStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {
                Inscripcion datosInscripcion = new Inscripcion(
                        datos.getInt("no_inscripcion"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_evento"),
                        datos.getString("tipo_inscripcion"));

                lista.add(datosInscripcion);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inscripciones");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Inscripcion inscripcion) {//Actualizar una inscripcion
        try {
            Statement updateStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    inscripcion.getCorreoElectronico(),
                    inscripcion.getCodigoEvento(),
                    inscripcion.getTipoInscripcion(),
                    inscripcion.getNoInscripcion());//Formato para ingresar inscripcion

            updateStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("SQL ejecutado (Inscripcion actualizada): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al actualizar la inscripcion");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, int id) {//Eliminar registro de inscripcion
        try {
            Statement deleteStatement = connection.createStatement();

            String sql = String.format(ELIMINAR, id);//Formato para eliminar inscripcion

            deleteStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("SQL ejecutado (Inscripcion eliminada): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al eliminar la inscripcion");
            e.printStackTrace();
        }
    }
}
