/**
 * Clase CircuitoMojadoProyecto
 * Clase que implementa la complicacion circuito mojado del proyecto.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */

public class CircuitoMojadoProyecto extends CircuitoProyectoDecorado{
	/*valores usados para el calculo de esta complicacion(complejidad y distancia circuito)*/
	private static final double complejiCirc=1.15;
	private static final double distancia=0.85;

	public CircuitoMojadoProyecto(CircuitoProyectoInterfaz cpi) {
		super(cpi);
	}

	/**
	 * Método que calcula la complejidad para el circuitoMojado
	 * @return complejidad circuitoMojado
	 */
	@Override/*Para que se calcule el método de esta clase*/
	public double getValorComplejidadCircuito() {
		return Math.round(cp.getValorComplejidadCircuito()*complejiCirc*100d)/100d;
	}

	/**
	 * Método que calcula la distancia para el circuitoMojado
	 * @return distancia circuitoMojado
	 */
	@Override/*Para que se calcule el método de esta clase*/
	public double getValorDistanciaCircuito() {
		return Math.round(cp.getValorDistanciaCircuito()*distancia*100d)/100d;
	}

}
