/**
 * Clase CircuitoProyecto
 * Clase que implementa la interfaz del decorator.Se trata de los circuitos normales sin complicaciones.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class CircuitoProyecto implements CircuitoProyectoInterfaz {
	private Complejidad complejidadCircuito;
	private Distancia   distanciaCircuito;
	private String nombreCircuito;

	public CircuitoProyecto(Complejidad complejidadCircuito, Distancia distanciaCircuito, String nombreCircuito) {
		this.complejidadCircuito = complejidadCircuito;
		this.distanciaCircuito = distanciaCircuito;
		this.nombreCircuito = nombreCircuito;
	}
	public CircuitoProyecto() {
		this.complejidadCircuito=null;
		this.distanciaCircuito=null;
		this.nombreCircuito="";
	}
	public Complejidad getComplejidadCircuito() {
		return complejidadCircuito;
	}
	public void setComplejidadCircuito(Complejidad complejidadCircuito) {
		this.complejidadCircuito = complejidadCircuito;
	}
	public Distancia getDistanciaCircuito() {
		return distanciaCircuito;
	}
	public void setDistanciaCircuito(Distancia distanciaCircuito) {
		this.distanciaCircuito = distanciaCircuito;
	}
	public String getNombreCircuito() {
		return nombreCircuito;
	}
	public void setNombreCircuito(String nombreCircuito) {
		this.nombreCircuito = nombreCircuito;
	}
	public double getValorDistanciaCircuito() {
		return this.distanciaCircuito.getValor();
	}
	public double getValorComplejidadCircuito() {
		return this.complejidadCircuito.getValor();
	}
	/**
	 *Método para mostrar las complicaciones en un circuito normal(ninguna)
	 * @return  ""
	 */
	public String getComplicaciones(){
		return "";
	}

	@Override
	public String toString() {
		return "<circuito:"+getNombreCircuito()+"> <cond:"+ getComplicaciones()+"> <comp: "+getComplejidadCircuito().toString()+"(actual:"+getValorComplejidadCircuito()+")> <dist: "+getDistanciaCircuito().toString()+"(actual:"+getValorDistanciaCircuito()+")>";
	}

}
