
package com.chomsky.simulador_chomsky.nivel1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import com.chomsky.simulador_chomsky.util.EstilosUI;

public class VentanaMaquinaLineal extends JFrame {

    private MaquinaLineal maquina;

    private PanelCinta panelCinta;

    private PanelControles panelControles;

    private PanelHistorial panelHistorial;

    private Timer temporizador;

    public VentanaMaquinaLineal() {

        maquina = new MaquinaLineal();

        configurarVentana();

        construirInterfaz();

        configurarEventos();

        temporizador = new Timer(
                700,
                e -> ejecutarTransicion()
        );
    }

    // =====================================
    // CONFIGURACIÓN
    // =====================================

    private void configurarVentana() {

        setTitle("Nivel 1 - Máquina Linealmente Acotada");

        setSize(1050, 720);

        setMinimumSize(new Dimension(850, 620));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(EstilosUI.FONDO);
    }

    // =====================================
    // CONSTRUIR INTERFAZ
    // =====================================

    private void construirInterfaz() {

        JPanel contenido = new JPanel(new BorderLayout(0, 15));

        contenido.setBackground(EstilosUI.FONDO);

        contenido.setBorder(
                BorderFactory.createEmptyBorder(20, 22, 20, 22)
        );

        // Encabezado.

        JPanel encabezado = new JPanel(
                new BorderLayout(5, 5)
        );

        encabezado.setBackground(EstilosUI.FONDO);

        JLabel titulo = EstilosUI.crearTitulo(
                "Máquina Linealmente Acotada"
        );

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 26)
        );

        JLabel subtitulo = EstilosUI.crearSubtitulo(
                "Nivel 1 · Reconocimiento del lenguaje aⁿbⁿcⁿ"
        );

        encabezado.add(titulo, BorderLayout.NORTH);

        encabezado.add(subtitulo, BorderLayout.CENTER);

        // Panel de controles.

        panelControles = new PanelControles();

        // Tarjeta de cinta.

        JPanel tarjetaCinta = EstilosUI.crearTarjeta();

        tarjetaCinta.setLayout(new BorderLayout(0, 12));

        tarjetaCinta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(41, 51, 73)
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 18, 15, 18
                        )
                )
        );

        JLabel tituloCinta = new JLabel("Cinta de trabajo");

        tituloCinta.setFont(EstilosUI.TITULO_TARJETA);

        tituloCinta.setForeground(EstilosUI.TEXTO);

        panelCinta = new PanelCinta();

        JScrollPane scrollCinta = new JScrollPane(panelCinta);

        scrollCinta.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollCinta.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_NEVER
        );

        scrollCinta.setBorder(null);
        scrollCinta.getViewport().setBackground(EstilosUI.TARJETA);

        scrollCinta.setPreferredSize(new Dimension(850, 125));

        tarjetaCinta.add(tituloCinta, BorderLayout.NORTH);

        tarjetaCinta.add(
                scrollCinta,
                BorderLayout.CENTER
        );

        // Tarjeta de historial.

        JPanel tarjetaHistorial = EstilosUI.crearTarjeta();

        tarjetaHistorial.setLayout(
                new BorderLayout(0, 8)
        );

        tarjetaHistorial.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(41, 51, 73)
                        ),
                        BorderFactory.createEmptyBorder(
                                12, 18, 12, 18
                        )
                )
        );

        JLabel tituloHistorial = new JLabel(
                "Historial de transiciones"
        );

        tituloHistorial.setFont(EstilosUI.TITULO_TARJETA);

        tituloHistorial.setForeground(EstilosUI.TEXTO);

        panelHistorial = new PanelHistorial();

        tarjetaHistorial.add(
                tituloHistorial,
                BorderLayout.NORTH
        );

        tarjetaHistorial.add(
                panelHistorial,
                BorderLayout.CENTER
        );

        tarjetaHistorial.setPreferredSize(
                new Dimension(900, 205)
        );

        // Zona central.

        JPanel zonaCentral = new JPanel(
                new BorderLayout(0, 12)
        );

        zonaCentral.setBackground(EstilosUI.FONDO);

        zonaCentral.add(
                panelControles,
                BorderLayout.NORTH
        );

        zonaCentral.add(
                tarjetaCinta,
                BorderLayout.CENTER
        );

        zonaCentral.add(
                tarjetaHistorial,
                BorderLayout.SOUTH
        );

        contenido.add(encabezado, BorderLayout.NORTH);

        contenido.add(zonaCentral, BorderLayout.CENTER);

        setContentPane(contenido);
    }

    // =====================================
    // EVENTOS
    // =====================================

    private void configurarEventos() {

        panelControles.getBotonIniciar().addActionListener(
                e -> iniciarSimulacion()
        );

        panelControles.getBotonSiguiente().addActionListener(
                e -> ejecutarTransicion()
        );

        panelControles.getBotonAutomatico().addActionListener(
                e -> controlarAutomatico()
        );

        panelControles.getBotonReiniciar().addActionListener(
                e -> reiniciarSimulacion()
        );

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                if (temporizador != null) {
                    temporizador.stop();
                }
            }
        });
    }

    // =====================================
    // INICIAR SIMULACIÓN
    // =====================================

    private void iniciarSimulacion() {

        detenerAutomatico();

        panelHistorial.limpiar();

        String entrada = panelControles.getEntrada();

        boolean iniciada = maquina.iniciar(entrada);

        if (!iniciada) {

            panelCinta.limpiar();

            panelControles.actualizarEstado(
                    "Rechazada",
                    maquina.getMensaje()
            );

            panelControles.actualizarBotones(false);

            return;
        }

        actualizarInterfaz();

        panelControles.actualizarBotones(true);
    }

    // =====================================
    // EJECUTAR TRANSICIÓN
    // =====================================

    private void ejecutarTransicion() {

        if (!maquina.estaEjecutando()) {

            detenerAutomatico();

            return;
        }

        MaquinaLineal.RegistroTransicion registro =
                maquina.ejecutarPaso();

        if (registro != null) {

            panelHistorial.agregarTransicion(registro);
        }

        actualizarInterfaz();

        if (maquina.haTerminado()) {

            detenerAutomatico();

            panelControles.actualizarBotones(false);
        }
    }

    // =====================================
    // EJECUCIÓN AUTOMÁTICA
    // =====================================

    private void controlarAutomatico() {

        if (!maquina.estaEjecutando()) {
            return;
        }

        if (temporizador.isRunning()) {

            detenerAutomatico();

        } else {

            temporizador.start();

            panelControles.cambiarTextoAutomatico(true);
        }
    }

    private void detenerAutomatico() {

        if (temporizador != null) {
            temporizador.stop();
        }

        if (panelControles != null) {
            panelControles.cambiarTextoAutomatico(false);
        }
    }

    // =====================================
    // REINICIAR
    // =====================================

    private void reiniciarSimulacion() {

        detenerAutomatico();

        maquina.reiniciar();

        panelCinta.limpiar();

        panelHistorial.limpiar();

        panelControles.reiniciarControles();
    }

    // =====================================
    // ACTUALIZAR INTERFAZ
    // =====================================

    private void actualizarInterfaz() {

        panelCinta.actualizarCinta(
                maquina.getCinta(),
                maquina.getPosicionCabezal()
        );

        String estado;

        switch (maquina.getResultado()) {

            case EJECUTANDO:

                estado = "Ejecutando (" +
                        maquina.getEstadoActual() + ")";

                break;

            case ACEPTADA:

                estado = "Cadena aceptada";

                break;

            case RECHAZADA:

                estado = "Cadena rechazada";

                break;

            default:

                estado = "Sin iniciar";

                break;
        }

        panelControles.actualizarEstado(
                estado,
                maquina.getMensaje()
        );
    }
}
