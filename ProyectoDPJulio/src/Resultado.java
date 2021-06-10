public class Resultado {
	private double tiempo;
	private double puntos;
	private CircuitoProyectoInterfaz  cp;

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
	public Resultado(double tiempo, double puntos, CircuitoProyectoInterfaz cp) {
		this.tiempo = tiempo;
		this.puntos = puntos;
		this.cp = cp;
	}
	public Resultado() {
		this.tiempo = 0.0;
		this.puntos = 0.0;
		this.cp = null;	
	}
	@Override
	public String toString() {
		return "Tiempo: "+this.tiempo+" - Puntos: "+this.puntos;
	}

}
