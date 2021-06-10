import java.util.Comparator;
public class ComparadorCocheParaInitData implements Comparator<InterfazCoches> {
	
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
