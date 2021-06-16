import java.util.Comparator;
/**
 * Clase ComparadorEscuderiaFinal
 * Se utiliza en el metodo mostrarFinCampeonato de Organizacion para ordenar las escuderias al mostrar la clasificacion final de las escuderias
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorEscuderiaFinal implements Comparator<EscuderiaProyecto> {
	@Override
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
