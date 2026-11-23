
package com.chomsky.simulador_chomsky.nivel3;

import java.util.*;

public class AutomataFinito {

    private List<Estado> estados;
    private Set<Character> alfabeto;
    private List<Transicion> transiciones;

    private Estado estadoInicial;

    public AutomataFinito() {
        estados = new ArrayList<>();
        alfabeto = new LinkedHashSet<>();
        transiciones = new ArrayList<>();
    }

    public void agregarEstado(Estado estado) {

        estados.add(estado);

        if (estado.esInicial()) {
            estadoInicial = estado;
        }
    }

    public void agregarSimbolo(char simbolo) {
        alfabeto.add(simbolo);
    }

    public void agregarTransicion(
            Estado origen,
            char simbolo,
            Estado destino) {

        if (!estados.contains(origen) || !estados.contains(destino)) {
            throw new IllegalArgumentException("Estado no registrado.");
        }

        if (!alfabeto.contains(simbolo)) {
            throw new IllegalArgumentException("Símbolo fuera del alfabeto.");
        }

        for (Transicion t : transiciones) {
            if (t.getOrigen() == origen &&
                t.getSimbolo() == simbolo) {

                throw new IllegalArgumentException(
                    "Ya existe una transición para ese símbolo."
                );
            }
        }

        transiciones.add(new Transicion(origen, simbolo, destino));
    }

    public Estado obtenerDestino(Estado origen, char simbolo) {

        for (Transicion t : transiciones) {

            if (t.getOrigen() == origen &&
                t.getSimbolo() == simbolo) {

                return t.getDestino();
            }
        }

        return null;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public List<Estado> getEstados() {
        return Collections.unmodifiableList(estados);
    }

    public Set<Character> getAlfabeto() {
        return Collections.unmodifiableSet(alfabeto);
    }

    public List<Transicion> getTransiciones() {
        return Collections.unmodifiableList(transiciones);
    }

    public Estado buscarEstado(String nombre) {

        for (Estado estado : estados) {
            if (estado.getNombre().equals(nombre)) {
                return estado;
            }
        }

        return null;
    }

    // AFD predeterminado:
    // Reconoce cadenas binarias que terminan en 1.

    public static AutomataFinito crearAutomataTerminaEnUno() {

        AutomataFinito automata = new AutomataFinito();

        Estado q0 = new Estado("q0", true, false);
        Estado q1 = new Estado("q1", false, true);

        automata.agregarEstado(q0);
        automata.agregarEstado(q1);

        automata.agregarSimbolo('0');
        automata.agregarSimbolo('1');

        automata.agregarTransicion(q0, '0', q0);
        automata.agregarTransicion(q0, '1', q1);

        automata.agregarTransicion(q1, '0', q0);
        automata.agregarTransicion(q1, '1', q1);

        return automata;
    }
}
