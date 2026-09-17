# Automatización de Pruebas

Proyecto desarrollado para implementar un flujo completo de automatización de pruebas utilizando Java, Maven, JUnit 5, Cucumber, Gherkin, k6, Git, GitHub y GitHub Actions.

El proyecto permite ejecutar pruebas unitarias, pruebas de integración y escenarios BDD de manera local y automática mediante un pipeline de Integración Continua. Además, genera reportes navegables, ejecuta validaciones de aceptación antes del despliegue, simula un proceso de deployment y contempla una estrategia de rollback ante posibles fallos.

También se implementó una prueba básica de rendimiento mediante k6 y un dashboard para visualizar métricas funcionales y de performance.

## Objetivo

Implementar un proceso de automatización que permita validar funcionalidades mediante pruebas unitarias, pruebas de integración y escenarios BDD, ejecutando dichas validaciones automáticamente dentro de un pipeline de Integración Continua.

El flujo incorpora un Acceptance Test Gate antes del despliegue, un deployment simulado, una estrategia de rollback ante fallos, generación de reportes y análisis básico de rendimiento.

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
│       │       ├── CalculadoraIntegracionTest.java
│       │       ├── RunCucumberTest.java
│       │       └── steps/
│       │           └── CalculadoraSteps.java
│       │
│       └── resources/
│           └── features/
│               ├── calculadora.feature
│               └── aceptacion.feature
│
├── .gitignore
├── dashboard-metricas.html
├── performance-test.js
├── pom.xml
└── README.md
```

## Pruebas unitarias

Se implementaron pruebas unitarias utilizando JUnit 5 para comprobar el funcionamiento individual de las operaciones disponibles en la clase `Calculadora`.

Las pruebas permiten validar operaciones como:

- Suma de dos números.
- Resta de dos números.

Cada prueba se ejecuta de manera independiente y utiliza aserciones para comparar el resultado esperado con el resultado obtenido.

## Prueba de integración

Se incorporó una prueba de integración mediante:

```text
src/test/java/cl/iplacex/automatizacion/CalculadoraIntegracionTest.java
```

Esta prueba permite comprobar el comportamiento conjunto de las operaciones implementadas en la calculadora y complementar las pruebas unitarias existentes.

La prueba de integración fue incorporada mediante una rama independiente y posteriormente validada automáticamente por GitHub Actions antes de ser integrada a la rama principal.

## Ejecución de las pruebas

Para ejecutar las pruebas desde la terminal se utiliza:

```bash
mvn clean test
```

En la ejecución final realizada se obtuvo:

```text
Tests run: 8
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

Esto permite comprobar que las pruebas configuradas en el proyecto finalizaron satisfactoriamente.

## Reporte de pruebas unitarias

Para ejecutar las pruebas y generar un reporte HTML mediante Maven Surefire se utiliza:

```bash
mvn clean test surefire-report:report
```

El reporte generado se encuentra en:

```text
target/reports/surefire.html
```

El reporte permite consultar visualmente información relacionada con las pruebas ejecutadas, incluyendo errores, fallos, pruebas omitidas y resultados obtenidos.

## Pruebas BDD con Cucumber

Se incorporaron pruebas BDD utilizando Cucumber y lenguaje Gherkin.

Los escenarios principales se encuentran definidos en:

```text
src/test/resources/features/calculadora.feature
```

Se implementó un escenario simple para comprobar la operación de suma y un `Scenario Outline` con diferentes conjuntos de datos para comprobar la operación de resta.

El uso de `Examples` permite ejecutar un mismo escenario utilizando diferentes valores de entrada y resultados esperados.

## Step Definitions

Las definiciones de pasos encargadas de relacionar los escenarios escritos en Gherkin con el código Java se encuentran en:

```text
src/test/java/cl/iplacex/automatizacion/steps/CalculadoraSteps.java
```

Los Step Definitions implementan las acciones correspondientes a:

- `Dado`: inicialización de la calculadora.
- `Cuando`: ejecución de una operación.
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

Este reporte permite visualizar los escenarios ejecutados y comprobar cuáles fueron aprobados durante el proceso de automatización.

El reporte es publicado además como artefacto mediante GitHub Actions.

## Prueba de aceptación

Se incorporó una prueba de aceptación mediante el archivo:

```text
src/test/resources/features/aceptacion.feature
```

El escenario permite validar una operación principal de la calculadora antes de avanzar hacia el proceso de despliegue.

La prueba fue definida utilizando lenguaje Gherkin:

```gherkin
Característica: Validación de aceptación de la calculadora

  Como usuario
  Quiero verificar el funcionamiento principal de la calculadora
  Para asegurar que la aplicación esté preparada para ser desplegada

  Escenario: Validar operación principal antes del despliegue
    Dado que tengo una calculadora
    Cuando sumo 10 y 5
    Entonces el resultado debe ser 15
```

Esta validación permite representar una condición de aceptación previa al despliegue de una versión.

