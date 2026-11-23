package com.chomsky.simulador_chomsky.nivel2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Pila {

    private Deque<String> elementos;

    public Pila() {
        elementos = new ArrayDeque<>();
    }

    // Inserta un elemento en la parte superior de la pila.
    public void apilar(String elemento) {
        elementos.push(elemento);
    }

    // Elimina y devuelve el elemento superior.
    public String desapilar() {
        if (!elementos.isEmpty()) {
            return elementos.pop();
        }

        return null;
    }

    // Consulta el elemento superior sin eliminarlo.
    public String consultarTope() {
        return elementos.peek();
    }

    // Comprueba si la pila está vacía.
    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    // Devuelve la cantidad de elementos.
    public int tamanio() {
        return elementos.size();
    }

    // Elimina todos los elementos.
    public void limpiar() {
        elementos.clear();
    }

    // Devuelve los elementos desde el tope hacia abajo.
    public List<String> obtenerElementos() {
        return new ArrayList<>(elementos);
    }
}