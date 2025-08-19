/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.asistencia;

import com.mycompany.triforcesoftware.controladores.ContoladorAsistencia;
import com.mycompany.triforcesoftware.controladores.ControladorActividad;
import com.mycompany.triforcesoftware.controladores.ControladorValidaInscripcion;
import com.mycompany.triforcesoftware.frontend.asistencia.AsistenciaPanel;
import com.mycompany.triforcesoftware.modelos.actividad.Actividad;
import com.mycompany.triforcesoftware.modelos.asistencia.Asistencia;
import com.mycompany.triforcesoftware.modelos.validainscripcion.ValidarInscripcion;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class AsistenciaBackend {

    private final AsistenciaPanel PANEL;

    //Listado de validacion de inscripciones
    private final ControladorValidaInscripcion CONTROLADOR;
    private LinkedList<ValidarInscripcion> lista;
    //Listado de actividades
    private final ControladorActividad CONTROLADOR_EVENTO = new ControladorActividad();
    private LinkedList<Actividad> listaActividad = CONTROLADOR_EVENTO.getLista();

    public AsistenciaBackend(AsistenciaPanel PANEL) {
        this.PANEL = PANEL;
        CONTROLADOR = new ControladorValidaInscripcion();
        lista = CONTROLADOR.getLista();
    }

    public AsistenciaBackend() {
        this.PANEL = null;
        CONTROLADOR = new ControladorValidaInscripcion();
        lista = CONTROLADOR.getLista();
    }

    public void comprobarDatos() {//Comprobar si los datos esta ingresados correctamente
        String correoElectronico = PANEL.getCorreoElectronico().getItemAt(PANEL.getCorreoElectronico().getSelectedIndex());//Correo electronico
        String codigoActividad = PANEL.getCodigoActividades().getItemAt(PANEL.getCodigoActividades().getSelectedIndex());//Codigo de actividad 

        Asistencia asistencia = new Asistencia(correoElectronico, codigoActividad);//Crear registro asistencia
        datosValidos(asistencia);
    }

    public boolean datosValidos(Asistencia asistencia) { //Ver si los campos estan llenados correctamente]
        if (validarCodigoActividad(asistencia)) {

            ContoladorAsistencia controlador = new ContoladorAsistencia();

            if (controlador.verificarAsistencia(asistencia.getCorreoElectronico(), asistencia.getCodigoActividad())) {//Si no existe asistencia creada
                controlador.nuevaAsistencia(asistencia);//Ingresar evento en base
            }
        }
        return false;
    }

    public boolean validarCodigoActividad(Asistencia asistencia) {//llenar campos de codigo de eventos inscritos 
        for (Actividad actividad : listaActividad) {//Validar si existe el codigo de la actividad
            if (actividad.getCodigoActividad().equals(asistencia.getCodigoActividad())) {
                if (validarInscripcion(asistencia, actividad.getCodigoEvento())) {
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No existe actividad");
        return false;
    }

    private boolean validarInscripcion(Asistencia asistencia, String codigoEVN) {//Validar si el codigo de la catividad existe en evento
        for (ValidarInscripcion validarInscripcion : lista) {
            if (validarInscripcion.getCorreoElectronico().equals(asistencia.getCorreoElectronico()) && codigoEVN.equals(validarInscripcion.getCodigoEvento())) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "No existe incripcion");
        return false;
    }

    public void informacionInscripcion() {//Llenar el comoBox con los correos de inscripciones creados
        PANEL.getCorreoElectronico().removeAll();
        PANEL.getCorreoElectronico().repaint();
        for (ValidarInscripcion validacioninscripcion : lista) {
            String correo = validacioninscripcion.getCorreoElectronico();
            boolean existe = false;
            for (int i = 0; i < PANEL.getCorreoElectronico().getItemCount(); i++) {//Comprobar si ya existe el correo
                if (correo.equals(PANEL.getCorreoElectronico().getItemAt(i))) {//Si ya existe no imprimir
                    existe = true;
                    break;
                }
            }
            if (!existe) {//Si no existe imprimir
                PANEL.getCorreoElectronico().addItem(correo);
            }
        }
    }

    public void seleleccionCorreo() {//llenar campos de codigo de eventos inscritos 
        PANEL.getCodigoActividades().removeAllItems();
        PANEL.getCodigoActividades().removeAll();
        PANEL.getCodigoActividades().repaint();
        PANEL.getCodigoActividades().addItem("");

        String correoSeleccionado = PANEL.getCorreoElectronico().getItemAt(PANEL.getCorreoElectronico().getSelectedIndex());//Correo seleccionado 
        for (ValidarInscripcion validarInscripcion : lista) {
            if (validarInscripcion.getCorreoElectronico().equals(correoSeleccionado)) {
                seleleccionActividadPorCorreo(validarInscripcion.getCodigoEvento());
            }
        }
    }

    public void seleleccionActividadPorCorreo(String codigoEVN) {//llenar campos de codigo de eventos inscritos 
        PANEL.getCodigoActividades().removeAllItems();
        PANEL.getCodigoActividades().removeAll();
        PANEL.getCodigoActividades().repaint();

        for (Actividad actividad : listaActividad) {
            if (actividad.getCodigoEvento().equals(codigoEVN)) {
                PANEL.getCodigoActividades().addItem(actividad.getCodigoActividad());//Anadir eventos incritos por correo
            }
        }
    }
}
