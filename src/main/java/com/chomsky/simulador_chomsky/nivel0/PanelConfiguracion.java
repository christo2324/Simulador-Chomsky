
package com.chomsky.simulador_chomsky.nivel0;

import java.awt.*;
import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelConfiguracion extends JPanel {

    private JComboBox<String> cmbModalidad;
    private JTextField txtEntrada;
    private JLabel lblDescripcion;

    public PanelConfiguracion() {

        setLayout(new GridBagLayout());
        setBackground(EstilosUI.TARJETA);
        setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Configuración de máquina");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel lblModalidad = crearEtiqueta("Modalidad:");
        gbc.gridx = 0;
        add(lblModalidad, gbc);

        cmbModalidad = new JComboBox<>(new String[]{
                "Reconocimiento de cadenas",
                "Suma unaria"
        });

        cmbModalidad.setFont(EstilosUI.TEXTO_NORMAL);
        gbc.gridx = 1;
        add(cmbModalidad, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(crearEtiqueta("Entrada:"), gbc);

        txtEntrada = new JTextField();
        txtEntrada.setFont(EstilosUI.TEXTO_NORMAL);
        txtEntrada.setBackground(EstilosUI.FONDO);
        txtEntrada.setForeground(EstilosUI.TEXTO);
        txtEntrada.setCaretColor(EstilosUI.TEXTO);

        gbc.gridx = 1;
        add(txtEntrada, gbc);

        lblDescripcion = new JLabel("Lenguaje: 0ⁿ1ⁿ, con n ≥ 1");
        lblDescripcion.setForeground(EstilosUI.TEXTO_SECUNDARIO);
        lblDescripcion.setFont(EstilosUI.TEXTO_PEQUENO);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(lblDescripcion, gbc);

        cmbModalidad.addActionListener(e -> actualizarDescripcion());
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(EstilosUI.TEXTO_SECUNDARIO);
        etiqueta.setFont(EstilosUI.TEXTO_NORMAL);
        return etiqueta;
    }

    private void actualizarDescripcion() {

        if (cmbModalidad.getSelectedIndex() == 0) {
            lblDescripcion.setText("Lenguaje: 0ⁿ1ⁿ, con n ≥ 1");
            txtEntrada.setToolTipText("Ejemplo: 0011");
        } else {
            lblDescripcion.setText("Suma de números naturales en unario. Ejemplo: 111+11");
            txtEntrada.setToolTipText("Ejemplo: 111+11");
        }
    }

    public String getEntrada() {
        return txtEntrada.getText().trim();
    }

    public int getModalidad() {
        return cmbModalidad.getSelectedIndex();
    }

    public void setEntrada(String entrada) {
        txtEntrada.setText(entrada);
    }
}
