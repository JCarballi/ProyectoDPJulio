

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PilotoEstrellaTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class PilotoEstrellaTest
{
    private CocheResistenteProyecto cocheRes1;
    private PilotoNovato pilotoNo1;


    /**
     * Default constructor for test class PilotoEstrellaTest
     */
    public PilotoEstrellaTest()
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
        cocheRes1 = new CocheResistenteProyecto("Citröen C5", Velocidad.RAPIDA, Combustible.ELEFANTE, null);
        pilotoNo1 = new PilotoNovato("Makinen", cocheRes1, Concentracion.ZEN, null);
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
        assertEquals(1.0, pilotoNo1.calcularDestrezaPiloto(), 0.1);
    }
}





