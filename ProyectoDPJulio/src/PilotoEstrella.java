import java.io.BufferedWriter;

public class PilotoEstrella extends Piloto{

	PilotoEstrella(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto, BufferedWriter writer){
		super(nombrePiloto, c, concentracionPiloto, writer);
	}

	@Override
	public double calcularDestrezaPiloto()	{
		return Math.round(((((getConcentracionActual()+6)/140)*1.06)+0.05)*100d)/100d;	 
	}

}
