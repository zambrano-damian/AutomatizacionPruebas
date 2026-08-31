# Automatización de Pruebas

Proyecto desarrollado para implementar un flujo de automatización de pruebas utilizando Java, Maven, JUnit 5, Cucumber, k6, Git, GitHub y GitHub Actions.

El proyecto permite ejecutar pruebas unitarias y escenarios BDD de manera local y automática mediante un pipeline de integración continua. Además, permite generar reportes navegables y realizar una prueba básica de rendimiento para analizar métricas como throughput, latencia y errores.

## Objetivo

Implementar un proceso de automatización que permita validar funcionalidades mediante pruebas unitarias y BDD, ejecutar dichas pruebas automáticamente durante el proceso de integración continua y analizar métricas básicas de rendimiento.

## Tecnologías utilizadas

- Java 21
- Apache Maven
- JUnit 5
- Cucumber
- Gherkin
- k6
- Git
- GitHub
- GitHub Actions
- Maven Surefire
- Maven Surefire Report Plugin
- HTML

## Estructura del proyecto

```text
AutomatizacionPruebas/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── cl/iplacex/automatizacion/
│   │           └── Calculadora.java
│   │
│   └── test/
│       ├── java/
│       │   └── cl/iplacex/automatizacion/
│       │       ├── CalculadoraTest.java
│       │       ├── RunCucumberTest.java
│       │       └── steps/
│       │           └── CalculadoraSteps.java
│       │
│       └── resources/
│           └── features/
│               └── calculadora.feature
│
├── .gitignore
├── dashboard-metricas.html
├── performance-test.js
├── pom.xml
└── README.md
```

## Pruebas unitarias

Se implementaron pruebas unitarias utilizando JUnit 5 para comprobar el funcionamiento de la clase `Calculadora`.

Las pruebas realizadas validan las siguientes operaciones:

- Suma de dos números.
- Resta de dos números.

Cada prueba se ejecuta de manera independiente y utiliza aserciones para comparar el resultado esperado con el resultado obtenido, manteniendo la atomicidad de las pruebas.

## Ejecución de las pruebas

Para ejecutar las pruebas desde la terminal se utiliza:

```bash
mvn test
```

Durante la ejecución conjunta de JUnit y Cucumber se obtuvo:

