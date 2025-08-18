/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.modelos.asistencia.pagos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class PagoCRUD {

    private static final String CREAR = "INSERT INTO pago "
            + "(correo_electronico, codigo_evento, metodo_pago, monto) "
            + "VALUES (?, ?, ?, ?)";
    private static final String LEER_TABLA = "SELECT * FROM pago";

    public int crear(Connection connection, Pago pago) throws SQLException {//Crear un pago para evento
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            preparedStatement = connection.prepareStatement(CREAR);//Ingresar comando SQL
            preparedStatement.setString(1, pago.getCorreoElectronico());
            preparedStatement.setString(2, pago.getCodigoEvento());
            preparedStatement.setString(3, pago.getMetodoPago());
            preparedStatement.setDouble(4, pago.getMonto());//Formato para ingresar pago

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Hubo un error al guardar pago");
            e.printStackTrace(System.out);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return rowsAffected;
    }

    public LinkedList<Pago> leerTodos(Connection connection) throws SQLException {//Obtener todos los registros de pagos
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Pago> lista = new LinkedList<>();//Crear una lista nula
        
        try {
            preparedStatement = connection.prepareStatement(LEER_TABLA);//Ingresar comando SQL
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {//Obtener datos de los eventos y guardalos en LinkedList
                Pago pago = new Pago(
                        resultSet.getInt("no_pago"),
                        resultSet.getString("correo_electronico"),
                        resultSet.getString("codigo_evento"),
                        resultSet.getString("metodo_pago"),
                        resultSet.getDouble("monto")
                );

                lista.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer pagos");
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
