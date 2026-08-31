package cl.iplacex.automatizacion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    @Test
    void deberiaSumarDosNumerosCorrectamente() {
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.sumar(5, 3);

        assertEquals(8, resultado);
    }

    @Test
    void deberiaRestarDosNumerosCorrectamente() {
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.restar(10, 4);

        assertEquals(6, resultado);
    }
}