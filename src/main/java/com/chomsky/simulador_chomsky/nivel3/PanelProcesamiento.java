
package com.chomsky.simulador_chomsky.nivel3;

import javax.swing.*;
import java.awt.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelProcesamiento extends JPanel {

    private JTextArea areaHistorial;

    public PanelProcesamiento() {

        setLayout(new BorderLayout(10, 10));

        setBackground(EstilosUI.TARJETA);
        
        

        setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(41, 51, 73)
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel titulo = new JLabel("Historial de procesamiento");

        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        add(titulo, BorderLayout.NORTH);

        areaHistorial = new JTextArea();

        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaHistorial.setForeground(EstilosUI.TEXTO);
        areaHistorial.setBackground(EstilosUI.FONDO);

        areaHistorial.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaHistorial);

        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);
    }

    public void actualizarHistorial(SimuladorAFD simulador) {

        StringBuilder contenido = new StringBuilder();

        for (String movimiento : simulador.getHistorial()) {

            contenido.append("  •  ")
                     .append(movimiento)
                     .append("\n");
        }

        areaHistorial.setText(contenido.toString());

        areaHistorial.setCaretPosition(
            areaHistorial.getDocument().getLength()
        );
    }

    public void limpiar() {
        areaHistorial.setText("");
    }
}
