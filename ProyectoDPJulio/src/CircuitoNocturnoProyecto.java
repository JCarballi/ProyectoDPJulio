/**
 * Clase CircuitoNocturnoProyecto
 * Clase que implementa la complicacion circuito nocturno del proyecto.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class CircuitoNocturnoProyecto extends CircuitoProyectoDecorado {
    /*valores usados para el calculo de esta complicacion(complejidad y distancia circuito)*/
    private static final double complejiCirc=1.2;
    private static final double distancia=0.8;

    public CircuitoNocturnoProyecto(CircuitoProyectoInterfaz cpi) {
        super(cpi);
    }
    /**
     * Método que calcula la complejidad para el circuitoNocturno
     * @return complejidad circuitoNocturno
     */
    @Override/*Para que se calcule el método de esta clase*/
    public double getValorComplejidadCircuito() {
        return Math.round(cp.getValorComplejidadCircuito()*complejiCirc*100d)/100d;
    }
    /**
     * Método que calcula la distancia para el circuitoNocturno
     * @return distancia circuitoNocturno
     */
    @Override/*Para que se calcule el método de esta clase*/
    public double getValorDistanciaCircuito() {
        return Math.round(cp.getValorDistanciaCircuito()*distancia*100d)/100d;
    }

}
