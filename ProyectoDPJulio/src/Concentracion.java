/**
 * Clase Concentracion
 * Clase utilizada para las distintas instancias fijas del tipo enumerado Concentracion de un piloto.
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */
public enum Concentracion {
	DESPISTADO("DESPISTADO",90.0),
	NORMAL("NORMAL",100.0),
	CONCENTRADO("CONCENTRADO",110.0),
	ZEN("ZEN",120.0);
	private final String nombre;
	private final double valor;
	public String getNombre() {
		return nombre;
	}
	public double getValor() {
		return valor;
	}

	Concentracion(String nombre, double valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	public String toString() {
		return getNombre()+"("+getValor()+")";
	}

}
