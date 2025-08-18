/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triforcesoftware.backend.pago;

import com.mycompany.triforcesoftware.controladores.ControladorEvento;
import com.mycompany.triforcesoftware.controladores.ControladorInscripcion;
import com.mycompany.triforcesoftware.controladores.ControladorPago;
import com.mycompany.triforcesoftware.frontend.pago.PagoPanel;
import com.mycompany.triforcesoftware.modelos.asistencia.pagos.Pago;
import com.mycompany.triforcesoftware.modelos.evento.Evento;
import com.mycompany.triforcesoftware.modelos.inscripcion.Inscripcion;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author huron_clinch
 */
public class PagoBackend {

    private final PagoPanel PANEL;
    private final String[] TIPO = {"EFECTIVO", "TRANSFERENCIA", "TARJETA"};
    //Datos para inscripcion
    private final ControladorInscripcion CONTROLADOR;
    private LinkedList<Inscripcion> lista;
    //Datos para eventos
    private final ControladorEvento CONTROLADOR_EVENTO = new ControladorEvento();
    private LinkedList<Evento> listaEventos = CONTROLADOR_EVENTO.getLista();

    public PagoBackend(PagoPanel PANEL) throws SQLException {
        this.PANEL = PANEL;
        CONTROLADOR = new ControladorInscripcion();
    }

    public PagoBackend() throws SQLException {
        this.PANEL = null;
        CONTROLADOR = new ControladorInscripcion();
    }

    public void comprobarDatos() throws SQLException {//Comprobar si los datos esta ingresados correctamente
        String correoElectronico = PANEL.getCorreoElectronico().getItemAt(PANEL.getCorreoElectronico().getSelectedIndex());//Correo electronico
        String codigoEvento = PANEL.getCodigoEvento().getItemAt(PANEL.getCodigoEvento().getSelectedIndex());//Codigo evento 
        String tipo = PANEL.getMetodoPago().getItemAt(PANEL.getMetodoPago().getSelectedIndex());//Get tipo elegido
        double costo = (double) PANEL.getMonto().getValue();//Costo evento 

        Pago pago = new Pago(0, correoElectronico, codigoEvento, tipo, costo);//Crear pago
        datosValidos(pago);
    }

    public boolean datosValidos(Pago pago) {//Ver si los campos estan llenados correctamente]
        lista = CONTROLADOR.getLista();
        try {
            if (validarInscripcion(pago)
                    && (pago.getMetodoPago().equals(TIPO[0]) || pago.getMetodoPago().equals(TIPO[1]) || pago.getMetodoPago().equals(TIPO[2]))) {//Comprobar que los tatos sean correctos

                ControladorPago controlador = new ControladorPago();

                if (controlador.verificarPago(pago.getCorreoElectronico(), pago.getCodigoEvento())) {//Si no existe el correo ingresado

                    controlador.nuevoEvento(pago);//Ingresar evento en base 
                    JOptionPane.showMessageDialog(null, "pago de inscripcion creado exitosamente");
                }
            } else {
                System.out.println("Tipo no encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Error validar datos");
            e.printStackTrace(System.out);
        }
        return false;
    }

    public void datos() throws SQLException {//Llenar el comboBox con las valores definidos
        PANEL.getMetodoPago().removeAll();
        PANEL.getMetodoPago().repaint();

        for (int i = 0; i < TIPO.length; i++) {
            PANEL.getMetodoPago().addItem(TIPO[i]);
        }
        informacionInscripcion();//ver las inscripciones hechas
    }

    private void informacionInscripcion() throws SQLException {//Llenar el comoBox con los correos de inscripciones creados
        lista = CONTROLADOR.getLista();
        PANEL.getCorreoElectronico().removeAll();
        PANEL.getCorreoElectronico().repaint();
        PANEL.getCorreoElectronico().addItem("");
        for (Inscripcion inscripcion : lista) {
            String correo = inscripcion.getCorreoElectronico();
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
        PANEL.getCodigoEvento().removeAllItems();
        PANEL.getCodigoEvento().removeAll();
        PANEL.getCodigoEvento().repaint();

        String correoSeleccionado = PANEL.getCorreoElectronico().getItemAt(PANEL.getCorreoElectronico().getSelectedIndex());//Correo seleccionado 
        for (Inscripcion inscripciones : lista) {
            if (inscripciones.getCorreoElectronico().equals(correoSeleccionado)) {
                PANEL.getCodigoEvento().addItem(inscripciones.getCodigoEvento());//Anadir eventos incritos por correo
            }
        }
    }

    public void seleccionEvento() throws SQLException {//Mostrar monto por evento
        for (Evento evento : listaEventos) {
            if (evento.getCodigoEvento().equals(PANEL.getCodigoEvento().getItemAt(PANEL.getCodigoEvento().getSelectedIndex()))) {
                PANEL.getMonto().setValue(evento.getCostoInscripcion());
                return;//Existe el evento
            }
        }
    }

    private boolean validarInscripcion(Pago pago) {//Validar los datos si son correctos para el pago
        boolean datosCorrectos = false;
        int contadoInscripciones = 0;
        int contadoEventos = 0;

        String correoIncrito = null;
        String eventoIncrito = null;
        double costoEvento = 0;

        for (Inscripcion inscripciones : lista) {//Comprobar si existe el correo en incripciones
            if (inscripciones.getCorreoElectronico().equals(pago.getCorreoElectronico())) {
                correoIncrito = inscripciones.getCorreoElectronico();
                contadoInscripciones++;
            }
        }
        if (contadoInscripciones == 0) {//no hay inscripciones validas
            JOptionPane.showMessageDialog(null, "No existe inscripciones con el correo");
            return false;
        }

        for (Evento evento : listaEventos) {//Econtrar el codigo ingresado
            if (evento.getCodigoEvento().equals(pago.getCodigoEvento())) {
                eventoIncrito = evento.getCodigoEvento();
                costoEvento = evento.getCostoInscripcion();
                contadoEventos++;
            }
        }
        if (contadoEventos == 0) {//No hay eventos con el codigo
            JOptionPane.showMessageDialog(null, "No existe evento");
            return false;
        }

        if (!pago.getCodigoEvento().equals(eventoIncrito)) {//comprobar si existe el evento con el codigo
            JOptionPane.showMessageDialog(null, "El codigo de evento no existefffffffffffffffffffffff");
            return false;
        }
        if (pago.getMonto() != costoEvento) {//Comprobar si el monto es el correcto
            JOptionPane.showMessageDialog(null, "El monto es incorrecto");
            return false;
        }
        return true;
    }
}
