public class PilotoExperimentado extends Piloto{

	public PilotoExperimentado(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto){
		super(nombrePiloto, c, concentracionPiloto);
	}

	@Override
	public double calcularDestrezaPiloto()	{
		return Math.round((((getConcentracionActual()+3)/130)*1.03)*100d)/100d;	 
	}

}
