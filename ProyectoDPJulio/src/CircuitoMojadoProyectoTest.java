

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CircuitoMojadoProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CircuitoMojadoProyectoTest
{
    private CircuitoProyecto circuito1;
    private CircuitoMojadoProyecto circuito2;

    
    

    /**
     * Default constructor for test class CircuitoMojadoProyectoTest
     */
    public CircuitoMojadoProyectoTest()
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
        circuito1 = new CircuitoProyecto(Complejidad.MEDIA, Distancia.LARGA, "Alemania");
        circuito2 = new CircuitoMojadoProyecto(circuito1);
        circuito1.setDistanciaCircuito(Distancia.INTERMEDIA);
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
        assertEquals(1.44, circuito2.getValorComplejidadCircuito(), 0.1);
    }

    @Test
    public void testValorDistancia()
    {
        assertEquals(233.75, circuito2.getValorDistanciaCircuito(), 0.1);
    }
}


