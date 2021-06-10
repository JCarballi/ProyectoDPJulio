import java.util.Comparator;
public class ComparadorEscuderiaFinal implements Comparator<EscuderiaProyecto> {

	public int compare(EscuderiaProyecto e1,EscuderiaProyecto e2) {
		if(e1.puntosEscuderiaAcum() == e2.puntosEscuderiaAcum())
			if(e1.carrerasEscuderiaAcum() == e2.carrerasEscuderiaAcum())
				return e1.getNombreEscuderia().compareTo(e2.getNombreEscuderia());
			else if (e1.carrerasEscuderiaAcum() > e2.carrerasEscuderiaAcum())
				return 1;
			else 
				return -1;
		else if(e1.puntosEscuderiaAcum()>e2.puntosEscuderiaAcum())
			return 1;
		else
			return -1;
	}

}
