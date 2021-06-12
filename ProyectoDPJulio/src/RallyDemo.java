import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;

public class RallyDemo {

	public static void main(String[] args) throws IOException {
		File wf = new File("output.log");

		BufferedWriter reset = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(wf, false), "UTF-8"));
		reset.close();

		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(wf, true), "UTF-8"));

		DatosCampeonatoCompleto d = new DatosCampeonatoCompleto(writer); //initData privado
		//DatosCampeonatoFinPrematuro d2 = new DatosCampeonatoFinPrematuro(writer);
		//DatosCampeonatoPremioDesierto d3 = new DatosCampeonatoPremioDesierto(writer);

		Comparator<Piloto> cPilotoCarrerasTotales = new ComparadorPilotoTotalCarreras();
		OrganizacionProyecto.getInstance(writer).campeonato(cPilotoCarrerasTotales);  
		
		writer.close();
	}
}

