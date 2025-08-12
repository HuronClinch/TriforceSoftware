/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia.pagos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class PagoCRUD {

    private static final String CREAR = "INSERT INTO pago "
            + "(correo_electronico, codigo_evento, metodo_pago, monto) "
            + "VALUES ('%s', '%s', '%s', %.2f)";
    private static final String LEER_TABLA = "SELECT * FROM pago";
    private static final String LEER = "SELECT * FROM pago WHERE no_pago = %d";
    private static final String ACTUALIZAR = "UPDATE pago SET "
            + "correo_electronico='%s', codigo_evento='%s', metodo_pago='%s', monto=%.2f "
            + "WHERE no_pago=%d";
    private static final String ELIMINAR = "DELETE FROM pago WHERE no_pago = %d";

    public void crear(Connection connection, Pago pago) {//Crear un pago para evento
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(CREAR,
                    pago.getCorreoElectronico(),
                    pago.getCodigoEvento(),
                    pago.getMetodoPago(),
                    pago.getMonto());//Formato para ingresar pago

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("SQL ejecutado (Pago creado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar pago");
            e.printStackTrace();
        }
    }

    public Pago leer(Connection connection, int noPago) {//Leer un evento
        Pago pago = null;//Crear evento vacio
        try {
            Statement stmt = connection.createStatement();
            String sql = String.format(LEER, noPago);//Formato para buscar pago
            ResultSet datos = stmt.executeQuery(sql);//Ingresar comado SQL

            if (datos.next()) {//Obtener datos del pago
                pago = new Pago(
                        datos.getInt("no_pago"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_evento"),
                        datos.getString("metodo_pago"),
                        datos.getDouble("monto"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer pago");
            e.printStackTrace();
        }
        return pago;
    }

    public LinkedList<Pago> leerTodos(Connection connection) {//Obtener todos los registros de pagos
        LinkedList<Pago> lista = new LinkedList<>();
        try {
            Statement insertStatement = connection.createStatement();
            ResultSet datos = insertStatement.executeQuery(LEER_TABLA);//Ingresar comando SQL

            while (datos.next()) {//Obtener datos de los eventos y guardarlos en linkedList
                Pago pago = new Pago(
                        datos.getInt("no_pago"),
                        datos.getString("correo_electronico"),
                        datos.getString("codigo_evento"),
                        datos.getString("metodo_pago"),
                        datos.getDouble("monto"));

                lista.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos");
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Connection connection, Pago pago) {//Actualizar un pago
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ACTUALIZAR,
                    pago.getCorreoElectronico(),
                    pago.getCodigoEvento(),
                    pago.getMetodoPago(),
                    pago.getMonto(),
                    pago.getNoPago());//Formato para actualizar pago

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("SQL ejecutado (Pago actualizado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al actualizar pago");
            e.printStackTrace();
        }
    }

    public void eliminar(Connection connection, int noPago) {//Eliminar registro de pago
        try {
            Statement insertStatement = connection.createStatement();

            String sql = String.format(ELIMINAR, noPago);//Formato para elimar pago

            insertStatement.executeUpdate(sql);//Ingresar comando SQL
            System.out.println("SQL ejecutado (Pago eliminado): " + sql);
        } catch (SQLException e) {
            System.out.println("Hubo un error al eliminar pago");
            e.printStackTrace();
        }
    }
}
