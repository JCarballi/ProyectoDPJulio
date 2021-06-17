

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PilotoExperimentadoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class PilotoExperimentadoTest
{
    private CocheRapidoProyecto cocheRap1;
    private PilotoExperimentado pilotoEx1;

    /**
     * Default constructor for test class PilotoExperimentadoTest
     */
    public PilotoExperimentadoTest()
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
        cocheRap1 = new CocheRapidoProyecto("Seat Ateca", Velocidad.GUEPARDO, Combustible.GENEROSO, null);
        pilotoEx1 = new PilotoExperimentado("Ogier", cocheRap1, Concentracion.NORMAL, null);
        pilotoEx1.calcularDestrezaPiloto();
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
    public void testCalcularDestreza()
    {
        assertEquals(0.82, pilotoEx1.calcularDestrezaPiloto(), 0.1);
    }
}

