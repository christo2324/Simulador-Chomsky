
package com.chomsky.simulador_chomsky.nivel3;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class VentanaAutomataFinito extends JFrame {

    private JTextField txtCadena;

    private JButton btnIniciar;
    private JButton btnPaso;
    private JButton btnEjecutar;
    private JButton btnReiniciar;

    private JLabel lblEstado;
    private JLabel lblProgreso;

    private PanelAutomata panelAutomata;
    private PanelProcesamiento panelProcesamiento;

    private AutomataFinito automata;
    private SimuladorAFD simulador;



public VentanaAutomataFinito() {

    setTitle("Nivel 3 - Autómatas Finitos");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    cargarAutomata();

    setContentPane(crearContenido());

    // Adaptar la ventana al espacio disponible
    Dimension pantalla = Toolkit.getDefaultToolkit()
            .getScreenSize();

    int ancho = Math.min(950, pantalla.width - 60);
    int alto = Math.min(720, pantalla.height - 80);

    setSize(ancho, alto);

    setMinimumSize(new Dimension(650, 550));

    setLocationRelativeTo(null);

    actualizarInterfaz();
}




private JPanel crearContenido() {

    JPanel principal = EstilosUI.crearPanel();

    principal.setLayout(new BorderLayout(15, 15));

    principal.setBorder(
    	    new EmptyBorder(15, 20, 15, 20)
    	);

    // =====================================
    // ENCABEZADO
    // =====================================

    JPanel encabezado = new JPanel();

    encabezado.setBackground(EstilosUI.FONDO);

    encabezado.setLayout(
        new BoxLayout(encabezado, BoxLayout.Y_AXIS)
    );

    encabezado.add(
        EstilosUI.crearTitulo("Autómatas Finitos")
    );

    encabezado.add(Box.createVerticalStrut(5));

    encabezado.add(
        EstilosUI.crearSubtitulo(
            "Reconocimiento y procesamiento de cadenas regulares"
        )
    );

    principal.add(encabezado, BorderLayout.NORTH);

    // =====================================
    // CONFIGURACIÓN
    // =====================================

    JPanel configuracion = EstilosUI.crearTarjeta();

    configuracion.setLayout(new BorderLayout(10, 10));

    JLabel tituloConfig = new JLabel(
        "Configuración del lenguaje"
    );

    tituloConfig.setFont(EstilosUI.TITULO_TARJETA);
    tituloConfig.setForeground(EstilosUI.TEXTO);

    JPanel informacion = new JPanel();

    informacion.setOpaque(false);

    informacion.setLayout(
        new BoxLayout(informacion, BoxLayout.Y_AXIS)
    );

    JLabel lenguaje = new JLabel(
        "Lenguaje: Cadenas binarias que terminan en 1"
    );

    lenguaje.setFont(EstilosUI.TEXTO_NORMAL);
    lenguaje.setForeground(EstilosUI.TEXTO);

    JLabel alfabeto = new JLabel(
        "Alfabeto: { 0, 1 }"
    );

    alfabeto.setFont(EstilosUI.TEXTO_PEQUENO);
    alfabeto.setForeground(EstilosUI.TEXTO_SECUNDARIO);

    informacion.add(lenguaje);
    informacion.add(Box.createVerticalStrut(5));
    informacion.add(alfabeto);

    JPanel entrada = new JPanel(new BorderLayout(10, 0));

    entrada.setOpaque(false);

    JLabel etiqueta = new JLabel("Cadena:");

    etiqueta.setFont(EstilosUI.BOTON);
    etiqueta.setForeground(EstilosUI.TEXTO);

    txtCadena = new JTextField();

    txtCadena.setFont(EstilosUI.TEXTO_NORMAL);
    txtCadena.setBackground(EstilosUI.FONDO);
    txtCadena.setForeground(EstilosUI.TEXTO);
    txtCadena.setCaretColor(EstilosUI.TEXTO);

    txtCadena.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(41, 51, 73)
            ),
            new EmptyBorder(8, 10, 8, 10)
        )
    );

    entrada.add(etiqueta, BorderLayout.WEST);
    entrada.add(txtCadena, BorderLayout.CENTER);

    btnIniciar = EstilosUI.crearBoton(
        "Iniciar simulación",
        EstilosUI.PRIMARIO
    );

    btnIniciar.addActionListener(
        e -> iniciarSimulacion()
    );

    JPanel botonesEntrada = new JPanel(
        new FlowLayout(FlowLayout.RIGHT, 0, 0)
    );

    botonesEntrada.setOpaque(false);

    botonesEntrada.add(btnIniciar);

    JPanel contenidoConfig = new JPanel(
        new BorderLayout(10, 10)
    );

    contenidoConfig.setOpaque(false);

    contenidoConfig.add(informacion, BorderLayout.NORTH);
    contenidoConfig.add(entrada, BorderLayout.CENTER);
    contenidoConfig.add(botonesEntrada, BorderLayout.SOUTH);

    configuracion.add(tituloConfig, BorderLayout.NORTH);
    configuracion.add(contenidoConfig, BorderLayout.CENTER);

    // =====================================
    // PANEL DEL AUTÓMATA
    // =====================================

    JPanel tarjetaAutomata = EstilosUI.crearTarjeta();

    tarjetaAutomata.setLayout(new BorderLayout(10, 10));

    JLabel tituloAutomata = new JLabel(
        "Representación del autómata"
    );

    tituloAutomata.setFont(EstilosUI.TITULO_TARJETA);
    tituloAutomata.setForeground(EstilosUI.TEXTO);

    panelAutomata = new PanelAutomata();

    tarjetaAutomata.add(
        tituloAutomata,
        BorderLayout.NORTH
    );

    tarjetaAutomata.add(
        panelAutomata,
        BorderLayout.CENTER
    );

    // =====================================
    // HISTORIAL
    // =====================================

    panelProcesamiento = new PanelProcesamiento();

    panelProcesamiento.setPreferredSize(
        new Dimension(600, 170)
    );

    // Separador visual entre autómata e historial

    JSplitPane separador = new JSplitPane(
        JSplitPane.VERTICAL_SPLIT,
        tarjetaAutomata,
        panelProcesamiento
    );

    separador.setResizeWeight(0.65);

    separador.setDividerSize(8);

    separador.setBorder(null);

    separador.setContinuousLayout(true);

    // =====================================
    // CONTROLES
    // =====================================

    JPanel controles = new JPanel(
        new BorderLayout(10, 10)
    );

    controles.setBackground(EstilosUI.FONDO);

    lblEstado = new JLabel(
        "Estado: Esperando cadena"
    );

    lblEstado.setFont(EstilosUI.BOTON);

    lblEstado.setForeground(
        EstilosUI.TEXTO_SECUNDARIO
    );

    lblProgreso = new JLabel(
        "Progreso: 0 / 0"
    );

    lblProgreso.setFont(EstilosUI.TEXTO_NORMAL);

    lblProgreso.setForeground(
        EstilosUI.TEXTO_SECUNDARIO
    );

    JPanel indicadores = new JPanel(
        new BorderLayout()
    );

    indicadores.setOpaque(false);

    indicadores.add(lblEstado, BorderLayout.WEST);
    indicadores.add(lblProgreso, BorderLayout.EAST);

    JPanel botones = new JPanel(
        new FlowLayout(FlowLayout.CENTER, 10, 0)
    );

    botones.setOpaque(false);

    btnPaso = EstilosUI.crearBoton(
    	    "Siguiente símbolo",
    	    EstilosUI.PRIMARIO
    	);

    	btnPaso.setForeground(Color.WHITE);

    	btnEjecutar = EstilosUI.crearBoton(
    	    "Ejecutar todo",
    	    EstilosUI.SECUNDARIO
    	);

    	btnEjecutar.setForeground(Color.WHITE);

    btnReiniciar = EstilosUI.crearBoton(
        "Reiniciar",
        new Color(71, 85, 105)
    );
    btnPaso.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    btnEjecutar.setUI(new javax.swing.plaf.basic.BasicButtonUI());

    btnPaso.setForeground(Color.WHITE);
    btnEjecutar.setForeground(Color.WHITE);

    btnPaso.addActionListener(e -> avanzarPaso());

    btnEjecutar.addActionListener(e -> ejecutarCompleto());

    btnReiniciar.addActionListener(e -> reiniciarSimulacion());

    botones.add(btnPaso);
    botones.add(btnEjecutar);
    botones.add(btnReiniciar);

    controles.add(indicadores, BorderLayout.NORTH);
    controles.add(botones, BorderLayout.CENTER);

    // =====================================
    // ORGANIZAR CONTENIDO
    // =====================================

    JPanel centro = new JPanel(new BorderLayout(0, 15));

    centro.setBackground(EstilosUI.FONDO);

    centro.add(configuracion, BorderLayout.NORTH);
    centro.add(separador, BorderLayout.CENTER);
 // AGREGAR EL CONTENIDO CENTRAL AL PANEL PRINCIPAL
    principal.add(centro, BorderLayout.CENTER);

