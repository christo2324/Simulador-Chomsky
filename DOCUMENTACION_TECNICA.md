# Documentación técnica
## Simulador Chomsky

**Asignatura:** Autómatas, Gramáticas y Lenguajes  
**Tecnología:** Java  
**Interfaz gráfica:** Java Swing  
**Gestión de dependencias:** Maven

---

## 1. Introducción

El proyecto Simulador Chomsky consiste en el desarrollo de una aplicación de escritorio que permite representar y simular los modelos computacionales correspondientes a los cuatro niveles de la Jerarquía de Chomsky.

La aplicación fue desarrollada en Java utilizando la biblioteca Swing para la construcción de interfaces gráficas y Maven para la organización y administración del proyecto.

Su finalidad es facilitar la comprensión de los conceptos relacionados con autómatas, gramáticas y lenguajes formales mediante representaciones visuales e interactivas que permiten observar el procesamiento de cadenas, los cambios de estado, las operaciones sobre estructuras de memoria y las transiciones de los diferentes modelos computacionales.

El sistema se encuentra organizado en cuatro módulos independientes, cada uno asociado a un nivel de la Jerarquía de Chomsky.

## 2. Objetivos

### 2.1. Objetivo general

Desarrollar una aplicación de escritorio con interfaz gráfica que permita visualizar y simular el comportamiento de los modelos computacionales representativos de los cuatro niveles de la Jerarquía de Chomsky.

### 2.2. Objetivos específicos

- Implementar los modelos computacionales correspondientes a los cuatro niveles de la Jerarquía de Chomsky.
- Representar gráficamente los componentes principales de cada modelo.
- Permitir la ejecución de simulaciones paso a paso y de manera automática.
- Visualizar los cambios de estado, las transiciones y las estructuras de memoria utilizadas durante el procesamiento.
- Aplicar conceptos de programación orientada a objetos para organizar y estructurar el software.
- Relacionar los fundamentos teóricos de los lenguajes formales con implementaciones prácticas.

## 3. Fundamentos teóricos

La Jerarquía de Chomsky clasifica las gramáticas formales y los lenguajes que estas generan en cuatro tipos, relacionados con diferentes modelos computacionales.

### 3.1. Tipo 3: Lenguajes regulares y autómatas finitos

Los lenguajes regulares corresponden al nivel más restrictivo de la Jerarquía de Chomsky.

Su reconocimiento puede realizarse mediante autómatas finitos, los cuales procesan una cadena símbolo por símbolo y cambian de estado de acuerdo con una función de transición.

Un autómata finito determinista se representa mediante la siguiente tupla:

\[
M=(Q,\Sigma,\delta,q_0,F)
\]

Donde:

- \(Q\): conjunto finito de estados.
- \(\Sigma\): alfabeto de entrada.
- \(\delta\): función de transición.
- \(q_0\): estado inicial.
- \(F\): conjunto de estados finales o de aceptación.

Una cadena es aceptada cuando, después de procesar todos sus símbolos, el autómata termina en un estado perteneciente al conjunto de estados finales.

**Aplicación en el proyecto:**

El módulo de Nivel 3 representa el funcionamiento de un autómata finito mediante estados y transiciones. Permite introducir cadenas y observar su procesamiento, identificando el recorrido realizado y el resultado del reconocimiento.

### 3.2. Tipo 2: Lenguajes libres de contexto y autómatas de pila

Los lenguajes libres de contexto son reconocidos por autómatas de pila.

A diferencia de los autómatas finitos, estos modelos cuentan con una estructura de memoria adicional denominada pila, que permite almacenar y recuperar símbolos siguiendo el principio LIFO (*Last In, First Out*).

Las operaciones principales de una pila son:

- **Apilar (push):** insertar un símbolo en la parte superior.
- **Desapilar (pop):** retirar el símbolo superior.
- **Consultar (peek):** consultar el símbolo ubicado en la parte superior sin retirarlo.

Un autómata de pila puede representarse formalmente mediante una tupla que incluye estados, alfabeto de entrada, alfabeto de pila, función de transición, estado inicial y condiciones de aceptación.

**Aplicación en el proyecto:**

El módulo de Nivel 2 implementa un autómata de pila para el reconocimiento de cadenas. La simulación permite observar el estado actual, las operaciones realizadas sobre la pila y el historial de transiciones.

El lenguaje utilizado en la implementación es:

\[
L=\{a^n b^n\mid n\geq 1\}
\]

Este lenguaje contiene cadenas con una cantidad igual de símbolos `a` seguidos de símbolos `b`.

Ejemplos:

- Cadenas aceptadas: `ab`, `aabb`, `aaabbb`.
- Cadenas rechazadas: `aab`, `abb`, `abab`.

El funcionamiento consiste en apilar un símbolo por cada `a` procesada y desapilar uno por cada `b`. De esta manera, la pila permite verificar que ambas cantidades sean iguales y que los símbolos respeten el orden establecido.

