package cl.iplacex.automatizacion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraIntegracionTest {

    @Test
    void flujoCompletoDeOperaciones() {

        // Se crea una instancia real de la calculadora
        Calculadora calculadora = new Calculadora();

        // Primera operación: suma
        int resultadoSuma = calculadora.sumar(100, 50);

        // Segunda operación: se utiliza el resultado anterior
        int resultadoFinal = calculadora.restar(resultadoSuma, 30);

        // Se valida el resultado del flujo completo
        assertEquals(120, resultadoFinal);
    }
}