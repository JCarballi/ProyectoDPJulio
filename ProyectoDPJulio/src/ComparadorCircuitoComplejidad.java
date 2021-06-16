import java.util.*;
/**
 * Clase ComparadorCircuitoComplejidad
 * Comparador para ordenar los circuitos por complejidad en la salida DatosCampeonatoFinPrematuro.
 * @author Javier Santamar�a Caballero
 * @author Juan Jos� Carballo Pacheco
 */
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
