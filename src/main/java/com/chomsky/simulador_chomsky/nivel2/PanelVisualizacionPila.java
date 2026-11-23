package com.chomsky.simulador_chomsky.nivel2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelVisualizacionPila extends JPanel {

    private List<String> elementos;

    private PanelDibujoPila panelDibujo;

    public PanelVisualizacionPila() {

        elementos = new ArrayList<>();

        setLayout(new BorderLayout(5, 10));
        setBackground(EstilosUI.TARJETA);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Visualización de la pila");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        panelDibujo = new PanelDibujoPila();

        JScrollPane scroll = new JScrollPane(panelDibujo);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(EstilosUI.TARJETA);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    // Actualiza los elementos de la pila.
    public void actualizar(Pila pila) {

        elementos = pila.obtenerElementos();

        panelDibujo.revalidate();
        panelDibujo.repaint();
    }

    public void limpiar() {

        elementos.clear();

        panelDibujo.revalidate();
        panelDibujo.repaint();
    }

    // Panel encargado de dibujar la pila.
    private class PanelDibujoPila extends JPanel {

        public PanelDibujoPila() {

            setBackground(EstilosUI.TARJETA);
        }

        @Override
        public Dimension getPreferredSize() {

            int altura = Math.max(250, 70 + elementos.size() * 55);

            return new Dimension(200, altura);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int ancho = 90;
            int alto = 42;

            int x = (getWidth() - ancho) / 2;

            // Dibuja los elementos desde el fondo hasta el tope.
            for (int i = elementos.size() - 1; i >= 0; i--) {

                int y = 20 + (elementos.size() - 1 - i) * 55;

                String elemento = elementos.get(i);

                if (elemento.equals("A")) {

                    g2.setColor(EstilosUI.PRIMARIO);

                } else if (elemento.equals("Z0")) {

                    g2.setColor(EstilosUI.SECUNDARIO);

                } else {

                    g2.setColor(new Color(71, 85, 105));
                }

                g2.fillRoundRect(x, y, ancho, alto, 12, 12);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Consolas", Font.BOLD, 17));

                FontMetrics fm = g2.getFontMetrics();

                int textoX = x + (ancho - fm.stringWidth(elemento)) / 2;
                int textoY = y + (alto + fm.getAscent() - fm.getDescent()) / 2;

                g2.drawString(elemento, textoX, textoY);

                g2.setColor(new Color(255, 255, 255, 70));

                g2.drawRoundRect(x, y, ancho, alto, 12, 12);
            }

            if (elementos.isEmpty()) {

                g2.setColor(EstilosUI.TEXTO_SECUNDARIO);
                g2.setFont(EstilosUI.TEXTO_NORMAL);

                g2.drawString(
                        "Pila vacía",
                        (getWidth() - 65) / 2,
                        120
                );
            }

            g2.dispose();
        }
    }
}