import java.io.BufferedWriter;
/**
 * 	Clase PilotoNovato
 *  Clase utilizada para el tipo de piloto novato del strategy.
 *  @author Javier Santamar�a Caballero
 *  @author Juan Jos� Carballo Pacheco
 * */
public class PilotoNovato extends Piloto {
	/**
	 * Constructor parametrizado de la clase que llama al constructor del padre.
	 *
	 * */
	public PilotoNovato(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto, BufferedWriter writer){
		super(nombrePiloto, c, concentracionPiloto, writer);
	}
	/**
	 * M�todo que calcula la destreza del piloto para un piloto novato.
	 * @return la destreza calculada del piloto y redondeada.
	 * 
	 *
	 * */
	@Override /*porque existe en piloto y por tanto tengo que sobreescribirlo para que calcule la destreza del tipo novato en concreto*/
	public double calcularDestrezaPiloto()	{
		return Math.round((((getConcentracionActual()*0.97)/120)-0.03)*100d)/100d; 
	}

}
