import java.util.Comparator;
/**
 * Clase ComparadorCocheParaInitData
 * Comparador para ordenar las escuderias en las 3 salidas.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorCocheParaInitData implements Comparator<InterfazCoches> {
	@Override
	public int compare(InterfazCoches c1,InterfazCoches c2) {
		if(c1.getCombustibleAct()==c2.getCombustibleAct()) {
			return c1.getNombreCoche().compareTo(c2.getNombreCoche());
		}
		else if(c1.getCombustibleAct()>c2.getCombustibleAct())
			return 1;
		else
			return -1;
	}	
}
