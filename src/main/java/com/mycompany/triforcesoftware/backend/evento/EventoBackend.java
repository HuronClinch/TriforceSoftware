/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.evento;

import com.mycompany.triforcesoftware.Atajos;
import com.mycompany.triforcesoftware.controladores.ControladorEvento;
import com.mycompany.triforcesoftware.frontend.evento.EventoPanel;
import com.mycompany.triforcesoftware.modelos.evento.Evento;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author huron_clinch
 */
public class EventoBackend {

    private final EventoPanel PANEL;
    private final String[] TIPO = {"CHARLA", "CONGRESO", "TALLER", "DEBATE"};

    public EventoBackend(EventoPanel PANEL) {
        this.PANEL = PANEL;
    }

    public void comprobarDatos() throws SQLException {//Comprobar si los datos esta ingresados correctamente
        String codigo = "EVT-" + (int) PANEL.getCodigo().getValue();//Codigo evento
        if (!comprobarCampofecha(PANEL.getFecha().getDate())) {//Comproabr si ingreso fecha
            return;
        }
        SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = formatoDate.format(PANEL.getFecha().getDate());//Fecha valida
        String tipo = PANEL.getTipo().getItemAt(PANEL.getTipo().getSelectedIndex());//Tipo evneto
        String titulo = PANEL.getTitulo().getText();//Titulo evento
        String ubicacion = PANEL.getUbicacion().getText();//Ubicacion evento
        int cupoMaximo = (int) PANEL.getCupoMaximo().getValue();//Cupo maximo evento
        double costo = (double) PANEL.getCosto().getValue();//Costo evento

        Atajos atajos = new Atajos();
        if ((tipo.equals(TIPO[0]) || tipo.equals(TIPO[1]) || tipo.equals(TIPO[2]) || tipo.equals(TIPO[3]))
                && atajos.comprobarTexto(titulo, "Titulo del evento", 45)
                && atajos.comprobarTexto(ubicacion, "Ubicacion", 150)) {//Comprobar si los valeres cumplen con los requisitos

            ControladorEvento controlador = new ControladorEvento();
            if (verificarCodigo(controlador.getLista(), codigo)) {//Si no existe el correo ingresado
                Evento evento = new Evento(codigo, fechaStr, tipo, titulo, ubicacion, cupoMaximo, costo);//Crear evento
                controlador.nuevoEvento(evento);//Ingresar evento en base 
                JOptionPane.showMessageDialog(null, "Participante creado exitosamente");
                limpiarDatos();
            }
        } else {
            System.out.println("Tipo no encontrado");
        }
    }

    private boolean verificarCodigo(LinkedList<Evento> lista, String codigo) {//Comprobar si ya existe el correo ingresado 
        for (Evento evento : lista) {
            if (evento.getCodigoEvento().equals(codigo)) {//Comprobar si ya esta ingresado el correo
                JOptionPane.showMessageDialog(null, "Codigo no valido, ya existe");
                return false;
            }
        }
        return true;
    }

    private boolean comprobarCampofecha(Date fechaComprobar) {
        if (fechaComprobar == null) {
            JOptionPane.showMessageDialog(null, "No ingreso fecha");
            return false;
        }
        return true;
    }

    private void limpiarDatos() {//Limpiar campos
        PANEL.getCodigo().setValue(0);
        PANEL.getTitulo().setText("");
        PANEL.getUbicacion().setText("");
        PANEL.getCupoMaximo().setValue(10);
        PANEL.getCosto().setValue(0);
    }

    public void datos() {//Llenar co comboBox con las valores definidos
        PANEL.getTipo().removeAll();
        PANEL.getTipo().repaint();

        for (int i = 0; i < TIPO.length; i++) {
            PANEL.getTipo().addItem(TIPO[i]);
        }
    }

    public void limiteFechas() {//Mostrar en DateChosser un formato
        PANEL.getFecha().setDateFormatString("dd/MM/yyyy");//Establecer el formato de fecha
        Date fechaLimite = new Date();//Fecha de hoy
        PANEL.getFecha().setMinSelectableDate(fechaLimite);//Establecer fecha mínima como hoy
        ((JTextField) PANEL.getFecha().getDateEditor().getUiComponent()).setEditable(false);//Quitar la opcion de escribir en jDateChooser
    }
}