principal.add(controles, BorderLayout.SOUTH);

// Permitir desplazamiento vertical
JScrollPane scroll = new JScrollPane(principal);

scroll.setBorder(null);

scroll.setBackground(EstilosUI.FONDO);

scroll.getViewport().setBackground(EstilosUI.FONDO);

scroll.setHorizontalScrollBarPolicy(
    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);

scroll.setVerticalScrollBarPolicy(
    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
);

return crearContenedor(scroll);

}

	// =====================================
	// CREAR CONTENEDOR
	//======================================


private JPanel crearContenedor(JScrollPane scroll) {

    JPanel contenedor = new JPanel(new BorderLayout());

    contenedor.add(scroll, BorderLayout.CENTER);

    return contenedor;
}



    // =====================================
    // CARGAR AUTÓMATA
    // =====================================

    private void cargarAutomata() {

        automata = AutomataFinito.crearAutomataTerminaEnUno();

        simulador = new SimuladorAFD(automata);
    }

    // =====================================
    // INICIAR SIMULACIÓN
    // =====================================

    private void iniciarSimulacion() {

        String cadena = txtCadena.getText().trim();

        // Validar que no existan espacios internos
        if (cadena.contains(" ")) {

            JOptionPane.showMessageDialog(
                this,
                "La cadena no debe contener espacios.",
                "Cadena inválida",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        simulador.iniciar(cadena);

        // Si la cadena está vacía, finalizar su evaluación.
        if (cadena.isEmpty()) {
            simulador.avanzar();
        }

        actualizarInterfaz();
    }

    // =====================================
    // AVANZAR UN SÍMBOLO
    // =====================================

    private void avanzarPaso() {

        simulador.avanzar();

        actualizarInterfaz();
    }

    // =====================================
    // EJECUTAR SIMULACIÓN COMPLETA
    // =====================================

    private void ejecutarCompleto() {

        simulador.ejecutarCompleto();

        actualizarInterfaz();
    }

    // =====================================
    // REINICIAR SIMULACIÓN
    // =====================================

    private void reiniciarSimulacion() {

        simulador.reiniciar();

        panelProcesamiento.limpiar();

        actualizarInterfaz();
    }

    // =====================================
    // ACTUALIZAR INTERFAZ
    // =====================================

    private void actualizarInterfaz() {

        Estado actual = simulador.getEstadoActual();

        if (actual != null) {

            panelAutomata.actualizarEstado(
                actual.getNombre()
            );
        }

        panelProcesamiento.actualizarHistorial(simulador);

        int procesados = simulador.getPosicion();

        int total = simulador.getCadena() == null
                ? 0
                : simulador.getCadena().length();

        lblProgreso.setText(
            "Progreso: " + procesados + " / " + total
        );

        if (simulador.tieneError()) {

            lblEstado.setText("Estado: Cadena inválida");
            lblEstado.setForeground(EstilosUI.SECUNDARIO);

        } else if (simulador.getCadena() == null) {

            lblEstado.setText("Estado: Esperando cadena");
            lblEstado.setForeground(EstilosUI.TEXTO_SECUNDARIO);

        } else if (simulador.estaTerminado()) {

            if (simulador.esAceptada()) {

                lblEstado.setText("Resultado: Cadena aceptada");
                lblEstado.setForeground(new Color(34, 197, 94));

            } else {

                lblEstado.setText("Resultado: Cadena rechazada");
                lblEstado.setForeground(EstilosUI.SECUNDARIO);
            }

        } else {

            lblEstado.setText("Estado: Procesando cadena");
            lblEstado.setForeground(EstilosUI.PRIMARIO);
        }

        boolean activo = simulador.getCadena() != null
                && !simulador.estaTerminado()
                && !simulador.tieneError();

        btnPaso.setEnabled(activo);
        btnEjecutar.setEnabled(activo);

        btnPaso.setVisible(true);
        btnEjecutar.setVisible(true);

    }
}
