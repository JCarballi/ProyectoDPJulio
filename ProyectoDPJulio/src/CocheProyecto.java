import java.io.BufferedWriter;
import java.io.IOException;
/**
 * Clase CocheProyecto
 * Clase padre de la herencia  para los coches que a su vez representa a los coches normales.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
/*En este caso utilizamos herencia porque cada subclase implementa funcionalidades diferentes y no se aplica strategy porque no se usa para evitar duplicidad de codigo*/
public class CocheProyecto implements InterfazCoches {
	private String nombreCoche;
	private Velocidad velocidadCoche;
	private Combustible combustibleCoche;
	protected double combustibleAct; /*protegido  para poder usarlo en los hijos de la herencia*/
	protected BufferedWriter writer;

	public double getCombustibleAct() {
		return combustibleAct;
	}
	/**
	 *Método para reducir el combustible de un coche de manera normal segun el tiempo empleado en la carrera.
	 *@param combustibleAct combustible a reducir segun el tiempo empleado en esa carrera.
	 */
	public void reducirCombustible(double combustibleAct) {
		this.combustibleAct = Math.round((this.combustibleAct - combustibleAct)*100d)/100d;
	}

	public String getNombreCoche() {
		return nombreCoche;
	}
	public void setNombreCoche(String nombreCoche) {
		this.nombreCoche = nombreCoche;
	}
	public Velocidad getVelocidadCoche() {
		return velocidadCoche;
	}

	public void setVelocidadCoche(Velocidad velocidadCoche) {
		this.velocidadCoche = velocidadCoche;
	}

	public Combustible getCombustibleCoche() {
		return combustibleCoche;
	}


	public void setCombustibleCoche(Combustible combustibleCoche) {
		this.combustibleCoche = combustibleCoche;
	}

	public CocheProyecto(String nombreCoche, Velocidad velocidadCoche, Combustible combustibleCoche,BufferedWriter writer) {

		this.nombreCoche = nombreCoche;
		this.velocidadCoche = velocidadCoche;
		this.combustibleCoche = combustibleCoche;
		this.combustibleAct=this.combustibleCoche.getValor();
		this.writer=writer;
	}
	public CocheProyecto() {
		this.nombreCoche="";
		this.velocidadCoche=null;
		this.combustibleCoche =null;
		this.writer=null;
	}
	/**
	 *Método para calcular la velocidad real del coche de manera normal aplicando la fórmula.
	 *@param destreza del piloto
	 *@param c circuito usado
	 *@throws IOException
	 *@return veloReal
	 */
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) throws IOException {
		double veloReal = Math.round(((this.velocidadCoche.getValor() *destreza) / c.getValorComplejidadCircuito())*100d)/100d;	

		System.out.println("Con estas condiciones es capaz de correr a "+ veloReal +"km/h");
		writer.write("Con estas condiciones es capaz de correr a "+ veloReal +"km/h\n");

		return veloReal;	

	}
	/**
	 * Metodo que calcula el tiempo necesario para acabar la carrera
	 * @param destreza es la destreza del piloto
	 * @param c        Es el circuito actual en el que esta corriendo
	 * @return tiempo finalizacion de la carrera
	 * @throws IOException 
	 * 
	 */
	public double tiempoNecesarioTerminar(double destreza, CircuitoProyectoInterfaz c) throws IOException {
		double veloReal=this.velocidadRealCoche(destreza, c);

		return  Math.round(((c.getValorDistanciaCircuito()/veloReal)*60)*100d)/100d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((combustibleCoche == null) ? 0 : combustibleCoche.hashCode());
		result = prime * result + ((nombreCoche == null) ? 0 : nombreCoche.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CocheProyecto other = (CocheProyecto) obj;
		if (combustibleCoche != other.combustibleCoche)
			return false;
		if (nombreCoche == null) {
			if (other.nombreCoche != null)
				return false;
		} else if (!nombreCoche.equals(other.nombreCoche))
			return false;
		return true;
	}

	@Override/*Dependiendo de la clase en la que nos encontremos dela herencialos datos del toString variaran(el tipo de coche)*/
	public String toString() {
		return "<coche: "+getNombreCoche()+"> <tipo:"+getClass().getName()+"> <vel_teó: "+getVelocidadCoche().toString()+"> <comb: "+getCombustibleCoche().toString()+"(actual: "+getCombustibleAct()+")>";

	}

}
