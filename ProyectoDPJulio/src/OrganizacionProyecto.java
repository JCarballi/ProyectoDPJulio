import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * Clase Organizaci�n
 * Clase donde se muestra la salida por pantalla de la simulaci�n y por tanto clase es la clase capaz de gestionar el campeonato.
 *@author Javier Santamar�a Caballero
 *@author Juan Jos� Carballo Pacheco
 */
public class OrganizacionProyecto {
	ArrayList<EscuderiaProyecto> listaEscuderias;/*admitimos escuderias duplicadas y nos interesa ordenarlas o no usando un determinado comparador*/
	ArrayList<Piloto> listaPilotosProyecto;/*posibles pilotos duplicados y posible ordenaci�n*/
	ArrayList<CircuitoProyectoInterfaz>listaCircuitos;/*lista de circuitos de la organizaci�n*/
	private static OrganizacionProyecto op= null;/*instancia �nica de organizaci�n*/
	private  int limiteAbandonos;
	private  int limitePilotos;
	private Comparator<CircuitoProyectoInterfaz> cC;/*comparador usado para la losta de circuitos y que pasamos por par�metros*/
	private BufferedWriter writer;


	/**
	 * Constructor privado para el Singleton con par�metros(el que usamos cuando tenemos que crear la instancia si no est� creada
	 * @param PilotosLimite limite de pilotos de esa carrera.
	 * @param AbandonosLimite  limite de abandonos.
	 * @param cC  comparador de circuitos.
	 * @param writer escritura en fichero.
	 */
	private OrganizacionProyecto(int pilotosLimite,int abandonosLimite,  Comparator<CircuitoProyectoInterfaz> cC,BufferedWriter writer) {
		this.listaCircuitos = new ArrayList<CircuitoProyectoInterfaz>();
		this.listaPilotosProyecto=new ArrayList<Piloto>();
		this.listaEscuderias= new ArrayList<EscuderiaProyecto>();
		this.limiteAbandonos=abandonosLimite;
		this.limitePilotos=pilotosLimite;
		this.writer=writer;
		this.cC=cC;
	}
	/**
	 * Constructor privado para el Singleton sin par�metros(el que usamos cuando obtenemos la instancia ya creada)
	 * @param PilotosLimite limite de pilotos de esa carrera.
	 * @param AbandonosLimite  limite de abandonos.
	 * @param cC  comparador de circuitos.
	 * @throws IOException
	 */

	private OrganizacionProyecto(int pilotosLimite,int abandonosLimite,  Comparator<CircuitoProyectoInterfaz> cC) throws IOException {
		this.listaCircuitos = new ArrayList<CircuitoProyectoInterfaz>();
		this.listaPilotosProyecto=new ArrayList<Piloto>();
		this.listaEscuderias= new ArrayList<EscuderiaProyecto>();
		this.limiteAbandonos=abandonosLimite;
		this.limitePilotos=pilotosLimite;
		this.cC=cC;
		this.writer=new BufferedWriter(new FileWriter("errorSalida.log"));
	}

