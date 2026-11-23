
package com.chomsky.simulador_chomsky.nivel1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaquinaLineal {

    public static final char MARCA_A = 'X';
    public static final char MARCA_B = 'Y';
    public static final char MARCA_C = 'Z';

    public static final char LIMITE_IZQUIERDO = '⊢';
    public static final char LIMITE_DERECHO = '⊣';
    public static final char BLANCO = '□';

    private static final String Q_INICIO = "qInicio";
    private static final String Q_BUSCAR_A = "qBuscarA";
    private static final String Q_BUSCAR_B = "qBuscarB";
    private static final String Q_BUSCAR_C = "qBuscarC";
    private static final String Q_REGRESAR = "qRegresar";
    private static final String Q_VERIFICAR = "qVerificar";
    private static final String Q_ACEPTACION = "qAceptacion";
    private static final String Q_RECHAZO = "qRechazo";

    public enum Direccion {
        IZQUIERDA,
        DERECHA,
        QUIETO
    }

    public enum Resultado {
        SIN_INICIAR,
        EJECUTANDO,
        ACEPTADA,
        RECHAZADA
    }

    private static class Transicion {

        private final String estadoSiguiente;
        private final char simboloEscrito;
        private final Direccion direccion;
        private final String descripcion;

        public Transicion(
                String estadoSiguiente,
                char simboloEscrito,
                Direccion direccion,
                String descripcion) {

            this.estadoSiguiente = estadoSiguiente;
            this.simboloEscrito = simboloEscrito;
            this.direccion = direccion;
            this.descripcion = descripcion;
        }
    }

    public static class RegistroTransicion {

        private final int numero;
        private final String estadoAnterior;
        private final char simboloLeido;
        private final char simboloEscrito;
        private final Direccion direccion;
        private final String estadoNuevo;
        private final String descripcion;

        public RegistroTransicion(
                int numero,
                String estadoAnterior,
                char simboloLeido,
                char simboloEscrito,
                Direccion direccion,
                String estadoNuevo,
                String descripcion) {

            this.numero = numero;
            this.estadoAnterior = estadoAnterior;
            this.simboloLeido = simboloLeido;
            this.simboloEscrito = simboloEscrito;
            this.direccion = direccion;
            this.estadoNuevo = estadoNuevo;
            this.descripcion = descripcion;
        }

        public int getNumero() {
            return numero;
        }

        public String getEstadoAnterior() {
            return estadoAnterior;
        }

        public char getSimboloLeido() {
            return simboloLeido;
        }

        public char getSimboloEscrito() {
            return simboloEscrito;
        }

        public Direccion getDireccion() {
            return direccion;
        }

        public String getEstadoNuevo() {
            return estadoNuevo;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private final Map<String, Transicion> transiciones;
    private final List<RegistroTransicion> historial;

    private char[] cinta;
    private int posicionCabezal;
    private String estadoActual;
    private Resultado resultado;
    private String mensaje;
    private int contadorTransiciones;

    public MaquinaLineal() {

        transiciones = new HashMap<>();
        historial = new ArrayList<>();

        resultado = Resultado.SIN_INICIAR;

        construirTransiciones();
    }

    // =====================================
    // TABLA DE TRANSICIONES
    // =====================================

    private void construirTransiciones() {

        // Estado inicial: marcar la primera a.

        agregarTransicion(
                Q_INICIO, 'a',
                Q_BUSCAR_B, MARCA_A,
                Direccion.DERECHA,
                "Se marca la primera a con X."
        );

        // Buscar a sin procesar.

        agregarTransicion(
                Q_BUSCAR_A, MARCA_A,
                Q_BUSCAR_A, MARCA_A,
                Direccion.DERECHA,
                "Se avanza sobre una a procesada."
        );

        agregarTransicion(
                Q_BUSCAR_A, 'a',
                Q_BUSCAR_B, MARCA_A,
                Direccion.DERECHA,
                "Se encuentra y marca una nueva a."
        );

        agregarTransicion(
                Q_BUSCAR_A, MARCA_B,
                Q_VERIFICAR, MARCA_B,
                Direccion.DERECHA,
                "Todas las a fueron procesadas. Se verifica el resto."
        );

        agregarTransicion(
                Q_BUSCAR_A, MARCA_C,
                Q_VERIFICAR, MARCA_C,
                Direccion.DERECHA,
                "Se inicia la verificación final."
        );

        agregarTransicion(
                Q_BUSCAR_A, LIMITE_DERECHO,
                Q_ACEPTACION, LIMITE_DERECHO,
                Direccion.QUIETO,
                "Todos los símbolos fueron procesados correctamente."
        );

        // Buscar b sin procesar.

        agregarTransicion(
                Q_BUSCAR_B, 'a',
                Q_BUSCAR_B, 'a',
                Direccion.DERECHA,
                "Se avanza sobre una a."
        );

        agregarTransicion(
                Q_BUSCAR_B, MARCA_B,
                Q_BUSCAR_B, MARCA_B,
                Direccion.DERECHA,
                "Se salta una b procesada."
        );

        agregarTransicion(
                Q_BUSCAR_B, 'b',
                Q_BUSCAR_C, MARCA_B,
                Direccion.DERECHA,
                "Se encuentra y marca una b con Y."
        );

        // Buscar c sin procesar.

        agregarTransicion(
                Q_BUSCAR_C, 'b',
                Q_BUSCAR_C, 'b',
                Direccion.DERECHA,
                "Se avanza sobre una b."
        );

        agregarTransicion(
                Q_BUSCAR_C, MARCA_C,
                Q_BUSCAR_C, MARCA_C,
                Direccion.DERECHA,
                "Se salta una c procesada."
        );

        agregarTransicion(
                Q_BUSCAR_C, 'c',
                Q_REGRESAR, MARCA_C,
                Direccion.IZQUIERDA,
                "Se encuentra y marca una c con Z."
        );

        // Regresar al inicio.

        for (char simbolo : new char[]{
                'a', 'b', 'c',
                MARCA_A, MARCA_B, MARCA_C
        }) {

            agregarTransicion(
                    Q_REGRESAR, simbolo,
                    Q_REGRESAR, simbolo,
                    Direccion.IZQUIERDA,
                    "El cabezal regresa hacia el inicio."
            );
        }

        agregarTransicion(
                Q_REGRESAR, LIMITE_IZQUIERDO,
                Q_BUSCAR_A, LIMITE_IZQUIERDO,
                Direccion.DERECHA,
                "Se alcanza la frontera izquierda. Nueva iteración."
        );

        // Verificar que no queden símbolos sin procesar.

        agregarTransicion(
                Q_VERIFICAR, MARCA_B,
                Q_VERIFICAR, MARCA_B,
                Direccion.DERECHA,
                "Se verifica una b procesada."
        );

        agregarTransicion(
                Q_VERIFICAR, MARCA_C,
                Q_VERIFICAR, MARCA_C,
                Direccion.DERECHA,
                "Se verifica una c procesada."
        );

        agregarTransicion(
                Q_VERIFICAR, LIMITE_DERECHO,
                Q_ACEPTACION, LIMITE_DERECHO,
                Direccion.QUIETO,
                "Todos los símbolos fueron procesados."
        );
    }

    private void agregarTransicion(
            String estado,
            char simboloLeido,
            String estadoSiguiente,
            char simboloEscrito,
            Direccion direccion,
            String descripcion) {

        String clave = crearClave(estado, simboloLeido);

        transiciones.put(
                clave,
                new Transicion(
                        estadoSiguiente,
                        simboloEscrito,
                        direccion,
                        descripcion
                )
        );
    }

    private String crearClave(String estado, char simbolo) {

        return estado + "|" + simbolo;
    }

    // =====================================
    // INICIAR SIMULACIÓN
    // =====================================

    public boolean iniciar(String entrada) {

        historial.clear();

        contadorTransiciones = 0;

        estadoActual = null;
        cinta = null;
        posicionCabezal = 0;

        if (entrada == null || entrada.isEmpty()) {

            resultado = Resultado.RECHAZADA;

            mensaje = "La cadena no puede estar vacía.";

            return false;
        }

        for (int i = 0; i < entrada.length(); i++) {

            char simbolo = entrada.charAt(i);

            if (simbolo != 'a' &&
                simbolo != 'b' &&
                simbolo != 'c') {

                resultado = Resultado.RECHAZADA;

                mensaje = "La cadena contiene símbolos no permitidos.";

                return false;
            }
        }

        // Cinta limitada a la longitud de entrada + 2 fronteras.

        cinta = new char[entrada.length() + 2];

        cinta[0] = LIMITE_IZQUIERDO;

        for (int i = 0; i < entrada.length(); i++) {

            cinta[i + 1] = entrada.charAt(i);
        }

        cinta[cinta.length - 1] = LIMITE_DERECHO;

        posicionCabezal = 1;

        estadoActual = Q_INICIO;

        resultado = Resultado.EJECUTANDO;

        mensaje = "Simulación iniciada.";

        return true;
    }

    // =====================================
    // EJECUTAR UNA TRANSICIÓN
    // =====================================

    public RegistroTransicion ejecutarPaso() {

        if (resultado != Resultado.EJECUTANDO) {
            return null;
        }

        String estadoAnterior = estadoActual;

        char simboloLeido = cinta[posicionCabezal];

        String clave = crearClave(
                estadoActual,
                simboloLeido
        );

        Transicion transicion = transiciones.get(clave);

        // No existe una transición válida.

        if (transicion == null) {

            resultado = Resultado.RECHAZADA;

            estadoActual = Q_RECHAZO;

            mensaje = "No existe una transición válida para "
                    + estadoAnterior + " leyendo '" + simboloLeido + "'.";

            return registrar(
                    estadoAnterior,
                    simboloLeido,
                    simboloLeido,
                    Direccion.QUIETO,
                    estadoActual,
                    mensaje
            );
        }

        int nuevaPosicion = posicionCabezal;

        switch (transicion.direccion) {

            case IZQUIERDA:
                nuevaPosicion--;
                break;

            case DERECHA:
                nuevaPosicion++;
                break;

            case QUIETO:
                break;
        }

        // Comprobar que el cabezal no salga de la cinta.

        if (nuevaPosicion < 0 ||
            nuevaPosicion >= cinta.length) {

            resultado = Resultado.RECHAZADA;

            estadoActual = Q_RECHAZO;

            mensaje = "El cabezal intentó salir de los límites de la cinta.";

            return registrar(
                    estadoAnterior,
                    simboloLeido,
                    simboloLeido,
                    transicion.direccion,
                    estadoActual,
                    mensaje
            );
        }

        // Ejecutar escritura.

        cinta[posicionCabezal] = transicion.simboloEscrito;

        // Cambiar estado.

        estadoActual = transicion.estadoSiguiente;

        // Mover cabezal.

        posicionCabezal = nuevaPosicion;

        // Comprobar resultado.

        if (estadoActual.equals(Q_ACEPTACION)) {

            resultado = Resultado.ACEPTADA;

            mensaje = "La cadena pertenece al lenguaje a^n b^n c^n.";

        } else if (estadoActual.equals(Q_RECHAZO)) {

            resultado = Resultado.RECHAZADA;

            mensaje = "La cadena no pertenece al lenguaje.";

        } else {

            mensaje = transicion.descripcion;
        }

        return registrar(
                estadoAnterior,
                simboloLeido,
                transicion.simboloEscrito,
                transicion.direccion,
                estadoActual,
                transicion.descripcion
        );
    }

    // =====================================
    // REGISTRAR TRANSICIÓN
    // =====================================

    private RegistroTransicion registrar(
            String estadoAnterior,
            char leido,
            char escrito,
            Direccion direccion,
            String estadoNuevo,
            String descripcion) {

        contadorTransiciones++;

        RegistroTransicion registro = new RegistroTransicion(
                contadorTransiciones,
                estadoAnterior,
                leido,
                escrito,
                direccion,
                estadoNuevo,
                descripcion
        );

        historial.add(registro);

        return registro;
    }

    // =====================================
    // REINICIAR
    // =====================================

    public void reiniciar() {

        cinta = null;

        posicionCabezal = 0;

        estadoActual = null;

        resultado = Resultado.SIN_INICIAR;

        historial.clear();

        contadorTransiciones = 0;

        mensaje = "Simulación reiniciada.";
    }

    // =====================================
    // GETTERS
    // =====================================

    public char[] getCinta() {

        return cinta == null ? new char[0] : cinta.clone();
    }

    public int getPosicionCabezal() {
        return posicionCabezal;
    }

    public String getEstadoActual() {

        return estadoActual == null ? "Sin iniciar" : estadoActual;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<RegistroTransicion> getHistorial() {
        return new ArrayList<>(historial);
    }

    public boolean estaEjecutando() {
        return resultado == Resultado.EJECUTANDO;
    }

    public boolean haTerminado() {

        return resultado == Resultado.ACEPTADA ||
               resultado == Resultado.RECHAZADA;
    }

    public int getCantidadCeldas() {
        return cinta == null ? 0 : cinta.length;
    }

    public int getLongitudEntrada() {
        return cinta == null ? 0 : cinta.length - 2;
    }
}
