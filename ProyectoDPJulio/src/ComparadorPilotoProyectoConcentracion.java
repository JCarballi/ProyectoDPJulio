import java.util.Comparator;
public class ComparadorPilotoProyectoConcentracion implements Comparator<Piloto> {

	public int compare(Piloto p1,Piloto p2) {
		if(p1.getConcentracionActual()==p2.getConcentracionActual())
			return 0;
		else if(p1.getConcentracionActual()>p2.getConcentracionActual())
			return 1;
		else
			return -1;
	}

}
