
package com.chomsky.simulador_chomsky.nivel0;

import java.util.*;

public class MaquinaTuring {

    private final CintaTuring cinta;

    private final Map<String, Map<Character, Transicion>> transiciones;

    private final Set<String> estadosAceptacion;
    private final Set<String> estadosRechazo;

    private final Set<Character> alfabetoEntrada;
    private final Set<Character> alfabetoCinta;

    private final String estadoInicial;
    private final char blanco;

    private String estadoActual;
    private boolean finalizada;
    private boolean aceptada;

    private int contadorTransiciones;

    private final List<Transicion> historial;

    public MaquinaTuring(
            String estadoInicial,
            char blanco,
            Set<Character> alfabetoEntrada,
            Set<Character> alfabetoCinta,
            Set<String> estadosAceptacion,
            Set<String> estadosRechazo) {

        this.estadoInicial = estadoInicial;
        this.blanco = blanco;

        this.alfabetoEntrada = new HashSet<>(alfabetoEntrada);
        this.alfabetoCinta = new HashSet<>(alfabetoCinta);

        this.estadosAceptacion = new HashSet<>(estadosAceptacion);
        this.estadosRechazo = new HashSet<>(estadosRechazo);

        this.cinta = new CintaTuring(blanco);

        this.transiciones = new HashMap<>();
        this.historial = new ArrayList<>();

        this.estadoActual = estadoInicial;
        this.finalizada = false;
        this.aceptada = false;
        this.contadorTransiciones = 0;
    }

    public void agregarTransicion(Transicion transicion) {

        if (!alfabetoCinta.contains(transicion.getSimboloLeido())
                || !alfabetoCinta.contains(transicion.getSimboloEscrito())) {
            throw new IllegalArgumentException("Símbolo fuera del alfabeto de cinta");
        }

        transiciones
            .computeIfAbsent(
                transicion.getEstadoActual(),
                k -> new HashMap<>()
            )
            .put(transicion.getSimboloLeido(), transicion);
    }

    public void iniciar(String entrada) {

        for (char simbolo : entrada.toCharArray()) {
            if (!alfabetoEntrada.contains(simbolo)) {
                throw new IllegalArgumentException(
                    "Símbolo no permitido en la entrada: " + simbolo
                );
            }
        }

        cinta.inicializar(entrada);

        estadoActual = estadoInicial;
        finalizada = false;
        aceptada = false;

        contadorTransiciones = 0;
        historial.clear();

        comprobarEstadoFinal();
    }

    public boolean ejecutarTransicion() {

        if (finalizada) {
            return false;
        }

        char simboloLeido = cinta.leer();

        Map<Character, Transicion> reglas =
                transiciones.get(estadoActual);

        Transicion transicion = reglas == null
                ? null
                : reglas.get(simboloLeido);

        if (transicion == null) {
            finalizada = true;
            aceptada = false;
            return false;
        }

        cinta.escribir(transicion.getSimboloEscrito());

        cinta.mover(transicion.getDireccion());

        estadoActual = transicion.getEstadoSiguiente();

        historial.add(transicion);
        contadorTransiciones++;

        comprobarEstadoFinal();

        return true;
    }

    private void comprobarEstadoFinal() {

        if (estadosAceptacion.contains(estadoActual)) {
            finalizada = true;
            aceptada = true;
        }

        if (estadosRechazo.contains(estadoActual)) {
            finalizada = true;
            aceptada = false;
        }
    }

    public void ejecutarTodo(int limiteTransiciones) {

        int contador = 0;

        while (!finalizada && contador < limiteTransiciones) {
            ejecutarTransicion();
            contador++;
        }

        if (!finalizada) {
            finalizada = true;
            aceptada = false;
        }
    }

    public void reiniciar() {
        cinta.reiniciar();

        estadoActual = estadoInicial;
        finalizada = false;
        aceptada = false;

        contadorTransiciones = 0;
        historial.clear();
    }

    public CintaTuring getCinta() {
        return cinta;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public char getBlanco() {
        return blanco;
    }

    public boolean estaFinalizada() {
        return finalizada;
    }

    public boolean fueAceptada() {
        return aceptada;
    }

    public int getContadorTransiciones() {
        return contadorTransiciones;
    }

    public List<Transicion> getHistorial() {
        return new ArrayList<>(historial);
    }

    public Set<Character> getAlfabetoEntrada() {
        return new TreeSet<>(alfabetoEntrada);
    }

    public Set<Character> getAlfabetoCinta() {
        return new TreeSet<>(alfabetoCinta);
    }

    public Set<String> getEstadosAceptacion() {
        return new HashSet<>(estadosAceptacion);
    }

    public Set<String> getEstadosRechazo() {
        return new HashSet<>(estadosRechazo);
    }

    public String getResultado() {

        if (!finalizada) {
            return "En ejecución";
        }

        return aceptada ? "Aceptada" : "Rechazada";
    }
    public List<Transicion> getReglas() {

        List<Transicion> reglas = new ArrayList<>();

        for (Map<Character, Transicion> mapa : transiciones.values()) {
            reglas.addAll(mapa.values());
        }

        return reglas;
    }
}
