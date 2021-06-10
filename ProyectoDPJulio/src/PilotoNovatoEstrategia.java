
public class PilotoNovatoEstrategia  implements EstrategiaPilotos {

 
public double CalcularDestrezaP(double concentracion)	{
	return ((concentracion*0.97)/120)-0.03; 
 }
	
@Override
public String toString() {
	return "PilotoNovatoEstrategia [destreza=  ]";
}
	
	
	
	
	
	
}
