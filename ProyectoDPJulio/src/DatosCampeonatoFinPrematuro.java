import java.io.BufferedWriter;
import java.io.IOException;
/**
 * Clase DatosCampeonatoFinPrematuro
 * Clase utilizada para una simulación que concluye antes de finalizar el campeonato con un unico piloto sin descalificar
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class DatosCampeonatoFinPrematuro {
    BufferedWriter writer;
    public DatosCampeonatoFinPrematuro(BufferedWriter writer) throws IOException {
        this.writer=writer;
        System.out.println("*********************************************************************************************************");
        System.out.println("****ESTA SIMULACIÓN CONCLUYE ANTES DE FINALIZAR EL CAMPEONATO CON UN ÚNICO PILOTO SIN DESCALIFICAR****");        
        System.out.println("*********************************************************************************************************\n");
        writer.write("*********************************************************************************************************\n");
        writer.write("****ESTA SIMULACIÓN CONCLUYE ANTES DE FINALIZAR EL CAMPEONATO CON UN ÚNICO PILOTO SIN DESCALIFICAR****\n");
        writer.write("*********************************************************************************************************\n");
        initData() ;        
    }
    /**
     * Clase para crear los elementos para esta simulación
     * @throws IOException
     */
    private void initData() throws IOException {
        /*Ordenamos los circuitos por complejidad descendente estableciendo el limite de abandonos a 1 y el limite de pilotos a 3*/
        ComparadorCircuitoComplejidad cCcomplejidad= new ComparadorCircuitoComplejidad();
        OrganizacionProyecto.getInstance(3, 1, cCcomplejidad.reversed(), writer);
        /*creamos los diferentes circuitos cumpliendo polimorfismo y cumpliendo con el decorator para las distintas capas*/
        /*Portugal*/
        CircuitoProyectoInterfaz cirProyectoPortugal=new CircuitoProyecto(Complejidad.MEDIA, Distancia.INTERMEDIA, "Portugal");
        cirProyectoPortugal= new CircuitoGravillaProyecto(cirProyectoPortugal);
        cirProyectoPortugal=new CircuitoNocturnoProyecto(cirProyectoPortugal);
        OrganizacionProyecto.getInstance().inscribirCircuito(cirProyectoPortugal);
        /*cerdeña*/
        CircuitoProyectoInterfaz circCerdenia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Cerdeña");
        circCerdenia= new CircuitoGravillaProyecto(circCerdenia);
        circCerdenia=new CircuitoMojadoProyecto(circCerdenia);
        OrganizacionProyecto.getInstance().inscribirCircuito(circCerdenia);
        /*Australia*/
        CircuitoProyectoInterfaz circAustralia=new CircuitoProyecto(Complejidad.BAJA,Distancia.LARGA,"Australia");
        circAustralia= new CircuitoGravillaProyecto(circAustralia);
        OrganizacionProyecto.getInstance().inscribirCircuito(circAustralia);
        /*Corcega*/
        CircuitoProyectoInterfaz  circCorcega=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Córcega");
        circCorcega= new CircuitoNocturnoProyecto(circCorcega);
        circCorcega=new CircuitoGravillaProyecto(circCorcega);
        OrganizacionProyecto.getInstance().inscribirCircuito(circCorcega);
        /*Finlandia*/
        CircuitoProyectoInterfaz circFinlandia=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Finlandia");
        circFinlandia= new CircuitoNocturnoProyecto(circFinlandia);
        circFinlandia=new CircuitoFrioProyecto(circFinlandia);
        circFinlandia=new CircuitoMojadoProyecto(circFinlandia);
        OrganizacionProyecto.getInstance().inscribirCircuito(circFinlandia);
        /*Alemania*/
        CircuitoProyectoInterfaz circAlemania=new CircuitoProyecto(Complejidad.MEDIA,Distancia.INTERMEDIA,"Alemania");
        circAlemania= new CircuitoMojadoProyecto(circAlemania);
        OrganizacionProyecto.getInstance().inscribirCircuito(circAlemania);
        /*Chile*/
        CircuitoProyectoInterfaz circChile=new CircuitoProyecto(Complejidad.ALTA,Distancia.CORTA,"Chile");
        circChile= new CircuitoGravillaProyecto(circChile);
        OrganizacionProyecto.getInstance().inscribirCircuito(circChile);
        /*creamos los comparadores para las escuderias*/        
        ComparadorPilotoPuntos compPuntos= new ComparadorPilotoPuntos();
        ComparadorCocheParaInitData cInitCoch=new ComparadorCocheParaInitData();
        /*Creamos las diferentes escuderias y las inscribimos*/
        /*Peugeot*/
        EscuderiaProyecto ePeugeot =new EscuderiaProyecto("Peugeot",cInitCoch.reversed(),compPuntos.reversed(), writer);
        ePeugeot.inscribirEscuderia();
        /*Citroen*/
        EscuderiaProyecto eCitroen=new EscuderiaProyecto("Citroen",cInitCoch,compPuntos, writer);
        eCitroen.inscribirEscuderia();
        /*Seat*/
        EscuderiaProyecto eSeat=new EscuderiaProyecto("Seat",cInitCoch.reversed(),compPuntos.reversed(), writer);
        eSeat.inscribirEscuderia();
        /*Creamos los diferentes coches y pilotos de las distintas escuderias cumpliendo polimorfismo*/    
        /*Citroen*/
        InterfazCoches cocheResisC5= new CocheResistenteProyecto("Citröen C5", Velocidad.RAPIDA, Combustible.ELEFANTE, writer);
        InterfazCoches cocheRapC4= new CocheRapidoProyecto("Citröen C4",Velocidad.RAPIDA,Combustible.ESCASO, writer);
        InterfazCoches cocheNormC3= new CocheProyecto("Citröen C3",Velocidad.RAPIDA,Combustible.ESCASO, writer);
        eCitroen.meterCocheEscuderia(cocheResisC5);
        eCitroen.meterCocheEscuderia(cocheRapC4);
        eCitroen.meterCocheEscuderia(cocheNormC3);

        Piloto  pExperimLoeb=  new PilotoExperimentado("Loeb", null, Concentracion.NORMAL, writer);
        Piloto pEstrellMakinen= new PilotoEstrella("Makinen", null, Concentracion.ZEN, writer);
        Piloto pNovatoAuriol=new PilotoNovato("Auriol", null, Concentracion.NORMAL, writer);
        eCitroen.meterPilotoEscuderia(pExperimLoeb);
        eCitroen.meterPilotoEscuderia(pEstrellMakinen);
        eCitroen.meterPilotoEscuderia(pNovatoAuriol);
        /*Seat*/
        InterfazCoches cocheResistTarraco= new CocheResistenteProyecto("Seat Tarraco", Velocidad.TORTUGA, Combustible.GENEROSO, writer);
        InterfazCoches cocheRapAteca= new CocheRapidoProyecto("Seat Ateca",Velocidad.GUEPARDO,Combustible.GENEROSO, writer);
        InterfazCoches cocheNormArona= new CocheProyecto("Seat Arona",Velocidad.RAPIDA,Combustible.ESCASO, writer);
        eSeat.meterCocheEscuderia(cocheResistTarraco);
        eSeat.meterCocheEscuderia(cocheRapAteca);
        eSeat.meterCocheEscuderia(cocheNormArona);

        Piloto pExperimOgier=  new PilotoExperimentado("Ogier", null, Concentracion.NORMAL, writer);
        Piloto pEstrellMcRae= new PilotoEstrella("McRae", null, Concentracion.CONCENTRADO, writer);
        Piloto pNovatoBlomquist=new PilotoNovato("Blomquist", null, Concentracion.DESPISTADO, writer); 
        eSeat.meterPilotoEscuderia(pExperimOgier);
        eSeat.meterPilotoEscuderia(pEstrellMcRae);
        eSeat.meterPilotoEscuderia(pNovatoBlomquist);
        /*Peugeot*/
        InterfazCoches cocheResist5008= new CocheResistenteProyecto("Peugeot 5008", Velocidad.LENTA, Combustible.GENEROSO, writer);
        InterfazCoches cocheRap3008= new CocheRapidoProyecto("Peugeot 3008",Velocidad.GUEPARDO,Combustible.NORMAL, writer);
        InterfazCoches cocheNorm2008= new CocheProyecto("Peugeot 2008",Velocidad.NORMAL,Combustible.ESCASO, writer);
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
