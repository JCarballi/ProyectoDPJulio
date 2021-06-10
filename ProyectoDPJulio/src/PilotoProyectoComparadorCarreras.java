import java.util.Comparator;

public class PilotoProyectoComparadorCarreras implements Comparator<Piloto>{
	@Override
	public int compare(Piloto p1, Piloto p2) {
		if(p1.puntosAcumulados()>p2.puntosAcumulados())
			return 1;
		else if(p1.puntosAcumulados()<p2.puntosAcumulados())
			return -1;
		else {
			return new ComparadorPilotoTotalCarreras().compare(p1, p2);

		}

	}

}
