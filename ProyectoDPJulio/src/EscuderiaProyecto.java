import java.util.*;
/**
 * Clase escuderia proyecto
 * */
public class EscuderiaProyecto {
	private String nombreEscuderia;
	private Set<Piloto> conjuntoPilotos;
	private Set<InterfazCoches> conjuntoCoches;
	private List<String> listaNombrePilotos;//creo que no seria necesaria
	
	public EscuderiaProyecto(String nombreEscuderia,Comparator<InterfazCoches>ccP,Comparator<Piloto> cpili) {
		this.nombreEscuderia = nombreEscuderia;
		this.conjuntoPilotos=new TreeSet<Piloto>(cpili);
		this.conjuntoCoches=new TreeSet<InterfazCoches>(ccP);//
		this.listaNombrePilotos = new ArrayList<String>();
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
			pTotalesE+=p.puntosAcumulados();
		} 
		return pTotalesE;
	}
	public boolean devolverPilotoEscuderia(Piloto p) {
		if(listaNombrePilotos.contains(p.getNombrePiloto())) {
			conjuntoPilotos.add(p);
			meterCocheEscuderia(p.getC()); //TODO quitar cast
			p.setC(null);

			return true;
		}else
			return false;
	}
	public Piloto enviarAOrganizacion() {
		Iterator<Piloto> itPil=conjuntoPilotos.iterator();
		Piloto pi=null;
		boolean salir=false;
		boolean hayCoche=false;//
		while(itPil.hasNext()&&!salir) {
			pi=itPil.next();
			if(pi.isDescalificado()==false) {
				salir=true;
			}
		}
		if(pi!=null) {
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
			}else {
				conjuntoCoches.remove(c);
				conjuntoPilotos.remove(pi);
				return pi;	
			}
		}
		return null;
	}
		public void setOrdenacionEstrategiaPiloto(Comparator<Piloto> epil) {
		Set<Piloto> aux = new TreeSet<>(epil);
		aux.addAll(conjuntoPilotos);
		this.conjuntoPilotos = aux;
	}
	public void setOrdenacionEstrategiaCoche(Comparator<InterfazCoches> eCoch) {
		Set<InterfazCoches> aux = new TreeSet<>(eCoch);
		aux.addAll(conjuntoCoches);
		this.conjuntoCoches = aux;
	}
	public List<Piloto> devolverPilotos() {
		List<Piloto> arrayPilotos = new ArrayList<Piloto>(); 
		arrayPilotos.addAll(conjuntoPilotos);
		return arrayPilotos;
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