### 3.3. Tipo 1: Lenguajes sensibles al contexto y autómatas linealmente acotados

Los lenguajes sensibles al contexto corresponden al Tipo 1 de la Jerarquía de Chomsky.

Su reconocimiento puede realizarse mediante autómatas linealmente acotados, que son máquinas de Turing cuya memoria está limitada por una cantidad proporcional a la longitud de la entrada.

Estos modelos utilizan una cinta de trabajo delimitada, un cabezal de lectura y escritura y un conjunto de estados que determinan las operaciones que se realizan sobre la cinta.

El cabezal puede desplazarse hacia la izquierda o hacia la derecha, leer símbolos y modificar el contenido de las posiciones de memoria, respetando los límites establecidos.

**Aplicación en el proyecto:**

El módulo de Nivel 1 representa un autómata linealmente acotado mediante una cinta de trabajo delimitada.

La simulación permite visualizar el movimiento bidireccional del cabezal, la lectura y modificación de símbolos y los cambios de estado durante el procesamiento.

El lenguaje utilizado como referencia en la implementación es:

\[
L=\{a^n b^n c^n\mid n\geq 1\}
\]

Este lenguaje requiere verificar que exista la misma cantidad de símbolos `a`, `b` y `c`, manteniendo el orden correspondiente.

La estrategia de procesamiento utiliza marcas sobre los símbolos ya verificados, permitiendo relacionar cada `a` con una `b` y una `c` sin necesitar una pila de tamaño ilimitado.

### 3.4. Tipo 0: Lenguajes recursivamente enumerables y máquinas de Turing

El Tipo 0 corresponde a la clase más general de la Jerarquía de Chomsky.

Los lenguajes de este nivel pueden ser reconocidos mediante máquinas de Turing, un modelo computacional que dispone de una cinta de trabajo, un cabezal de lectura y escritura y un conjunto de estados.

Una máquina de Turing realiza operaciones mediante una función de transición que determina:

- El estado actual.
- El símbolo leído.
- El nuevo estado.
- El símbolo que se escribe.
- La dirección en la que se desplaza el cabezal.

Una transición puede representarse mediante:

\[
\delta(q,a)=(p,b,D)
\]

Donde:

- \(q\): estado actual.
- \(a\): símbolo leído.
- \(p\): nuevo estado.
- \(b\): símbolo que se escribe.
- \(D\): dirección del movimiento del cabezal.

**Aplicación en el proyecto:**

El módulo de Nivel 0 permite simular el comportamiento de una máquina de Turing mediante una cinta de trabajo, un cabezal de lectura y escritura y un conjunto de estados y transiciones.

La cinta permite representar los símbolos de entrada y los símbolos utilizados durante la ejecución. El cabezal puede desplazarse para leer o modificar las posiciones correspondientes.

El módulo contempla la ejecución de simulaciones de reconocimiento de cadenas y operaciones aritméticas mediante transiciones de la máquina.

Una de las operaciones consideradas es la suma unaria. En esta representación, cada unidad se expresa mediante el símbolo `1`.

Por ejemplo:

\[
111+11=11111
\]

La operación se desarrolla mediante las reglas de transición de la máquina y no simplemente mediante una suma directa del lenguaje de programación.

## 4. Arquitectura del software

El proyecto utiliza una arquitectura modular organizada mediante paquetes Java. Esta organización permite separar la implementación de cada modelo computacional y facilita el mantenimiento del código.

### 4.1. Organización de paquetes

La estructura general del proyecto es la siguiente:

```text
com.chomsky.simulador_chomsky
│
├── nivel0
│   └── Clases de la Máquina de Turing
│
├── nivel1
│   └── Clases del Autómata Linealmente Acotado
│
├── nivel2
│   └── Clases del Autómata de Pila
│
├── nivel3
│   └── Clases de los Autómatas Finitos
│
├── util
│   └── Clases y recursos reutilizables
│
└── vista
    └── Ventanas generales de la aplicación
```

Cada módulo contiene las clases encargadas de representar su modelo computacional, ejecutar las simulaciones y construir sus componentes gráficos.

El paquete `util` contiene recursos compartidos, como la clase `EstilosUI`, que centraliza los colores, las fuentes y los estilos de los componentes gráficos.

El paquete `vista` contiene los elementos generales de navegación y presentación de la aplicación.

### 4.2. Interfaz gráfica

La interfaz gráfica fue desarrollada mediante Java Swing, utilizando componentes como:

- `JFrame`: ventanas principales.
- `JPanel`: organización de los componentes.
- `JLabel`: presentación de información.
- `JButton`: controles de interacción.
- `JTextField`: entrada de datos.
- `JTextArea`: presentación de información e historiales.
- `JScrollPane`: desplazamiento de contenido.
- `JComboBox`: selección de opciones cuando corresponde.

La aplicación utiliza una apariencia visual común para mantener la coherencia entre los diferentes módulos.

