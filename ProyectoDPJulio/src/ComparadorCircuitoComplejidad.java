import java.util.*;
public class ComparadorCircuitoComplejidad implements Comparator<CircuitoProyectoInterfaz>{
	@Override
	public int compare(CircuitoProyectoInterfaz cir1, CircuitoProyectoInterfaz cir2) {
		if(cir1.getValorComplejidadCircuito()==cir2.getValorComplejidadCircuito())
			return 0;
		else if(cir1.getValorComplejidadCircuito()>cir2.getValorComplejidadCircuito())
			return 1;
		else
			return -1;

	}

}
