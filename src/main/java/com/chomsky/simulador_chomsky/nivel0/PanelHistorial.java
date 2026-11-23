
package com.chomsky.simulador_chomsky.nivel0;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelHistorial extends JPanel {

    private DefaultListModel<String> modelo;
    private JList<String> lista;

    public PanelHistorial() {

        setLayout(new BorderLayout(5, 8));
        setBackground(EstilosUI.TARJETA);
        setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel titulo = new JLabel("Historial de transiciones");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultListModel<>();

        lista = new JList<>(modelo);
        lista.setBackground(EstilosUI.FONDO);
        lista.setForeground(EstilosUI.TEXTO);
        lista.setFont(EstilosUI.TEXTO_PEQUENO);
        lista.setSelectionBackground(EstilosUI.PRIMARIO);
        lista.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(400, 125));

        scroll.setBorder(BorderFactory.createLineBorder(
                new Color(41, 51, 73)
        ));

        add(scroll, BorderLayout.CENTER);
    }

    public void agregarTransicion(
            int numero,
            String estadoAnterior,
            Transicion transicion) {

        String registro = String.format(
                "#%d | %s | Lee: %s | Escribe: %s | %s | %s | %s",
                numero,
                estadoAnterior,
                transicion.getSimboloLeido(),
                transicion.getSimboloEscrito(),
                transicion.getDireccionTexto(),
                transicion.getEstadoSiguiente(),
                transicion.getDescripcion()
        );

        modelo.addElement(registro);

        lista.ensureIndexIsVisible(modelo.size() - 1);
    }

    public void limpiar() {
        modelo.clear();
    }
}
