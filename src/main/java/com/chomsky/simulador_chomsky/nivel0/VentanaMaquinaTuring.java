
package com.chomsky.simulador_chomsky.nivel0;

import java.awt.*;
import javax.swing.*;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class VentanaMaquinaTuring extends JFrame {

    private PanelConfiguracion panelConfiguracion;
    private PanelCinta panelCinta;
    private PanelInformacion panelInformacion;
    private PanelHistorial panelHistorial;

    private JButton btnIniciar;
    private JButton btnSiguiente;
    private JButton btnAutomatico;
    private JButton btnReiniciar;

    private MaquinaTuring maquina;
    private Timer temporizador;

    private int limiteTransiciones = 10000;

    public VentanaMaquinaTuring() {

        setTitle("Nivel 0 - Máquina de Turing");
        setSize(1050, 720);
        setMinimumSize(new Dimension(850, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(12, 12));

        getContentPane().setBackground(EstilosUI.FONDO);

        crearInterfaz();
        configurarEventos();

        temporizador = new Timer(350, e -> ejecutarPaso());

        actualizarControles(false);
    }

    private void crearInterfaz() {

        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBackground(EstilosUI.FONDO);
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = EstilosUI.crearTitulo("Máquina de Turing");

        JLabel subtitulo = EstilosUI.crearSubtitulo(
                "Nivel 0 - Jerarquía de Chomsky"
        );

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 3));
        encabezado.setOpaque(false);

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        principal.add(encabezado, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(10, 10));
        contenido.setOpaque(false);

        panelConfiguracion = new PanelConfiguracion();

        contenido.add(panelConfiguracion, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setOpaque(false);

        panelCinta = new PanelCinta();
        centro.add(panelCinta, BorderLayout.CENTER);

        panelInformacion = new PanelInformacion();
        centro.add(panelInformacion, BorderLayout.SOUTH);

        contenido.add(centro, BorderLayout.CENTER);

        panelHistorial = new PanelHistorial();
        contenido.add(panelHistorial, BorderLayout.SOUTH);

        JPanel controles = crearPanelControles();

        JPanel cuerpo = new JPanel(new BorderLayout(10, 10));
        cuerpo.setOpaque(false);

        cuerpo.add(contenido, BorderLayout.CENTER);
        cuerpo.add(controles, BorderLayout.SOUTH);

        principal.add(cuerpo, BorderLayout.CENTER);

        add(principal, BorderLayout.CENTER);
    }

    private JPanel crearPanelControles() {

        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5)
        );

        panel.setBackground(EstilosUI.FONDO);

        btnIniciar = EstilosUI.crearBoton(
                "Iniciar simulación",
                EstilosUI.PRIMARIO
        );

        btnSiguiente = EstilosUI.crearBoton(
                "Siguiente transición",
                new Color(71, 85, 105)
        );

        btnAutomatico = EstilosUI.crearBoton(
                "Ejecutar automáticamente",
                EstilosUI.SECUNDARIO
        );

        btnReiniciar = EstilosUI.crearBoton(
                "Reiniciar",
                new Color(71, 85, 105)
        );

        panel.add(btnIniciar);
        panel.add(btnSiguiente);
        panel.add(btnAutomatico);
        panel.add(btnReiniciar);

        return panel;
    }

    private void configurarEventos() {

        btnIniciar.addActionListener(e -> iniciarSimulacion());

        btnSiguiente.addActionListener(e -> ejecutarPaso());

        btnAutomatico.addActionListener(e -> ejecutarAutomaticamente());

        btnReiniciar.addActionListener(e -> reiniciarSimulacion());
    }

    private void iniciarSimulacion() {

        detenerTemporizador();

        String entrada = panelConfiguracion.getEntrada();

        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Introduce una cadena para iniciar.",
                    "Entrada vacía",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            if (panelConfiguracion.getModalidad() == 0) {
                maquina = MaquinasTuring.crearReconocedora();
            } else {
                maquina = MaquinasTuring.crearSumadora();

                if (!entrada.matches("1*\\+1*")
                        || !entrada.matches("1+\\+1+")) {
                    throw new IllegalArgumentException(
                            "Introduce dos números naturales positivos en unario. Ejemplo: 111+11"
                    );
                }
            }

            maquina.iniciar(entrada);

            panelHistorial.limpiar();

            actualizarInterfaz();

            actualizarControles(true);

            if (maquina.estaFinalizada()) {
                mostrarResultado();
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Entrada inválida",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void ejecutarPaso() {

        if (maquina == null || maquina.estaFinalizada()) {
            detenerTemporizador();
            return;
        }

        String estadoAnterior = maquina.getEstadoActual();

        Transicion transicion = obtenerTransicionActual();

        boolean ejecutada = maquina.ejecutarTransicion();

        if (ejecutada && transicion != null) {

            panelHistorial.agregarTransicion(
                    maquina.getContadorTransiciones(),
                    estadoAnterior,
                    transicion
            );
        }

        actualizarInterfaz();

        if (maquina.estaFinalizada()) {

            detenerTemporizador();

            actualizarControles(true);

            mostrarResultado();
        }
    }

    private Transicion obtenerTransicionActual() {

        if (maquina == null) {
            return null;
        }

        String estado = maquina.getEstadoActual();
        char simbolo = maquina.getCinta().leer();

        for (Transicion transicion : maquina.getReglas()) {
            if (transicion.getEstadoActual().equals(estado)
                    && transicion.getSimboloLeido() == simbolo) {
                return transicion;
            }
        }

        return null;
    }

    private void ejecutarAutomaticamente() {

        if (maquina == null || maquina.estaFinalizada()) {
            return;
        }

        if (temporizador.isRunning()) {
            detenerTemporizador();
            return;
        }

        temporizador.start();
        btnAutomatico.setText("Pausar");
    }

    private void detenerTemporizador() {

        if (temporizador != null) {
            temporizador.stop();
        }

        if (btnAutomatico != null) {
            btnAutomatico.setText("Ejecutar automáticamente");
        }
    }

    private void reiniciarSimulacion() {

        detenerTemporizador();

        if (maquina == null) {
            return;
        }

        iniciarSimulacion();
    }

    private void actualizarInterfaz() {

        if (maquina == null) {
            return;
        }

        panelCinta.actualizarCinta(maquina.getCinta());

        panelInformacion.actualizar(maquina);
    }

    private void actualizarControles(boolean iniciado) {

        btnSiguiente.setEnabled(iniciado);
        btnAutomatico.setEnabled(iniciado);
        btnReiniciar.setEnabled(iniciado);
    }

    private void mostrarResultado() {

        String resultado = maquina.getResultado();

        JOptionPane.showMessageDialog(
                this,
                resultado.equals("Aceptada")
                        ? "La cadena fue aceptada."
                        : resultado.equals("Rechazada")
                            ? "La máquina rechazó la entrada o quedó bloqueada."
                            : "La ejecución terminó.",
                "Resultado de simulación",
                resultado.equals("Aceptada")
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.WARNING_MESSAGE
        );
    }

    @Override
    public void dispose() {
        detenerTemporizador();
        super.dispose();
    }
}
