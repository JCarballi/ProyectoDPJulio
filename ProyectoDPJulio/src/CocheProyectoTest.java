

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CocheProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CocheProyectoTest
{
    private CircuitoProyecto circuito1;
    private CocheProyecto cochePro1;
    private PilotoNovato pilotoNo1;
    private CircuitoGravillaProyecto circuito2;


    /**
     * Default constructor for test class CocheProyectoTest
     */
    public CocheProyectoTest()
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
        cochePro1 = new CocheProyecto("Peugeot 2008", Velocidad.NORMAL, Combustible.ESCASO, null);
        pilotoNo1 = new PilotoNovato("Sordo", cochePro1, Concentracion.DESPISTADO, null);
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
    public void testVelocidadReal() throws java.io.IOException
    {
        assertEquals(146.67, cochePro1.velocidadRealCoche(pilotoNo1.calcularDestrezaPiloto(), circuito2), 0.1);
    }

    @Test
    public void testTiempoNecesario() throws java.io.IOException
    {
        assertEquals(116.59, cochePro1.tiempoNecesarioTerminar(pilotoNo1.calcularDestrezaPiloto(), circuito2), 0.1);
    }

    @Test
    public void testReducirCombustible()
    {
        cochePro1.reducirCombustible(90);
        assertEquals(260, cochePro1.getCombustibleAct(), 0.1);
    }
}




