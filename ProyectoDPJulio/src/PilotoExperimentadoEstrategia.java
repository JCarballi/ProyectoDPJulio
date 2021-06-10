
public class PilotoExperimentadoEstrategia  implements EstrategiaPilotos{
	
	
	public double CalcularDestrezaP(double concentracion)	{
		 
		return ((concentracion+3)/130)*1.03;
		 
	 }
	 @Override
		public String toString() {
			return "PilotoExperimentadoEstrategia [destreza=]";
		}


}
