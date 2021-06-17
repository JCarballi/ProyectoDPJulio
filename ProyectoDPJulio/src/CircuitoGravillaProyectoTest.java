

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CircuitoGravillaProyectoTest.
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CircuitoGravillaProyectoTest
{
    private CircuitoProyecto circuito1;
    private CircuitoGravillaProyecto circuito2;

    
    

    /**
     * Default constructor for test class CircuitoGravillaProyectoTest
     */
    public CircuitoGravillaProyectoTest()
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
        circuito1 = new CircuitoProyecto(Complejidad.BAJA, Distancia.LARGA, "Australia");
        circuito2 = new CircuitoGravillaProyecto(circuito1);
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
    public void testValorDistancia()
    {
        assertEquals(285.0, circuito2.getValorDistanciaCircuito(), 0.1);
    }

    @Test
    public void testValorComplejidad()
    {
        assertEquals(1.05, circuito2.getValorComplejidadCircuito(), 0.1);
    }
}


