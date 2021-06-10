public enum Combustible {
	ESCASO("ESCASO",350.0),
	NORMAL("NORMAL",440.0),
	GENEROSO("GENEROSO",460.0),
	ELEFANTE("ELEFANTE",480.0);
	private final String nombre;
	private final double valor;
	public String getNombre() {
		return nombre;
	}
	public double getValor() {
		return valor;
	}
	Combustible(String nombre, double valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	public String toString() {
		return getNombre()+"("+getValor()+")";

	}
	
}
