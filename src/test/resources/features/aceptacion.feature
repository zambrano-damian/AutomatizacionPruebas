# language: es

Característica: Validación de aceptación de la calculadora
  Como usuario
  Quiero verificar el funcionamiento principal de la calculadora
  Para asegurar que la aplicación esté preparada para ser desplegada

  Escenario: Validar operación principal antes del despliegue
    Dado que tengo una calculadora
    Cuando sumo 10 y 5
    Entonces el resultado debe ser 15