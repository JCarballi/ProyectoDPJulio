import java.io.BufferedWriter;
/**
 * Clase PilotoExperimentado
 * clase utilizada para el tipo de piloto experimentado del strategy
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 * */
public class PilotoExperimentado extends Piloto{
	/**
	 * Constructor parametrizado de la clase utilizando la superclase.
	 *
	 * */
	public PilotoExperimentado(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto, BufferedWriter writer){
		super(nombrePiloto, c, concentracionPiloto, writer);
	}
	/**
	 * Método que calcula la destreza del piloto para un piloto experimentado.
	 * @return la destreza calculada del piloto y redondeada.
	 * 
	 *
	 * */
	@Override
	public double calcularDestrezaPiloto()	{
		return Math.round((((getConcentracionActual()+3)/130)*1.03)*100d)/100d;	 
	}

}
