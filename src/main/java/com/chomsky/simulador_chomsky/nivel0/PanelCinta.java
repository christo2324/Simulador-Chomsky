
package com.chomsky.simulador_chomsky.nivel0;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelCinta extends JPanel {

    private JPanel panelCeldas;
    private JScrollPane scroll;

    private JLabel lblPosicion;

    private final int ANCHO_CELDA = 48;

    public PanelCinta() {

        setLayout(new BorderLayout(5, 10));
        setBackground(EstilosUI.TARJETA);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Cinta de trabajo");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        lblPosicion = new JLabel("Posición del cabezal: 0");
        lblPosicion.setFont(EstilosUI.TEXTO_PEQUENO);
        lblPosicion.setForeground(EstilosUI.TEXTO_SECUNDARIO);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setOpaque(false);
        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(lblPosicion, BorderLayout.EAST);

        add(encabezado, BorderLayout.NORTH);

        panelCeldas = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 5));
        panelCeldas.setBackground(EstilosUI.FONDO);

        scroll = new JScrollPane(
                panelCeldas,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setBorder(BorderFactory.createLineBorder(
                new Color(41, 51, 73)
        ));

        scroll.getHorizontalScrollBar().setUnitIncrement(30);

        add(scroll, BorderLayout.CENTER);

        actualizarCinta(null);
    }

    public void actualizarCinta(CintaTuring cinta) {

        panelCeldas.removeAll();

        int posicionCabezal = cinta == null
                ? 0
                : cinta.getPosicionCabezal();

        int minimo = cinta == null ? -5 : cinta.getPosicionMinima() - 5;
        int maximo = cinta == null ? 15 : cinta.getPosicionMaxima();

        minimo = Math.min(minimo, posicionCabezal - 5);
        maximo = Math.max(maximo, posicionCabezal + 10);

        for (int i = minimo; i <= maximo; i++) {

            JPanel celda = new JPanel(new BorderLayout(0, 3));
            celda.setPreferredSize(new Dimension(ANCHO_CELDA, 75));

            boolean activa = i == posicionCabezal;

            celda.setBackground(activa
                    ? EstilosUI.PRIMARIO
                    : EstilosUI.FONDO);

            celda.setBorder(BorderFactory.createLineBorder(
                    activa ? EstilosUI.SECUNDARIO : new Color(55, 65, 81),
                    activa ? 2 : 1
            ));

            JLabel flecha = new JLabel(
                    activa ? "▼" : " ",
                    SwingConstants.CENTER
            );

            flecha.setForeground(Color.WHITE);
            flecha.setFont(EstilosUI.TEXTO_PEQUENO);

            JLabel simbolo = new JLabel(
                    cinta == null
                            ? "B"
                            : String.valueOf(cinta.leer(i)),
                    SwingConstants.CENTER
            );

            simbolo.setFont(new Font("Segoe UI", Font.BOLD, 19));
            simbolo.setForeground(EstilosUI.TEXTO);

            JLabel indice = new JLabel(
                    String.valueOf(i),
                    SwingConstants.CENTER
            );

            indice.setForeground(EstilosUI.TEXTO_SECUNDARIO);
            indice.setFont(EstilosUI.TEXTO_PEQUENO);

            celda.add(flecha, BorderLayout.NORTH);
            celda.add(simbolo, BorderLayout.CENTER);
            celda.add(indice, BorderLayout.SOUTH);

            panelCeldas.add(celda);
        }

        lblPosicion.setText("Posición del cabezal: " + posicionCabezal);

        panelCeldas.revalidate();
        panelCeldas.repaint();
        final int minimoFinal = minimo;

        SwingUtilities.invokeLater(() -> {

            int indice = posicionCabezal - minimoFinal;

            int desplazamiento = Math.max(
                    0,
                    indice * (ANCHO_CELDA + 3)
            );

            scroll.getHorizontalScrollBar().setValue(desplazamiento);
        });
    }
}
