

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PilotoNovatoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class PilotoNovatoTest
{
    private CocheProyecto cochePro1;
    private PilotoNovato pilotoNo1;
    private CircuitoProyecto circuito1;
    private CircuitoProyecto circuito2;
    private CircuitoProyecto circuito3;
    private Resultado resultad1;
    private Resultado resultad2;
    private Resultado resultad3;

    
    
    
    /**
     * Default constructor for test class PilotoNovatoTest
     */
    public PilotoNovatoTest()
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
        cochePro1 = new CocheProyecto("Seat Arona", Velocidad.RAPIDA, Combustible.ESCASO, null);
        pilotoNo1 = new PilotoNovato("Blomquist", cochePro1, Concentracion.DESPISTADO, null);
        circuito1 = new CircuitoProyecto(Complejidad.ALTA, Distancia.LARGA, "Australia");
        circuito2 = new CircuitoProyecto(Complejidad.MEDIA, Distancia.CORTA, "Italia");
        circuito3 = new CircuitoProyecto(Complejidad.BAJA, Distancia.INTERMEDIA, "Inglaterra");
        resultad1 = new Resultado(100.0, 10, circuito1);
        resultad2 = new Resultado(-50.5, 0.0, circuito2);
        resultad3 = new Resultado(56.8, 0.0, circuito3);
        pilotoNo1.setResults(resultad1, "Australia");
        pilotoNo1.setResults(resultad2, "Italia");
        pilotoNo1.setResults(resultad3, "Inglaterra");
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
        assertEquals(0.7, pilotoNo1.calcularDestrezaPiloto(), 0.1);
    }

    @Test
    public void testPuntosAcumulados()
    {
        assertEquals(10.0, pilotoNo1.puntosAcumulados(), 0.1);
    }

    @Test
    public void testTotalParticipadas()
    {
        assertEquals(3, pilotoNo1.totalParticipadas());
    }

    @Test
    public void testTotalTerminadas()
    {
        assertEquals(2, pilotoNo1.totalTerminadas());
    }

    @Test
    public void testTotalAbandonadas()
    {
        assertEquals(1, pilotoNo1.totalAbandonadas());
    }
}





