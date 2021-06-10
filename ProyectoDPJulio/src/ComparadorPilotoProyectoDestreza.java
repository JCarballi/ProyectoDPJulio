import java.util.*;
public class ComparadorPilotoProyectoDestreza implements Comparator<Piloto> {
	
	public int compare(Piloto p1,Piloto p2) {
		if(p1.calcularDestrezaPiloto()==p2.calcularDestrezaPiloto())
			return 0;
		else if(p1.calcularDestrezaPiloto()>p2.calcularDestrezaPiloto())
			return 1;
		else
			return -1;
				
	}
	

}
