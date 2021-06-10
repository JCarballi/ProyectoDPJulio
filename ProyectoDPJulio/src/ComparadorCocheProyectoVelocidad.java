import java.util.*;
public class ComparadorCocheProyectoVelocidad implements Comparator<InterfazCoches>{

	public int compare(InterfazCoches c1,InterfazCoches c2) {
		if(c1.getVelocidadCoche().getValor()==c2.getVelocidadCoche().getValor())
			return 0;
		else if(c1.getVelocidadCoche().getValor()>c2.getVelocidadCoche().getValor())
			return 1;
		else 
			return -1;
	}

}
