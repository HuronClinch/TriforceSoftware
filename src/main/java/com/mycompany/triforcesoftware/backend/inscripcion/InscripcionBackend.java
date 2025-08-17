/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.inscripcion;

import com.mycompany.triforcesoftware.controladores.ControladorEvento;
import com.mycompany.triforcesoftware.controladores.ControladorInscripcion;
import com.mycompany.triforcesoftware.controladores.ControladorParticipante;
import com.mycompany.triforcesoftware.frontend.inscripcion.ParticipanteInscripcion;
import com.mycompany.triforcesoftware.modelos.evento.Evento;
import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import com.mycompany.triforcesoftware.modelos.participante.Participante;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class InscripcionBackend {

    private final ParticipanteInscripcion PANEL;
    //ESTUDIANTE, PROFESIONAL, INVITADO
    private final String[] TIPO = {"ASISTENTE", "CONFERENCISTA", "TALLERISTA", "OTRO"};

    public InscripcionBackend(ParticipanteInscripcion PANEL) {
        this.PANEL = PANEL;
    }

    public void comprobarDatos() throws SQLException {//Comprobar si los datos esta ingresados correctamente
        String correo = PANEL.getCorreo().getItemAt(PANEL.getCorreo().getSelectedIndex());//Obtener el correo de la persona seleccionado
        String codigoEvento = PANEL.getCodigoEvento().getItemAt(PANEL.getCodigoEvento().getSelectedIndex());//Obtener el codigo del evento seleccionado
        String tipoEvento = PANEL.getTipoEvnto().getItemAt(PANEL.getTipoEvnto().getSelectedIndex());//Obtener el tipo de evnto
        if (correo == null) {//Comprbar si hay participantes creados
            JOptionPane.showMessageDialog(null, "No hay participantes");
        } else if (codigoEvento == null) {//Comprbar si hay eventos ingresados
            JOptionPane.showMessageDialog(null, "No hay Eventos");
        }

        ControladorInscripcion controlador = new ControladorInscripcion();

        if (vericfiarInscripcion(controlador.getLista(), correo, codigoEvento)) {//Comprobar si no existe un incripcion
            Inscripcion inscripcion = new Inscripcion(correo, codigoEvento, tipoEvento);//Crear evento
            controlador.nuevoEvento(inscripcion);//Ingresar inscripcion en base 
            JOptionPane.showMessageDialog(null, "Incripcion agregada exitosamente");
        }
    }

    private boolean vericfiarInscripcion(LinkedList<Inscripcion> lista, String correo, String codigoEvento) {//Comprobar si ya existe el correo ingresado 
        for (Inscripcion inscripcion : lista) {
            if (inscripcion.getCorreoElectronico().equals(correo) && inscripcion.getCodigoEvento().equals(codigoEvento)) {//Comprobar si ya esta ingresado el correo
                JOptionPane.showMessageDialog(null, "Ya existe una incripcion con este participante");
                return false;
            }
        }
        return true;
    }

    public void datos() throws SQLException {//Llenar co comboBox con las valores definidos
        agregarParticipantes();
        agregarCodigoEvento();
        formatoComboBox(PANEL.getTipoEvnto());

        for (int i = 0; i < TIPO.length; i++) {
            PANEL.getTipoEvnto().addItem(TIPO[i]);
        }
    }

    private void agregarParticipantes() {//Comprobar si ya existe el correo ingresado 
        ControladorParticipante controlador = new ControladorParticipante();
        LinkedList<Participante> lista = controlador.getLista();
        formatoComboBox(PANEL.getCorreo());

        for (Participante participante : lista) {
            PANEL.getCorreo().addItem(participante.getCorreoElectronico());
        }
    }

    private void agregarCodigoEvento() throws SQLException {//Comprobar si ya existe el correo ingresado 
        ControladorEvento controlador = new ControladorEvento();
        LinkedList<Evento> lista = controlador.getLista();
        formatoComboBox(PANEL.getCodigoEvento());

        for (Evento evento : lista) {
            PANEL.getCodigoEvento().addItem(evento.getCodigoEvento());
        }
    }

    private void formatoComboBox(JComboBox<String> comboBox) {
        comboBox.removeAll();
        comboBox.repaint();
    }
}
