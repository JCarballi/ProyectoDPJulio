/**
 *Clase Complejidad
 *Tipo enumerado con los distintos valores de complejidad que puede tener un circuito.
 *@author Javier Santamar�a Caballero
 *@author Juan Jos� Carballo Pacheco
 */
public enum Complejidad {
    BAJA("BAJA",1.0),
    MEDIA("MEDIA",1.25),
    ALTA("ALTA",1.5);
    private final String nombre;
    private final double valor;
    public String getNombre() {
        return nombre;
    }
    public double getValor() {
        return valor;
    }

    Complejidad(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    public String toString() {
        return getNombre()+"(:"+getValor()+")";
    }

}
