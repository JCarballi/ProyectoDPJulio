public class CocheProyecto implements InterfazCoches {
	private String nombreCoche;
	private Velocidad velocidadCoche;
	private Combustible combustibleCoche;
	private double combustibleAct;
	
	public double getCombustibleAct() {
		return combustibleAct;
	}
	public void reducirCombustible(double combustibleAct) {
		this.combustibleAct -= combustibleAct;
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

	public CocheProyecto(String nombreCoche, Velocidad velocidadCoche, Combustible combustibleCoche) {

		this.nombreCoche = nombreCoche;
		this.velocidadCoche = velocidadCoche;
		this.combustibleCoche = combustibleCoche;
		this.combustibleAct=this.combustibleCoche.getValor();
	}
	public CocheProyecto() {
		this.nombreCoche="";
		this.velocidadCoche=null;
		this.combustibleCoche =null;
	}
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) {
		return Math.round(((this.velocidadCoche.getValor() *destreza) / c.getValorComplejidadCircuito())*100d)/100d;	

	}
	/**
	 * Metodo que calcula el tiempo necesario para acabar la carrera
	 * @param destreza es la destreza del piloto
	 * @param c        Es el circuito actual en el que esta corriendo
	 * @return tiempo finalizacion de la carrera
	 * 
	 */
	public double tiempoNecesarioTerminar(double destreza, CircuitoProyectoInterfaz c) {
		double veloReal=this.velocidadRealCoche(destreza, c);
		System.out.println("Con estas condiciones es capaz de correr a "+ veloReal +"km/h");
		return  Math.round(((c.getValorDistanciaCircuito()/veloReal)*60)*100d)/100d;
	}
	public double combustibleFinaldeCoche(double valor) {

		return this.combustibleAct-valor;
		
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

	@Override
	public String toString() {
		return "<coche: "+getNombreCoche()+"> <tipo:"+getClass().getName()+"> <vel_teó: "+getVelocidadCoche().toString()+"> <comb: "+getCombustibleCoche().toString()+"(actual: "+getCombustibleAct()+")>";

	}

}
