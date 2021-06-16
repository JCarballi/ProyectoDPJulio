/**
 * Clase ComparadorPilotoUltimoTiempoCarrera
 * Se usa en organizacion para asignar los puntos ordenando los pilotos de menor a mayor tiempo.
 * Es un comparador para la salida porque tenemos descalificados y no descalificados en la misma estructura.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
import java.util.*;

public class ComparadorPilotoUltimoTiempoCarrera implements Comparator<Piloto> {
	@Override
	public int compare(Piloto p1,Piloto p2) {
		if(p1.getUltimoTiempo()==p2.getUltimoTiempo())
			return (p1.getNombrePiloto().compareTo(p2.getNombrePiloto()));
		else if(p1.getUltimoTiempo() < 0 && p2.getUltimoTiempo() < 0) {/*si ambos son negativos lo hacemos al reves porque un numero negativo menor es mas grande que un numero negativo con valor mayor*/
			if(p1.getUltimoTiempo() < p2.getUltimoTiempo())
				return -1;
			else
				return 1;
		}
		else if(p1.getUltimoTiempo() < 0)/*si uno es positivo y el otro no,devolvemos 1 si el segundo es mayor*/
			return 1;
		else if(p2.getUltimoTiempo() < 0)/*devolvemos -1 si el segundo es menor*/
			return -1;
		else if(p1.getUltimoTiempo()<p2.getUltimoTiempo())
			return -1;
		else
			return 1;
	}	

	
}
