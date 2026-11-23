# Simulador Chomsky

Aplicación de escritorio desarrollada en Java Swing con Maven para la asignatura **Autómatas, Gramáticas y Lenguajes**. Su propósito es representar y simular los modelos computacionales correspondientes a los cuatro niveles de la Jerarquía de Chomsky mediante interfaces gráficas interactivas.

## Descripción del proyecto

El Simulador Chomsky permite explorar los modelos computacionales asociados a cada tipo de lenguaje formal, visualizar sus estructuras y observar paso a paso el procesamiento de cadenas o la ejecución de operaciones.

La aplicación está dividida en cuatro módulos:

| Nivel | Modelo computacional | Descripción |
|---|---|---|
| Nivel 3 | Autómatas finitos | Reconocimiento de cadenas mediante estados y transiciones. |
| Nivel 2 | Autómatas de pila | Procesamiento de cadenas utilizando una pila como memoria auxiliar. |
| Nivel 1 | Autómatas linealmente acotados | Procesamiento mediante una cinta de memoria limitada por la longitud de la entrada. |
| Nivel 0 | Máquinas de Turing | Simulación de una máquina de Turing con cinta de trabajo, cabezal y transiciones. |

## Tecnologías utilizadas

- **Java:** lenguaje de programación.
- **Java Swing:** desarrollo de la interfaz gráfica.
- **Maven:** administración y construcción del proyecto.
- **Eclipse IDE:** entorno de desarrollo utilizado.

## Requisitos

Para ejecutar el proyecto se necesita:

- JDK compatible con la versión configurada en el archivo `pom.xml`.
- Apache Maven.
- Eclipse IDE u otro entorno compatible con proyectos Maven.

## Instalación y ejecución

### 1. Clonar el repositorio

Desde una terminal, ejecutar:

```bash
git clone https://github.com/christo2324/Simulador-Chomsky.git
```

Ingresar a la carpeta del proyecto:

```bash
cd Simulador-Chomsky
```

### 2. Importar el proyecto en Eclipse

1. Abrir Eclipse.
2. Seleccionar **File → Import**.
3. Elegir **Maven → Existing Maven Projects**.
4. Seleccionar la carpeta donde se clonó el repositorio.
5. Verificar que Eclipse reconozca el archivo `pom.xml`.
6. Finalizar la importación y esperar a que Maven resuelva las dependencias.

### 3. Compilar el proyecto

Desde la terminal ubicada en la raíz del proyecto, ejecutar:

```bash
mvn clean compile
```

Para generar el paquete del proyecto:

```bash
mvn clean package
```

### 4. Ejecutar la aplicación

Desde Eclipse:

1. Abrir el explorador del proyecto.
2. Localizar la clase principal que contiene el método `public static void main(String[] args)`.
3. Hacer clic derecho sobre ella.
4. Seleccionar **Run As → Java Application**.

La ventana principal de Simulador Chomsky se abrirá y permitirá acceder a los cuatro módulos.

## Manual de usuario

### Ventana principal

Al iniciar la aplicación, se presenta el menú principal desde el cual se puede seleccionar cualquiera de los cuatro niveles de la Jerarquía de Chomsky.

Cada módulo cuenta con su propia interfaz y herramientas para configurar y ejecutar las simulaciones.

### Nivel 3: Autómatas finitos

Este módulo permite introducir una cadena y observar cómo es procesada por un autómata finito.

Durante la simulación se pueden visualizar los estados, las transiciones realizadas y el resultado del reconocimiento.

### Nivel 2: Autómatas de pila

Este módulo permite simular el comportamiento de un autómata de pila.

El usuario puede introducir una cadena y observar cómo el autómata procesa sus símbolos, realiza transiciones y modifica el contenido de la pila.

La visualización permite seguir el estado actual, el contenido de la pila y el historial de ejecución.

### Nivel 1: Autómatas linealmente acotados

Este módulo representa el funcionamiento de un autómata linealmente acotado mediante una cinta de trabajo delimitada.

Se puede observar el desplazamiento bidireccional del cabezal, la lectura y modificación de los símbolos y el procesamiento de la cadena.

### Nivel 0: Máquinas de Turing

Este módulo permite ejecutar simulaciones de una máquina de Turing.

Se representa la cinta de trabajo, el alfabeto, el cabezal de lectura y escritura y el estado activo. También permite observar las transiciones realizadas durante la ejecución de las operaciones disponibles.

## Controles de simulación

Los controles disponibles dependen del módulo, pero pueden incluir:

- **Iniciar simulación:** comienza el procesamiento de la entrada.
- **Siguiente símbolo:** ejecuta el siguiente paso de la simulación.
- **Ejecutar todo:** realiza automáticamente los pasos restantes.
- **Reiniciar:** restablece la simulación para comenzar nuevamente.

Durante la ejecución se pueden consultar las representaciones visuales y los historiales de transiciones disponibles en cada módulo.

## Estructura general del proyecto

```text
Simulador-Chomsky/
│
├── pom.xml
├── README.md
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── chomsky/
        │           └── simulador_chomsky/
        │               ├── nivel0/
        │               ├── nivel1/
        │               ├── nivel2/
        │               ├── nivel3/
        │               ├── util/
        │               └── vista/
        │
        └── resources/
```

Cada paquete representa una parte de la aplicación:

- `nivel0`: clases correspondientes a la Máquina de Turing.
- `nivel1`: clases del autómata linealmente acotado.
- `nivel2`: clases del autómata de pila.
- `nivel3`: clases de los autómatas finitos.
- `util`: herramientas y recursos reutilizables, como los estilos de la interfaz.
- `vista`: ventanas y componentes generales de navegación.

## Objetivo académico

Este proyecto busca fortalecer la comprensión de los modelos computacionales de la Jerarquía de Chomsky mediante su implementación y representación gráfica.

La aplicación permite relacionar los conceptos teóricos de autómatas, gramáticas y lenguajes formales con su funcionamiento práctico mediante simulaciones interactivas.

---

**Proyecto académico:** Simulador Chomsky  
**Asignatura:** Autómatas, Gramáticas y Lenguajes  
**Tecnología principal:** Java Swing  
**Gestión del proyecto:** Maven