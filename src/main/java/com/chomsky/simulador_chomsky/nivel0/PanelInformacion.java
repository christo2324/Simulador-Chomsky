
package com.chomsky.simulador_chomsky.nivel0;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelInformacion extends JPanel {

    private JLabel lblEstado;
    private JLabel lblAlfabetoEntrada;
    private JLabel lblAlfabetoCinta;
    private JLabel lblTransiciones;
    private JLabel lblResultado;

    public PanelInformacion() {

        setLayout(new GridLayout(0, 1, 5, 8));
        setBackground(EstilosUI.TARJETA);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Información de ejecución");
        titulo.setFont(EstilosUI.TITULO_TARJETA);
        titulo.setForeground(EstilosUI.TEXTO);

        add(titulo);

        lblEstado = crearDato("Estado activo: qInicio");
        lblAlfabetoEntrada = crearDato("Alfabeto de entrada: -");
        lblAlfabetoCinta = crearDato("Alfabeto de cinta: -");
        lblTransiciones = crearDato("Transiciones: 0");
        lblResultado = crearDato("Resultado: Sin iniciar");

        add(lblEstado);
        add(lblAlfabetoEntrada);
        add(lblAlfabetoCinta);
        add(lblTransiciones);
        add(lblResultado);
    }

    private JLabel crearDato(String texto) {

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(EstilosUI.TEXTO_NORMAL);
        etiqueta.setForeground(EstilosUI.TEXTO_SECUNDARIO);

        return etiqueta;
    }

    public void actualizar(MaquinaTuring maquina) {

        lblEstado.setText("Estado activo: " + maquina.getEstadoActual());

        lblAlfabetoEntrada.setText(
                "Alfabeto de entrada: " + maquina.getAlfabetoEntrada()
        );

        lblAlfabetoCinta.setText(
                "Alfabeto de cinta: " + maquina.getAlfabetoCinta()
        );

        lblTransiciones.setText(
                "Transiciones: " + maquina.getContadorTransiciones()
        );

        lblResultado.setText(
                "Resultado: " + maquina.getResultado()
        );

        if (maquina.estaFinalizada()) {
            lblResultado.setForeground(
                    maquina.fueAceptada()
                            ? new Color(52, 211, 153)
                            : EstilosUI.SECUNDARIO
            );
        } else {
            lblResultado.setForeground(EstilosUI.TEXTO_SECUNDARIO);
        }
    }

    public void reiniciar() {

        lblEstado.setText("Estado activo: qInicio");
        lblTransiciones.setText("Transiciones: 0");
        lblResultado.setText("Resultado: Sin iniciar");
        lblResultado.setForeground(EstilosUI.TEXTO_SECUNDARIO);
    }
}
