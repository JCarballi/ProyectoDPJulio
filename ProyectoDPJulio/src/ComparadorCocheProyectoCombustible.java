import java.util.*;
/**
 * Clase ComparadorCocheproyectoCombustible
 * Comparador para ordenar los coches por valor de combustible actual.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorCocheProyectoCombustible implements Comparator<InterfazCoches> {
	@Override
	public int compare(InterfazCoches c1,InterfazCoches c2) {
		if(c1.getCombustibleAct()==c2.getCombustibleAct())
			return 0;
		else if(c1.getCombustibleAct()>c2.getCombustibleAct())
			return 1;
		else
			return -1;
	}	
}
