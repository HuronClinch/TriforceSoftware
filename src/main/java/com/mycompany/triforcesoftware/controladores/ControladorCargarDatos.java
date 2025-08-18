/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.controladores;

import com.mycompany.triforcesoftware.backend.evento.EventoBackend;
import com.mycompany.triforcesoftware.backend.inscripcion.InscripcionBackend;
import com.mycompany.triforcesoftware.backend.pago.PagoBackend;
import com.mycompany.triforcesoftware.backend.participante.ParticipanteBackend;
import com.mycompany.triforcesoftware.modelos.asistencia.pagos.Pago;
import com.mycompany.triforcesoftware.modelos.evento.Evento;
import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import com.mycompany.triforcesoftware.modelos.participante.Participante;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author huron_clinch
 */
public class ControladorCargarDatos {

    public void procesarLinea(String linea) {
        try {
            int indiceParentesis = linea.indexOf("(");
            String nombreRegistro = linea.substring(0, indiceParentesis).trim();//Extraer el nombre antes del parentesis
            String[] parametros = extraerParametros(linea);

            switch (nombreRegistro) {
                case "REGISTRO_EVENTO" ->
                    registroEvento(parametros);
                case "REGISTRO_PARTICIPANTE" ->
                    registroParticipante(parametros);
                case "INSCRIPCION" ->
                    registroInscripcion(parametros);
                case "PAGO" ->
                    registroPago(parametros);
//                case "VALIDAR_INSCRIPCION" ->
//                    validarInscripcion(parametros);
//                case "REGISTRO_ACTIVIDAD" ->
//                    registrarActividad(parametros);
//                case "ASISTENCIA" ->
//                    registrarAsistencia(parametros);
//                case "CERTIFICADO" ->
//                    registrarCertificado(parametros);
//                case "REPORTE_PARTICIPANTES" ->
//                    reporteParticipantes(parametros);
//                case "REPORTE_ACTIVIDADES" ->
//                    reporteActividades(parametros);
//                case "REPORTE_EVENTOS" ->
//                    reporteEventos(parametros);
                default ->
                    System.out.println("Comando desconocido: " + nombreRegistro);
            }
        } catch (Exception e) {
            System.out.println("Error procesando línea: " + linea);
            e.printStackTrace();
        }
    }

    private String[] extraerParametros(String linea) {
        int inicio = linea.indexOf("(") + 1; // Comienza después del '('
        int fin = linea.lastIndexOf(")");    // Termina antes del ')'
        String contenido = linea.substring(inicio, fin).trim(); // Quitar espacios

        LinkedList<String> lista = new LinkedList<>();

        // Separar por coma
        String[] partes = contenido.split(",");

        for (String parte : partes) {
            parte = parte.trim();

            // Quitar comillas si existen
            if (parte.startsWith("\"") && parte.endsWith("\"")) {
                parte = parte.substring(1, parte.length() - 1);
            }

            lista.add(parte);
        }

        // Convertir LinkedList a String[]
        String[] resultado = new String[lista.size()];
        int i = 0;
        for (String s : lista) {
            resultado[i++] = s;
        }

        return resultado;
    }

    private void registroEvento(String[] parametros) throws SQLException {//Registro evento nuevo
        if (parametros.length != 7) {
            System.out.println("Error: parámetros incorrectos para REGISTRO_EVENTO");
            return;
        }
        try {
            String codigoEvento = parametros[0];
            String fecha = parametros[1];
            String tipo = parametros[2];
            String tituloEvento = parametros[3];
            String lugar = parametros[4];
            int capacidad = Integer.parseInt(parametros[5]);
            double costo = Double.parseDouble(parametros[6]);

            Evento evento = new Evento(codigoEvento, fecha, tipo, tituloEvento, lugar, capacidad, costo);//Crear evento

            EventoBackend ingresarEvento = new EventoBackend();
            ingresarEvento.datosValidos(evento);//Validar si los datos estan correctos y guardar si es asi

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número/costo: ");
            e.printStackTrace();
        }
    }

    private void registroParticipante(String[] parametros) throws SQLException {//Registro participante nuevo
        if (parametros.length != 4) {
            System.out.println("Error: parámetros incorrectos para REGISTRO_EVENTO");
            return;
        }
        try {
            String nombre = parametros[0];
            String tipoParticipante = parametros[1];
            String institucion = parametros[2];
            String correo = parametros[3];

            Participante participante = new Participante(nombre, tipoParticipante, institucion, correo);//Crear participante 

            ParticipanteBackend ingresarParticipante = new ParticipanteBackend();
            ingresarParticipante.datosValidos(participante);//Validar si los datos estan correctos y guardar si es asi

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número/costo: ");
            e.printStackTrace();
        }
    }

    private void registroInscripcion(String[] parametros) throws SQLException {//Registro inscripcion nueva
        if (parametros.length != 3) {
            System.out.println("Error: parámetros incorrectos para REGISTRO_EVENTO");
            return;
        }
        try {
            String correo = parametros[0];
            String codigoEvento = parametros[1];
            String tipoInscripcion = parametros[2];

            Inscripcion inscripcion = new Inscripcion(correo, codigoEvento, tipoInscripcion);//Crear inscripcion

            InscripcionBackend ingresarInscripcion = new InscripcionBackend();
            ingresarInscripcion.datosValidos(inscripcion);//Validar si los datos estan correctos y guardar si es asi

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número/costo: ");
            e.printStackTrace();
        }
    }

    private void registroPago(String[] parametros) throws SQLException {//Registro pago de inscripcion evento
        if (parametros.length != 4) {
            System.out.println("Error: parámetros incorrectos para REGISTRO_EVENTO");
            return;
        }
        try {
            String correoElectronico = parametros[0];
            String codigoEvento = parametros[1];
            String metodoPago = parametros[2];
            double monto = Double.parseDouble(parametros[3]);

            Pago pago = new Pago(0, correoElectronico, codigoEvento, metodoPago, monto);//Crear inscripcion

            PagoBackend ingresarPago = new PagoBackend();
            ingresarPago.datosValidos(pago);//Validar si los datos estan correctos para uns icnripcion hecha

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número/costo: ");
            e.printStackTrace();
        }
    }

}
