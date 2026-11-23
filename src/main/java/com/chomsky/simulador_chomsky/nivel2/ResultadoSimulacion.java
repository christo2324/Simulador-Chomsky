package com.chomsky.simulador_chomsky.nivel2;

public class ResultadoSimulacion {

    private boolean aceptada;
    private boolean finalizada;
    private String mensaje;

    public ResultadoSimulacion(
            boolean aceptada,
            boolean finalizada,
            String mensaje) {

        this.aceptada = aceptada;
        this.finalizada = finalizada;
        this.mensaje = mensaje;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public String getMensaje() {
        return mensaje;
    }
}