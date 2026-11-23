
package com.chomsky.simulador_chomsky.nivel3;

import javax.swing.*;
import java.awt.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelAutomata extends JPanel {

    private String estadoActual = "q0";

    public PanelAutomata() {

        setBackground(EstilosUI.FONDO);

        setPreferredSize(new Dimension(550, 250));
        setMinimumSize(new Dimension(350, 220));
    }

    public void actualizarEstado(String estado) {

        estadoActual = estado;

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

        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2 + 15;

        int separacion = Math.min(230, getWidth() / 3);

        int x0 = centroX - separacion / 2;
        int x1 = centroX + separacion / 2;

        int radio = 43;

        g2.setStroke(new BasicStroke(2));

        g2.setFont(EstilosUI.BOTON);

        // =====================================
        // TRANSICIÓN q0 -> q1 (SÍMBOLO 1)
        // =====================================

        g2.setColor(EstilosUI.TEXTO_SECUNDARIO);

        g2.drawLine(
            x0 + radio,
            centroY - 12,
            x1 - radio - 8,
            centroY - 12
        );

        dibujarFlecha(
            g2,
            x1 - radio - 8,
            centroY - 12,
            true
        );

        dibujarEtiqueta(
            g2,
            "1",
            centroX,
            centroY - 23
        );

        // =====================================
        // TRANSICIÓN q1 -> q0 (SÍMBOLO 0)
        // =====================================

        g2.drawLine(
            x1 - radio,
            centroY + 13,
            x0 + radio + 8,
            centroY + 13
        );

        dibujarFlecha(
            g2,
            x0 + radio + 8,
            centroY + 13,
            false
        );

        dibujarEtiqueta(
            g2,
            "0",
            centroX,
            centroY + 35
        );

        // =====================================
        // BUCLE DE q0 (SÍMBOLO 0)
        // =====================================

        g2.drawArc(
            x0 - 22,
            centroY - radio - 58,
            44,
            55,
            0,
            180
        );

        dibujarEtiqueta(
            g2,
            "0",
            x0,
            centroY - radio - 48
        );

        // =====================================
        // BUCLE DE q1 (SÍMBOLO 1)
        // =====================================

        g2.drawArc(
            x1 - 22,
            centroY - radio - 58,
            44,
            55,
            0,
            180
        );

        dibujarEtiqueta(
            g2,
            "1",
            x1,
            centroY - radio - 48
        );

        // =====================================
        // FLECHA DEL ESTADO INICIAL
        // =====================================

        int inicioX = x0 - 100;

        g2.drawLine(
            inicioX,
            centroY,
            x0 - radio,
            centroY
        );

        dibujarFlecha(
            g2,
            x0 - radio,
            centroY,
            true
        );

        // =====================================
        // ESTADOS
        // =====================================

        dibujarEstado(g2, x0, centroY, "q0", false);

        dibujarEstado(g2, x1, centroY, "q1", true);

        // =====================================
        // INFORMACIÓN INFERIOR
        // =====================================

        g2.setColor(EstilosUI.TEXTO_SECUNDARIO);

        g2.setFont(EstilosUI.TEXTO_NORMAL);

        String informacion =
            "Estado actual: " + estadoActual;

        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(
            informacion,
            centroX - fm.stringWidth(informacion) / 2,
            getHeight() - 20
        );

        g2.dispose();
    }

    private void dibujarEstado(
            Graphics2D g2,
            int x,
            int y,
            String nombre,
            boolean esFinal) {

        int radio = 43;

        boolean actual = estadoActual.equals(nombre);

        g2.setColor(
            actual ? EstilosUI.PRIMARIO : EstilosUI.TARJETA
        );

        g2.fillOval(
            x - radio,
            y - radio,
            radio * 2,
            radio * 2
        );

        g2.setColor(
            actual
                ? EstilosUI.PRIMARIO.brighter()
                : EstilosUI.TEXTO_SECUNDARIO
        );

        g2.setStroke(new BasicStroke(2));

        g2.drawOval(
            x - radio,
            y - radio,
            radio * 2,
            radio * 2
        );

        // Doble círculo para estado final

        if (esFinal) {

            g2.drawOval(
                x - radio + 7,
                y - radio + 7,
                radio * 2 - 14,
                radio * 2 - 14
            );
        }

        g2.setColor(EstilosUI.TEXTO);

        g2.setFont(new Font("Segoe UI", Font.BOLD, 17));

        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(
            nombre,
            x - fm.stringWidth(nombre) / 2,
            y + fm.getAscent() / 2 - 2
        );
    }

    private void dibujarFlecha(
            Graphics2D g2,
            int x,
            int y,
            boolean derecha) {

        int direccion = derecha ? 1 : -1;

        Polygon flecha = new Polygon();

        flecha.addPoint(x, y);

        flecha.addPoint(
            x - direccion * 10,
            y - 6
        );

        flecha.addPoint(
            x - direccion * 10,
            y + 6
        );

        g2.fillPolygon(flecha);
    }

    private void dibujarEtiqueta(
            Graphics2D g2,
            String texto,
            int x,
            int y) {

        g2.setColor(EstilosUI.PRIMARIO);

        g2.setFont(EstilosUI.BOTON);

        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(
            texto,
            x - fm.stringWidth(texto) / 2,
            y
        );
    }
}
