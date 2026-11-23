package com.chomsky.simulador_chomsky.nivel2;

import java.awt.*;
import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class VentanaAutomataPila extends JFrame {

    private AutomataPila automata;

    private PanelConfiguracionPila panelConfiguracion;
    private PanelCadena panelCadena;
    private PanelVisualizacionPila panelPila;
    private PanelTransiciones panelTransiciones;

    private JLabel lblEstado;

    private Timer temporizador;

    public VentanaAutomataPila() {

        automata = new AutomataPila();

        configurarVentana();
        inicializarComponentes();
        configurarEventos();
    }

    // Configuración general de la ventana.
    private void configurarVentana() {

        setTitle("Nivel 2 - Autómata de Pila");

        setSize(900, 700);

        setMinimumSize(new Dimension(760, 560));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setBackground(EstilosUI.FONDO);
    }

    // Construcción de la interfaz.
    private void inicializarComponentes() {

        JPanel principal = new JPanel(new BorderLayout(12, 12));

        principal.setBackground(EstilosUI.FONDO);

        principal.setBorder(
                BorderFactory.createEmptyBorder(15, 18, 15, 18));

        // Encabezado.
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(EstilosUI.FONDO);

        JLabel titulo = EstilosUI.crearTitulo("Autómata de Pila");

        JLabel subtitulo = EstilosUI.crearSubtitulo(
                "Reconocimiento del lenguaje L = { aⁿbⁿ | n ≥ 1 }"
        );

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(5));
        encabezado.add(subtitulo);

        // Panel de configuración.
        panelConfiguracion = new PanelConfiguracionPila();

        // Panel de visualización.
        panelCadena = new PanelCadena();

        panelPila = new PanelVisualizacionPila();

        // Altura fija del área visual.
        panelCadena.setPreferredSize(new Dimension(0, 280));
        panelPila.setPreferredSize(new Dimension(0, 280));

        JPanel panelVisual = new JPanel(new GridLayout(1, 2, 12, 0));

        panelVisual.setBackground(EstilosUI.FONDO);

        panelVisual.setPreferredSize(new Dimension(0, 280));

        panelVisual.add(panelCadena);
        panelVisual.add(panelPila);

        // Panel central.
        JPanel centro = new JPanel(new BorderLayout(12, 12));

        centro.setBackground(EstilosUI.FONDO);

        centro.add(panelConfiguracion, BorderLayout.NORTH);
        centro.add(panelVisual, BorderLayout.CENTER);

        // Panel inferior.
        JPanel inferior = new JPanel(new BorderLayout(8, 8));

        inferior.setBackground(EstilosUI.FONDO);

        // Historial de transiciones.
        panelTransiciones = new PanelTransiciones();

        panelTransiciones.setPreferredSize(new Dimension(0, 150));
        panelTransiciones.setMinimumSize(new Dimension(0, 150));

        // Estado de la simulación.
        lblEstado = new JLabel("Estado: esperando una cadena...");
        lblEstado.setFont(EstilosUI.BOTON);
        lblEstado.setForeground(EstilosUI.TEXTO_SECUNDARIO);

        inferior.add(panelTransiciones, BorderLayout.CENTER);
        inferior.add(lblEstado, BorderLayout.SOUTH);

        // Altura preferida del contenedor inferior.
        inferior.setPreferredSize(new Dimension(0, 190));
        inferior.setMinimumSize(new Dimension(0, 190));

        // Organización final.
        principal.add(encabezado, BorderLayout.NORTH);
        principal.add(centro, BorderLayout.CENTER);
        principal.add(inferior, BorderLayout.SOUTH);

        setContentPane(principal);
    }

    // Eventos de los botones.
    private void configurarEventos() {

        panelConfiguracion.getBtnIniciar().addActionListener(e -> {
            iniciarSimulacion();
        });

        panelConfiguracion.getBtnSiguiente().addActionListener(e -> {
            siguientePaso();
        });

        panelConfiguracion.getBtnEjecutar().addActionListener(e -> {
            ejecutarTodo();
        });

        panelConfiguracion.getBtnReiniciar().addActionListener(e -> {
            reiniciar();
        });
    }

    // Inicia una nueva simulación.
    private void iniciarSimulacion() {

        String cadena = panelConfiguracion.getCadena();

        if (cadena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una cadena para iniciar la simulación.",
                    "Entrada inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!cadena.matches("[ab]+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "La cadena solamente puede contener los símbolos a y b.",
                    "Entrada inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        detenerTemporizador();

        automata.iniciar(cadena);

        panelConfiguracion.habilitarSimulacion(true);

        actualizarInterfaz();
    }

    // Ejecuta un único símbolo.
    private void siguientePaso() {

        if (automata.isFinalizada()) {
            detenerTemporizador();
            return;
        }

        automata.siguientePaso();

        actualizarInterfaz();

        if (automata.isFinalizada()) {

            detenerTemporizador();

            panelConfiguracion.habilitarSimulacion(false);

            mostrarResultado();
        }
    }

    // Ejecuta automáticamente todos los pasos.
    private void ejecutarTodo() {

        if (automata.isFinalizada()) {
            return;
        }

        detenerTemporizador();

        temporizador = new Timer(550, e -> {
            siguientePaso();
        });

        temporizador.start();
    }

    // Actualiza todos los paneles.
    private void actualizarInterfaz() {

        panelCadena.actualizar(
                automata.getCadena(),
                automata.getPosicion()
        );

        panelPila.actualizar(automata.getPila());

        panelTransiciones.actualizar(
                automata.getHistorial()
        );

        lblEstado.setText(
                "Estado actual: " + automata.getEstado()
                        + "     |     Posición: "
                        + automata.getPosicion()
                        + "/" + automata.getCadena().length()
        );

        lblEstado.setForeground(EstilosUI.TEXTO_SECUNDARIO);
    }

    // Muestra el resultado de la simulación.
    private void mostrarResultado() {

        if (automata.isAceptada()) {

            lblEstado.setText(
                    "¡CADENA ACEPTADA! La pila quedó en Z0."
            );

            lblEstado.setForeground(new Color(52, 211, 153));

        } else {

            lblEstado.setText(
                    "CADENA RECHAZADA. No se cumple el lenguaje."
            );

            lblEstado.setForeground(EstilosUI.SECUNDARIO);
        }
    }

    // Reinicia la simulación.
    private void reiniciar() {

        detenerTemporizador();

        automata.reiniciar();

        panelConfiguracion.habilitarSimulacion(false);
        panelConfiguracion.reiniciarEntrada();

        panelCadena.limpiar();
        panelPila.limpiar();
        panelTransiciones.limpiar();

        lblEstado.setText("Estado: esperando una cadena...");
        lblEstado.setForeground(EstilosUI.TEXTO_SECUNDARIO);
    }

    // Detiene el temporizador automático.
    private void detenerTemporizador() {

        if (temporizador != null && temporizador.isRunning()) {
            temporizador.stop();
        }
    }
}