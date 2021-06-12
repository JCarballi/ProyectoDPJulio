import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
/**
 * 
 * 
 *
 */
public class OrganizacionProyecto {
	ArrayList<EscuderiaProyecto> listaEscuderias;
	ArrayList<Piloto> listaPilotosProyecto;
	HashMap<String,Piloto> pilotoEscuderia;
	ArrayList<CircuitoProyectoInterfaz>listaCircuitos;
	private static OrganizacionProyecto op= null;
	private  int limiteAbandonos;
	private  int limitePilotos;
	private Comparator<CircuitoProyectoInterfaz> cC;
	private BufferedWriter writer;


	/**
	 * 
	 * @param PilotosLimite
	 * @param AbandonosLimite
	 * @param cC
	 */
	private OrganizacionProyecto(int pilotosLimite,int abandonosLimite,  Comparator<CircuitoProyectoInterfaz> cC,BufferedWriter writer) {
		this.pilotoEscuderia = new HashMap<String, Piloto>();
		this.listaCircuitos = new ArrayList<CircuitoProyectoInterfaz>();
		this.listaPilotosProyecto=new ArrayList<Piloto>();
		this.listaEscuderias= new ArrayList<EscuderiaProyecto>();
		this.limiteAbandonos=abandonosLimite;
		this.limitePilotos=pilotosLimite;
		this.writer=writer;
		this.cC=cC;
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
						
			
			pilotosCarrera();
			if(this.listaPilotosProyecto.size()>1) {
				listaPilotosProyecto.sort(compaP);	
				for(Piloto p : this.listaPilotosProyecto) {
					System.out.println(p.toString());
					writer.write(p.toString()+"\n");
				}
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("+++++++++++++++++++++++++ Comienza la carrera en "+ circui.getNombreCircuito() +"++++++++++++++++++++++++++");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				writer.write("+++++++++++++++++++++++++ Comienza la carrera en "+ circui.getNombreCircuito() +"++++++++++++++++++++++++++\n");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

				for(int j=0;j<listaPilotosProyecto.size();j++) {//
					Piloto p=listaPilotosProyecto.get(j);

					System.out.println("@@@ Piloto "+ (j+1) +" de "+ this.listaPilotosProyecto.size()); 
					System.out.println(p.toString()+" con "+p.getC().toString());
					writer.write("@@@ Piloto "+ (j+1) +" de "+ this.listaPilotosProyecto.size()+"\n");
					writer.write(p.toString()+" con "+p.getC().toString()+"\n");

					p.conducir(circui);
					Resultado r=p.getResults(circui.getNombreCircuito());
					if(r.getTiempo()<0) {
						if(p.totalAbandonadas()==this.limiteAbandonos) {
							p.setDescalificado(true);
							System.out.println("¡¡¡ "+p.getNombrePiloto()+" DESCALIFICADO del campeonato por alcanzar el límite de abandonos("+limiteAbandonos+") !!!");
							writer.write("¡¡¡ "+p.getNombrePiloto()+" DESCALIFICADO del campeonato por alcanzar el límite de abandonos("+limiteAbandonos+") !!!\n");
						}
					}

				}
				asignarPuntos(circui);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + 
						"+++++++++++++++++ Clasificación final de la carrera en "+ circui.getNombreCircuito() +"+++++++++++++++++++\n" + 
						"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				writer.write("+++++++++++++++++ Clasificación final de la carrera en "+ circui.getNombreCircuito() +"+++++++++++++++++++\n");
				writer.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				
				
				
				for(int j=0; j<this.listaPilotosProyecto.size(); j++) {
					Piloto p = listaPilotosProyecto.get(j);
					if(p.getResults(circui.getNombreCircuito()).getTiempo() <= 0) {
						System.out.print("¡¡¡ Ha abandonado "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito()));
						writer.write("¡¡¡ Ha abandonado "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito())+"\n");
						if(p.isDescalificado()) {
							System.out.println(" - Además ha sido descalificado para el resto del Campeonato !!!");
							writer.write(" - Además ha sido descalificado para el resto del Campeonato !!!\n");
						}else {
							System.out.println(" !!!");
							writer.write(" !!!\n");
						}
					}else {
						System.out.println("@@@ Posición("+ (j+1) +"): "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito())+" @@@");
						writer.write("@@@ Posición("+ (j+1) +"): "+ p.getNombrePiloto() +" "+p.getResults(circui.getNombreCircuito())+" @@@\n");
					}
				}
				int pDescalificados=0;
				for(int j=0; j<listaPilotosProyecto.size(); j++) {
					if(listaPilotosProyecto.get(j).isDescalificado())
						pDescalificados++;
				}

				if(listaPilotosProyecto.size()-pDescalificados <= 1 && i<listaCircuitos.size()-1) {
					finPrematuro=true;
					circui = listaCircuitos.get(i+1);
					System.out.println("********************************************************************************************************");
					System.out.println("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***");
					System.out.println("********************************************************************************************************");
					System.out.println();
					System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n"
							+ "¡¡¡ No se celebra esta carrera ni la(s) siguiente(s) por no haber pilotos para competir !!!!\r\n"
							+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
					writer.write("********************************************************************************************************\n");
					writer.write("*** CARRERA<"+ (this.listaCircuitos.lastIndexOf(circui)+1) +"> EN"+ circui.toString() +"***\n");
					writer.write("********************************************************************************************************\n");
					writer.write("\n");
					writer.write("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n");
					writer.write("¡¡¡ No se celebra esta carrera ni la(s) siguiente(s) por no haber pilotos para competir !!!!\r\n");
					writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
					
					
					
					
					
					
					
				}

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
	public void asignarPuntos(CircuitoProyectoInterfaz cp) throws IOException{
		Piloto Ppuntos;
		int posicion;
		Comparator<Piloto> cpUltima = new ComparadorPilotoUltimoTiempoCarrera(); 
		listaPilotosProyecto.sort(cpUltima);
		Resultado r;
		for(posicion=0;posicion<this.listaPilotosProyecto.size();posicion++) {
			try {
				Ppuntos=listaPilotosProyecto.get(posicion);
				r = Ppuntos.getResults(cp.getNombreCircuito());
				if(r.getTiempo()<0)
					r.setPuntos(0);

				else	{

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
					case 4:
						r.setPuntos(2);
						break;
					default:
						r.setPuntos(0);
						break;
					}
				}
				Ppuntos.setResults(r, cp.getNombreCircuito());
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Excepcion array: "+e.getMessage());
				writer.write("Excepcion array: "+e.getMessage()+"\n");
			}
		}

	}
	public void inscribirEscuderia(EscuderiaProyecto e) {
		listaEscuderias.add(e);
	}
	public void pilotosCarrera() throws IOException {
		if(listaPilotosProyecto.size()>0) {
			listaPilotosProyecto.clear();
			pilotoEscuderia.clear();
		}
		Iterator<EscuderiaProyecto> it=listaEscuderias.iterator();
		while(it.hasNext()) {
			EscuderiaProyecto e =it.next();
			for (int i = 0; i < this.limitePilotos; i++) {
				Piloto p=e.enviarAOrganizacion();
				if(p!=null) {

					this.listaPilotosProyecto.add(p);
					this.pilotoEscuderia.put(e.getNombreEscuderia(), p);
				}
			}

		}


	}

	public void inscribirCircuito(CircuitoProyectoInterfaz c)  {
		listaCircuitos.add(c);
		listaCircuitos.sort(cC);
	}

	/**
	 * 
	 * @param LimiteDePilotos
	 * @param LimiteDeAbandonos
	 * @param cC
	 * @return
	 */
	public static OrganizacionProyecto getInstance(int limiteDePilotos,int limiteDeAbandonos, Comparator<CircuitoProyectoInterfaz> cC,BufferedWriter writer ) {
		if(op == null) {
			return op = new OrganizacionProyecto(limiteDePilotos,limiteDeAbandonos, cC,writer);
		}
		return op;
	}
	public static OrganizacionProyecto getInstance(BufferedWriter writer) {
		if(op == null) {
			return op = new OrganizacionProyecto(2, 3, new ComparadorCircuitoDistancia(),writer);
		}
		return op;
	}

	private void mostrarInicioCampeonato() throws IOException {
		System.out.println( "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("||||||||||||||||||| CIRCUITOS DEL CAMPEONATO |||||||||||||||||||");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"); 
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		writer.write("||||||||||||||||||| CIRCUITOS DEL CAMPEONATO |||||||||||||||||||\n");
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
		
		
		
		
		for(CircuitoProyectoInterfaz c : this.listaCircuitos) {
			System.out.println(c.toString());
		}
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		writer.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%% ESCUDERÍAS DEL CAMPEONATO %%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		writer.write("%%%%%%%% ESCUDERÍAS DEL CAMPEONATO %%%%%%%%\n");
		writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		for(EscuderiaProyecto e : this.listaEscuderias) {
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

	private void mostrarFinCampeonato() throws IOException {
		System.out.println("****************************************************");
		System.out.println("**************** FIN DEL CAMPEONATO ****************");
		System.out.println("****************************************************");
		System.out.println("********** CLASIFICACIÓN FINAL DE PILOTOS **********");
		System.out.println("****************************************************");
		writer.write("****************************************************\n");
		writer.write("**************** FIN DEL CAMPEONATO ****************\n");
		writer.write("****************************************************\n");
		writer.write("********** CLASIFICACIÓN FINAL DE PILOTOS **********\n");
		writer.write("****************************************************\n");
		
		

		int escuderiasDescalificadas=0;

		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			listaPilotosProyecto.addAll(e.devolverPilotos());
			if(e.isDescalificada()) {
				escuderiasDescalificadas++;
			}
		}	
		if(escuderiasDescalificadas==listaEscuderias.size()) {
			System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n"
					+ "¡¡¡ Campeonato de pilotos queda desierto por haber sido descalificados todos los pilotos !!!\r\n"
					+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			writer.write("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n");
			writer.write("¡¡¡ Campeonato de pilotos queda desierto por haber sido descalificados todos los pilotos !!!\r\n");
			writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
			
			
			
			
		}

		listaPilotosProyecto.sort(new ComparadorPilotosNoDescalificados().reversed());
		int posicion = 1;

		for(int i=0; i<listaPilotosProyecto.size(); i++) {
			Piloto p = listaPilotosProyecto.get(i);
			if(!p.isDescalificado()) {
				System.out.println("@@@ Posición("+ posicion +"): "+ p.getNombrePiloto() +" - Puntos totales: "+ p.puntosAcumulados() +" @@@");
				writer.write("@@@ Posición("+ posicion +"): "+ p.getNombrePiloto() +" - Puntos totales: "+ p.puntosAcumulados() +" @@@\n");
				posicion++;
				for(CircuitoProyectoInterfaz c : listaCircuitos) {

					if(p.getResults(c.getNombreCircuito()) != null)
						System.out.println("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos");
					writer.write("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos\n");
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
		
		
		
		
		
		

		for(int i=0; i<listaPilotosProyecto.size(); i++) {
			Piloto p = listaPilotosProyecto.get(i);
			if(p.isDescalificado()) {
				System.out.println("--- Piloto Descalificado: "+p.getNombrePiloto()+" - Puntos Totales Anulados: "+p.puntosAcumulados()+" ---");
				writer.write("--- Piloto Descalificado: "+p.getNombrePiloto()+" - Puntos Totales Anulados: "+p.puntosAcumulados()+" ---\n");
				for(CircuitoProyectoInterfaz c : listaCircuitos) {
					if(p.getResults(c.getNombreCircuito()) != null)
						System.out.println("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos");
					writer.write("Carrera("+c.getNombreCircuito()+") - Puntos:"+p.getResults(c.getNombreCircuito()).getPuntos()+" - Tiempo:"+p.getResults(c.getNombreCircuito()).getTiempo()+" minutos\n");
				}
				System.out.println("");
				writer.write("\n");
			}
		}
		System.out.println("****************************************************\n" + 
				"******** CLASIFICACIÓN FINAL DE ESCUDERÍAS *********\n" + 
				"****************************************************");
		writer.write("****************************************************\n");
		writer.write("******** CLASIFICACIÓN FINAL DE ESCUDERÍAS *********\n");
		writer.write("****************************************************\n");
		
		
		
		if(escuderiasDescalificadas == listaEscuderias.size()) {
			System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n"
					+ "¡¡¡ Campeonato de escuderías queda desierto por haber sido descalificados todos los pilotos !!!\r\n"
					+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			writer.write("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡\r\n");
			writer.write("¡¡¡ Campeonato de escuderías queda desierto por haber sido descalificados todos los pilotos !!!\r\n");
			writer.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
			
			
			
			
		}

		listaEscuderias.sort(new ComparadorEscuderiaFinal().reversed());
		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			if(!e.isDescalificada()) {
				System.out.println("@@@ Posición("+(i+1)+") "+ e.getNombreEscuderia() +" con "+e.puntosEscuderiaAcum()+" puntos @@@");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%");
				System.out.println(e.toString());
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				writer.write("@@@ Posición("+(i+1)+") "+ e.getNombreEscuderia() +" con "+e.puntosEscuderiaAcum()+" puntos @@@\n");
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
		
		
		
		for(int i=0; i<listaEscuderias.size(); i++) {
			EscuderiaProyecto e = listaEscuderias.get(i);
			if(e.isDescalificada()) {
				System.out.println("¡¡¡ Escudería Descalificada: "+e.getNombreEscuderia()+" con "+e.puntosEscuderiaAcum()+" puntos !!!");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%");
				System.out.println(e.toString());
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				writer.write("¡¡¡ Escudería Descalificada: "+e.getNombreEscuderia()+" con "+e.puntosEscuderiaAcum()+" puntos !!!\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				writer.write("%%% "+e.getNombreEscuderia().toUpperCase()+" %%%\n");
				writer.write(e.toString()+"\n");
				writer.write("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				
				
				
				
				
				
				
				
			}
		}
	}

	public void campeonato(Comparator <Piloto> compaP) throws IOException {
		mostrarInicioCampeonato();
		carrera(compaP);
		mostrarFinCampeonato();
	}

}

