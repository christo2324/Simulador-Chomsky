
package com.chomsky.simulador_chomsky.nivel3;

public class Transicion {

    private Estado origen;
    private Estado destino;
    private char simbolo;

    public Transicion(Estado origen, char simbolo, Estado destino) {
        this.origen = origen;
        this.simbolo = simbolo;
        this.destino = destino;
    }

    public Estado getOrigen() {
        return origen;
    }

    public Estado getDestino() {
        return destino;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