```text
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

De estas ejecuciones, 2 corresponden a las pruebas unitarias desarrolladas con JUnit y 4 a los escenarios BDD ejecutados mediante Cucumber.

## Reporte de pruebas unitarias

Para ejecutar las pruebas y generar un reporte HTML mediante Maven Surefire se utiliza:

```bash
mvn clean test surefire-report:report
```

El reporte generado se encuentra en:

```text
target/reports/surefire.html
```

El reporte permite consultar de manera visual los resultados de las pruebas, incluyendo cantidad de pruebas ejecutadas, errores, fallos, pruebas omitidas y porcentaje de éxito.

En la ejecución inicial de las pruebas unitarias se obtuvo:

```text
Tests: 2
Errors: 0
Failures: 0
Skipped: 0
Success Rate: 100%
```

## Pruebas BDD con Cucumber

Se incorporaron pruebas BDD utilizando Cucumber y lenguaje Gherkin.

Los escenarios se encuentran definidos en:

```text
src/test/resources/features/calculadora.feature
```

Se implementó un escenario simple para comprobar la operación de suma y un esquema de escenario con diferentes conjuntos de datos para comprobar la operación de resta.

El archivo contiene un `Scenario Outline` con `Examples`, permitiendo reutilizar un mismo escenario con distintos valores de entrada y resultados esperados.

## Step Definitions

Las definiciones de pasos encargadas de relacionar los escenarios escritos en Gherkin con el código Java se encuentran en:

```text
src/test/java/cl/iplacex/automatizacion/steps/CalculadoraSteps.java
```

Los Step Definitions implementan las acciones correspondientes a:

- `Dado`: inicialización de la calculadora.
- `Cuando`: ejecución de las operaciones de suma o resta.
- `Entonces`: comparación entre el resultado obtenido y el resultado esperado.

La ejecución de Cucumber se configura mediante:

```text
src/test/java/cl/iplacex/automatizacion/RunCucumberTest.java
```

## Reporte BDD de Cucumber

La ejecución de las pruebas BDD genera automáticamente un reporte HTML:

```text
target/cucumber-report.html
```

El reporte permite visualizar los escenarios ejecutados y comprobar cuáles fueron aprobados.

Durante la ejecución realizada se obtuvieron:

```text
4 escenarios ejecutados
4 escenarios aprobados
100 % de éxito
```

Esto permite comprobar que los escenarios definidos mediante Gherkin se encuentran correctamente relacionados con sus Step Definitions.

## Integración continua

El proyecto utiliza GitHub Actions para implementar un pipeline de integración continua.

El workflow se encuentra en:

```text
.github/workflows/ci.yml
```

El pipeline permite:

- Descargar el código fuente.
- Configurar Java 21.
- Compilar el proyecto mediante Maven.
- Ejecutar las pruebas unitarias con JUnit.
- Ejecutar los escenarios BDD con Cucumber.
- Generar el reporte de Maven Surefire.
- Generar el reporte HTML de Cucumber.
- Publicar los reportes como artefactos de GitHub Actions.

La integración continua permite detectar posibles errores antes de integrar los cambios a la rama principal.

## Control de versiones

Para el desarrollo se utilizó Git mediante una estrategia basada en ramas.

Entre las ramas utilizadas durante el desarrollo se encuentran:

```text
main
feature/configuracion-inicial
feature/integracion-continua
feature/reporte-readme
feature/bdd-cucumber
```

Los cambios fueron registrados mediante commits descriptivos y posteriormente integrados a la rama principal mediante Pull Requests.

Antes de realizar los merge, GitHub Actions ejecutó automáticamente las pruebas configuradas, permitiendo comprobar que los cambios no generaban errores.

## Flujo de trabajo

El proceso implementado considera las siguientes etapas:

1. Desarrollo o modificación del código.
2. Creación de pruebas unitarias.
3. Definición de escenarios BDD mediante Gherkin.
4. Implementación de Step Definitions.
5. Ejecución local mediante Maven.
6. Generación de reportes de pruebas.
7. Registro de cambios mediante Git.
8. Trabajo mediante ramas independientes.
9. Creación de Pull Requests.
10. Ejecución automática del pipeline mediante GitHub Actions.
11. Validación de las pruebas.
12. Integración de los cambios a la rama `main`.
13. Ejecución de pruebas básicas de rendimiento.
14. Análisis y visualización de métricas.

## Prueba de rendimiento

Se implementó una prueba básica de performance utilizando k6.

El archivo utilizado es:

```text
performance-test.js
```

Para ejecutar la prueba se utiliza:

```bash
k6 run performance-test.js
```

La prueba fue configurada utilizando 5 usuarios virtuales durante 10 segundos.

Durante la ejecución se analizaron los siguientes indicadores:

- Throughput o solicitudes procesadas por segundo.
- Latencia promedio.
- Latencia P95.
- Porcentaje de errores HTTP.
- Cantidad total de solicitudes realizadas.

En la ejecución realizada se obtuvieron los siguientes resultados:

```text
Solicitudes HTTP: 80
Throughput aproximado: 7,15 solicitudes/segundo
Latencia promedio: 138,88 ms
Latencia P95: 310,78 ms
Errores HTTP: 0 %
```

Los resultados obtenidos permiten analizar el comportamiento de las solicitudes frente a una carga concurrente básica.

## Dashboard de métricas

Para representar de manera consolidada los resultados funcionales y de rendimiento se creó el archivo:

```text
dashboard-metricas.html
```

El dashboard permite visualizar los siguientes indicadores:

- Escenarios BDD ejecutados.
- Porcentaje de escenarios exitosos.
- Cantidad de solicitudes HTTP.
- Throughput.
- Latencia promedio.
- Latencia P95.
- Porcentaje de errores HTTP.

Los resultados obtenidos permiten observar de manera resumida el estado de las pruebas funcionales y de rendimiento.

En un entorno productivo, este tipo de métricas podría integrarse automáticamente con herramientas especializadas de monitoreo y visualización.

## Alertas y monitoreo

Como estrategia de monitoreo se propone utilizar alertas asociadas a los resultados obtenidos durante las pruebas y al pipeline de integración continua.

Las alertas podrían activarse ante las siguientes condiciones:

- Fallo de una prueba unitaria.
- Fallo de un escenario BDD.
- Tasa de errores HTTP superior al 1 %.
- Latencia P95 superior a 500 ms.
- Fallo de una ejecución del pipeline de GitHub Actions.

En un entorno productivo, estas alertas podrían complementarse con mecanismos de notificación al equipo responsable, permitiendo detectar oportunamente fallos o degradaciones en el comportamiento de la aplicación.

## Resultados obtenidos

La automatización implementada permitió integrar diferentes estrategias de pruebas dentro de un mismo proyecto.

Las pruebas unitarias desarrolladas con JUnit permitieron comprobar las operaciones de la clase `Calculadora`, mientras que Cucumber permitió implementar escenarios BDD utilizando lenguaje Gherkin.

La ejecución automatizada obtuvo:

- 2 pruebas unitarias ejecutadas correctamente.
- 4 escenarios BDD ejecutados correctamente.
- 0 fallos.
- 0 errores.
- Reportes HTML navegables.
- Pipeline de integración continua funcionando correctamente.
- Prueba básica de rendimiento mediante k6.
- Dashboard para visualizar métricas funcionales y de rendimiento.

## Conclusión

La implementación realizada permitió desarrollar un flujo completo de automatización de pruebas, combinando pruebas unitarias, BDD, integración continua, reporting y análisis básico de rendimiento.

JUnit permitió validar de manera independiente las operaciones desarrolladas, mientras que Cucumber permitió representar el comportamiento esperado mediante escenarios escritos en lenguaje Gherkin.

Git y GitHub permitieron mantener la trazabilidad de los cambios mediante ramas, commits y Pull Requests, mientras que GitHub Actions permitió automatizar la ejecución de las pruebas antes de integrar modificaciones a la rama principal.

Finalmente, k6 permitió realizar una prueba básica de rendimiento y obtener indicadores relacionados con throughput, latencia y errores, los cuales fueron representados mediante un dashboard de métricas.

De esta forma, el proyecto integra diferentes técnicas y herramientas orientadas a mejorar la calidad, visibilidad y automatización del proceso de pruebas.