	/**
	 * Metodo que realiza una carrera completa
	 * @param compaP es el orden en el que se ordenan los pilotos que van a competir
	 * @throws IOException 
	 *
	 */
	private void carrera(Comparator<Piloto>compaP) throws IOException {
		boolean finPrematuro=false;

		for(int i=0;i<listaCircuitos.size() && !finPrematuro;i++) { 
			CircuitoProyectoInterfaz circui=listaCircuitos.get(i);
			/*ponemos el n�mero de cada carrera empezando en 1 as� como las caracter�sticas del circuito*/
			System.out.println("********************************************************************************************************");
			System.out.println("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***");
			System.out.println("********************************************************************************************************");
			System.out.println("********************************************************************************************************");
			System.out.println("******************************** Pilotos que van a competir en "+ circui.getNombreCircuito() +"*******************************");
			System.out.println("**********************************************************************************************************");
			writer.write("********************************************************************************************************\n");
			writer.write("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***\n");
			writer.write("********************************************************************************************************\n");
			writer.write("********************************************************************************************************\n");
			writer.write("******************************** Pilotos que van a competir en "+ circui.getNombreCircuito() +"*******************************\n");
			writer.write("**********************************************************************************************************\n");

			/*enviamos los pilotos que van a competir en la carrera*/
			pilotosCarrera();
			/*si tenemos mas de 1 piloto los ordenamos por un determinado comparador para pilotos*/
			if(this.listaPilotosProyecto.size()>1) {
				listaPilotosProyecto.sort(compaP);	
				for(Piloto p : this.listaPilotosProyecto) {/*mostramos las caracter�sticas de los pilotos que compiten*/
					System.out.println(p.toString());
					writer.write(p.toString()+"\n");
				}
				/*comienza la carrera*/
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("+++++++++++++++++++++++++ Comienza la carrera en "+ circui.getNombreCircuito() +"++++++++++++++++++++++++++");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				writer.write("+++++++++++++++++++++++++ Comienza la carrera en "+ circui.getNombreCircuito() +"++++++++++++++++++++++++++\n");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

				for(int j=0;j<listaPilotosProyecto.size();j++) {
					Piloto p=listaPilotosProyecto.get(j);
					/*mostramos 1 a 1 los pilotos que compiten en cada carrera*/
					System.out.println("@@@ Piloto "+ (j+1) +" de "+ this.listaPilotosProyecto.size()); 
					System.out.println(p.toString()+" con "+p.getC().toString());
					writer.write("@@@ Piloto "+ (j+1) +" de "+ this.listaPilotosProyecto.size()+"\n");
					writer.write(p.toString()+" con "+p.getC().toString()+"\n");

					try{/*llamamos al m�todo que permite conducir al piloto*/
						p.conducir(circui);
					}catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					/*obtenemos los resultados tras la carrera*/
					Resultado r=p.getResults(circui.getNombreCircuito());
					if(r.getTiempo()<0) {
						if(p.totalAbandonadas()==this.limiteAbandonos) {
							p.setDescalificado(true);/*descalificamos al piloto si ha llegado al limite de abandonos*/
							System.out.println("��� "+p.getNombrePiloto()+" DESCALIFICADO del campeonato por alcanzar el l�mite de abandonos("+limiteAbandonos+") !!!");
							writer.write("��� "+p.getNombrePiloto()+" DESCALIFICADO del campeonato por alcanzar el l�mite de abandonos("+limiteAbandonos+") !!!\n");
						}
					}

				}
				asignarPuntos(circui);/*asignamos los puntos a todos los pilotos est�n descalificados o no*/
				/*clasificaci�n final de las carreras*/
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + 
						"+++++++++++++++++ Clasificaci�n final de la carrera en "+ circui.getNombreCircuito() +"+++++++++++++++++++\n" + 
						"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				writer.write("+++++++++++++++++ Clasificaci�n final de la carrera en "+ circui.getNombreCircuito() +"+++++++++++++++++++\n");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

				/*mostramos la clasificaci�n de la carrera para los pilotos que compitan*/

				for(int j=0; j<this.listaPilotosProyecto.size(); j++) {
					Piloto p = listaPilotosProyecto.get(j);
					if(p.getResults(circui.getNombreCircuito()).getTiempo() <= 0) {/*si no ha acabado*/
						System.out.print("��� Ha abandonado "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito()));
						writer.write("��� Ha abandonado "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito()));
						if(p.isDescalificado()) {
							System.out.println(" - Adem�s ha sido descalificado para el resto del Campeonato !!!");
							writer.write(" - Adem�s ha sido descalificado para el resto del Campeonato !!!\n");
						}else {/*si no ha acabado y tampoco ha sido descalificado*/
							System.out.println(" !!!");
							writer.write(" !!!\n");
						}
					}else {/*si ha terminado la carrera*/
						System.out.println("@@@ Posici�n("+ (j+1) +"): "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito())+" @@@");
						writer.write("@@@ Posici�n("+ (j+1) +"): "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito())+" @@@\n");
					}
				}/*calculamos el n�mero de descalificados para ver si podemos encontrarnos ante un fin prematuro*/
				int pDescalificados=0;
				for(int j=0; j<listaPilotosProyecto.size(); j++) {
					if(listaPilotosProyecto.get(j).isDescalificado())
						pDescalificados++;
				}
				/*si nos queda 1 piloto o menos sin descalificar,estamos en fin prematuro*/
				if(listaPilotosProyecto.size()-pDescalificados <= 1 && i<listaCircuitos.size()-1) {
					finPrematuro=true;
					circui = listaCircuitos.get(i+1);/*si ocurre esto,las siguientes carreras a la carrera i no se podr�n celebrar por el fin prematuro*/
					System.out.println("********************************************************************************************************");
					System.out.println("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***");
					System.out.println("********************************************************************************************************");
					System.out.println();
					System.out.println("��������������������������������������������������������������������������������������������\r\n"
							+ "��� No se celebra esta carrera ni la(s) siguiente(s) por no haber pilotos para competir !!!!\r\n"
							+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
					writer.write("********************************************************************************************************\n");
					writer.write("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***\n");
					writer.write("********************************************************************************************************\n");
					writer.write("\n");
					writer.write("��������������������������������������������������������������������������������������������\r\n");
					writer.write("��� No se celebra esta carrera ni la(s) siguiente(s) por no haber pilotos para competir !!!!\r\n");
					writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");







				}
				/*obtenemos las escuderias y devolvemos los pilotos a sus escuder�as*/
				EscuderiaProyecto ee;
				for(int k=0;k<this.listaEscuderias.size();k++) {
					ee=listaEscuderias.get(k);

					int j=0;
					while (j < listaPilotosProyecto.size()) {
						Piloto pe=	listaPilotosProyecto.get(j);
						if(ee.devolverPilotoEscuderia(pe)) {
							listaPilotosProyecto.remove(j);
						}else {
							j++;
						}
					}
				}
			}
		}
	}
	/**
	 * M�todo que asigna los puntos a cada piloto que compita en la carrera en funci�n de la posici�n en la que acabe la carrera
	 * @param cp circuito en el que compiten los pilotos.
	 *  
	 * 
	 */
	public void asignarPuntos(CircuitoProyectoInterfaz cp) throws IOException{
		Piloto Ppuntos;
		int posicion;
		Comparator<Piloto> cpUltima = new ComparadorPilotoUltimoTiempoCarrera(); 
		listaPilotosProyecto.sort(cpUltima);/*ordenamos los pilotos de menor a mayor por �ltimo tiempo de carrera*/
		Resultado r;
		for(posicion=0;posicion<this.listaPilotosProyecto.size();posicion++) {
			try {
				Ppuntos=listaPilotosProyecto.get(posicion);/*obtenemos todos los pilotos*/
				r = Ppuntos.getResults(cp.getNombreCircuito());
				if(r.getTiempo()<0)/*si no acaba la carrera,se le asignan 0 puntos*/
					r.setPuntos(0);

				else	{/*si no,asignamos los puntos de mejor a peor posici�n*/

					switch(posicion) {
					case 0:
						r.setPuntos(10);
						break;
					case 1:
						r.setPuntos(8);
						break;
					case 2:
						r.setPuntos(6);
						break;
					case 3:
						r.setPuntos(4);
						break;
					default:
						r.setPuntos(2);
						break;
					}
				}
				Ppuntos.setResults(r, cp.getNombreCircuito());/*ponemos  los resultados de cada piloto en su mapa*/
			}catch(ArrayIndexOutOfBoundsException e) {/*Excepci�n creada para controlar que no nos salimos del array*/
				System.out.println("Excepcion array: "+e.getMessage());
				writer.write("Excepcion array: "+e.getMessage()+"\n");
			}
		}

	}/**
	 * 
	 * m�todo que inscribe una determinada escuder�a 
	 * @param e escuderia a inscribir
	 */
	public void inscribirEscuderia(EscuderiaProyecto e) {
		listaEscuderias.add(e);
	}
	/**
	 * 
	 * m�todo que env�a los pilotos que van a competir en una determinada carrera.
	 * @throws IOException
	 * 
	 */
	public void pilotosCarrera() throws IOException {
		if(listaPilotosProyecto.size()>0) {/*vaciamos la lista para enviar los siguientes que van a competir*/
			listaPilotosProyecto.clear();
		}
		Iterator<EscuderiaProyecto> it=listaEscuderias.iterator();/*recorremos todas las escuder�as*/
		while(it.hasNext()) {
			EscuderiaProyecto e =it.next();
			for (int i = 0; i < this.limitePilotos; i++) {/*enviamos a la carrera los pilotos que van a competir hasta el l�mite establecido*/
				Piloto p=e.enviarAOrganizacion();/*enviamos cada piloto a competir a la organizaci�n*/
				if(p!=null) {/*enviamos los pilotos que existan*/

					this.listaPilotosProyecto.add(p);
				}
			}

		}


	}
	/**
	 * 
	 * m�todo que inscribe un determinado circuito
	 * @param c circuito a inscribir
	 */
	public void inscribirCircuito(CircuitoProyectoInterfaz c)  {
		listaCircuitos.add(c);
		listaCircuitos.sort(cC);/*nos interesa que los circuitos est�n siempre ordenados*/
	}

	/**
	 * Singleton de organizaci�n con par�metros para crear la instancia �nica en caso de que no exista
	 * @param limiteDePilotos limite de pilotos de esa carrera.
	 * @param limiteDeAbandonos  limite de abandonos.
	 * @param cC  comparador de circuitos.
	 * @param writer para escritura en fichero.
	 * @throws IOException
	 * @return op instancia de organizacion.
	 * 
	 */
	public static OrganizacionProyecto getInstance(int limiteDePilotos,int limiteDeAbandonos, Comparator<CircuitoProyectoInterfaz> cC,BufferedWriter writer ) {
		if(op == null) {/*si no existe la instancia,la creo*/
			return op = new OrganizacionProyecto(limiteDePilotos,limiteDeAbandonos, cC,writer);
		}
		return op;
	}
	/**
	 * Singleton de organizaci�n sin par�metros para que obtenga la instancia ya creada si existe
	 * @return op instancia de organizacion.
	 * @throws IOException
	 */
	public static OrganizacionProyecto getInstance() throws IOException {
		if(op == null) {
			return op = new OrganizacionProyecto(2, 3, new ComparadorCircuitoDistancia());
		}
		return op;
	}
	/**
	 * M�todo para mostrar la salida del inicio del campeonato.
	 * @throws IOException
	 * 
	 */
	private void mostrarInicioCampeonato() throws IOException {
		System.out.println( "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("||||||||||||||||||| CIRCUITOS DEL CAMPEONATO |||||||||||||||||||");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"); 
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		writer.write("||||||||||||||||||| CIRCUITOS DEL CAMPEONATO |||||||||||||||||||\n");
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");




		for(CircuitoProyectoInterfaz c : this.listaCircuitos) {/*mostramos la informaci�n de todos los circuitos*/
			System.out.println(c.toString());
			writer.write(c.toString()+"\n");
		}
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		/*salida para las escuderias*/
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%% ESCUDER�AS DEL CAMPEONATO %%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		writer.write("%%%%%%%% ESCUDER�AS DEL CAMPEONATO %%%%%%%%\n");
		writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		for(EscuderiaProyecto e : this.listaEscuderias) {/*mostramos la informaci�n de todas las escuder�as*/
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("%%%"+ e.getNombreEscuderia().toUpperCase() +"%%%");
			System.out.println(e.toString());
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			writer.write("%%%"+ e.getNombreEscuderia().toUpperCase() +"%%%\n");
			writer.write(e.toString()+"\n");
			writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");









		}
	}	
	/**
	 * M�todo para mostrar el fin del campeonato as� como las diferentes clasificaciones finales
	 * @throws IOException
	 * 
	 */
	private void mostrarFinCampeonato() throws IOException {
		System.out.println("****************************************************");
		System.out.println("**************** FIN DEL CAMPEONATO ****************");
		System.out.println("****************************************************");
		System.out.println("********** CLASIFICACI�N FINAL DE PILOTOS **********");
		System.out.println("****************************************************");
		writer.write("****************************************************\n");
		writer.write("**************** FIN DEL CAMPEONATO ****************\n");
		writer.write("****************************************************\n");
		writer.write("********** CLASIFICACI�N FINAL DE PILOTOS **********\n");
		writer.write("****************************************************\n");



		int escuderiasDescalificadas=0;

		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			listaPilotosProyecto.addAll(e.devolverPilotos());/*devolvemos todos los pilotos a sus escuder�as*/
			if(e.isDescalificada()) {
				escuderiasDescalificadas++;
			}
		}	
		if(escuderiasDescalificadas==listaEscuderias.size()) {/*caso en el que nos encontramos con la salida de premio desierto porque todas las escuder�as han quedado descalificadas*/
			System.out.println("��������������������������������������������������������������������������������������������\r\n"
					+ "��� Campeonato de pilotos queda desierto por haber sido descalificados todos los pilotos !!!\r\n"
					+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			writer.write("��������������������������������������������������������������������������������������������\r\n");
			writer.write("��� Campeonato de pilotos queda desierto por haber sido descalificados todos los pilotos !!!\r\n");
			writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");




		}
		/*ordenamos los pilotos de manera descendente seg�n el comparador*/
		listaPilotosProyecto.sort(new ComparadorPilotoTotalCarreras().reversed());
		int posicion = 1;

		for(int i=0; i<listaPilotosProyecto.size(); i++) {
			Piloto p = listaPilotosProyecto.get(i);
			if(!p.isDescalificado()) {/*mostramos la clasificaci�n final de todos los pilotos*/
				System.out.println("@@@ Posici�n("+ posicion +"): "+ p.getNombrePiloto() +" - Puntos totales: "+ p.puntosAcumulados() +" @@@");
				writer.write("@@@ Posici�n("+ posicion +"): "+ p.getNombrePiloto() +" - Puntos totales: "+ p.puntosAcumulados() +" @@@\n");
				posicion++;
				for(CircuitoProyectoInterfaz c : listaCircuitos) {/*mostramos las carreras en las que compite cada piloto*/

					if(p.getResults(c.getNombreCircuito()) != null) {/*para cada carrera en la que compite mostramos los puntos conseguidos y el tiempo empleado,la haya acabado o no*/
						System.out.println("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos");
						writer.write("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos\n");
					}
				}
				System.out.println("");
				writer.write("\n");
			}
		}

		System.out.println("****************************************************");
		System.out.println("************** PILOTOS DESCALIFICADOS *************");
		System.out.println("****************************************************");
		writer.write("****************************************************\n");
		writer.write("************** PILOTOS DESCALIFICADOS *************\n");
		writer.write("****************************************************\n");






		/*para los pilotos que hayn sido descalificados mostramos las carreras en las que ha competido asi como los puntos y el tiempo empleado*/
		for(int i=0; i<listaPilotosProyecto.size(); i++) {
			Piloto p = listaPilotosProyecto.get(i);
			if(p.isDescalificado()) {
				System.out.println("--- Piloto Descalificado: "+p.getNombrePiloto()+" - Puntos Totales Anulados: "+p.puntosAcumulados()+" ---");
				writer.write("--- Piloto Descalificado: "+p.getNombrePiloto()+" - Puntos Totales Anulados: "+p.puntosAcumulados()+" ---\n");
				for(CircuitoProyectoInterfaz c : listaCircuitos) {
					if(p.getResults(c.getNombreCircuito()) != null) {
						System.out.println("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos");
						writer.write("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos\n");
					}
				}
				System.out.println("");
				writer.write("\n");
			}
		}
		System.out.println("****************************************************\n" + 
				"******** CLASIFICACI�N FINAL DE ESCUDER�AS *********\n" + 
				"****************************************************");
		writer.write("****************************************************\n");
		writer.write("******** CLASIFICACI�N FINAL DE ESCUDER�AS *********\n");
		writer.write("****************************************************\n");


		/*caso para salida desierto por quedar todas las escuderias descalificadas*/
		if(escuderiasDescalificadas == listaEscuderias.size()) {
			System.out.println("�����������������������������������������������������������������������������������������������\r\n"
					+ "��� Campeonato de escuder�as queda desierto por haber sido descalificados todos los pilotos !!!\r\n"
					+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			writer.write("�����������������������������������������������������������������������������������������������\r\n");
			writer.write("��� Campeonato de escuder�as queda desierto por haber sido descalificados todos los pilotos !!!\r\n");
			writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");




		}
		/*clasificaci�n final para todas las escuderias mostrando la posici�n en la que finalizaron asi como la informaci�n sobre pilotos y coches asociada a cada escuderia*/
		listaEscuderias.sort(new ComparadorEscuderiaFinal().reversed());/*ordenamos las escuder�as de manera descendente por los puntos acumulados de la escuder�a (si no hay empate)*/
		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			if(!e.isDescalificada()) {
				System.out.println("@@@ Posici�n("+(i+1)+") "+ e.getNombreEscuderia() +" con "+e.puntosEscuderiaAcum()+" puntos @@@");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%");
				System.out.println(e.toString());
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				writer.write("@@@ Posici�n("+(i+1)+") "+ e.getNombreEscuderia() +" con "+e.puntosEscuderiaAcum()+" puntos @@@\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				writer.write("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%\n");
				writer.write(e.toString()+"\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");







			}
		}
		System.out.println("****************************************************\n" + 
				"************ ESCUDERIAS DESCALIFICADAS *************\n" + 
				"****************************************************");
		writer.write("****************************************************\n");
		writer.write("************ ESCUDERIAS DESCALIFICADAS *************\n");
		writer.write("****************************************************\n");

		listaEscuderias.sort(new ComparadorEscuderiaFinal());

		/*Mostramos la informaci�n asociada a las escuder�as descalificadas*/
		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			if(e.isDescalificada()) {
				System.out.println("��� Escuder�a Descalificada: "+e.getNombreEscuderia()+" con 0.0 puntos !!!");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%");
				System.out.println(e.toString());
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				writer.write("��� Escuder�a Descalificada: "+e.getNombreEscuderia()+" con 0.0 puntos !!!\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				writer.write("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%\n");
				writer.write(e.toString()+"\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");








			}
		}
	}
	/**
	 * M�todo que llama a los m�todos correspondientes para la ejecuci�n de la salida por pantalla(o fichero tambi�n)
	 * @param compaP comparador de pilotos por total de carreras.
	 * @throws IOException
	 * 
	 */
	public void campeonato(Comparator <Piloto> compaP) throws IOException {
		mostrarInicioCampeonato();
		carrera(compaP);
		mostrarFinCampeonato();
	}

}

