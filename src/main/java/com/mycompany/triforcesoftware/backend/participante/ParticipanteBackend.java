/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.participante;

import com.mycompany.triforcesoftware.Atajos;
import com.mycompany.triforcesoftware.controladores.ControladorParticipante;
import com.mycompany.triforcesoftware.frontend.participante.ParticipantePanel;
import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import com.mycompany.triforcesoftware.modelos.participante.Participante;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class ParticipanteBackend {

    final ParticipantePanel PANEL;
    //ESTUDIANTE, PROFESIONAL, INVITADO
    private final String[] TIPO = {"ESTUDIANTE", "PROFESIONAL", "INVITADO"};

    public ParticipanteBackend(ParticipantePanel PANEL) {
        this.PANEL = PANEL;
    }

    public ParticipanteBackend() {
        this.PANEL = null;
    }

    public void datos() {//Llenar co comboBox con las valores definidos
        PANEL.getCampo2().removeAll();
        PANEL.getCampo2().repaint();

        for (int i = 0; i < TIPO.length; i++) {
            PANEL.getCampo2().addItem(TIPO[i]);
        }
    }

    public void comprobarDatos() {//Comprobar si los datos esta ingresados correctamente
        String nombre = PANEL.getCampo1().getText();
        String tipoParticipante = PANEL.getCampo2().getItemAt(PANEL.getCampo2().getSelectedIndex());
        String institucion = PANEL.getCampo3().getText();
        String correo = PANEL.getCampo4().getText();

        Participante participante = new Participante(nombre, tipoParticipante, institucion, correo);//Crear Personaje
        if (datosValidos(participante)) {
            LimpiarDatos();
        }

    }

    public boolean datosValidos(Participante participante) {
        try {
            Atajos atajos = new Atajos();
            if (atajos.comprobarTexto(participante.getNombreCompleto(), "Nombre completo", 45)
                    && (participante.getTipoParticipante().equals(TIPO[0]) || participante.getTipoParticipante().equals(TIPO[1]) || participante.getTipoParticipante().equals(TIPO[2]))
                    && atajos.comprobarTexto(participante.getInstitucionProcedencia(), "Institucion de procedencia", 150)
                    && atajos.comprobarTexto(participante.getCorreoElectronico(), "Correo electronico", 45)) {//Comprobar si los valeres cumplen con los requisitos

                ControladorParticipante controlador = new ControladorParticipante();

                if (verificarCorreo(controlador.getLista(), participante.getCorreoElectronico())) {//Si no existe el correo ingresado
                    controlador.nuevoParticipante(participante);//Ingresar participante en base
                    JOptionPane.showMessageDialog(null, "Participante creado exitosamente");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al Validar datos participante");
            e.printStackTrace(System.out);
        }
        return false;
    }

    private boolean verificarCorreo(LinkedList<Participante> lista, String correo) {//Comprobar si ya existe el correo ingresado 
        for (Participante participante : lista) {
            if (participante.getCorreoElectronico().equals(correo)) {//Comprobar si ya esta ingresado el correo
                JOptionPane.showMessageDialog(null, "Correo no valido para registro de participante, ya existe");
                return false;
            }
        }
        return true;
    }

    private void LimpiarDatos() {//Limpiar campos de texto
        PANEL.getCampo1().setText("");
        PANEL.getCampo3().setText("");
        PANEL.getCampo4().setText("");
    }
}
