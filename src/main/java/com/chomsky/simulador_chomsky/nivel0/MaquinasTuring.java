
package com.chomsky.simulador_chomsky.nivel0;

import java.util.*;

public class MaquinasTuring {

    private MaquinasTuring() {
    }

    // ==========================================
    // MÁQUINA 1: RECONOCIMIENTO 0^n 1^n
    // ==========================================

    public static MaquinaTuring crearReconocedora() {

        Set<Character> entrada = conjunto('0', '1');
        Set<Character> cinta = conjunto('0', '1', 'X', 'Y', 'B');

        Set<String> aceptacion = conjuntoEstados("qAceptar");
        Set<String> rechazo = conjuntoEstados("qRechazar");

        MaquinaTuring maquina = new MaquinaTuring(
                "qInicio",
                'B',
                entrada,
                cinta,
                aceptacion,
                rechazo
        );

        agregar(maquina, "qInicio", 'X', 'X', 1, "qInicio",
                "Buscar el siguiente cero");

        agregar(maquina, "qInicio", '0', 'X', 1, "qBuscarUno",
                "Marcar un cero");

        agregar(maquina, "qInicio", 'Y', 'Y', 1, "qVerificar",
                "Comenzar verificación final");

        agregar(maquina, "qInicio", '1', '1', 0, "qRechazar",
                "Hay un uno sin pareja");

        agregar(maquina, "qInicio", 'B', 'B', 0, "qRechazar",
                "La cadena no contiene pares");

        agregar(maquina, "qBuscarUno", '0', '0', 1, "qBuscarUno",
                "Buscar el correspondiente uno");

        agregar(maquina, "qBuscarUno", 'Y', 'Y', 1, "qBuscarUno",
                "Saltar uno marcado");

        agregar(maquina, "qBuscarUno", '1', 'Y', -1, "qRegresar",
                "Marcar un uno");

        agregar(maquina, "qBuscarUno", 'B', 'B', 0, "qRechazar",
                "No existe un uno correspondiente");

        agregar(maquina, "qRegresar", '0', '0', -1, "qRegresar",
                "Regresar al inicio");

        agregar(maquina, "qRegresar", '1', '1', -1, "qRegresar",
                "Regresar al inicio");

        agregar(maquina, "qRegresar", 'Y', 'Y', -1, "qRegresar",
                "Regresar al inicio");

        agregar(maquina, "qRegresar", 'X', 'X', -1, "qRegresar",
                "Regresar al inicio");

        agregar(maquina, "qRegresar", 'B', 'B', 1, "qInicio",
                "Volver al inicio de la cinta");

        agregar(maquina, "qVerificar", 'Y', 'Y', 1, "qVerificar",
                "Verificar unos marcados");

        agregar(maquina, "qVerificar", 'B', 'B', 0, "qAceptar",
                "Todos los símbolos fueron emparejados");

        agregar(maquina, "qVerificar", '1', '1', 0, "qRechazar",
                "Quedan unos sin emparejar");

        agregar(maquina, "qVerificar", '0', '0', 0, "qRechazar",
                "Quedan ceros sin emparejar");

        return maquina;
    }

    // ==========================================
    // MÁQUINA 2: SUMA UNARIA
    // ==========================================

    public static MaquinaTuring crearSumadora() {

        Set<Character> entrada = conjunto('1', '+');
        Set<Character> cinta = conjunto('1', '+', 'B');

        Set<String> aceptacion = conjuntoEstados("qAceptar");
        Set<String> rechazo = conjuntoEstados("qRechazar");

        MaquinaTuring maquina = new MaquinaTuring(
                "qInicio",
                'B',
                entrada,
                cinta,
                aceptacion,
                rechazo
        );

        agregar(maquina, "qInicio", '1', '1', 1, "qInicio",
                "Recorrer primer operando");

        agregar(maquina, "qInicio", '+', '1', 1, "qBuscarFinal",
                "Convertir separador en una unidad");

        agregar(maquina, "qBuscarFinal", '1', '1', 1, "qBuscarFinal",
                "Recorrer segundo operando");

        agregar(maquina, "qBuscarFinal", 'B', 'B', -1, "qBorrarUltimo",
                "Llegar al final de la cinta");

        agregar(maquina, "qBorrarUltimo", '1', 'B', 0, "qAceptar",
                "Eliminar la unidad sobrante del segundo operando");

        return maquina;
    }

    private static void agregar(
            MaquinaTuring maquina,
            String estado,
            char leido,
            char escrito,
            int direccion,
            String siguiente,
            String descripcion) {

        maquina.agregarTransicion(
                new Transicion(
                        estado,
                        leido,
                        escrito,
                        direccion,
                        siguiente,
                        descripcion
                )
        );
    }

    private static Set<Character> conjunto(Character... simbolos) {
        return new HashSet<>(Arrays.asList(simbolos));
    }

    private static Set<String> conjuntoEstados(String... estados) {
        return new HashSet<>(Arrays.asList(estados));
    }
}