La clase `EstilosUI` permite reutilizar los colores, las fuentes y los estilos de los componentes, evitando repetir configuraciones visuales en cada ventana.

### 4.3. Gestión de simulaciones

Cada módulo mantiene la información necesaria para ejecutar su modelo computacional.

Durante una simulación se actualizan los elementos correspondientes al modelo, como:

- Estado actual.
- Posición del cabezal o símbolo procesado.
- Contenido de las estructuras de memoria.
- Transición ejecutada.
- Resultado del procesamiento.

La separación entre la lógica de simulación y la representación gráfica permite mantener organizado el funcionamiento del sistema.

## 5. Implementación de los módulos

### 5.1. Implementación del Nivel 3

El módulo de autómatas finitos se encarga de procesar cadenas de entrada mediante un conjunto de estados y transiciones.

Su implementación contempla la lectura secuencial de los símbolos de entrada, la consulta de las transiciones correspondientes y la determinación del resultado final.

La interfaz permite observar el comportamiento del autómata durante la ejecución.

### 5.2. Implementación del Nivel 2

El módulo de autómatas de pila incorpora una estructura de datos basada en una pila para almacenar los símbolos necesarios durante el procesamiento.

La lógica de simulación realiza operaciones de inserción y eliminación de elementos, de acuerdo con las transiciones definidas para el autómata.

La representación gráfica permite consultar el contenido de la pila y observar cómo cambia durante la ejecución.

### 5.3. Implementación del Nivel 1

El módulo de autómatas linealmente acotados implementa una cinta de trabajo con límites establecidos.

La lógica de simulación controla la posición del cabezal, la lectura de símbolos, la escritura de marcas y los movimientos permitidos.

La interfaz representa visualmente la cinta, sus delimitadores y el desplazamiento del cabezal.

### 5.4. Implementación del Nivel 0

El módulo de máquinas de Turing implementa una cinta de trabajo que permite almacenar y modificar símbolos durante la ejecución.

La lógica de simulación utiliza estados y reglas de transición para determinar el comportamiento de la máquina.

El cabezal puede leer y escribir símbolos y desplazarse por la cinta según las instrucciones de cada transición.

La interfaz permite observar el estado activo, el contenido de la cinta y los pasos realizados durante la ejecución.

## 6. Conceptos de programación aplicados

Durante el desarrollo del proyecto se aplicaron diferentes conceptos de programación orientada a objetos y organización del software.

### 6.1. Clases y objetos

Los componentes de cada modelo computacional se representan mediante clases que encapsulan sus atributos y comportamientos.

Esto permite crear objetos encargados de administrar la información y ejecutar las operaciones correspondientes.

### 6.2. Encapsulamiento

Los atributos y métodos se organizan dentro de las clases correspondientes, permitiendo controlar el acceso a la información y mantener la integridad de los datos de cada simulación.

### 6.3. Modularidad

La aplicación se divide en cuatro módulos independientes, lo que permite desarrollar, probar y modificar cada modelo computacional sin afectar directamente la implementación de los demás.

### 6.4. Reutilización de código

Los recursos compartidos, como los estilos visuales y los componentes generales, se centralizan para evitar duplicación de código y facilitar futuras modificaciones.

### 6.5. Estructuras de datos

Según las necesidades de cada modelo computacional, se utilizan estructuras de datos para representar estados, transiciones, cintas y memoria auxiliar.

En particular, el autómata de pila utiliza una estructura LIFO para administrar los símbolos almacenados durante el procesamiento.

### 6.6. Programación orientada a eventos

La interfaz gráfica utiliza eventos para responder a las acciones del usuario, como iniciar una simulación, avanzar un paso, ejecutar automáticamente el procesamiento o reiniciar el módulo.

## 7. Herramientas y tecnologías

| Tecnología | Aplicación |
|---|---|
| Java | Implementación de la lógica del sistema. |
| Java Swing | Desarrollo de las interfaces gráficas. |
| Maven | Administración del proyecto y construcción. |
| Eclipse IDE | Entorno de desarrollo. |
| Git y GitHub | Control de versiones y alojamiento del código fuente. |

## 8. Conclusiones

El desarrollo de Simulador Chomsky permitió aplicar los fundamentos teóricos de la Jerarquía de Chomsky mediante la implementación de modelos computacionales en una aplicación de escritorio.

La separación de los cuatro niveles facilita la comprensión de las diferencias entre los autómatas finitos, los autómatas de pila, los autómatas linealmente acotados y las máquinas de Turing.

La representación gráfica de los estados, las transiciones, las estructuras de memoria y los movimientos del cabezal permite observar el comportamiento de cada modelo durante su ejecución.

Finalmente, el proyecto permitió fortalecer conocimientos relacionados con lenguajes formales, estructuras de datos, programación orientada a objetos, interfaces gráficas y desarrollo de aplicaciones mediante Java y Maven.

---

**Proyecto:** Simulador Chomsky  
**Asignatura:** Autómatas, Gramáticas y Lenguajes