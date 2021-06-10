import java.util.*;
public class ComparadorPilotoUltimoTiempoCarrera implements Comparator<Piloto> {
	public int compare(Piloto p1,Piloto p2) {
		if(p1.getUltimoTiempo()==p2.getUltimoTiempo())
			return (p1.getNombrePiloto().compareTo(p2.getNombrePiloto()));
		else if(p1.getUltimoTiempo() < 0 && p2.getUltimoTiempo() < 0) {
			if(p1.getUltimoTiempo() < p2.getUltimoTiempo())
				return -1;
			else
				return 1;
		}
		else if(p1.getUltimoTiempo() < 0)
			return 1;
		else if(p2.getUltimoTiempo() < 0)
			return -1;
		else if(p1.getUltimoTiempo()<p2.getUltimoTiempo())
			return -1;
		else
			return 1;
	}	
}
