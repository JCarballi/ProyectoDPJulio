import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
public abstract class Piloto {
	private String nombrePiloto;
	private double ultimoTiempo; //TODO Para ordenar usando el ultimo tiempo sin nombreCircuito
	InterfazCoches c;
	private Concentracion concentracionPiloto;
	private boolean descalificado;
	private HashMap<String,Resultado> results;
	
	private BufferedWriter writer;

	public Piloto(String nombrePiloto, InterfazCoches c, Concentracion concentracionPiloto, BufferedWriter writer) {
		this.nombrePiloto = nombrePiloto;
		this.c = c;
		this.concentracionPiloto = concentracionPiloto;
		this.descalificado = false;
		this.ultimoTiempo = -1;
		this.writer = writer;
		results=new HashMap<>();

	}
	public double getUltimoTiempo() {
		return ultimoTiempo;
	}
	public void setUltimoTiempo(double ultimoTiempo) {
		this.ultimoTiempo = ultimoTiempo;
	}
	public double getConcentracionActual() {
		return getConcentracionPiloto().getValor();
	}
	public String getNombrePiloto() {
		return nombrePiloto;
	}
	public void setNombrePiloto(String nombrePiloto) {
		this.nombrePiloto = nombrePiloto;
	}
	public InterfazCoches getC() {
		return c;
	}
	public void setC(InterfazCoches c) {
		this.c = c;
	}
	public Concentracion getConcentracionPiloto() {
		return concentracionPiloto;
	}
	public void setConcentracionPiloto(Concentracion concentracionPiloto) {
		this.concentracionPiloto = concentracionPiloto;
	}
	public boolean isDescalificado() {
		return descalificado;
	}
	public void setDescalificado(boolean descalificado) {
		this.descalificado = descalificado;
	}
	public abstract double calcularDestrezaPiloto();
	
	public Resultado getResults(String nombrecircuito) {
		return results.get(nombrecircuito);
	}
	public void setResults(Resultado r,String nombrecirc) {
		this.results.put(nombrecirc, r);
	}
	public double puntosAcumulados() {
		
		double totalPunts=0;
		for (String  nombreCircuito : results.keySet()) {//voy cogiendo 1 a 1 todas ñlas claves del mapa
			Resultado r=	results.get(nombreCircuito);
			totalPunts+=r.getPuntos();
		}
		return totalPunts;
	}
	public int totalParticipadas() {
		int totalParti=	results.keySet().size();
		return totalParti;
	}
	public int totalTerminadas() {
		int totalesT=0;
		for (String  nombreCircuito : results.keySet()) {
			Resultado r=	results.get(nombreCircuito);
			if(r.getTiempo()>=0) {
				totalesT++;
			}
		}
		return totalesT;
	}
	public int totalAbandonadas() {
		int totalesA=0;
		for (String  nombreCircuito : results.keySet()) {
			Resultado r=	results.get(nombreCircuito);
			if(r.getTiempo()<0) {
				totalesA++;
			}
		}
		return totalesA;

	}
	/**
	 * @throws IOException 
	 * 
	 * @param cir
	 */
	public void conducir(CircuitoProyectoInterfaz cir) throws IOException {
		if(this.c!=null) {
			Resultado r=new Resultado();
			r.setCp(cir);
			double tiempoNeces=c.tiempoNecesarioTerminar(this.calcularDestrezaPiloto(), cir);
			double concentracionPrevia = getConcentracionActual();
			double concentracionRestante = Math.round((concentracionPrevia - tiempoNeces)*100d)/100d;
			c.reducirCombustible(tiempoNeces);
			if(c.getCombustibleAct() < 0 ||  concentracionRestante < 0) {
				if(c.getCombustibleAct() <= concentracionRestante) {
					r.setTiempo(c.getCombustibleAct());
					setUltimoTiempo(c.getCombustibleAct());
				}else {
					r.setTiempo(concentracionRestante);
					setUltimoTiempo(concentracionRestante);
					c.reducirCombustible(concentracionRestante);
				}
			}else {
				r.setTiempo(tiempoNeces);
				setUltimoTiempo(tiempoNeces);
			}
			if(cir.getNombreCircuito() == null)
				throw new IllegalArgumentException("Clave nula: nombreCircuito");
			this.setResults(r, cir.getNombreCircuito());
			if(this.getResults(cir.getNombreCircuito()).getTiempo() <= 0) {
				if(this.getC().getCombustibleAct() <= 0) {
					//TODO
					
					System.out.println("¡¡¡ El "+ this.getC().getNombreCoche() +" se quedó sin combustible a falta de "+ -this.getC().getCombustibleAct() +" minutos para terminar !!!");
					System.out.println("¡¡¡ En el momento de quedarse sin combustible llevaba en carrera "+ (Math.round(tiempoNeces+this.getC().getCombustibleAct()*100d)/100d) +" minutos !!!");
					writer.write("¡¡¡ El "+ this.getC().getNombreCoche() +" se quedó sin combustible a falta de "+ -this.getC().getCombustibleAct() +" minutos para terminar !!! \n");
					writer.write("¡¡¡ En el momento de quedarse sin combustible llevaba en carrera "+ (Math.round(tiempoNeces+this.getC().getCombustibleAct()*100d)/100d) +" minutos !!! \n");
					
				}else {
					System.out.println("¡¡¡ "+this.getNombrePiloto()+" perdió la concentración a falta de "+ -concentracionRestante +" minutos para terminar !!! ");
					System.out.println("¡¡¡ En el momento del despiste llevaba en carrera "+ (Math.round((tiempoNeces+concentracionRestante)*100d)/100d)+" minutos !!! ");
					writer.write("¡¡¡ "+this.getNombrePiloto()+" perdió la concentración a falta de "+ -concentracionRestante +" minutos para terminar !!! \n");
					writer.write("¡¡¡ En el momento del despiste llevaba en carrera "+ (Math.round((tiempoNeces+concentracionRestante)*100d)/100d)+" minutos !!! \n");
					
				}
			}else {
				System.out.println("+++ "+this.getNombrePiloto()+" termina la carrera en "+ tiempoNeces +"minutos +++");
				writer.write("+++ "+this.getNombrePiloto()+" termina la carrera en "+ tiempoNeces +"minutos +++ \n");

			}
			System.out.println("+++ El combustible del "+ this.getC().getNombreCoche() +" tras la carrera es "+ this.getC().getCombustibleAct() +" +++");
			writer.write("+++ El combustible del "+ this.getC().getNombreCoche() +" tras la carrera es "+ this.getC().getCombustibleAct() +" +++ \n");

			System.out.println("@@@");
			writer.write("@@@ \n");

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concentracionPiloto == null) ? 0 : concentracionPiloto.hashCode());
		result = prime * result + ((nombrePiloto == null) ? 0 : nombrePiloto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piloto other = (Piloto) obj;
		if (concentracionPiloto != other.concentracionPiloto)
			return false;
		if (nombrePiloto == null) {
			if (other.nombrePiloto != null)
				return false;
		} else if (!nombrePiloto.equals(other.nombrePiloto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "<piloto:"+getNombrePiloto()+"> <tipo:"+getClass().getName()+"> <dest:"+calcularDestrezaPiloto()+"> <conc:"+ getConcentracionPiloto().toString()+"> <descalificado:"+isDescalificado()+">";
	}

}
