
package com.chomsky.simulador_chomsky.nivel1;

import javax.swing.*;
import java.awt.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelCinta extends JPanel {

    private char[] cinta;
    private int posicionCabezal;

    private static final int ANCHO_CELDA = 48;
    private static final int ALTO_CELDA = 48;
    private static final int ESPACIO = 4;

    public PanelCinta() {
        setBackground(EstilosUI.TARJETA);
        setPreferredSize(new Dimension(850, 115));
        setMinimumSize(new Dimension(600, 115));

        cinta = new char[0];
        posicionCabezal = -1;
    }

    public void actualizarCinta(char[] nuevaCinta, int nuevaPosicion) {
        this.cinta = nuevaCinta.clone();
        this.posicionCabezal = nuevaPosicion;

        repaint();
    }

    public void limpiar() {
        cinta = new char[0];
        posicionCabezal = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (cinta.length == 0) {
            g2.setColor(EstilosUI.TEXTO_SECUNDARIO);
            g2.setFont(EstilosUI.TEXTO_NORMAL);

            String mensaje = "Inicia una simulación para visualizar la cinta";
            FontMetrics fm = g2.getFontMetrics();

            int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
            int y = getHeight() / 2;

            g2.drawString(mensaje, x, y);
            g2.dispose();
            return;
        }

        int anchoTotal = cinta.length * (ANCHO_CELDA + ESPACIO) - ESPACIO;
        int inicioX = (getWidth() - anchoTotal) / 2;

        int inicioY = 30;

        for (int i = 0; i < cinta.length; i++) {

            int x = inicioX + i * (ANCHO_CELDA + ESPACIO);

            Color fondo = new Color(30, 41, 59);
            Color borde = new Color(71, 85, 105);

            if (i == 0 || i == cinta.length - 1) {
                fondo = new Color(88, 28, 135);
                borde = EstilosUI.PRIMARIO;
            }

            if (i == posicionCabezal) {
                fondo = new Color(76, 29, 149);
                borde = EstilosUI.PRIMARIO;
            }

            g2.setColor(fondo);
            g2.fillRoundRect(
                x, inicioY,
                ANCHO_CELDA, ALTO_CELDA,
                10, 10
            );

            g2.setColor(borde);
            g2.setStroke(new BasicStroke(2));

            g2.drawRoundRect(
                x, inicioY,
                ANCHO_CELDA, ALTO_CELDA,
                10, 10
            );

            g2.setColor(EstilosUI.TEXTO);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 20));

            if (i == 0) {
                // Delimitador izquierdo: ⊢
                g2.setStroke(new BasicStroke(3));
                g2.drawLine(x + 15, inicioY + 10, x + 15, inicioY + 38);
                g2.drawLine(x + 15, inicioY + 24, x + 32, inicioY + 24);

            } else if (i == cinta.length - 1) {
                // Delimitador derecho: ⊣
                g2.setStroke(new BasicStroke(3));
                g2.drawLine(x + 32, inicioY + 10, x + 32, inicioY + 38);
                g2.drawLine(x + 15, inicioY + 24, x + 32, inicioY + 24);

            } else {
                String simbolo = String.valueOf(cinta[i]);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 20));

                FontMetrics fm = g2.getFontMetrics();

                int textoX = x + (ANCHO_CELDA - fm.stringWidth(simbolo)) / 2;
                int textoY = inicioY + (ALTO_CELDA + fm.getAscent() - fm.getDescent()) / 2;

                g2.drawString(simbolo, textoX, textoY);
            }

            // Cabezal de lectura y escritura.
            if (i == posicionCabezal) {
                g2.setColor(EstilosUI.SECUNDARIO);

                int centroX = x + ANCHO_CELDA / 2;

                int[] puntosX = {
                    centroX - 8,
                    centroX + 8,
                    centroX
                };

                int[] puntosY = {
                    17,
                    17,
                    27
                };

                g2.fillPolygon(puntosX, puntosY, 3);
            }
        }

        g2.dispose();
    }
}
