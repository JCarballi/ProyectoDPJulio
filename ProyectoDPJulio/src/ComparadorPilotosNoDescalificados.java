import java.util.*;
public class ComparadorPilotosNoDescalificados  implements Comparator<Piloto>{
	
	public int compare(Piloto p1,Piloto p2) {
		if(p1.puntosAcumulados()==p2.puntosAcumulados()) {
			if(p1.totalTerminadas()==p2.totalTerminadas()) {
				return p1.getNombrePiloto().compareTo(p2.getNombrePiloto())	;

			}
			else if(p1.totalTerminadas()>p2.totalTerminadas()) {
				return 1;
			}
			else 
				return -1;
		}
		else if(p1.puntosAcumulados()>p2.puntosAcumulados())
			return 1;
		else
			return -1;

	}	

}
