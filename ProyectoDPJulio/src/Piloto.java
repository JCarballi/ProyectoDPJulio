import java.io.BufferedWriter;
/**
 * Clase Piloto
 *Clase padre de Piloto que es abstracta porque no pueden existir pilotos normales,solo de uno de los 3 tipos.
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 * */
import java.io.IOException;
import java.util.*;
public abstract class Piloto {
    private String nombrePiloto;
    private double ultimoTiempo;/*para ordenar  a los pilotos por su ultimo tiempo en la ultima carrera disputada*/
    InterfazCoches c;/*para el strategy*/
    private Concentracion concentracionPiloto;
    private boolean descalificado;
    private HashMap<String,Resultado> results;/*Mapa de resultados cuya clave es el nombre del circuito*/

    private BufferedWriter writer;/*escritura en el fichero de lo que se muestre por pantalla en la clase*/

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
    public double getConcentracionActual() {/*obtenemos el valor numérico de la concentración*/
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
    /**
     * Método abstracto que se utilizará para calcular la destreza de los 3 tipos de piloto.
     *@return destreza del tipo de piloto correspondiente.
     *
     * */
    public abstract double calcularDestrezaPiloto();

    public Resultado getResults(String nombrecircuito) {
        return results.get(nombrecircuito);
    }
    public void setResults(Resultado r,String nombrecirc) {
        this.results.put(nombrecirc, r);
    }
    /**
     * Método que calcula los puntos acumulados de un piloto en todos los circuitos en los que compite.
     *@return totalPunts
     *
     * */
    public double puntosAcumulados() {

        double totalPunts=0;
        for (String  nombreCircuito : results.keySet()) {//voy cogiendo 1 a 1 todas ñlas claves del mapa
            Resultado r=    results.get(nombreCircuito);
            totalPunts+=r.getPuntos();
        }
        return totalPunts;
        /**
         * Método que calcula el número de carreras en las que ha participado un piloto.
         *@return totalParti
         *
         * */
    }
    public int totalParticipadas() {
        int totalParti=    results.keySet().size();
        return totalParti;
    }
    /**
     * Método que calcula el número de carreras que ha acabado un piloto.
     *@return totalesT
     *
     * */
    public int totalTerminadas() {
        int totalesT=0;
        for (String  nombreCircuito : results.keySet()) {
            Resultado r=    results.get(nombreCircuito);
            if(r.getTiempo()>=0) {
                totalesT++;
            }
        }
        return totalesT;
    }
    /**
     * Método que calcula el número de carreras que ha abandonado un piloto.
     *@return totalesA
     *
     * */
    public int totalAbandonadas() {
        int totalesA=0;
        for (String  nombreCircuito : results.keySet()) {
            Resultado r=    results.get(nombreCircuito);
            if(r.getTiempo()<0) {
                totalesA++;
            }
        }
        return totalesA;

    }
    /**
     * @throws IOException 
     * Clase utilizada para simular la conducción en el campeonato
     * @param cir circuito en el que conduce
     * 
     */
    public void conducir(CircuitoProyectoInterfaz cir) throws IOException {
        if(this.c!=null) {/*si el coche es nulo,no hacemos nada*/
            Resultado r=new Resultado();
            r.setCp(cir);/*asignamos el circuito*/
            double tiempoNeces=c.tiempoNecesarioTerminar(this.calcularDestrezaPiloto(), cir);
            double concentracionPrevia = getConcentracionActual();/*concentracion que tenia el piloto antes de conducir*/
            double concentracionRestante = Math.round((concentracionPrevia - tiempoNeces)*100d)/100d;/*concentración después de conducir*/
            c.reducirCombustible(tiempoNeces);/*reducimos el combustible en función de los minutos que hayamos necesitado para acabar*/
            if(c.getCombustibleAct() < 0 ||  concentracionRestante < 0) {/*si no acabamos la carrera*/
                if(c.getCombustibleAct() <= concentracionRestante) {/*si es por falta de combustible*/
                    r.setTiempo(c.getCombustibleAct());/*asignamos el tiempo en función del combustible que nos hubiera hecho falta*/
                    setUltimoTiempo(c.getCombustibleAct());
                }else {/*si es por falta de concentración*/
                    r.setTiempo(concentracionRestante);/*asignamos el tiempo en función de la concentración que nos hubiera hecho falta*/
                    setUltimoTiempo(concentracionRestante);
                    c.reducirCombustible(concentracionRestante);
                }
            }else {/*si hemos acabado el tiempo empleado es el tiempo que ha sido necesario para acabar la carrera*/
                r.setTiempo(tiempoNeces);
                setUltimoTiempo(tiempoNeces);
            }
            if(cir.getNombreCircuito() == null)/*excepción creada para caso de clave nula*/
                throw new IllegalArgumentException("Clave nula: nombreCircuito");
            this.setResults(r, cir.getNombreCircuito());/*metemos los resultados obtenidos en el mapa de resultados del piloto*/
            if(this.getResults(cir.getNombreCircuito()).getTiempo() <= 0) {/*si no ha acabado la carrera*/
                if(this.getC().getCombustibleAct() <= 0) {/*si ha sido por falta de concentración*/
                    /*mostramos los mensajes correspondientes en la simulación por la perdida de combustible*/
                    System.out.println("¡¡¡ El "+ this.getC().getNombreCoche() +" se quedó sin combustible a falta de "+ -this.getC().getCombustibleAct() +" minutos para terminar !!!");
                    System.out.println("¡¡¡ En el momento de quedarse sin combustible llevaba en carrera "+ (Math.round(tiempoNeces+this.getC().getCombustibleAct()*100d)/100d) +" minutos !!!");
                    writer.write("¡¡¡ El "+ this.getC().getNombreCoche() +" se quedó sin combustible a falta de "+ -this.getC().getCombustibleAct() +" minutos para terminar !!! \n");
                    writer.write("¡¡¡ En el momento de quedarse sin combustible llevaba en carrera "+ (Math.round(tiempoNeces+this.getC().getCombustibleAct()*100d)/100d) +" minutos !!! \n");

                }else {/*si ha sido por falta de concentración*/
                    /*mostramos los mensajes correspondientes en la simulación por la perdida de concentración*/
                    System.out.println("¡¡¡ "+this.getNombrePiloto()+" perdió la concentración a falta de "+ -concentracionRestante +" minutos para terminar !!! ");
                    System.out.println("¡¡¡ En el momento del despiste llevaba en carrera "+ (Math.round((tiempoNeces+concentracionRestante)*100d)/100d)+" minutos !!! ");
                    writer.write("¡¡¡ "+this.getNombrePiloto()+" perdió la concentración a falta de "+ -concentracionRestante +" minutos para terminar !!! \n");
                    writer.write("¡¡¡ En el momento del despiste llevaba en carrera "+ (Math.round((tiempoNeces+concentracionRestante)*100d)/100d)+" minutos !!! \n");

                }
            }else {/*en caso contrario,ha terminado la carrera*/
                System.out.println("+++ "+this.getNombrePiloto()+" termina la carrera en "+ tiempoNeces +"minutos +++");
                writer.write("+++ "+this.getNombrePiloto()+" termina la carrera en "+ tiempoNeces +"minutos +++ \n");

            }/*mostramos el combustible restante*/
            System.out.println("+++ El combustible del "+ this.getC().getNombreCoche() +" tras la carrera es "+ this.getC().getCombustibleAct() +" +++");
            writer.write("+++ El combustible del "+ this.getC().getNombreCoche() +" tras la carrera es "+ this.getC().getCombustibleAct() +" +++ \n");

            System.out.println("@@@");
            writer.write("@@@ \n");

        }
    }

    @Override/*utilizamos hashCode y equals en clases que utilizan un set para el cálculo sin valores repetidos*/
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
    /*se ejecuta el toString de un piloto u otro según la clase de la jerarquia en la que nos encontremos*/
    @Override
    public String toString() {
        return "<piloto:"+getNombrePiloto()+"> <tipo:"+getClass().getName()+"> <dest:"+calcularDestrezaPiloto()+"> <conc:"+ getConcentracionPiloto().toString()+"> <descalificado:"+isDescalificado()+">";
    }

}
