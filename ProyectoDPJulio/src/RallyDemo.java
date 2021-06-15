import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;

/**
 *Clase RallyDemo
 *Clase que realiza la simulación del proyecto.
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 * */
public class RallyDemo {

	public static void main(String[] args) throws IOException {
		/*fichero de salida que se generará para la ampliación del fichero de salida(en funcion del tipo de datos que cargamos se escribirá un contenido u otro)*/
		
		BufferedWriter writer = new BufferedWriter(/*este writer es ell que escribre el programa en el fichero*/
				new FileWriter("output.log"));
		
		
       /*elegimos entre un conjunto de datos para la simulación u otros)*/
		DatosCampeonatoCompleto d = new DatosCampeonatoCompleto(writer); //lo hacemos así porque su initData es privado
		//DatosCampeonatoFinPrematuro d2 = new DatosCampeonatoFinPrematuro(writer);
		//DatosCampeonatoPremioDesierto d3 = new DatosCampeonatoPremioDesierto(writer);
     
		Comparator<Piloto> cPilotoCarrerasTotales = new ComparadorPilotoTotalCarreras();/*pàra ordenar los pilotos dentro de la carrera*/
		OrganizacionProyecto.getInstance().campeonato(cPilotoCarrerasTotales); /*ejecutar con los datos de uan determinada salida elegida arriba*/ 
		
		writer.close();
	}
}

