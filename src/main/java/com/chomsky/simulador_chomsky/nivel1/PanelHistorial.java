
package com.chomsky.simulador_chomsky.nivel1;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class PanelHistorial extends JPanel {

    private DefaultListModel<String> modelo;

    private JList<String> listaHistorial;

    private JScrollPane desplazamiento;

    public PanelHistorial() {

        setLayout(new BorderLayout(5, 10));

        setBackground(EstilosUI.TARJETA);

        setBorder(
                BorderFactory.createEmptyBorder(15, 18, 15, 18)
        );

        construirInterfaz();
    }

    private void construirInterfaz() {

        modelo = new DefaultListModel<>();

        listaHistorial = new JList<>(modelo);

        listaHistorial.setBackground(EstilosUI.FONDO);

        listaHistorial.setForeground(EstilosUI.TEXTO);

        listaHistorial.setFont(EstilosUI.TEXTO_PEQUENO);

        listaHistorial.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        listaHistorial.setFixedCellHeight(48);

        listaHistorial.setCellRenderer(
                new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean enfocado) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        enfocado
                );

                setBackground(
                        seleccionado
                                ? new java.awt.Color(73, 48, 120)
                                : EstilosUI.FONDO
                );

                setForeground(EstilosUI.TEXTO);

                setFont(EstilosUI.TEXTO_PEQUENO);

                setBorder(
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                );

                return this;
            }
        });

        desplazamiento = new JScrollPane(listaHistorial);

        desplazamiento.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        desplazamiento.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        desplazamiento.getViewport().setBackground(EstilosUI.FONDO);

        desplazamiento.setBorder(
                BorderFactory.createLineBorder(
                        new java.awt.Color(41, 51, 73)
                )
        );

        add(desplazamiento, BorderLayout.CENTER);
    }

    public void agregarTransicion(
            MaquinaLineal.RegistroTransicion registro) {

        String direccion;

        switch (registro.getDireccion()) {

            case IZQUIERDA:
                direccion = "← Izquierda";
                break;

            case DERECHA:
                direccion = "→ Derecha";
                break;

            default:
                direccion = "• Sin movimiento";
                break;
        }

        String texto = String.format(
                "#%d | %s → %s | '%c' → '%c' | %s",
                registro.getNumero(),
                registro.getEstadoAnterior(),
                registro.getEstadoNuevo(),
                registro.getSimboloLeido(),
                registro.getSimboloEscrito(),
                direccion
        );

        modelo.addElement(
                texto + " | " + registro.getDescripcion()
        );

        int ultimo = modelo.getSize() - 1;

        listaHistorial.ensureIndexIsVisible(ultimo);
    }

    public void limpiar() {

        modelo.clear();
    }

    public int getCantidadTransiciones() {

        return modelo.getSize();
    }
}
