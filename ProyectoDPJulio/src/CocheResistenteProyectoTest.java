

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CocheResistenteProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class CocheResistenteProyectoTest
{
    private CocheResistenteProyecto cocheRes1;
    private CircuitoProyecto c1;
    private CircuitoNocturnoProyecto c2;
    private CircuitoFrioProyecto c3;
    private CircuitoMojadoProyecto c4;
    private PilotoNovato pilotoNo1;

    /**
     * Default constructor for test class CocheResistenteProyectoTest
     */
    public CocheResistenteProyectoTest()
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
        c1 = new CircuitoProyecto(Complejidad.ALTA, Distancia.CORTA, "Finlandia");
        c2 = new CircuitoNocturnoProyecto(c1);
        c3 = new CircuitoFrioProyecto(c2);
        c4 = new CircuitoMojadoProyecto(c3);
        pilotoNo1 = new PilotoNovato("Auriol", cocheRes1, Concentracion.NORMAL, null);
        cocheRes1.reducirCombustible(459.61);
        cocheRes1.reducirCombustible(-459.61);
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
}

