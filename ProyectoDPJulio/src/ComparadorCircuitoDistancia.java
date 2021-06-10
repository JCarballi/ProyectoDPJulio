import java.util.*;
public class ComparadorCircuitoDistancia implements Comparator<CircuitoProyectoInterfaz> {
	@Override
	public int compare(CircuitoProyectoInterfaz cir1, CircuitoProyectoInterfaz cir2) {
		if(cir1.getValorDistanciaCircuito()==cir2.getValorDistanciaCircuito())
			return 0;
		else if(cir1.getValorDistanciaCircuito()>cir2.getValorDistanciaCircuito())
			return 1;
		else
			return -1;
	}

}
