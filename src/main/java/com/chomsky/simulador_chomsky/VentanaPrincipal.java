
package com.chomsky.simulador_chomsky;

import com.chomsky.simulador_chomsky.util.EstilosUI;

import com.chomsky.simulador_chomsky.nivel3.VentanaAutomataFinito;
import com.chomsky.simulador_chomsky.nivel2.VentanaAutomataPila;
import com.chomsky.simulador_chomsky.nivel1.VentanaMaquinaLineal;
import com.chomsky.simulador_chomsky.nivel0.VentanaMaquinaTuring;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        // Configuración de la ventana
        setTitle("Simulador - Jerarquía de Chomsky");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // =====================================
        // PANEL PRINCIPAL
        // =====================================

        JPanel panelPrincipal = new JPanel(
            new BorderLayout(20, 30)
        );

        panelPrincipal.setBackground(EstilosUI.FONDO);

        panelPrincipal.setBorder(
            new EmptyBorder(40, 50, 30, 50)
        );

        // =====================================
        // ENCABEZADO
        // =====================================

        JPanel encabezado = new JPanel();

        encabezado.setLayout(
            new BoxLayout(encabezado, BoxLayout.Y_AXIS)
        );

        encabezado.setBackground(EstilosUI.FONDO);

        // Etiqueta superior
        JLabel etiquetaSuperior = new JLabel(
            "SIMULADOR INTERACTIVO"
        );

        etiquetaSuperior.setFont(
            EstilosUI.TEXTO_PEQUENO.deriveFont(Font.BOLD)
        );

        etiquetaSuperior.setForeground(
            EstilosUI.PRIMARIO
        );

        etiquetaSuperior.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // Título
        JLabel titulo = EstilosUI.crearTitulo(
            "JERARQUÍA DE CHOMSKY"
        );

        titulo.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // Subtítulo
        JLabel subtitulo = EstilosUI.crearSubtitulo(
            "Explora los modelos computacionales y sus capacidades"
        );

        subtitulo.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // Agregar elementos al encabezado
        encabezado.add(etiquetaSuperior);
        encabezado.add(Box.createVerticalStrut(12));
        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(10));
        encabezado.add(subtitulo);

        // =====================================
        // PANEL DE TARJETAS
        // =====================================

        JPanel panelTarjetas = new JPanel(
            new GridLayout(2, 2, 20, 20)
        );

        panelTarjetas.setBackground(EstilosUI.FONDO);

        // Nivel 3
        panelTarjetas.add(crearTarjeta(
            "NIVEL 3",
            "Autómata Finito",
            "Lenguajes regulares",
            EstilosUI.PRIMARIO,
            () -> new VentanaAutomataFinito().setVisible(true)
        ));

        // Nivel 2
        panelTarjetas.add(crearTarjeta(
            "NIVEL 2",
            "Autómata de Pila",
            "Lenguajes independientes del contexto",
            EstilosUI.SECUNDARIO,
            () -> new VentanaAutomataPila().setVisible(true)
        ));

        // Nivel 1
        panelTarjetas.add(crearTarjeta(
            "NIVEL 1",
            "Máquina Linealmente Acotada",
            "Lenguajes sensibles al contexto",
            EstilosUI.PRIMARIO,
            () -> new VentanaMaquinaLineal().setVisible(true)
        ));

        // Nivel 0
        panelTarjetas.add(crearTarjeta(
            "NIVEL 0",
            "Máquina de Turing",
            "Lenguajes recursivamente enumerables",
            EstilosUI.SECUNDARIO,
            () -> new VentanaMaquinaTuring().setVisible(true)
        ));

        // =====================================
        // PIE DE PÁGINA
        // =====================================

        JLabel piePagina = new JLabel(
            "SIMULADOR DE MODELOS COMPUTACIONALES  |  2026",
            SwingConstants.CENTER
        );

        piePagina.setFont(
            EstilosUI.TEXTO_PEQUENO
        );

        piePagina.setForeground(
            EstilosUI.TEXTO_SECUNDARIO
        );

        // =====================================
        // AGREGAR COMPONENTES
        // =====================================

        panelPrincipal.add(
            encabezado,
            BorderLayout.NORTH
        );

        panelPrincipal.add(
            panelTarjetas,
            BorderLayout.CENTER
        );

        panelPrincipal.add(
            piePagina,
            BorderLayout.SOUTH
        );

        add(panelPrincipal);
    }

    // =====================================
    // MÉTODO PARA CREAR TARJETAS
    // =====================================

    private JPanel crearTarjeta(
        String nivel,
        String nombre,
        String descripcion,
        Color color,
        Runnable accion
    ) {

        // Panel de la tarjeta
        JPanel tarjeta = EstilosUI.crearTarjeta();

        tarjeta.setLayout(
            new BoxLayout(tarjeta, BoxLayout.Y_AXIS)
        );

        // Etiqueta del nivel
        JLabel etiquetaNivel = new JLabel(nivel);

        etiquetaNivel.setFont(
            EstilosUI.TEXTO_NORMAL.deriveFont(Font.BOLD)
        );

        etiquetaNivel.setForeground(color);

        // Nombre del módulo
        JLabel etiquetaNombre = new JLabel(
            "<html><div style='width:260px'>" +
            nombre +
            "</div></html>"
        );

        etiquetaNombre.setFont(
            EstilosUI.TITULO_TARJETA
        );

        etiquetaNombre.setForeground(
            EstilosUI.TEXTO
        );

        // Descripción
        JLabel etiquetaDescripcion = new JLabel(
            "<html><div style='width:260px'>" +
            descripcion +
            "</div></html>"
        );

        etiquetaDescripcion.setFont(
            EstilosUI.TEXTO_NORMAL
        );

        etiquetaDescripcion.setForeground(
            EstilosUI.TEXTO_SECUNDARIO
        );

        // Botón de navegación
        JButton boton = EstilosUI.crearBoton(
            "Explorar módulo  →",
            color
        );

        // Acción del botón
        boton.addActionListener(e -> accion.run());

        // Alineación de componentes
        etiquetaNivel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        etiquetaNombre.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        etiquetaDescripcion.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        boton.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        // Agregar componentes a la tarjeta
        tarjeta.add(etiquetaNivel);
        tarjeta.add(Box.createVerticalStrut(15));

        tarjeta.add(etiquetaNombre);
        tarjeta.add(Box.createVerticalStrut(10));

        tarjeta.add(etiquetaDescripcion);

        // Separación entre descripción y botón
        tarjeta.add(Box.createVerticalStrut(10));

        tarjeta.add(boton);

        return tarjeta;
    }
}