## Integración Continua

El proyecto utiliza GitHub Actions para implementar un pipeline de Integración Continua.

El workflow se encuentra definido en:

```text
.github/workflows/ci.yml
```

El pipeline final contempla las siguientes etapas:

1. Descargar el código.
2. Configurar Java 21.
3. Realizar el Build del proyecto.
4. Ejecutar las pruebas automatizadas.
5. Ejecutar el Acceptance Test Gate.
6. Ejecutar un deployment simulado.
7. Contemplar una estrategia de rollback ante fallos.
8. Publicar el reporte de Maven Surefire.
9. Publicar el reporte BDD de Cucumber.

La ejecución automática permite detectar posibles errores antes de integrar cambios a la rama principal y mantener trazabilidad sobre las validaciones realizadas.

## Acceptance Test Gate

Se incorporó una etapa denominada:

```text
Acceptance Test Gate
```

Esta etapa ejecuta las pruebas antes de permitir que el flujo avance hacia el deployment:

```yaml
- name: Acceptance Test Gate
  run: mvn test
```

Si las pruebas finalizan correctamente, el pipeline puede continuar hacia la siguiente etapa.

Si alguna validación falla, el flujo no debería avanzar normalmente hacia el despliegue.

De esta manera, el Acceptance Test Gate representa un punto de control previo a la entrega de una versión.

## Deployment simulado

Después de superar correctamente el Acceptance Test Gate se incorporó una etapa de deployment simulado.

```yaml
- name: Deployment simulado
  run: |
    echo "Acceptance Test Gate aprobado"
    echo "Iniciando despliegue de la version validada"
    echo "Deployment completado correctamente"
```

Esta etapa representa el proceso de entrega de una versión previamente validada sin realizar un despliegue real hacia infraestructura productiva.

La simulación permite demostrar cómo una versión podría avanzar automáticamente hacia deployment después de superar las validaciones configuradas.

## Estrategia de rollback

Se incorporó una estrategia de rollback simulada mediante GitHub Actions.

La configuración utilizada es:

```yaml
- name: Simulacion de rollback
  if: failure()
  run: |
    echo "Se detecto un fallo durante el proceso"
    echo "Iniciando rollback a la ultima version estable"
    echo "Rollback completado correctamente"
```

La condición:

```text
if: failure()
```

indica que esta etapa debe activarse cuando se produce un fallo previo dentro del flujo automatizado.

Cuando todas las etapas terminan correctamente, el rollback es omitido porque no existe ningún fallo que requiera recuperación.

Este mecanismo representa una estrategia básica para regresar a una versión estable cuando se detectan problemas durante un proceso automatizado.

## Pipeline final

El flujo final implementado puede representarse de la siguiente manera:

```text
Código fuente
     ↓
Build
     ↓
Pruebas automatizadas
     ↓
Acceptance Test Gate
     ↓
Deployment simulado
     ↓
Publicación de reportes

Si ocurre un fallo:
     ↓
Rollback simulado
```

GitHub Actions permite observar el estado de cada etapa y determinar rápidamente si una ejecución fue satisfactoria o presentó algún problema.

## Publicación de reportes

El pipeline publica dos artefactos principales:

```text
reporte-pruebas-surefire
reporte-bdd-cucumber
```

Estos artefactos permiten conservar los resultados generados durante la ejecución automatizada y revisar posteriormente la evidencia de las pruebas.

## Control de versiones

Para el desarrollo se utilizó Git mediante una estrategia basada en ramas.

Entre las ramas utilizadas durante el proyecto se encuentran:

```text
main
feature/configuracion-inicial
feature/integracion-continua
feature/reporte-readme
feature/bdd-cucumber
feature/examen-final-integracion
feature/examen-final-deployment
feature/examen-final-rollback
```

Los cambios fueron registrados mediante commits descriptivos y posteriormente integrados a la rama principal mediante Pull Requests.

Antes de realizar los merges, GitHub Actions permitió validar automáticamente los cambios incorporados.

Esta estrategia mantiene trazabilidad sobre la evolución del proyecto y evita modificar directamente la rama principal durante el desarrollo de nuevas funcionalidades.

## Pull Requests

Durante el desarrollo se utilizaron Pull Requests para integrar progresivamente las modificaciones realizadas.

En la etapa final se utilizaron Pull Requests para incorporar:

- Prueba de integración y actualización del pipeline.
- Acceptance Test Gate y deployment simulado.
- Estrategia de rollback ante fallos.

Las validaciones automáticas permitieron comprobar el estado del proyecto antes de realizar cada merge hacia `main`.

## Flujo de trabajo

El proceso implementado considera las siguientes etapas:

1. Desarrollo o modificación del código.
2. Creación de pruebas unitarias.
3. Implementación de pruebas de integración.
4. Definición de escenarios BDD mediante Gherkin.
5. Implementación de Step Definitions.
6. Ejecución local mediante Maven.
7. Generación de reportes.
8. Registro de cambios mediante Git.
9. Desarrollo mediante ramas independientes.
10. Creación de Pull Requests.
11. Ejecución automática del pipeline mediante GitHub Actions.
12. Build del proyecto.
13. Ejecución de pruebas automatizadas.
14. Validación mediante Acceptance Test Gate.
15. Deployment simulado.
16. Estrategia de rollback ante fallos.
17. Publicación de reportes.
18. Integración de los cambios a la rama `main`.
19. Ejecución de pruebas básicas de rendimiento.
20. Análisis y visualización de métricas.

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

En la ejecución realizada se obtuvieron:

```text
Solicitudes HTTP: 80
Throughput aproximado: 7,15 solicitudes/segundo
Latencia promedio: 138,88 ms
Latencia P95: 310,78 ms
Errores HTTP: 0 %
```

Estos resultados permiten analizar el comportamiento de las solicitudes frente a una carga concurrente básica.

## Dashboard de métricas

Para representar de manera consolidada los resultados funcionales y de rendimiento se creó:

```text
dashboard-metricas.html
```

El dashboard permite visualizar:

- Escenarios BDD ejecutados.
- Porcentaje de escenarios exitosos.
- Cantidad de solicitudes HTTP.
- Throughput.
- Latencia promedio.
- Latencia P95.
- Porcentaje de errores HTTP.

Esta visualización permite observar de manera resumida el estado de las pruebas funcionales y de rendimiento.

En un entorno productivo, estas métricas podrían integrarse automáticamente con herramientas especializadas de monitoreo.

## Alertas y monitoreo

GitHub Actions permite monitorear cada ejecución del pipeline y observar el estado de las diferentes etapas implementadas.

Como estrategia de monitoreo se consideran posibles alertas ante:

- Fallo de una prueba unitaria.
- Fallo de una prueba de integración.
- Fallo de un escenario BDD.
- Fallo del Acceptance Test Gate.
- Fallo durante el pipeline.
- Tasa de errores HTTP superior al 1 %.
- Latencia P95 superior a 500 ms.

En un entorno productivo, estas alertas podrían complementarse mediante mecanismos de notificación al equipo responsable.

## Recuperación ante fallos

Además del monitoreo, el pipeline incorpora una simulación de rollback.

Cuando GitHub Actions detecta un fallo previo, la condición:

```text
if: failure()
```

permite ejecutar la etapa de recuperación.

En una ejecución satisfactoria esta etapa aparece omitida, debido a que no existe ningún error que requiera regresar a una versión anterior.

En un entorno productivo, este concepto podría implementarse mediante mecanismos reales de recuperación o restauración de una versión estable.

## Resultados obtenidos

La automatización desarrollada permitió integrar diferentes estrategias de pruebas y validaciones dentro de un mismo proyecto.

Se logró implementar:

- Pruebas unitarias mediante JUnit 5.
- Prueba de integración.
- Escenarios BDD mediante Cucumber y Gherkin.
- Escenario de aceptación previo al deployment.
- Ejecución automatizada mediante Maven.
- Pipeline de Integración Continua mediante GitHub Actions.
- Acceptance Test Gate.
- Deployment simulado.
- Estrategia de rollback simulada.
- Pull Requests con validación automática.
- Reportes HTML navegables.
- Publicación de reportes como artefactos.
- Prueba básica de rendimiento mediante k6.
- Dashboard de métricas.
- Estrategia de monitoreo y alertas.

En la ejecución final local se obtuvo:

```text
Tests run: 8
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Además, las ejecuciones finales del pipeline mediante GitHub Actions finalizaron satisfactoriamente.

## Conclusión

La implementación realizada permitió desarrollar un flujo completo de automatización de pruebas combinando pruebas unitarias, pruebas de integración, BDD, pruebas de aceptación, Integración Continua, reporting y análisis básico de rendimiento.

JUnit permitió validar las funcionalidades implementadas, mientras que Cucumber y Gherkin permitieron representar el comportamiento esperado mediante escenarios comprensibles y reutilizables.

Git y GitHub permitieron mantener la trazabilidad de los cambios mediante ramas, commits y Pull Requests. GitHub Actions permitió automatizar el Build, las pruebas y las validaciones necesarias antes de integrar las modificaciones a la rama principal.

La incorporación de un Acceptance Test Gate permitió establecer un punto de control antes del despliegue, mientras que el deployment simulado permitió representar el proceso de entrega de una versión previamente validada.

Finalmente, la estrategia de rollback permitió representar un mecanismo de recuperación ante fallos, complementando el monitoreo del pipeline. Por otra parte, k6 permitió analizar métricas básicas de rendimiento relacionadas con throughput, latencia y errores.

De esta forma, el proyecto integra distintas técnicas y herramientas orientadas a mejorar la calidad, trazabilidad, monitoreo y automatización del proceso de pruebas y entrega de software.
