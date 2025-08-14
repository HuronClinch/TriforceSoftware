/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.participante;

import com.mycompany.triforcesoftware.Atajos;
import com.mycompany.triforcesoftware.controladores.ControladorParticipante;
import com.mycompany.triforcesoftware.frontend.participante.ParticipantePanel;
import com.mycompany.triforcesoftware.modelos.participante.Participante;
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
        String istitucion = PANEL.getCampo3().getText();
        String correo = PANEL.getCampo4().getText();

        Atajos atajos = new Atajos();
        if (atajos.comprobarTexto(nombre, "Nombre completo", 45)
                && (tipoParticipante.equals(TIPO[0]) || tipoParticipante.equals(TIPO[1]) || tipoParticipante.equals(TIPO[2]))
                && atajos.comprobarTexto(istitucion, "Institucion de procedencia", 150)
                && atajos.comprobarTexto(correo, "Correo electronico", 45)) {//Comprobar si los valeres cumplen con los requisitos

            ControladorParticipante controlador = new ControladorParticipante();

            if (VerificarCorreo(controlador.getLista(), correo)) {//Si no existe el correo ingresado
                controlador.nuevoParticipante(nombre, tipoParticipante, istitucion, correo);//Ingresar participante en base 
                JOptionPane.showMessageDialog(null, "Participante creado exitosamente");
            }
        }
    }

    private boolean VerificarCorreo(LinkedList<Participante> lista, String correo) {//Comprobar si ya existe el correo ingresado 
        for (Participante participante : lista) {
            if (participante.getCorreoElectronico().equals(correo)) {//Comprobar si ya esta ingresado el correo
                JOptionPane.showMessageDialog(null, "Correo no valido, ya existe");
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
