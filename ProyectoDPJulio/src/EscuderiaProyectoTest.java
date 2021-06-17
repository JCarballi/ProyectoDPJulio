

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EscuderiaProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class EscuderiaProyectoTest
{
    private ComparadorCocheParaInitData cc1;
    private ComparadorPilotoPuntos cp1;
    private EscuderiaProyecto escuderi1;
    private CocheProyecto coche1;
    private CocheResistenteProyecto coche2;
    private CocheRapidoProyecto coche3;
    private PilotoExperimentado p1;
    private PilotoNovato p2;
    private PilotoEstrella p3;
    private CircuitoProyecto circuito1;
    private CircuitoProyecto circuito2;
    private Resultado r1;
    private Resultado r2;

    /**
     * Default constructor for test class EscuderiaProyectoTest
     */
    public EscuderiaProyectoTest()
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
        cc1 = new ComparadorCocheParaInitData();
        cp1 = new ComparadorPilotoPuntos();
        escuderi1 = new EscuderiaProyecto("Peugeot", cc1, cp1, null);
        coche1 = new CocheProyecto("Peugeot 2008", Velocidad.NORMAL, Combustible.ESCASO, null);
        coche2 = new CocheResistenteProyecto("Peugeot 5008", Velocidad.LENTA, Combustible.GENEROSO, null);
        coche3 = new CocheRapidoProyecto("Peugeot 3008", Velocidad.RAPIDA, Combustible.NORMAL, null);
        coche3.setVelocidadCoche(Velocidad.GUEPARDO);
        coche3.setCombustibleCoche(Combustible.ELEFANTE);
        p1 = new PilotoExperimentado("Kankunnen", null, Concentracion.CONCENTRADO, null);
        p2 = new PilotoNovato("Sordo", null, Concentracion.DESPISTADO, null);
        p3 = new PilotoEstrella("Sainz", null, Concentracion.ZEN, null);
        escuderi1.meterPilotoEscuderia(p1);
        escuderi1.meterPilotoEscuderia(p2);
        escuderi1.meterPilotoEscuderia(p3);
        escuderi1.meterCocheEscuderia(coche1);
        escuderi1.meterCocheEscuderia(coche2);
        escuderi1.meterCocheEscuderia(coche3);
        circuito1 = new CircuitoProyecto(Complejidad.BAJA, Distancia.CORTA, "Australia");
        circuito2 = new CircuitoProyecto(Complejidad.MEDIA, Distancia.INTERMEDIA, "Suzuka");
        r1 = new Resultado(5.0, 2.0, circuito1);
        r2 = new Resultado(-4.2, 0.0, circuito2);
        p1.setResults(r1, circuito1.getNombreCircuito());
        p2.setResults(r2, circuito2.getNombreCircuito());
        p3.setResults(r1, circuito1.getNombreCircuito());
        p3.setResults(r2, circuito2.getNombreCircuito());
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
    public void testIsDescalificada()
    {
        assertEquals(false, escuderi1.isDescalificada());
        p3.setDescalificado(true);
        p2.setDescalificado(true);
        p1.setDescalificado(true);
        assertEquals(true, escuderi1.isDescalificada());
    }

    @Test
    public void testTotalCarreras()
    {
        assertEquals(2, escuderi1.carrerasEscuderiaAcum());
    }

    @Test
    public void testDevolverPilotoEscuderia()
    {
        p1.setC(coche1);
        p2.setC(coche2);
        p3.setC(coche3);
        assertEquals(true, escuderi1.devolverPilotoEscuderia(p1));
    }

    @Test
    public void testPuntosEscuderiaAcumulados()
    {
        assertEquals(4.0, escuderi1.puntosEscuderiaAcum(), 0.1);
    }

    @Test
    public void testDevolverPilotos()
    {
        assertEquals(3, escuderi1.devolverPilotos().size());
    }

    @Test
    public void testEnviarAOrganizacion() throws java.io.IOException
    {
        assertEquals("Sordo", escuderi1.enviarAOrganizacion().getNombrePiloto());
        assertEquals("Peugeot 3008", escuderi1.enviarAOrganizacion().getC().getNombreCoche());
    }
}






