package com.chomsky.simulador_chomsky.nivel2;

import java.awt.*;
import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelCadena extends JPanel {

    private JPanel panelSimbolos;

    public PanelCadena() {

        setLayout(new BorderLayout(5, 10));
        setBackground(EstilosUI.TARJETA);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Cadena de entrada");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        panelSimbolos = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        panelSimbolos.setBackground(EstilosUI.TARJETA);

        JScrollPane scroll = new JScrollPane(panelSimbolos);
        scroll.setBorder(null);
        scroll.setBackground(EstilosUI.TARJETA);
        scroll.getViewport().setBackground(EstilosUI.TARJETA);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    // Actualiza los símbolos y resalta el símbolo actual.
    public void actualizar(String cadena, int posicion) {

        panelSimbolos.removeAll();

        if (cadena != null) {

            for (int i = 0; i < cadena.length(); i++) {

                JLabel simbolo = new JLabel(
                        String.valueOf(cadena.charAt(i)),
                        SwingConstants.CENTER
                );

                simbolo.setPreferredSize(new Dimension(38, 38));
                simbolo.setFont(new Font("Consolas", Font.BOLD, 18));

                if (i < posicion) {

                    simbolo.setBackground(new Color(55, 65, 81));

                } else if (i == posicion) {

                    simbolo.setBackground(EstilosUI.PRIMARIO);

                } else {

                    simbolo.setBackground(EstilosUI.FONDO);
                }

                simbolo.setForeground(EstilosUI.TEXTO);
                simbolo.setOpaque(true);

                simbolo.setBorder(BorderFactory.createLineBorder(
                        new Color(65, 75, 100), 1, true));

                panelSimbolos.add(simbolo);
            }
        }

        panelSimbolos.revalidate();
        panelSimbolos.repaint();
    }

    public void limpiar() {
        panelSimbolos.removeAll();
        panelSimbolos.revalidate();
        panelSimbolos.repaint();
    }
}