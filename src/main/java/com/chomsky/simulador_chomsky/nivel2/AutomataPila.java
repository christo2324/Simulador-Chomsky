package com.chomsky.simulador_chomsky.nivel2;

import java.util.ArrayList;
import java.util.List;

public class AutomataPila {

    private String cadena;
    private int posicion;
    private String estado;

    private Pila pila;

    private List<TransicionPila> historial;

    private boolean finalizada;
    private boolean aceptada;

    public AutomataPila() {
        pila = new Pila();
        historial = new ArrayList<>();
        estado = "q0";
    }

    // Inicializa una nueva simulación.
    public void iniciar(String cadena) {

        this.cadena = cadena;

        posicion = 0;
        estado = "q0";

        pila.limpiar();
        pila.apilar("Z0");

        historial.clear();

        finalizada = false;
        aceptada = false;
    }

    // Ejecuta un único paso del autómata.
    public ResultadoSimulacion siguientePaso() {

        if (finalizada) {
            return obtenerResultado("La simulación ya terminó.");
        }

        if (cadena == null || cadena.isEmpty()) {
            rechazar("La cadena no puede estar vacía.");
            return obtenerResultado("Cadena vacía.");
        }

        if (posicion >= cadena.length()) {
            finalizarSimulacion();
            return obtenerResultado("Se terminó de procesar la cadena.");
        }

        String simbolo = String.valueOf(cadena.charAt(posicion));
        String tope = pila.consultarTope();

        String estadoAnterior = estado;
        String operacion;
        String estadoNuevo = estado;

        // Estado inicial: se procesan las letras a.
        if (estado.equals("q0")) {

            if (simbolo.equals("a")) {

                pila.apilar("A");

                operacion = "Apilar A";

                estadoNuevo = "q0";

            } else if (simbolo.equals("b") && "A".equals(tope)) {

                pila.desapilar();

                operacion = "Desapilar A";

                estadoNuevo = "q1";

            } else {

                rechazar("No se esperaba el símbolo '" + simbolo
                        + "' en el estado q0.");

                return obtenerResultado("Cadena rechazada.");
            }

        }

        // Estado q1: solamente se permiten letras b.
        else if (estado.equals("q1")) {

            if (simbolo.equals("b") && "A".equals(tope)) {

                pila.desapilar();

                operacion = "Desapilar A";

                estadoNuevo = "q1";

            } else {

                rechazar("Transición inválida: se esperaba una b "
                        + "y un símbolo A en la pila.");

                return obtenerResultado("Cadena rechazada.");
            }

        } else {

            rechazar("Estado no válido.");

            return obtenerResultado("Cadena rechazada.");
        }

        // Actualiza el estado.
        estado = estadoNuevo;

        // Registra la transición.
        historial.add(new TransicionPila(
                estadoAnterior,
                simbolo,
                tope,
                operacion,
                estadoNuevo,
                obtenerDescripcion(operacion)
        ));

        // Avanza en la cadena.
        posicion++;

        // Comprueba si ya terminó la cadena.
        if (posicion >= cadena.length()) {
            finalizarSimulacion();
        }

        return obtenerResultado("Paso ejecutado.");
    }

    // Finaliza la simulación y comprueba la aceptación.
    private void finalizarSimulacion() {

        if (estado.equals("q1")
                && pila.tamanio() == 1
                && "Z0".equals(pila.consultarTope())) {

            String estadoAnterior = estado;

            estado = "qf";

            aceptada = true;

            historial.add(new TransicionPila(
                    estadoAnterior,
                    "ε",
                    "Z0",
                    "Aceptar",
                    "qf",
                    "La cadena fue aceptada. La pila quedó en su símbolo inicial."
            ));

        } else {

            aceptada = false;

            historial.add(new TransicionPila(
                    estado,
                    "ε",
                    pila.consultarTope(),
                    "Rechazar",
                    estado,
                    "La cadena terminó, pero no se alcanzó el estado final "
                            + "o la pila no quedó en Z0."
            ));
        }

        finalizada = true;
    }

    // Registra una transición de rechazo.
    private void rechazar(String mensaje) {

        String simbolo = cadena != null && posicion < cadena.length()
                ? String.valueOf(cadena.charAt(posicion))
                : "ε";

        String tope = pila.consultarTope();

        historial.add(new TransicionPila(
                estado,
                simbolo,
                tope,
                "Rechazar",
                estado,
                mensaje
        ));

        aceptada = false;
        finalizada = true;
    }

    // Obtiene una descripción de la operación realizada.
    private String obtenerDescripcion(String operacion) {

        if (operacion.equals("Apilar A")) {
            return "Se leyó una 'a' y se agregó A a la pila.";
        }

        if (operacion.equals("Desapilar A")) {
            return "Se leyó una 'b' y se eliminó A de la pila.";
        }

        return "Operación del autómata.";
    }

    private ResultadoSimulacion obtenerResultado(String mensaje) {

        return new ResultadoSimulacion(
                aceptada,
                finalizada,
                mensaje
        );
    }

    // Reinicia completamente el autómata.
    public void reiniciar() {

        cadena = "";
        posicion = 0;
        estado = "q0";

        pila.limpiar();

        historial.clear();

        finalizada = false;
        aceptada = false;
    }

    public String getCadena() {
        return cadena;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getEstado() {
        return estado;
    }

    public Pila getPila() {
        return pila;
    }

    public List<TransicionPila> getHistorial() {
        return historial;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean isAceptada() {
        return aceptada;
    }
}