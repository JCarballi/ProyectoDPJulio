/**
 * Clase Resultado 
 * clase que almacena los resultados de los pilotos(tiempo y puntos).
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 * */
public class Resultado {
	private double tiempo;/*tiempo empleado por ese piloto en ese circuito*/
	private double puntos;/*puntos obtenidos por ese piloto en ese circuito*/
	private CircuitoProyectoInterfaz  cp;/*Circuito en el que compite ese piloto*/

	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public double getPuntos() {
		return puntos;
	}
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	public CircuitoProyectoInterfaz getCp() {
		return cp;
	}
	public void setCp(CircuitoProyectoInterfaz cp) {
		this.cp = cp;
	}
	/**
	 * Constructor parametrizado de la clase.
	 *
	 * */
	public Resultado(double tiempo, double puntos, CircuitoProyectoInterfaz cp) {
		this.tiempo = tiempo;
		this.puntos = puntos;
		this.cp = cp;
	}
	/**
	 * Constructor por defecto de la clase.
	 *
	 * */
	public Resultado() {
		this.tiempo = 0.0;
		this.puntos = 0.0;
		this.cp = null;	
	}
	/**
	 * Método toString de la clase
	 * */
	@Override
	public String toString() {
		return "Tiempo: "+this.tiempo+" - Puntos: "+this.puntos;
	}

}
