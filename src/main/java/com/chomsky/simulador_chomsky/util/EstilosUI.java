package com.chomsky.simulador_chomsky.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EstilosUI {

    // =====================================
    // PALETA DE COLORES
    // =====================================

    public static final Color FONDO =
        Color.decode("#0B0F17");

    public static final Color TARJETA =
        Color.decode("#151C2C");

    public static final Color PRIMARIO =
        Color.decode("#8B5CF6");

    public static final Color SECUNDARIO =
        Color.decode("#F43F5E");

    public static final Color TEXTO =
        Color.decode("#F1F5F9");

    public static final Color TEXTO_SECUNDARIO =
        Color.decode("#94A3B8");


    // =====================================
    // TIPOGRAFÍAS
    // =====================================

    public static final Font TITULO =
        new Font("Segoe UI", Font.BOLD, 30);

    public static final Font SUBTITULO =
        new Font("Segoe UI", Font.PLAIN, 14);

    public static final Font TITULO_TARJETA =
        new Font("Segoe UI", Font.BOLD, 19);

    public static final Font TEXTO_NORMAL =
        new Font("Segoe UI", Font.PLAIN, 13);

    public static final Font TEXTO_PEQUENO =
        new Font("Segoe UI", Font.PLAIN, 11);

    public static final Font BOTON =
        new Font("Segoe UI", Font.BOLD, 13);


    // =====================================
    // MÉTODOS PARA COMPONENTES
    // =====================================

    // Crear panel con el fondo general
    public static JPanel crearPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(FONDO);

        return panel;
    }

    // Crear título
    public static JLabel crearTitulo(String texto) {

        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(TITULO);
        etiqueta.setForeground(TEXTO);

        return etiqueta;
    }

    // Crear subtítulo
    public static JLabel crearSubtitulo(String texto) {

        JLabel etiqueta = new JLabel(texto);

        etiqueta.setFont(SUBTITULO);
        etiqueta.setForeground(TEXTO_SECUNDARIO);

        return etiqueta;
    }

    // Crear botón personalizado
    public static JButton crearBoton(
        String texto,
        Color color
    ) {

        JButton boton = new JButton(texto);

        boton.setFont(BOTON);
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);

        boton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        // Bordes redondeados
        boton.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    color, 1, true
                ),
                new EmptyBorder(10, 15, 10, 15)
            )
        );

        // Efecto hover
        Color colorHover = color.brighter();

        boton.addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(
                    java.awt.event.MouseEvent e
                ) {
                    boton.setBackground(colorHover);
                }

                @Override
                public void mouseExited(
                    java.awt.event.MouseEvent e
                ) {
                    boton.setBackground(color);
                }
            }
        );

        return boton;
    }

    // Crear tarjeta
    public static JPanel crearTarjeta() {

        JPanel tarjeta = new JPanel();

        tarjeta.setBackground(TARJETA);

        tarjeta.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(41, 51, 73), 1
                ),
                new EmptyBorder(25, 22, 25, 22)
            )
        );

        return tarjeta;
    }
}
