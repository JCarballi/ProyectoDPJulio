import java.io.IOException;

public interface InterfazCoches {
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) throws IOException;
	public double tiempoNecesarioTerminar(double destreza, CircuitoProyectoInterfaz c) throws IOException;
	public void reducirCombustible(double combustibleAct);
	public String getNombreCoche();
	public double getCombustibleAct();
	public Velocidad getVelocidadCoche();
	
}
