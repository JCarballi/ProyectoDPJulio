import java.io.BufferedWriter;
import java.io.IOException;

public class DatosCampeonatoCompleto {
	private BufferedWriter writer;
	
	public DatosCampeonatoCompleto(BufferedWriter writer) throws IOException{     
		this.writer = writer;
		System.out.println("*********************************************************************************************************");
		System.out.println("*****************ESTA SIMULACIÓN CONCLUYE NORMALMENTE COMPLETÁNDOSE TODAS LAS CARRERAS*******************");        
		System.out.println("*********************************************************************************************************\n");
		writer.write("*********************************************************************************************************\n"+
				"*****************ESTA SIMULACIÓN CONCLUYE NORMALMENTE COMPLETÁNDOSE TODAS LAS CARRERAS*******************\n"+
				"*********************************************************************************************************\n");
		initData() ;  
	}
	private void initData() {
		ComparadorCircuitoDistancia cCd= new ComparadorCircuitoDistancia();
		OrganizacionProyecto.getInstance(2, 3, cCd.reversed());
		//arreglar todos
		CircuitoProyectoInterfaz  circProyectoPortugal=new CircuitoProyecto(Complejidad.MEDIA, Distancia.INTERMEDIA, "Portugal");
		circProyectoPortugal= new CircuitoGravillaProyecto(circProyectoPortugal);
		circProyectoPortugal=new CircuitoNocturnoProyecto(circProyectoPortugal);
		OrganizacionProyecto.getInstance().inscribirCircuito(circProyectoPortugal);

		CircuitoProyectoInterfaz circCerdenia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Cerdeña");
		circCerdenia= new CircuitoGravillaProyecto(circCerdenia);
		circCerdenia=new CircuitoMojadoProyecto(circCerdenia);
		OrganizacionProyecto.getInstance().inscribirCircuito(circCerdenia);

		CircuitoProyectoInterfaz circAustralia=new CircuitoProyecto(Complejidad.BAJA,Distancia.LARGA,"Australia");
		circAustralia= new CircuitoGravillaProyecto(circAustralia);
		OrganizacionProyecto.getInstance().inscribirCircuito(circAustralia);

		CircuitoProyectoInterfaz circCorcega=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Córcega");
		circCorcega= new CircuitoNocturnoProyecto(circCorcega);
		circCorcega=new CircuitoGravillaProyecto(circCorcega);
		OrganizacionProyecto.getInstance().inscribirCircuito(circCorcega);

		CircuitoProyectoInterfaz circFinlandia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Finlandia");
		circFinlandia= new CircuitoNocturnoProyecto(circFinlandia);
		circFinlandia=new CircuitoFrioProyecto(circFinlandia);
		circFinlandia=new CircuitoMojadoProyecto(circFinlandia);
		OrganizacionProyecto.getInstance().inscribirCircuito(circFinlandia);

		CircuitoProyectoInterfaz circAlemania=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Alemania");
		circAlemania= new CircuitoMojadoProyecto(circAlemania);
		OrganizacionProyecto.getInstance().inscribirCircuito(circAlemania);

		CircuitoProyectoInterfaz circChile=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Chile");
		circChile= new CircuitoGravillaProyecto(circChile);
		OrganizacionProyecto.getInstance().inscribirCircuito(circChile);

		ComparadorPilotoPuntos compPuntos= new ComparadorPilotoPuntos();
		ComparadorCocheParaInitData cInitCoch=new ComparadorCocheParaInitData();
		EscuderiaProyecto ePeugeot =new EscuderiaProyecto("Peugeot",cInitCoch,compPuntos);
		ePeugeot.inscribirEscuderia();

		EscuderiaProyecto eCitroen=new EscuderiaProyecto("Citroen",cInitCoch.reversed(),compPuntos.reversed());
		eCitroen.inscribirEscuderia();

		EscuderiaProyecto eSeat=new EscuderiaProyecto("Seat",cInitCoch,compPuntos);
		eSeat.inscribirEscuderia();
		//ARREGALR EL RESTO
		InterfazCoches cocheResisC5= new CocheResistenteProyecto("Citröen C5", Velocidad.RAPIDA, Combustible.ELEFANTE);
		InterfazCoches cocheRapC4= new CocheRapidoProyecto("Citröen C4",Velocidad.RAPIDA,Combustible.ESCASO);
		InterfazCoches cocheNormC3= new CocheProyecto("Citröen C3",Velocidad.RAPIDA,Combustible.ESCASO);
		eCitroen.meterCocheEscuderia(cocheResisC5);
		eCitroen.meterCocheEscuderia(cocheRapC4);
		eCitroen.meterCocheEscuderia(cocheNormC3);
		//ARREGLAR EL RESTO
		Piloto pExperimLoeb=  new PilotoExperimentado("Loeb", null, Concentracion.NORMAL, writer);
		Piloto pEstrellMakinen= new PilotoEstrella("Makinen", null, Concentracion.ZEN, writer);
		Piloto pNovatoAuriol=new PilotoNovato("Auriol", null, Concentracion.NORMAL, writer);
		eCitroen.meterPilotoEscuderia(pExperimLoeb);
		eCitroen.meterPilotoEscuderia(pEstrellMakinen);
		eCitroen.meterPilotoEscuderia(pNovatoAuriol);

		InterfazCoches cocheResistTarraco= new CocheResistenteProyecto("Seat Tarraco", Velocidad.TORTUGA, Combustible.GENEROSO);
		InterfazCoches cocheRapAteca= new CocheRapidoProyecto("Seat Ateca",Velocidad.GUEPARDO,Combustible.GENEROSO);
		InterfazCoches cocheNormArona= new CocheProyecto("Seat Arona",Velocidad.RAPIDA,Combustible.ESCASO);
		eSeat.meterCocheEscuderia(cocheResistTarraco);
		eSeat.meterCocheEscuderia(cocheRapAteca);
		eSeat.meterCocheEscuderia(cocheNormArona);

		Piloto pExperimOgier=  new PilotoExperimentado("Ogier", null, Concentracion.NORMAL, writer);
		Piloto pEstrellMcRae= new PilotoEstrella("McRae", null, Concentracion.CONCENTRADO, writer);
		Piloto Blomquist=new PilotoNovato("Blomquist", null, Concentracion.DESPISTADO, writer); 
		eSeat.meterPilotoEscuderia(pExperimOgier);
		eSeat.meterPilotoEscuderia(pEstrellMcRae);
		eSeat.meterPilotoEscuderia(Blomquist);

		InterfazCoches cocheResist5008= new CocheResistenteProyecto("Peugeot 5008", Velocidad.LENTA, Combustible.GENEROSO);
		InterfazCoches cocheRap3008= new CocheRapidoProyecto("Peugeot 3008",Velocidad.GUEPARDO,Combustible.NORMAL);
		InterfazCoches cocheNorm2008= new CocheProyecto("Peugeot 2008",Velocidad.NORMAL,Combustible.ESCASO);
		ePeugeot.meterCocheEscuderia(cocheResist5008);
		ePeugeot.meterCocheEscuderia(cocheRap3008);
		ePeugeot.meterCocheEscuderia(cocheNorm2008);

		Piloto pExperimKankunnen=  new PilotoExperimentado("Kankunnen", null, Concentracion.CONCENTRADO, writer);
		Piloto pEstrellSainz= new PilotoEstrella("Sainz", null, Concentracion.ZEN, writer);
		Piloto pNovatSordo=new PilotoNovato("Sordo", null, Concentracion.DESPISTADO, writer); 
		ePeugeot.meterPilotoEscuderia(pExperimKankunnen);
		ePeugeot.meterPilotoEscuderia(pEstrellSainz);
		ePeugeot.meterPilotoEscuderia(pNovatSordo);

	}

}
