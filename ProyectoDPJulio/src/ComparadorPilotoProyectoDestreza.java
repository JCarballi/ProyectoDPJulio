import java.util.*;
/**
 * Clase ComparadorPilotoProyectoDestreza
 * Comparador para ordenar los pilotos por valor de destreza.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorPilotoProyectoDestreza implements Comparator<Piloto> {
	@Override
	public int compare(Piloto p1,Piloto p2) {
		if(p1.calcularDestrezaPiloto()==p2.calcularDestrezaPiloto())
			return 0;
		else if(p1.calcularDestrezaPiloto()>p2.calcularDestrezaPiloto())
			return 1;
		else
			return -1;
				
	}
	

}
