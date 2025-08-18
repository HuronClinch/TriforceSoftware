/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.utileria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author huron_clinch
 */
public class Connect {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "practicaone";
    public static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    public static final String USER_NAME = "admindba";
    public static final String PASSWORD = "12345";
    private Connection connection;

    public Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {//comprobar si no hay coneccion encendida
                existeUsuario();//Comprobar si exite el usuario

                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                CrearBaseDatos crearDataBase = new CrearBaseDatos();
                crearDataBase.crearDataBase(connection);
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

    private void existeUsuario() throws SQLException {//Comprobar si exite el usuario
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        Statement preparedStatement = connection.createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(
                "SELECT COUNT(*) FROM mysql.user WHERE user='" + USER_NAME + "' AND host='localhost'");

        resultSet.next();
        int count = resultSet.getInt(1);

        if (count == 0) {//Crear usuario si no existe
            System.out.println("El usuario no existe. Creando usuario...");
            preparedStatement.executeUpdate("CREATE USER '" + USER_NAME + "'@'localhost' IDENTIFIED BY '" + PASSWORD + "'");
            preparedStatement.executeUpdate("GRANT ALL PRIVILEGES ON " + SCHEMA + ".* TO '" + USER_NAME + "'@'localhost'");
            preparedStatement.executeUpdate("FLUSH PRIVILEGES");
        }
    }
}
