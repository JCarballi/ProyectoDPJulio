import java.util.*;
public class ComparadorCocheProyectoCombustible implements Comparator<InterfazCoches> {
	
	public int compare(InterfazCoches c1,InterfazCoches c2) {
		if(c1.getCombustibleAct()==c2.getCombustibleAct())
			return 0;
		else if(c1.getCombustibleAct()>c2.getCombustibleAct())
			return 1;
		else
			return -1;
	}	
}
