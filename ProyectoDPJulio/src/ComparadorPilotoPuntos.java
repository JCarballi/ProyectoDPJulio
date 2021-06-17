import java.util.*;
/**
 * Clase ComparadorPilotoPuntos
 * Comparador para ordensr pilotos por puntosAcumulados en todos los circuitos inicialemnte y en caso de empate por destreza y en caso de empate por nombre.
 * Se usa en las 3 salidas para la ordenacion de escuderias.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public class ComparadorPilotoPuntos implements Comparator<Piloto> {
    @Override
    public int compare(Piloto p1,Piloto p2) {
        if(p1.puntosAcumulados()==p2.puntosAcumulados()) {
            if(p1.calcularDestrezaPiloto()==p2.calcularDestrezaPiloto()) {
                return p1.getNombrePiloto().compareTo(p2.getNombrePiloto())    ;
            }
            else if(p1.calcularDestrezaPiloto()>p2.calcularDestrezaPiloto())
                return 1;
            else
                return -1;
        }
        else if(p1.puntosAcumulados()>p2.puntosAcumulados())
            return 1;
        else 
            return -1;
    }

}    
