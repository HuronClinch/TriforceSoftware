/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.utileria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author huron_clinch
 */
public class Connect {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "triforce";
    public static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    public static final String USER_NAME = "admindba";
    public static final String PASSWORD = "12345";
    private Connection connection;

    public Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {//comprobar si no hay coneccion encendida
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("Se creo la coneccion correctamente");
                System.out.println("Esquema: " + connection.getSchema());
                System.out.println("Catalogo: " + connection.getCatalog());
            }
            return connection;
        } catch (SQLException e) {//Manejar excepcion
            System.out.println("Error al conectarse");
            e.printStackTrace();
        }
        return null;
    }
}
