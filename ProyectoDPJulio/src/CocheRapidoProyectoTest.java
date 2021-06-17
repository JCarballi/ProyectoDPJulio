

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CocheRapidoProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CocheRapidoProyectoTest
{
    private CircuitoProyecto c;
    private CircuitoGravillaProyecto c2;
    private CocheRapidoProyecto cr1;
    private PilotoEstrella pilotoEs1;

    
    
    
    

    /**
     * Default constructor for test class CocheRapidoProyectoTest
     */
    public CocheRapidoProyectoTest()
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
        c = new CircuitoProyecto(Complejidad.ALTA, Distancia.CORTA, "Chile");
        c2 = new CircuitoGravillaProyecto(c);
        cr1 = new CocheRapidoProyecto("Peugeot 3008", Velocidad.GUEPARDO, Combustible.NORMAL, null);
        pilotoEs1 = new PilotoEstrella("Sainz", cr1, Concentracion.ZEN, null);
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
    public void testVelocidadReal() throws java.io.IOException
    {
        assertEquals(182.28, cr1.velocidadRealCoche(pilotoEs1.calcularDestrezaPiloto(), c2), 0.1);
    }
}

