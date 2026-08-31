# language: es

Característica: Operaciones de la calculadora
  Como usuario
  Quiero realizar operaciones matemáticas
  Para obtener resultados correctos

  Escenario: Sumar dos números correctamente
    Dado que tengo una calculadora
    Cuando sumo 5 y 3
    Entonces el resultado debe ser 8

  Esquema del escenario: Restar diferentes números correctamente
    Dado que tengo una calculadora
    Cuando resto <numero1> y <numero2>
    Entonces el resultado debe ser <resultado>

    Ejemplos:
      | numero1 | numero2 | resultado |
      | 10      | 4       | 6         |
      | 20      | 8       | 12        |
      | 15      | 5       | 10        |