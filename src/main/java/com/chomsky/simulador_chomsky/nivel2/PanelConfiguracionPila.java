package com.chomsky.simulador_chomsky.nivel2;

import java.awt.*;
import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelConfiguracionPila extends JPanel {

    private JTextField txtCadena;

    private JButton btnIniciar;
    private JButton btnSiguiente;
    private JButton btnEjecutar;
    private JButton btnReiniciar;

    public PanelConfiguracionPila() {

        setLayout(new BorderLayout(15, 10));
        setBackground(EstilosUI.TARJETA);

        setBorder(BorderFactory.createEmptyBorder(15, 18, 15, 18));

        // Panel de entrada.
        JPanel panelEntrada = new JPanel(new BorderLayout(8, 5));
        panelEntrada.setBackground(EstilosUI.TARJETA);

        JLabel lblEntrada = new JLabel("Cadena de entrada");
        lblEntrada.setFont(EstilosUI.BOTON);
        lblEntrada.setForeground(EstilosUI.TEXTO);

        txtCadena = new JTextField();
        txtCadena.setFont(new Font("Consolas", Font.BOLD, 16));
        txtCadena.setBackground(EstilosUI.FONDO);
        txtCadena.setForeground(EstilosUI.TEXTO);
        txtCadena.setCaretColor(EstilosUI.TEXTO);
        txtCadena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(55, 65, 90), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        panelEntrada.add(lblEntrada, BorderLayout.NORTH);
        panelEntrada.add(txtCadena, BorderLayout.CENTER);

        // Panel de botones.
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 8, 8));
        panelBotones.setBackground(EstilosUI.TARJETA);

        btnIniciar = EstilosUI.crearBoton(
                "Iniciar", EstilosUI.PRIMARIO);

        btnSiguiente = EstilosUI.crearBoton(
                "Siguiente símbolo", new Color(59, 130, 246));

        btnEjecutar = EstilosUI.crearBoton(
                "Ejecutar todo", new Color(16, 185, 129));

        btnReiniciar = EstilosUI.crearBoton(
                "Reiniciar", EstilosUI.SECUNDARIO);

        panelBotones.add(btnIniciar);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnEjecutar);
        panelBotones.add(btnReiniciar);

        add(panelEntrada, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        habilitarSimulacion(false);
    }

    public String getCadena() {
        return txtCadena.getText().trim();
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JButton getBtnEjecutar() {
        return btnEjecutar;
    }

    public JButton getBtnReiniciar() {
        return btnReiniciar;
    }

    // Habilita o deshabilita los controles según el estado.
    public void habilitarSimulacion(boolean habilitar) {

        txtCadena.setEnabled(!habilitar);

        btnIniciar.setEnabled(!habilitar);
        btnSiguiente.setEnabled(habilitar);
        btnEjecutar.setEnabled(habilitar);
    }

    public void reiniciarEntrada() {
        txtCadena.setText("");
    }
}