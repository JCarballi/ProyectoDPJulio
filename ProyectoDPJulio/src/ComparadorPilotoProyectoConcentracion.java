import java.util.Comparator;
/**
 * Clase ComparadorPilotoProyectoConcentracion
 * Comparador para ordenar los pilotos por valor de concentracion actual.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorPilotoProyectoConcentracion implements Comparator<Piloto> {
    @Override
    public int compare(Piloto p1,Piloto p2) {
        if(p1.getConcentracionActual()==p2.getConcentracionActual())
            return 0;
        else if(p1.getConcentracionActual()>p2.getConcentracionActual())
            return 1;
        else
            return -1;
    }

}
