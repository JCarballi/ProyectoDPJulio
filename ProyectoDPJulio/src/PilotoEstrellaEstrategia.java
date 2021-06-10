
public class PilotoEstrellaEstrategia   implements EstrategiaPilotos{
	

	public double CalcularDestrezaP(double concentracion)	{
		return (((concentracion+6)/140)*1.06)+0.05;
		 
		 
	 }
	 @Override
		public String toString() {
			return "PilotoEstrellaEstrategia [destreza=]";
		}


}
