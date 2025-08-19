/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.registroactividad;

import com.mycompany.triforcesoftware.Atajos;
import com.mycompany.triforcesoftware.controladores.ControladorActividad;
import com.mycompany.triforcesoftware.controladores.ControladorEvento;
import com.mycompany.triforcesoftware.controladores.ControladorInscripcion;
import com.mycompany.triforcesoftware.frontend.registroActividad.RegistroActividadPanel;
import com.mycompany.triforcesoftware.modelos.actividad.Actividad;
import com.mycompany.triforcesoftware.modelos.evento.Evento;
import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author huron_clinch
 */
public class RegistroActividadBackend {

    private final RegistroActividadPanel PANEL;
    private final String[] TIPO = {"CHARLA", "TALLER", "DEBATE", "OTRA"};

    private ControladorInscripcion controladorInscripcion;//Listado de inscripciones
    private LinkedList<Inscripcion> listaInscripcion;

    private ControladorEvento controladorEvento;//Listado de eventos
    private LinkedList<Evento> listaEvento;

    public RegistroActividadBackend(RegistroActividadPanel PANEL) {
        this.PANEL = PANEL;
        try {
            controladorInscripcion = new ControladorInscripcion();
            listaInscripcion = controladorInscripcion.getLista();
            controladorEvento = new ControladorEvento();
            listaEvento = controladorEvento.getLista();
        } catch (SQLException e) {
            System.out.println("Erroe en subir participantes y eventos");
            e.printStackTrace();
        }
    }

    public RegistroActividadBackend() {
        this.PANEL = null;
        try {
            controladorInscripcion = new ControladorInscripcion();
            listaInscripcion = controladorInscripcion.getLista();
            controladorEvento = new ControladorEvento();
            listaEvento = controladorEvento.getLista();
        } catch (SQLException e) {
            System.out.println("Erroe en subir participantes y eventos");
            e.printStackTrace();
        }
    }

    public void comprobarDatos(boolean verMensaje) throws SQLException {//Comprobar si los datos esta ingresados correctamente
        String codigoActividad = "ACT-" + PANEL.getCodigo().getValue();//Codigo actividad
        String codigoEvento = PANEL.getCodigoEvento().getItemAt(PANEL.getCodigoEvento().getSelectedIndex());//Obtener el codigo del evento seleccionado
        String tipoActividad = PANEL.getTipoActividad().getItemAt(PANEL.getTipoActividad().getSelectedIndex());//Obtener el tipo de evnto
        String tituloActividad = PANEL.getTitulo().getText();//Obtener el titulo del evento
        String correoElectronico = PANEL.getCorreo().getItemAt(PANEL.getCorreo().getSelectedIndex());

        Date horaSeleccionadaInicio = (Date) PANEL.getHoraInicio().getValue();//Pasar la hora para SQL
        Time horaInicio = new java.sql.Time(horaSeleccionadaInicio.getTime());

        Date horaSeleccionadaFin = (Date) PANEL.getHoraFin().getValue();//Pasar la hora para SQL
        Time horaFin = new java.sql.Time(horaSeleccionadaFin.getTime());
        int cupoMaximo = (int) PANEL.getCupo().getValue();//Cupo maximo evento

        Actividad actividad = new Actividad(codigoActividad, codigoEvento, tipoActividad, tituloActividad, correoElectronico, horaInicio, horaFin, cupoMaximo);//Crear Actividad

        datosValidos(actividad, verMensaje);
//        if (datosValidos(actividad) && verMensaje) {//Comprobar que todos los datos sean correctos 
//            JOptionPane.showMessageDialog(null, "Evento creado exitosamente");
//        }
    }

    public boolean datosValidos(Actividad inscripcion, boolean verMensaje) throws SQLException {
        Atajos atajos = new Atajos();
        if ((inscripcion.getTipoActividad().equals(TIPO[0])
                || inscripcion.getTipoActividad().equals(TIPO[1])
                || inscripcion.getTipoActividad().equals(TIPO[2])
                || inscripcion.getTipoActividad().equals(TIPO[3]))
                && atajos.comprobarTexto(inscripcion.getTituloActividad(), "Titulo del evento", 200)
                && verificarInscripcion(inscripcion.getCorreoElectronicoPonente(), inscripcion.getCodigoEvento())) {//Comprobar que la incripcion al evento exista

            ControladorActividad controlador = new ControladorActividad();
            if (verificarCodigo(controlador.getLista(), inscripcion.getCodigoEvento())) {
                controlador.nuevaActividad(inscripcion);
                if (verMensaje) {
                    JOptionPane.showMessageDialog(null, "Evento creado exitosamente");
                }
                return true;
            }
        }
        return false;
    }

    private boolean verificarInscripcion(String correoElectronico, String codigoEvento) {
        for (Inscripcion inscripcion : listaInscripcion) {
            if (inscripcion.getCorreoElectronico().equals(correoElectronico)
                    && inscripcion.getCodigoEvento().equals(codigoEvento)
                    && !inscripcion.getTipoInscripcion().equals("ASISTENTE")) {//Si existe el correo
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "No existe inscripcion a evento con correo electronico");
        return false;
    }

    private boolean verificarCodigo(LinkedList<Actividad> lista, String codigoAct) {//Comprobar si ya existe el correo ingresado 
        for (Actividad actividad : lista) {
            if (actividad.getCodigoActividad().equals(codigoAct)) {//Comprobar si ya esta ingresado el codigo para la actividad
                JOptionPane.showMessageDialog(null, "Codigo no valido de actividad, ya existe");
                return false;
            }
        }
        return true;
    }

    public void formatoHoraSpinner(JSpinner spinnerForma) {//Dar forma para ingresar hora
        JSpinner spinnerHora = spinnerForma;//DefinirSpinner

        SpinnerDateModel modeloHora = new SpinnerDateModel();//Defini modelo
        spinnerHora.setModel(modeloHora);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHora, "hh:mm");//Definir texto spinner
        spinnerHora.setEditor(editor);
    }

    public void datos() {//Llenar co comboBox con las valores definidos
        try {
            agregarCodigoEvento();
            agregarCorreosConferenciastas();
            formatoComboBox(PANEL.getTipoActividad());

            for (int i = 0; i < TIPO.length; i++) {
                PANEL.getTipoActividad().addItem(TIPO[i]);
            }
        } catch (SQLException e) {
            System.out.println("Error subir participantes");
            e.printStackTrace();
        }
    }

    private void agregarCorreosConferenciastas() throws SQLException {//agregar Eventos
        formatoComboBox(PANEL.getCorreo());

        for (Inscripcion inscripcion : listaInscripcion) {
            if (!inscripcion.getTipoInscripcion().equals("ASISTENTE")) {//Si es diferente a asistente
                PANEL.getCorreo().addItem(inscripcion.getCorreoElectronico());
            }
        }
    }

    private void agregarCodigoEvento() throws SQLException {//agregar Eventos
        formatoComboBox(PANEL.getCodigoEvento());

        for (Evento evento : listaEvento) {
            PANEL.getCodigoEvento().addItem(evento.getCodigoEvento());
        }
    }

    private void formatoComboBox(JComboBox<String> comboBox) {
        comboBox.removeAll();
        comboBox.repaint();
    }

}
