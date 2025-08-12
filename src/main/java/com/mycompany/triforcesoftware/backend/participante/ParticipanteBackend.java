/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.participante;

import com.mycompany.triforcesoftware.Atajos;
import com.mycompany.triforcesoftware.frontend.participante.ParticipantePanel;

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
        String dato1 = PANEL.getCampo1().getText();
        String dato2 = PANEL.getCampo2().getItemAt(PANEL.getCampo2().getSelectedIndex());
        String dato3 = PANEL.getCampo3().getText();
        String dato4 = PANEL.getCampo4().getText();

        Atajos atajos = new Atajos();
        if (atajos.comprobarTexto(dato1, "Nombre completo", 45)
                && (dato2.equals(TIPO[0]) || dato2.equals(TIPO[1]) || dato2.equals(TIPO[2]))
                && atajos.comprobarTexto(dato3, "Institucion de procedencia", 150)
                && atajos.comprobarTexto(dato4, "Correo electronico", 45)) {//Comprobar si los valeres cumplen con los requisitos
            System.out.println("Correcto");
            /*
            guardar informacion participante
            hacer validacion de que no exita otro con el mismo nombre
             */
        } else {
            System.out.println("dato incorrecto");
        }
    }
}
