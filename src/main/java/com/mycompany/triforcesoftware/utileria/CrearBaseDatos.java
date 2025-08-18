/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.utileria;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class CrearBaseDatos {

    public void crearDataBase(Connection coneccion) {//Si no existe la base de datos
        try {
            Statement preparedStatement = coneccion.createStatement();

            preparedStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS practicaone");//Crar Base de datos si no existe
            preparedStatement.executeUpdate("USE practicaone");//Usar la base de datos

            //Crear tablas
            String crearParticipante = "CREATE TABLE IF NOT EXISTS participante ("
                    + "correo_electronico VARCHAR(100) NOT NULL,"
                    + "nombre_completo VARCHAR(45) NOT NULL,"
                    + "tipo_participante VARCHAR(15) NOT NULL,"
                    + "institucion_procedencia VARCHAR(150) NOT NULL,"
                    + "PRIMARY KEY (correo_electronico)"
                    + ")";
            preparedStatement.executeUpdate(crearParticipante);

            String crearEvento = "CREATE TABLE IF NOT EXISTS evento ("
                    + "codigo_evento VARCHAR(12) NOT NULL,"
                    + "fecha_evento DATE NOT NULL,"
                    + "tipo_evento VARCHAR(10) NOT NULL,"
                    + "titulo_evento VARCHAR(45) NOT NULL,"
                    + "ubicacion VARCHAR(150) NOT NULL,"
                    + "cupo_maximo INT NOT NULL,"
                    + "costo_inscripcion DECIMAL(10,2) NOT NULL,"
                    + "PRIMARY KEY (codigo_evento)"
                    + ")";
            preparedStatement.executeUpdate(crearEvento);

            String crearInscripcion = "CREATE TABLE IF NOT EXISTS inscripcion ("
                    + "correo_electronico VARCHAR(100) NOT NULL,"
                    + "codigo_evento VARCHAR(12) NOT NULL,"
                    + "tipo_inscripcion VARCHAR(15) NOT NULL,"
                    + "PRIMARY KEY (correo_electronico, codigo_evento),"
                    + "FOREIGN KEY (correo_electronico) REFERENCES participante(correo_electronico),"
                    + "FOREIGN KEY (codigo_evento) REFERENCES evento(codigo_evento)"
                    + ")";
            preparedStatement.executeUpdate(crearInscripcion);

            String crearPago = "CREATE TABLE IF NOT EXISTS pago ("
                    + "no_pago INT NOT NULL AUTO_INCREMENT,"
                    + "correo_electronico VARCHAR(100) NOT NULL,"
                    + "codigo_evento VARCHAR(12) NOT NULL,"
                    + "metodo_pago VARCHAR(15) NOT NULL,"
                    + "monto DECIMAL(10,2) NOT NULL,"
                    + "PRIMARY KEY (no_pago),"
                    + "FOREIGN KEY (correo_electronico, codigo_evento) REFERENCES inscripcion(correo_electronico, codigo_evento)"
                    + ")";
            preparedStatement.executeUpdate(crearPago);

            String crearActividad = "CREATE TABLE IF NOT EXISTS actividad ("
                    + "codigo_actividad VARCHAR(10) NOT NULL,"
                    + "codigo_evento VARCHAR(12) NOT NULL,"
                    + "tipo_actividad VARCHAR(10) NOT NULL,"
                    + "titulo_actividad VARCHAR(200) NOT NULL,"
                    + "correo_electronico_ponente VARCHAR(100) NOT NULL,"
                    + "hora_inicio TIME NOT NULL,"
                    + "hora_fin TIME NOT NULL,"
                    + "PRIMARY KEY (codigo_actividad),"
                    + "FOREIGN KEY (codigo_evento) REFERENCES evento(codigo_evento),"
                    + "FOREIGN KEY (correo_electronico_ponente) REFERENCES participante(correo_electronico)"
                    + ")";
            preparedStatement.executeUpdate(crearActividad);

            String crearAsistencia = "CREATE TABLE IF NOT EXISTS asistencia ("
                    + "correo_electronico VARCHAR(100) NOT NULL,"
                    + "codigo_actividad VARCHAR(10) NOT NULL,"
                    + "PRIMARY KEY (correo_electronico, codigo_actividad),"
                    + "FOREIGN KEY (correo_electronico) REFERENCES participante(correo_electronico),"
                    + "FOREIGN KEY (codigo_actividad) REFERENCES actividad(codigo_actividad)"
                    + ")";
            preparedStatement.executeUpdate(crearAsistencia);

            System.out.println("✅Base de datos creado correctamente");

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error con la creacion de la base de datos");
            e.printStackTrace(System.out);
        }
    }
}
