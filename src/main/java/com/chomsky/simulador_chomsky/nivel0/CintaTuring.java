
package com.chomsky.simulador_chomsky.nivel0;

import java.util.Map;
import java.util.TreeMap;

public class CintaTuring {

    private final Map<Integer, Character> celdas;
    private final char blanco;
    private int posicionCabezal;

    public CintaTuring(char blanco) {
        this.celdas = new TreeMap<>();
        this.blanco = blanco;
        this.posicionCabezal = 0;
    }

    public void inicializar(String entrada) {
        celdas.clear();
        posicionCabezal = 0;

        for (int i = 0; i < entrada.length(); i++) {
            celdas.put(i, entrada.charAt(i));
        }
    }

    public char leer() {
        return celdas.getOrDefault(posicionCabezal, blanco);
    }

    public void escribir(char simbolo) {
        if (simbolo == blanco) {
            celdas.remove(posicionCabezal);
        } else {
            celdas.put(posicionCabezal, simbolo);
        }
    }

    public void moverIzquierda() {
        posicionCabezal--;
    }

    public void moverDerecha() {
        posicionCabezal++;
    }

    public void mover(int direccion) {
        posicionCabezal += direccion;
    }

    public int getPosicionCabezal() {
        return posicionCabezal;
    }

    public char getBlanco() {
        return blanco;
    }

    public char leer(int posicion) {
        return celdas.getOrDefault(posicion, blanco);
    }

    public Map<Integer, Character> getCeldas() {
        return new TreeMap<>(celdas);
    }

    public int getPosicionMinima() {
        return Math.min(0, celdas.isEmpty() ? 0 : celdas.keySet().stream()
                .min(Integer::compareTo).orElse(0));
    }

    public int getPosicionMaxima() {
        return Math.max(posicionCabezal + 10,
                celdas.isEmpty() ? 10 :
                celdas.keySet().stream().max(Integer::compareTo).orElse(0) + 5);
    }

    public String obtenerContenido() {
        if (celdas.isEmpty()) {
            return String.valueOf(blanco);
        }

        int minimo = celdas.keySet().stream().min(Integer::compareTo).orElse(0);
        int maximo = celdas.keySet().stream().max(Integer::compareTo).orElse(0);

        StringBuilder contenido = new StringBuilder();

        for (int i = minimo; i <= maximo; i++) {
            contenido.append(leer(i));
        }

        return contenido.toString();
    }

    public void reiniciar() {
        celdas.clear();
        posicionCabezal = 0;
    }
}
