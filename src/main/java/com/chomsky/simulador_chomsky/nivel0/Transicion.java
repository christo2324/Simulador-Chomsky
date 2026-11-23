
package com.chomsky.simulador_chomsky.nivel0;

public class Transicion {

    public static final int IZQUIERDA = -1;
    public static final int QUIETO = 0;
    public static final int DERECHA = 1;

    private final String estadoActual;
    private final char simboloLeido;
    private final char simboloEscrito;
    private final int direccion;
    private final String estadoSiguiente;
    private final String descripcion;

    public Transicion(
            String estadoActual,
            char simboloLeido,
            char simboloEscrito,
            int direccion,
            String estadoSiguiente,
            String descripcion) {

        if (direccion < -1 || direccion > 1) {
            throw new IllegalArgumentException("Dirección inválida");
        }

        this.estadoActual = estadoActual;
        this.simboloLeido = simboloLeido;
        this.simboloEscrito = simboloEscrito;
        this.direccion = direccion;
        this.estadoSiguiente = estadoSiguiente;
        this.descripcion = descripcion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public char getSimboloLeido() {
        return simboloLeido;
    }

    public char getSimboloEscrito() {
        return simboloEscrito;
    }

    public int getDireccion() {
        return direccion;
    }

    public String getEstadoSiguiente() {
        return estadoSiguiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccionTexto() {
        switch (direccion) {
            case IZQUIERDA:
                return "Izquierda";
            case DERECHA:
                return "Derecha";
            default:
                return "Quieto";
        }
    }
}
