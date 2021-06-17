/**
 * Clase enumerada Velocidad
 * Clase utilizada para la creacion de los valores de las diferentes instancias de la clase velocidad teorica de los coches
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 *
 * */
/*no hacemos set en los enumerados porque son valores fijos y final*/
public enum Velocidad {
    /*instancias del tipo enumerado */
    TORTUGA("TORTUGA",200.0),
    LENTA("LENTA",210.0),
    NORMAL("NORMAL",220.0),
    RAPIDA("RAPIDA",230.0),
    GUEPARDO("GUEPARDO",240.0);
    private final String nombre;/*nombre de la instancia concreta del tipo enumerado*/
    private final double valor;/*valor de la instancia concreta del tipo enumerado*/
    /*es final porque siempre es un valor fijo ya que no se puede cambiar durante la ejecucion */
    /**
     * Método que obtiene el nombre de la instancia concreta del tipo enumerado.
     *
     * */
    public String getNombre() {
        return nombre;
    }
    /**
     * Método que obtiene el valor de la instancia concreta del tipo enumerado.
     *
     * */
    public double getValor() {
        return valor;
    }
    /**
     * Constructor parametrizado de la clase.
     *
     * */
    Velocidad(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    /**
     * Método toString de la clase.
     *
     * */
    @Override /*se sobreescribe para poder crear la cadena con el objeto de esta clase velocidad en concreta*/
    public String toString() {
        return getNombre()+"("+getValor()+")";
    }

}
