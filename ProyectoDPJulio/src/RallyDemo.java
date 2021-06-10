import java.util.Comparator;

public class RallyDemo {
	
	public static void main(String[] args) {
		DatosCampeonatoCompleto d = new DatosCampeonatoCompleto();
		//DatosCampeonatoFinPrematuro d2 = new DatosCampeonatoFinPrematuro();
		//DatosCampeonatoPremioDesierto d3 = new DatosCampeonatoPremioDesierto();
		Comparator<Piloto> cPilotoCarrerasTotales = new ComparadorPilotoTotalCarreras();
		OrganizacionProyecto.getInstance().campeonato(cPilotoCarrerasTotales);    	
	}

}
