import java.util.*;
/**
 * Clase ComparadorCocheProyectoVelocidad
 * Comparador para ordenar los coches por valor de velocidad.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorCocheProyectoVelocidad implements Comparator<InterfazCoches>{
	@Override
	public int compare(InterfazCoches c1,InterfazCoches c2) {
		if(c1.getVelocidadCoche().getValor()==c2.getVelocidadCoche().getValor())
			return 0;
		else if(c1.getVelocidadCoche().getValor()>c2.getVelocidadCoche().getValor())
			return 1;
		else 
			return -1;
	}

}
