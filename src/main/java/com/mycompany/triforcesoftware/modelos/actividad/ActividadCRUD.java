/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.actividad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ActividadCRUD {

    private static final String CREAR = "INSERT INTO participante "
            + "(codigo_actividad, codigo_evento, tipo_actividad, titulo_actividad, correo_electronico_ponente, hora_inicio, hora_fin) "
            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    private static final String LEER_TABLA = "SELECT * FROM actividad";
    private static final String LEER = "SELECT * FROM actividad WHERE correo_electronico = '%s'";
    private static final String ACTUALIZAR = "UPDATE participante SET "
            + "codigo_evento = '%s', tipo_actividad = '%s', titulo_actividad = '%s', correo_electronico_ponente = '%s', hora_inicio = '%s', hora_fin = '%s' "
            + "WHERE codigo_actividad = '%s'";
    private static final String ELIMINAR = "DELETE FROM actividad WHERE codigo_actividad = '%s'";

    public void crear(Connection connection, Actividad actividad) {//Crear una nueva actividad
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    actividad.getCodigoActividad(),
                    actividad.getCodigoEvento(),
                    actividad.getTipoActividad(),
                    actividad.getTituloActividad(),
                    actividad.getCorreoElectronicoPonente(),
                    actividad.getHoraInicio(),
                    actividad.getHoraFin());//Formato para ingresar actividad

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Actividad creada): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar actividad");
            e.printStackTrace();
        }
    }

    public Actividad leer(Connection connection, String codigoActividad) {//Leer una actividad
        Actividad actividad = null;//Crear una actividad vacia
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(LEER, codigoActividad);//Formato para buscar actividad 
            ResultSet datos = insertStatement.executeQuery(sql);//Ingresar comando SQL

            if (datos.next()) {
                actividad = new Actividad(
                        datos.getString("codigo_actividad"),
                        datos.getString("codigo_evento"),
                        datos.getString("tipo_actividad"),
                        datos.getString("titulo_actividad"),
                        datos.getString("correo_electronico_ponente"),
                        datos.getString("hora_inicio"),
                        datos.getString("hora_fin"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer actividad");
            e.printStackTrace();
        }
        return actividad;
    }

    public LinkedList<Actividad> leerTodos(Connection connection) {//Obtener todas las actividades
        LinkedList<Actividad> lista = new LinkedList<>();
        try {
            Statement insertStatement = connection.createStatement();
            ResultSet datos = insertStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {
                Actividad datosActividad = new Actividad(
                        datos.getString("codigo_actividad"),
                        datos.getString("codigo_evento"),
                        datos.getString("tipo_actividad"),
                        datos.getString("titulo_actividad"),
                        datos.getString("correo_electronico_ponente"),
                        datos.getString("hora_inicio"),
                        datos.getString("hora_fin"));

                lista.add(datosActividad);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar todos las actividades");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Actividad actividad) {//Actualizar a un actividad
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    actividad.getCodigoEvento(),
                    actividad.getTipoActividad(),
                    actividad.getTituloActividad(),
                    actividad.getCorreoElectronicoPonente(),
                    actividad.getHoraInicio(),
                    actividad.getHoraFin(),
                    actividad.getCodigoActividad());//Formato para ingresar participante

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Actividad actualizada):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al actualizar actividad");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, String correo) {//Eliminar actividad
        try {
            Statement insertStatement = connection.createStatement();
            String sql = String.format(ELIMINAR, correo);//Formato para eliminar actividad

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("sql ejecutado (Actividad eliminado):" + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al eliminar actividad");
            e.printStackTrace();
        }
    }
}
