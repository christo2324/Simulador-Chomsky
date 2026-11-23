
package com.chomsky.simulador_chomsky.nivel3;

import java.util.ArrayList;
import java.util.List;

public class SimuladorAFD {

    private AutomataFinito automata;
    private Estado estadoActual;

    private String cadena;
    private int posicion;

    private boolean terminado;
    private boolean error;

    private List<String> historial;

    public SimuladorAFD(AutomataFinito automata) {
        this.automata = automata;
        historial = new ArrayList<>();
    }


public void iniciar(String cadena) {

    this.cadena = cadena == null ? "" : cadena;

    this.posicion = 0;

    this.estadoActual = automata.getEstadoInicial();

    this.terminado = false;
    this.error = false;

    historial.clear();

    historial.add("Inicio → " + estadoActual.getNombre());

    // Una cadena vacía termina inmediatamente.
    if (this.cadena.isEmpty()) {
        terminado = true;
    }
}


    public boolean avanzar() {

        if (terminado || error || estadoActual == null) {
            return false;
        }

        if (posicion >= cadena.length()) {
            terminado = true;
            return false;
        }

        char simbolo = cadena.charAt(posicion);

        if (!automata.getAlfabeto().contains(simbolo)) {
            error = true;
            historial.add(
                "Error: símbolo '" + simbolo +
                "' no pertenece al alfabeto."
            );
            return false;
        }

        Estado anterior = estadoActual;

        estadoActual = automata.obtenerDestino(
            estadoActual, simbolo
        );

        if (estadoActual == null) {
            error = true;
            return false;
        }

        posicion++;

        historial.add(
            "Símbolo: " + simbolo +
            " | " + anterior.getNombre() +
            " → " + estadoActual.getNombre()
        );

        if (posicion == cadena.length()) {
            terminado = true;
        }

        return true;
    }

    public void ejecutarCompleto() {

        while (!terminado && !error) {
            avanzar();
        }
    }


public boolean esAceptada() {

    return cadena != null &&
           terminado &&
           !error &&
           posicion == cadena.length() &&
           estadoActual != null &&
           estadoActual.esFinal();
}


    public boolean estaTerminado() {
        return terminado;
    }

    public boolean tieneError() {
        return error;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getCadena() {
        return cadena;
    }

    public List<String> getHistorial() {
        return new ArrayList<>(historial);
    }



public void reiniciar() {

    cadena = null;
    posicion = 0;

    estadoActual = automata.getEstadoInicial();

    terminado = false;
    error = false;

    historial.clear();

    historial.add("Simulación reiniciada.");
}


}
