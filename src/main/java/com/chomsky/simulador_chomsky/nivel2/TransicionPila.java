package com.chomsky.simulador_chomsky.nivel2;

public class TransicionPila {

    private String estadoAnterior;
    private String simboloLeido;
    private String topeAnterior;
    private String operacion;
    private String estadoNuevo;
    private String descripcion;

    public TransicionPila(
            String estadoAnterior,
            String simboloLeido,
            String topeAnterior,
            String operacion,
            String estadoNuevo,
            String descripcion) {

        this.estadoAnterior = estadoAnterior;
        this.simboloLeido = simboloLeido;
        this.topeAnterior = topeAnterior;
        this.operacion = operacion;
        this.estadoNuevo = estadoNuevo;
        this.descripcion = descripcion;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public String getSimboloLeido() {
        return simboloLeido;
    }

    public String getTopeAnterior() {
        return topeAnterior;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return estadoAnterior + " | "
                + simboloLeido + " | "
                + topeAnterior + " | "
                + operacion + " | "
                + estadoNuevo;
    }
}