public interface InterfazCoches {
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c);
	public double tiempoNecesarioTerminar(double destreza, CircuitoProyectoInterfaz c);
	public void reducirCombustible(double combustibleAct);
	public String getNombreCoche();
	public double getCombustibleAct();
	public Velocidad getVelocidadCoche();
	
}
