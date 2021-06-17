import java.io.IOException;
/**
 * Clase InterfazCoches
 * Clase utilizada para el strategy en pilotos ya que los pilotos pueden cambiar de distintos coches en tiempo de ejecución.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public interface InterfazCoches {/*Para el strategy en Pilotos*/
	/*nota:los métodos que sean necesarios serán explicados en la clase que los implementa*/
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) throws IOException;
	public double tiempoNecesarioTerminar(double destreza, CircuitoProyectoInterfaz c) throws IOException;
	public void reducirCombustible(double combustibleAct);
	public String getNombreCoche();
	public double getCombustibleAct();
	public Velocidad getVelocidadCoche();

}
