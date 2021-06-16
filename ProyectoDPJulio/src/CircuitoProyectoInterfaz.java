/**
 * Clase CircuitoProyectointerfaz
 * Interfaz de circuitos  para cumplir con el patron decorator.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public interface CircuitoProyectoInterfaz {
	public abstract Complejidad getComplejidadCircuito();
	public abstract void setComplejidadCircuito(Complejidad complejidadCircuito);
	public abstract Distancia getDistanciaCircuito();
	public abstract void setDistanciaCircuito(Distancia distanciaCircuito);
	public abstract String getNombreCircuito();
	public abstract void setNombreCircuito(String nombreCircuito);
	public abstract double getValorDistanciaCircuito();
	public abstract double getValorComplejidadCircuito();
	public abstract String getComplicaciones();

}
