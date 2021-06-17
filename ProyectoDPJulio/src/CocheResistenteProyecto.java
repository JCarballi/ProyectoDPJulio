import java.io.BufferedWriter;
import java.io.IOException;
/**
 *Clase CocheResistenteproyecto
 *Clase que representa a uno de los coches de la herencia que se caracteriza por tener una reserva extra de combustible y reducir el combustible de manera diferente a los coches normales
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 */
public class CocheResistenteProyecto extends CocheProyecto {
	protected double reservaExtra=100.0;/*atributo de valor fijo que se puede reducir si se usa la reservaExtra*/


	protected double getReservaExtra() {
		return reservaExtra;
	}
	public CocheResistenteProyecto(String nombreCoche,Velocidad v,Combustible c,BufferedWriter writer) {
		super(nombreCoche,v,c, writer);

	}
	/**
	 *Método para reducir el combustible de manera especial en los cochesResistentes con la reservaExtra.
	 *@param combustibleAct es la cantidad de combustible que se va a reducir
	 */
	@Override/*lo sobreescribimos para que ejecute el método del hijo y no se vaya por el del padre*/
	public void reducirCombustible(double combustibleAct) {
		if(this.reservaExtra>0 && combustibleAct>super.getCombustibleAct()) {/*si tenemos reservaExtra y el combustible que se pierde es mayor que el combustible que tenemos ahora,palicamos lo de la reservaExtra*/
			this.combustibleAct = Math.round((this.combustibleAct + reservaExtra - combustibleAct)*100d)/100d;/*recalculamos el combustible actual*/
			this.reservaExtra=0;/*se utiliza toda la reserva porque la que sobre se usa mas adelante*/
			System.out.println("+++ El"+ getNombreCoche() +" tiene que recurrir al depósito de reserva para poder correr +++");
			try {
				writer.write("+++ El"+ getNombreCoche() +" tiene que recurrir al depósito de reserva para poder correr +++\n");
			} catch (IOException e) {
				System.out.println("Error imprimir reducirCombustible");
			}
		}else/*si no tenemos reservaExtra reducimos el combustible demanera normal igual que en los coches normales*/
			super.reducirCombustible(combustibleAct);

	}

	@Override/*se muestra toda la información de los coches mas la reservaExtra que es especial de cocheResistente*/
	public String toString() {
		return super.toString()+" <reserva: "+getReservaExtra()+">";
	}

}
