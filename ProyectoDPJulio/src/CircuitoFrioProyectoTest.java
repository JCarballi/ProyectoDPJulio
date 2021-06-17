

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CircuitoFrioProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CircuitoFrioProyectoTest
{
    private CircuitoProyecto circuito1;
    private CircuitoFrioProyecto circuito2;

    /**
     * Default constructor for test class CircuitoFrioProyectoTest
     */
    public CircuitoFrioProyectoTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        circuito1 = new CircuitoProyecto(Complejidad.BAJA, Distancia.CORTA, "Azerbaijan");
        circuito2 = new CircuitoFrioProyecto(circuito1);
        circuito2.getValorComplejidadCircuito();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testValorComplejidad()
    {
        assertEquals(1.1, circuito2.getValorComplejidadCircuito(), 0.1);
    }

    @Test
    public void testValorDistancia()
    {
        assertEquals(225.0, circuito2.getValorDistanciaCircuito(), 0.1);
    }
}


