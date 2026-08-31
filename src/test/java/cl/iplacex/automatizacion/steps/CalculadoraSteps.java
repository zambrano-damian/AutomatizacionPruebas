package cl.iplacex.automatizacion.steps;

import cl.iplacex.automatizacion.Calculadora;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraSteps {

    private Calculadora calculadora;
    private int resultado;

    @Dado("que tengo una calculadora")
    public void queTengoUnaCalculadora() {
        calculadora = new Calculadora();
    }

    @Cuando("sumo {int} y {int}")
    public void sumoY(int numero1, int numero2) {
        resultado = calculadora.sumar(numero1, numero2);
    }

    @Cuando("resto {int} y {int}")
    public void restoY(int numero1, int numero2) {
        resultado = calculadora.restar(numero1, numero2);
    }

    @Entonces("el resultado debe ser {int}")
    public void elResultadoDebeSer(int esperado) {
        assertEquals(esperado, resultado);
    }
}