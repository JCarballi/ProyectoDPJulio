import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
/**
 * Clase escuderia proyecto
 * Clase utilizada para las escuderias del proyecto asi como los coches y pilotos que almacenan
 * @author Javier santamaría Caballero
 * @author Juan José Carballo Pacheco
 * */
public class EscuderiaProyecto {
	private String nombreEscuderia;
	private Set<Piloto> conjuntoPilotos;/*nos interesa que haya orden y no repetición en los pilotos de la escuderia*/
	private Set<InterfazCoches> conjuntoCoches;
	private List<String> listaNombrePilotos;/*para obtener el nombre de todos los pilotos de cada escuderia,ya que con el conjunto de pilotos los borramos despues*/
	private BufferedWriter writer;

	
	public EscuderiaProyecto(String nombreEscuderia,Comparator<InterfazCoches>ccP,Comparator<Piloto> cpili,BufferedWriter writer) {
		this.nombreEscuderia = nombreEscuderia;
		this.conjuntoPilotos=new TreeSet<Piloto>(cpili);
		this.conjuntoCoches=new TreeSet<InterfazCoches>(ccP);//
		this.listaNombrePilotos = new ArrayList<String>();
		this.writer=writer;
	}
	public String getNombreEscuderia() {
		return nombreEscuderia;
	}
	public void setNombreEscuderia(String nombreEscuderia) {
		this.nombreEscuderia = nombreEscuderia;
	}
	public void inscribirEscuderia() throws IOException {
		OrganizacionProyecto.getInstance().inscribirEscuderia(this);
	}
	/**
	 * Método que mete al piloto en la escuderia(el objeto) así como su nombre en la lista de pilotos de la escuderia.
	 *@param pp piloto a insertar
	 * 
	 */
	public void meterPilotoEscuderia(Piloto pp) {
		conjuntoPilotos.add(pp);
		listaNombrePilotos.add(pp.getNombrePiloto());
	}
	/**
	 * Método que inserta un coche en el conjunto de coches de la escuderia.
	 * @param icoches coche a insertar
	 */
	public void meterCocheEscuderia(InterfazCoches icoches) {
		conjuntoCoches.add(icoches);

	}
	/**
	 * Método que calcula los puntos totales de esa escuderia teniendo en cuenta todos los puntos acumulados de todos los pilotos
	 *@return pTotalesE
	 * 
	 */
	public double puntosEscuderiaAcum() {
		double pTotalesE=0;
		for (Piloto p : conjuntoPilotos) {
			if(!p.isDescalificado())
				pTotalesE+=p.puntosAcumulados();
		} 
		return pTotalesE;
	}
	/**
	 * Método que devueleva al piloto a la escudería
	 *@param p piloto a devolver
	 * @return true o false
	 */
	public boolean devolverPilotoEscuderia(Piloto p) {
		if(listaNombrePilotos.contains(p.getNombrePiloto())) {/*Comprobamos si el piloto pertenece a esa escuderia y no a otra*/
			conjuntoPilotos.add(p);/*si se cumple,añadimos el piloto al conjunto de pilotos de la escuderia*/
			meterCocheEscuderia(p.getC()); /*añadimos su coche tambien*/
			p.setC(null);/*lo ponemos a null para no asignarle siempre el mismo coche*/
			return true;
		}else
			return false;
	}
	/**
	 * Método que envia un piloto a competir a la organización
	 *@return pi 
	 * @throws IOException
	 */
	public Piloto enviarAOrganizacion() throws IOException {
		Iterator<Piloto> itPil=conjuntoPilotos.iterator();/*iteramos hasta encontrar el primer piloto que no esté descalificado*/
		Piloto pi=null;
		boolean salir=false;
		boolean hayCoche=false;
		
		while(itPil.hasNext()&&!salir) {/*cuando encontremos el primer piloto no descalificado,dejamos de iterar y salimos*/
			pi=itPil.next();
			if(!pi.isDescalificado()) {
				salir=true;
			}
		}
		
		if(pi!=null) {/*si ese piloto no es nulo,debemos asignarle el coche con el que va a correr*/
			if(!pi.isDescalificado()) {
				Iterator<InterfazCoches> itCoc=conjuntoCoches.iterator();
				InterfazCoches c=null;
					while (itCoc.hasNext() && !hayCoche) {/*mientras el piloto no tenga coche,iteramos*/
					c = itCoc.next();
					if(c.getCombustibleAct()>0) {/*si encontramos un coche con combustible para el piloto,se lo asignamos y salimos del bucle*/
						pi.setC(c);
						hayCoche=true;
					}		
				} 
				if(!hayCoche) {/*si no hay ningun coche disponible porque ninguno tiene combustible,ese piloto no es enviado a competir*/
					System.out.println("¡¡¡ "+ pi.getNombrePiloto() +" NO ES ENVIADO A LA CARRERA porque su escudería("+this.nombreEscuderia+") no tiene más coches con combustible disponibles !!!");
					writer.write("¡¡¡ "+ pi.getNombrePiloto() +" NO ES ENVIADO A LA CARRERA porque su escudería("+this.nombreEscuderia+") no tiene más coches con combustible disponibles !!!\n");
					pi=null;/*ponemos el piloto a nulo porque no va a competir*/
				}else {/*en caso de que haya coche,una vez que está asignado lo borramos*/
					conjuntoCoches.remove(c);
					conjuntoPilotos.remove(pi);
					return pi;	
				}
			} else {/*si el piloto es nulo,devuelvo el piloto nulo*/
				pi=null;
			}
		}
		return pi;
	}
	/**
	 * Método que devuelve una copia de la lista de pilotos para el final de la competición(al mostrar la clasificación).
	 *@return arrayPilotos
	 * 
	 */
	public List<Piloto> devolverPilotos() {
		List<Piloto> arrayPilotos = new ArrayList<Piloto>(); 
		arrayPilotos.addAll(conjuntoPilotos);
		return arrayPilotos;
	}
	/**
	 * Método que te dice si una escudería está descalificada
	 *@return true o false
	 * 
	 */
	public Boolean isDescalificada() {
		int pDescalificados=0;
		for(Piloto p : conjuntoPilotos) {
			if(p.isDescalificado())
				pDescalificados++;
		}
		if(pDescalificados == conjuntoPilotos.size())/*si todos los pilotos de la escudería estan descalificados,la escudería está descalificada*/
			return true;
		else 
			return false;
	}
	/**
	 * Método que devueleve el total de carreras terminadas entre todos los pilotos de esa escuderia
	 *@return totalCarreras
	 * 
	 */
	public int carrerasEscuderiaAcum() {
		int totalCarreras=0;
		Iterator<Piloto> itPilotos= conjuntoPilotos.iterator();
		while(itPilotos.hasNext()) {
			Piloto p = itPilotos.next();
			totalCarreras += p.totalTerminadas();
		}
		return totalCarreras;
	}

	@Override/*el método que muestra la información de las escuderias esta compuesto por la información de sus pilotos y coches*/
	public String toString() {
		String pilotos="", coches="";
		for(Piloto p: conjuntoPilotos) {
			pilotos+=p.toString()+"\n";
		}
		for(InterfazCoches c: conjuntoCoches) {
			coches+=c.toString()+"\n";
		}
		return pilotos + coches;	
	}


}
