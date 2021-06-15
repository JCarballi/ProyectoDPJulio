import java.io.BufferedWriter;
/**
 * Clase PilotoEstrella
 *Clase utilizada para el tipo depiloto estrella del strategy
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 * */
public class PilotoEstrella extends Piloto{
	/**
	 * Constructor parametrizado de la clase usando el constructor de la clase padre.
	 *
	 * */
	PilotoEstrella(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto, BufferedWriter writer){
		super(nombrePiloto, c, concentracionPiloto, writer);
	}
	/**
	 * Método que calcula la destreza del piloto para un piloto estrella.
	 * @return la destreza calculada del piloto y redondeada.
	 * 
	 *
	 * */
	@Override
	public double calcularDestrezaPiloto()	{
		return Math.round(((((getConcentracionActual()+6)/140)*1.06)+0.05)*100d)/100d;	 
	}

}
