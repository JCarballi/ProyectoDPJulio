

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class OrganizacionProyectoTest.
 *
 * @author  Juan José Carballo Pacheco
 * @author  Javier Santamaría Caballero
 * @version 14/06/2021
 */
public class OrganizacionProyectoTest
{

    OrganizacionProyecto o;
    private ComparadorCircuitoDistancia comparad2;
    
    /**
     * Default constructor for test class OrganizacionProyectoTest
     */
    public OrganizacionProyectoTest()
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
        comparad2 = new ComparadorCircuitoDistancia();
        o = OrganizacionProyecto.getInstance(2, 3, comparad2, null);
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
    public void testSingleton() throws java.io.IOException
    {
        assertEquals(o, OrganizacionProyecto.getInstance());
    }
}


