# Automatización de Pruebas

Proyecto desarrollado para implementar un flujo básico de automatización de pruebas utilizando Java, Maven, JUnit 5, Git, GitHub y GitHub Actions.

El proyecto permite ejecutar pruebas unitarias de manera local y automática mediante un pipeline de integración continua, además de generar un reporte HTML con los resultados obtenidos.

## Objetivo

Implementar un proceso de automatización que permita validar funcionalidades mediante pruebas unitarias y ejecutar dichas pruebas automáticamente durante el proceso de integración continua.

## Tecnologías utilizadas

- Java 21
- Apache Maven 3.9.16
- JUnit 5
- Git
- GitHub
- GitHub Actions
- Maven Surefire
- Maven Surefire Report Plugin

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
│       └── java/
│           └── cl/iplacex/automatizacion/
│               └── CalculadoraTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

## Pruebas unitarias

Se implementaron pruebas unitarias utilizando JUnit 5 para comprobar el funcionamiento de la clase `Calculadora`.

Las pruebas realizadas validan las siguientes operaciones:

- Suma de dos números.
- Resta de dos números.

Cada prueba se ejecuta de manera independiente y utiliza aserciones para comparar el resultado esperado con el resultado obtenido.

## Ejecución de las pruebas

Para ejecutar las pruebas unitarias desde la terminal se utiliza:

```bash
mvn test
```

Una ejecución correcta muestra un resultado similar a:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Reporte de pruebas

Para ejecutar las pruebas y generar un reporte HTML se utiliza:

```bash
mvn clean test surefire-report:report
```

El reporte generado se encuentra en:

```text
target/reports/surefire.html
```

El reporte permite consultar de manera visual los resultados de las pruebas, incluyendo cantidad de pruebas ejecutadas, errores, fallos, pruebas omitidas y porcentaje de éxito.

En la ejecución realizada se obtuvo:

```text
Tests: 2
Errors: 0
Failures: 0
Skipped: 0
Success Rate: 100%
```

## Integración continua

El proyecto utiliza GitHub Actions para implementar un pipeline de integración continua.

El workflow se encuentra en:

```text
.github/workflows/ci.yml
```

El pipeline ejecuta automáticamente la compilación y las pruebas del proyecto Maven cuando se realizan cambios definidos en el flujo de integración del repositorio.

Esto permite detectar posibles errores antes de integrar cambios al proyecto principal.

## Control de versiones

Para el desarrollo se utilizó Git mediante una estrategia basada en ramas.

Entre las ramas utilizadas se encuentran:

```text
main
feature/configuracion-inicial
feature/integracion-continua
```

Los cambios fueron registrados mediante commits descriptivos y posteriormente integrados a la rama principal mediante Pull Requests.

## Flujo de trabajo

El proceso implementado considera las siguientes etapas:

1. Desarrollo o modificación del código.
2. Creación y ejecución de pruebas unitarias.
3. Ejecución local mediante Maven.
4. Registro de cambios mediante Git.
5. Trabajo mediante ramas.
6. Creación de Pull Request.
7. Ejecución automática del pipeline con GitHub Actions.
8. Validación de las pruebas.
9. Integración de los cambios a la rama `main`.
10. Generación y revisión del reporte de pruebas.

## Resultado

La automatización implementada permitió ejecutar correctamente las dos pruebas unitarias desarrolladas, obteniendo un 100% de éxito, sin errores ni fallos.

Además, el uso de GitHub Actions permite automatizar la validación del proyecto durante el proceso de integración continua, mientras que Maven Surefire permite generar un reporte navegable con los resultados de las pruebas.