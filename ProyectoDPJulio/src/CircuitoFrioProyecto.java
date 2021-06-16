/**
 * Clase CircuitoFrioProyecto
 * Clase que implementa la complicacion circuito frio del proyecto.
 * @author Javier Santamar�a Caballero
 * @author Juan Jos� Carballo Pacheco
 */
public class CircuitoFrioProyecto extends CircuitoProyectoDecorado {
	/*valores usados para el calculo de esta complicacion(complejidad y distancia circuito)*/
	private static final double complejiCirc=1.1;
	private static final double distancia=0.9;

	public CircuitoFrioProyecto(CircuitoProyectoInterfaz cpi) {
		super(cpi);
	}
	/**
	 * M�todo que calcula la complejidad para el circuitoFrio
	 * @return complejidad circuitoFrio
	 */
	@Override/*Para que se calcule el m�todo de esta clase*/
	public double getValorComplejidadCircuito() {
		return Math.round(cp.getValorComplejidadCircuito()*complejiCirc*100d)/100d;
	}

	/**
	 * M�todo que calcula la distancia para el circuitoFrio
	 * @return distancia circuitoFrio
	 */
	@Override/*Para que se calcule el m�todo de esta clase*/
	public double getValorDistanciaCircuito() {
		return Math.round(cp.getValorDistanciaCircuito()*distancia*100d)/100d;
	}

}
