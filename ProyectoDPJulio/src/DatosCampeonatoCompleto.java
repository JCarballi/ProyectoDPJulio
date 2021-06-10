public class DatosCampeonatoCompleto {

	public DatosCampeonatoCompleto(){     
		System.out.println("*********************************************************************************************************");
		System.out.println("*****************ESTA SIMULACIÓN CONCLUYE NORMALMENTE COMPLETÁNDOSE TODAS LAS CARRERAS*******************");        
		System.out.println("*********************************************************************************************************\n");
		initData() ;  
	}
	private void initData() {
		ComparadorCircuitoDistancia cCd= new ComparadorCircuitoDistancia();
		OrganizacionProyecto.getInstance(2, 3, cCd.reversed());
//arreglar todos
		CircuitoProyectoInterfaz  circProyectoPortugal=new CircuitoProyecto(Complejidad.MEDIA, Distancia.INTERMEDIA, "Portugal");
		CircuitoProyectoInterfaz circProPortugalGravilla= new CircuitoGravillaProyecto(circProyectoPortugal);
		CircuitoProyectoInterfaz circProPortugalNocturnoyGravilla=new CircuitoNocturnoProyecto(circProPortugalGravilla);
		OrganizacionProyecto.getInstance().inscribirCircuito(circProPortugalNocturnoyGravilla);
				
		CircuitoProyectoInterfaz circCerdenia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Cerdeña");
		CircuitoProyectoInterfaz circCerdeniaGravilla= new CircuitoGravillaProyecto(circCerdenia);
		CircuitoProyectoInterfaz circCerdeniaGravillayMojado=new CircuitoMojadoProyecto(circCerdeniaGravilla);
		OrganizacionProyecto.getInstance().inscribirCircuito(circCerdeniaGravillayMojado);

		CircuitoProyectoInterfaz circAustralia=new CircuitoProyecto(Complejidad.BAJA,Distancia.LARGA,"Australia");
		CircuitoProyectoInterfaz circAustraliaGravilla= new CircuitoGravillaProyecto(circAustralia);
		OrganizacionProyecto.getInstance().inscribirCircuito(circAustraliaGravilla);

		CircuitoProyectoInterfaz circCorcega=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Córcega");
		CircuitoProyectoInterfaz circCorcegaNocturno= new CircuitoNocturnoProyecto(circCorcega);
		CircuitoProyectoInterfaz circCorcegaNocturnoyGravilla=new CircuitoGravillaProyecto(circCorcegaNocturno);
		OrganizacionProyecto.getInstance().inscribirCircuito(circCorcegaNocturnoyGravilla);

		CircuitoProyectoInterfaz circFinlandia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Finlandia");
		CircuitoProyectoInterfaz circFinlandiaNocturno= new CircuitoNocturnoProyecto(circFinlandia);
		CircuitoProyectoInterfaz circFinlandiaNocturnoyFrio=new CircuitoFrioProyecto(circFinlandiaNocturno);
		CircuitoProyectoInterfaz circFinlandiaNocturnoyFrioyMojado=new CircuitoMojadoProyecto(circFinlandiaNocturnoyFrio);
		OrganizacionProyecto.getInstance().inscribirCircuito(circFinlandiaNocturnoyFrioyMojado);

		CircuitoProyecto circAlemania=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Alemania");
		CircuitoProyecto circAlemaniaMojado= new CircuitoMojadoProyecto(circAlemania);
		OrganizacionProyecto.getInstance().inscribirCircuito(circAlemaniaMojado);

		CircuitoProyectoInterfaz circChile=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Chile");
		CircuitoProyectoInterfaz circChileGravilla= new CircuitoGravillaProyecto(circChile);
		OrganizacionProyecto.getInstance().inscribirCircuito(circChileGravilla);
	           
		ComparadorPilotoPuntos compPuntos= new ComparadorPilotoPuntos();
		ComparadorCocheParaInitData cInitCoch=new ComparadorCocheParaInitData();
		EscuderiaProyecto ePeugeot =new EscuderiaProyecto("Peugeot",cInitCoch,compPuntos);
		OrganizacionProyecto.getInstance().inscribirEscuderia(ePeugeot);

		EscuderiaProyecto eCitroen=new EscuderiaProyecto("Citroen",cInitCoch.reversed(),compPuntos.reversed());
		OrganizacionProyecto.getInstance().inscribirEscuderia(eCitroen);

		EscuderiaProyecto eSeat=new EscuderiaProyecto("Seat",cInitCoch,compPuntos);
		OrganizacionProyecto.getInstance().inscribirEscuderia(eSeat);
		//ARREGALR EL RESTO
		InterfazCoches cocheResisC5= new CocheResistenteProyecto("Citröen C5", Velocidad.RAPIDA, Combustible.ELEFANTE);
		InterfazCoches cocheRapC4= new CocheRapidoProyecto("Citröen C4",Velocidad.RAPIDA,Combustible.ESCASO);
		InterfazCoches cocheNormC3= new CocheProyecto("Citröen C3",Velocidad.RAPIDA,Combustible.ESCASO);
		eCitroen.meterCocheEscuderia(cocheResisC5);
		eCitroen.meterCocheEscuderia(cocheRapC4);
		eCitroen.meterCocheEscuderia(cocheNormC3);
//ARREGLAR EL RESTO
		Piloto pExperimLoeb=  new PilotoExperimentado("Loeb", null, Concentracion.NORMAL);
		Piloto pEstrellMakinen= new PilotoEstrella("Makinen", null, Concentracion.ZEN);
		Piloto pNovatoAuriol=new PilotoNovato("Auriol", null, Concentracion.NORMAL);
		eCitroen.meterPilotoEscuderia(pExperimLoeb);
		eCitroen.meterPilotoEscuderia(pEstrellMakinen);
		eCitroen.meterPilotoEscuderia(pNovatoAuriol);

		InterfazCoches cocheResistTarraco= new CocheResistenteProyecto("Seat Tarraco", Velocidad.TORTUGA, Combustible.GENEROSO);
		InterfazCoches cocheRapAteca= new CocheRapidoProyecto("Seat Ateca",Velocidad.GUEPARDO,Combustible.GENEROSO);
		InterfazCoches cocheNormArona= new CocheProyecto("Seat Arona",Velocidad.RAPIDA,Combustible.ESCASO);
		eSeat.meterCocheEscuderia(cocheResistTarraco);
		eSeat.meterCocheEscuderia(cocheRapAteca);
		eSeat.meterCocheEscuderia(cocheNormArona);

		Piloto pExperimOgier=  new PilotoExperimentado("Ogier", null, Concentracion.NORMAL);
		Piloto pEstrellMcRae= new PilotoEstrella("McRae", null, Concentracion.CONCENTRADO);
		Piloto Blomquist=new PilotoNovato("Blomquist", null, Concentracion.DESPISTADO); 
		eSeat.meterPilotoEscuderia(pExperimOgier);
		eSeat.meterPilotoEscuderia(pEstrellMcRae);
		eSeat.meterPilotoEscuderia(Blomquist);

		InterfazCoches cocheResist5008= new CocheResistenteProyecto("Peugeot 5008", Velocidad.LENTA, Combustible.GENEROSO);
		InterfazCoches cocheRap3008= new CocheRapidoProyecto("Peugeot 3008",Velocidad.GUEPARDO,Combustible.NORMAL);
		InterfazCoches cocheNorm2008= new CocheProyecto("Peugeot 2008",Velocidad.NORMAL,Combustible.ESCASO);
		ePeugeot.meterCocheEscuderia(cocheResist5008);
		ePeugeot.meterCocheEscuderia(cocheRap3008);
		ePeugeot.meterCocheEscuderia(cocheNorm2008);

		Piloto pExperimKankunnen=  new PilotoExperimentado("Kankunnen", null, Concentracion.CONCENTRADO);
		Piloto pEstrellSainz= new PilotoEstrella("Sainz", null, Concentracion.ZEN);
		Piloto pNovatSordo=new PilotoNovato("Sordo", null, Concentracion.DESPISTADO); 
		ePeugeot.meterPilotoEscuderia(pExperimKankunnen);
		ePeugeot.meterPilotoEscuderia(pEstrellSainz);
		ePeugeot.meterPilotoEscuderia(pNovatSordo);
		
	}

}
