/**
 * Clase Distancia
 * Clase de tipo enumerado para las distintas instancias con valor fijo del enumerado Distancia de Circuitos.
 * @author Javier Santamar?a Caballero
 * @author Juan Jos? Carballo Pacheco
 */
public enum Distancia {
    CORTA("CORTA",250.0),
    INTERMEDIA("INTERMEDIA",275.0),
    LARGA("LARGA",300.0);
    private final String nombre;
    private final double valor;
    public String getNombre() {
        return nombre;
    }
    public double getValor() {
        return valor;
    }
    Distancia(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    public String toString() {
        return getNombre()+"(:"+getValor()+")";
    }

}
