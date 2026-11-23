
package com.chomsky.simulador_chomsky.nivel3;

public class Estado {

    private String nombre;
    private boolean inicial;
    private boolean finalEstado;

    public Estado(String nombre, boolean inicial, boolean finalEstado) {
        this.nombre = nombre;
        this.inicial = inicial;
        this.finalEstado = finalEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esInicial() {
        return inicial;
    }

    public boolean esFinal() {
        return finalEstado;
    }
}
