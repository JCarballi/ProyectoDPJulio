
public interface EstrategiaPilotos {//
	public abstract double CalcularDestrezaP(double concentracion);
	

}
//para no tener que hacer un metodo para cada tipo de estrategia hago una interfaz y asi tengo una clase comun(EstrategiaPilotos)
//el metodo siempore nes el miusmo ,lo unico que varia es el calculo
//no se usa extensds porque no es necesario herencia ,solo necesitamos variar una caracteristica,es una clase a parte
//declaro una interfaz estrategiaPiloto porque despues creo otras clases que implementan esta interfaz(diferentes estrategias)ya que esta interfaz tiene un metodo abstracto
//por lo que obliga a implementar este metodo(en este caso de diferentes formas) al extender esaas clase de ella