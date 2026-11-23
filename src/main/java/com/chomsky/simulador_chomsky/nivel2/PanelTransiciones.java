package com.chomsky.simulador_chomsky.nivel2;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelTransiciones extends JPanel {

    private JTextArea areaTransiciones;

    public PanelTransiciones() {

        setLayout(new BorderLayout(5, 10));
        setBackground(EstilosUI.TARJETA);

        setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JLabel titulo = new JLabel("Historial de transiciones");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        areaTransiciones = new JTextArea();

        areaTransiciones.setEditable(false);
        areaTransiciones.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaTransiciones.setForeground(EstilosUI.TEXTO);
        areaTransiciones.setBackground(EstilosUI.FONDO);
        areaTransiciones.setCaretColor(EstilosUI.TEXTO);

        areaTransiciones.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaTransiciones);

        scroll.setBorder(null);

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    // Actualiza el historial de transiciones.
    public void actualizar(List<TransicionPila> historial) {

        StringBuilder texto = new StringBuilder();

        texto.append("N° | ESTADO | SÍMBOLO | TOPE | OPERACIÓN | NUEVO ESTADO\n");
        texto.append("-------------------------------------------------------------\n");

        for (int i = 0; i < historial.size(); i++) {

            TransicionPila transicion = historial.get(i);

            texto.append(i + 1)
                    .append(" | ")
                    .append(transicion.toString())
                    .append("\n");

            texto.append("   ")
                    .append(transicion.getDescripcion())
                    .append("\n\n");
        }

        areaTransiciones.setText(texto.toString());

        areaTransiciones.setCaretPosition(
                areaTransiciones.getDocument().getLength());
    }

    public void limpiar() {
        areaTransiciones.setText("");
    }
}