import java.util.*;
public class ComparadorPilotoPuntos implements Comparator<Piloto> {

	public int compare(Piloto p1,Piloto p2) {
		if(p1.puntosAcumulados()==p2.puntosAcumulados()) {
			if(p1.calcularDestrezaPiloto()==p2.calcularDestrezaPiloto()) {
				return p1.getNombrePiloto().compareTo(p2.getNombrePiloto())	;
			}
			else if(p1.calcularDestrezaPiloto()>p2.calcularDestrezaPiloto())
				return 1;
			else
				return -1;
		}
		else if(p1.puntosAcumulados()>p2.puntosAcumulados())
			return 1;
		else 
			return -1;
	}

}	
