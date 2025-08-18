/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.utileria;

import com.mycompany.triforcesoftware.controladores.ControladorCargarDatos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class ArchivoTexto {

    private final String PATH_COMPLETO;

    public ArchivoTexto(String PATH_COMPLETO) {
        this.PATH_COMPLETO = PATH_COMPLETO;
    }

    public void leerTextoConScanner() {
        System.out.println("PATH_COMPLETO->" + PATH_COMPLETO);
        try (InputStream inputStream = Files.newInputStream(Path.of(PATH_COMPLETO))) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) { // mejor que while(true)
                String linea = scanner.nextLine().trim(); // quitar espacios al inicio y fin
                if (linea.isEmpty()) {
                    continue; // saltar línea en blanco
                }
                ControladorCargarDatos cargarDatos = new ControladorCargarDatos();
                cargarDatos.procesarLinea(linea);//Leer linea
                System.out.println(linea);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar archivo");
            e.printStackTrace();
        }
    }
}
