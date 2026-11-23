
package com.chomsky.simulador_chomsky.nivel1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelControles extends JPanel {

    private JTextField campoEntrada;

    private JButton botonIniciar;
    private JButton botonSiguiente;
    private JButton botonAutomatico;
    private JButton botonReiniciar;

    private JLabel etiquetaEstado;
    private JLabel etiquetaMensaje;

    public PanelControles() {

        setLayout(new BorderLayout(10, 12));

        setBackground(EstilosUI.TARJETA);

        setBorder(
                BorderFactory.createEmptyBorder(15, 18, 15, 18)
        );

        construirInterfaz();
    }

    private void construirInterfaz() {

        JPanel panelEntrada = new JPanel(new BorderLayout(10, 5));

        panelEntrada.setBackground(EstilosUI.TARJETA);

        JLabel tituloEntrada = new JLabel("Cadena de entrada");

        tituloEntrada.setFont(EstilosUI.TEXTO_NORMAL);

        tituloEntrada.setForeground(EstilosUI.TEXTO);

        campoEntrada = new JTextField();

        campoEntrada.setFont(EstilosUI.TEXTO_NORMAL);

        campoEntrada.setBackground(EstilosUI.FONDO);

        campoEntrada.setForeground(EstilosUI.TEXTO);

        campoEntrada.setCaretColor(EstilosUI.TEXTO);

        campoEntrada.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new java.awt.Color(55, 65, 85)
                        ),
                        BorderFactory.createEmptyBorder(9, 10, 9, 10)
                )
        );

        campoEntrada.setMargin(new Insets(5, 5, 5, 5));

        panelEntrada.add(tituloEntrada, BorderLayout.NORTH);

        panelEntrada.add(campoEntrada, BorderLayout.CENTER);

        // Botones.

        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 5)
        );

        panelBotones.setBackground(EstilosUI.TARJETA);

        botonIniciar = EstilosUI.crearBoton(
                "Iniciar simulación",
                EstilosUI.PRIMARIO
        );

        botonSiguiente = EstilosUI.crearBoton(
                "Siguiente transición",
                new java.awt.Color(55, 65, 85)
        );

        botonAutomatico = EstilosUI.crearBoton(
                "Ejecutar automático",
                EstilosUI.SECUNDARIO
        );

        botonReiniciar = EstilosUI.crearBoton(
                "Reiniciar",
                new java.awt.Color(55, 65, 85)
        );

        botonSiguiente.setEnabled(false);

        botonAutomatico.setEnabled(false);

        botonReiniciar.setEnabled(false);

        panelBotones.add(botonIniciar);
        panelBotones.add(botonSiguiente);
        panelBotones.add(botonAutomatico);
        panelBotones.add(botonReiniciar);

        // Información del estado.

        JPanel panelInformacion = new JPanel(
                new GridLayout(2, 1, 3, 3)
        );

        panelInformacion.setBackground(EstilosUI.TARJETA);

        etiquetaEstado = new JLabel("Estado: Sin iniciar");

        etiquetaEstado.setFont(EstilosUI.TEXTO_NORMAL);

        etiquetaEstado.setForeground(EstilosUI.TEXTO);

        etiquetaMensaje = new JLabel(
                "Ingresa una cadena para comenzar."
        );

        etiquetaMensaje.setFont(EstilosUI.TEXTO_PEQUENO);

        etiquetaMensaje.setForeground(EstilosUI.TEXTO_SECUNDARIO);

        panelInformacion.add(etiquetaEstado);

        panelInformacion.add(etiquetaMensaje);

        JPanel panelCentral = new JPanel(new BorderLayout(5, 8));

        panelCentral.setBackground(EstilosUI.TARJETA);

        panelCentral.add(panelEntrada, BorderLayout.NORTH);

        panelCentral.add(panelBotones, BorderLayout.CENTER);

        panelCentral.add(panelInformacion, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);
    }

    public void actualizarEstado(String estado, String mensaje) {

        etiquetaEstado.setText("Estado: " + estado);

        etiquetaMensaje.setText(mensaje);
    }

    public void actualizarBotones(boolean puedeEjecutar) {

        botonSiguiente.setEnabled(puedeEjecutar);

        botonAutomatico.setEnabled(puedeEjecutar);

        botonReiniciar.setEnabled(true);
    }

    public void cambiarTextoAutomatico(boolean ejecutando) {

        botonAutomatico.setText(
                ejecutando
                        ? "Detener automático"
                        : "Ejecutar automático"
        );
    }

    public void reiniciarControles() {

        botonSiguiente.setEnabled(false);

        botonAutomatico.setEnabled(false);

        botonReiniciar.setEnabled(false);

        botonAutomatico.setText("Ejecutar automático");

        etiquetaEstado.setText("Estado: Sin iniciar");

        etiquetaMensaje.setText(
                "Ingresa una cadena para comenzar."
        );
    }

    public String getEntrada() {
        return campoEntrada.getText().trim();
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonSiguiente() {
        return botonSiguiente;
    }

    public JButton getBotonAutomatico() {
        return botonAutomatico;
    }

    public JButton getBotonReiniciar() {
        return botonReiniciar;
    }

    public JTextField getCampoEntrada() {
        return campoEntrada;
    }
}
