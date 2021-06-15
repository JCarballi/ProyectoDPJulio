import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
/**
 * Clase escuderia proyecto
 * */
public class EscuderiaProyecto {
	private String nombreEscuderia;
	private Set<Piloto> conjuntoPilotos;
	private Set<InterfazCoches> conjuntoCoches;
	private List<String> listaNombrePilotos;//creo que no seria necesaria
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
	public void inscribirEscuderia() {
		OrganizacionProyecto.getInstance().inscribirEscuderia(this);
	}
	public void meterPilotoEscuderia(Piloto pp) {
		conjuntoPilotos.add(pp);
		listaNombrePilotos.add(pp.getNombrePiloto());
	}
	public void meterCocheEscuderia(InterfazCoches icoches) {//deberia llamarse interfazCoches el parametro
		conjuntoCoches.add(icoches);

	}
	public double puntosEscuderiaAcum() {
		double pTotalesE=0;
		for (Piloto p : conjuntoPilotos) {
			if(!p.isDescalificado())
				pTotalesE+=p.puntosAcumulados();
		} 
		return pTotalesE;
	}
	public boolean devolverPilotoEscuderia(Piloto p) {
		if(listaNombrePilotos.contains(p.getNombrePiloto())) {
			conjuntoPilotos.add(p);
			meterCocheEscuderia(p.getC()); 
			p.setC(null);
			return true;
		}else
			return false;
	}
	public Piloto enviarAOrganizacion() throws IOException {
		Iterator<Piloto> itPil=conjuntoPilotos.iterator();
		Piloto pi=null;
		boolean salir=false;
		boolean hayCoche=false;//
		
		while(itPil.hasNext()&&!salir) {
			pi=itPil.next();
			if(!pi.isDescalificado()) {
				salir=true;
			}
		}
		
		if(pi!=null) {
			if(!pi.isDescalificado()) {
				Iterator<InterfazCoches> itCoc=conjuntoCoches.iterator();
				InterfazCoches c=null;
					while (itCoc.hasNext() && !hayCoche) {
					c = itCoc.next();
					if(c.getCombustibleAct()>0) {
						pi.setC(c);
						hayCoche=true;
					}		
				} 
				if(!hayCoche) {
					System.out.println("¡¡¡ "+ pi.getNombrePiloto() +" NO ES ENVIADO A LA CARRERA porque su escudería("+this.nombreEscuderia+") no tiene más coches con combustible disponibles !!!");
					writer.write("¡¡¡ "+ pi.getNombrePiloto() +" NO ES ENVIADO A LA CARRERA porque su escudería("+this.nombreEscuderia+") no tiene más coches con combustible disponibles !!!\n");
					pi=null;
				}else {
					conjuntoCoches.remove(c);
					conjuntoPilotos.remove(pi);
					return pi;	
				}
			} else {
				pi=null;
			}
		}
		return pi;
	}
	
	public List<Piloto> devolverPilotos() {
		List<Piloto> arrayPilotos = new ArrayList<Piloto>(); 
		arrayPilotos.addAll(conjuntoPilotos);
		return arrayPilotos;
	}
	
	public Boolean isDescalificada() {
		int pDescalificados=0;
		for(Piloto p : conjuntoPilotos) {
			if(p.isDescalificado())
				pDescalificados++;
		}
		if(pDescalificados == conjuntoPilotos.size())
			return true;
		else 
			return false;
	}
	
	public int carrerasEscuderiaAcum() {
		int totalCarreras=0;
		Iterator<Piloto> itPilotos= conjuntoPilotos.iterator();
		while(itPilotos.hasNext()) {
			Piloto p = itPilotos.next();
			totalCarreras += p.totalTerminadas();
		}
		return totalCarreras;
	}

	@Override
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
