public class PilotoNovato extends Piloto {

	public PilotoNovato(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto){
		super(nombrePiloto, c, concentracionPiloto);
	}

	@Override
	public double calcularDestrezaPiloto()	{
		return Math.round((((getConcentracionActual()*0.97)/120)-0.03)*100d)/100d; 
	}

}
