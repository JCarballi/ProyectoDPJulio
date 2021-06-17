import java.io.BufferedWriter;
/**
 *Clase CocheRapidoProyecto
 *Clase que representa al tipo de coche rapido de la herencia que se caracteriza por tener un depositoExtra de nitro y calcular la velocidad real de diferente manera.
 *@author Javier Santamaría Caballero
 *@author Juan José Carballo Pacheco
 */
import java.io.IOException;

public class CocheRapidoProyecto extends CocheProyecto{
	protected double depositoN=80.0;/*atributo fijo que se puede reducir para el deposiuto de nitro que caracteriza a los coches rapidos*/
	protected  double getDepositoN() {
		return depositoN;
	}
	public CocheRapidoProyecto(String nombreCoche,Velocidad v,Combustible c,BufferedWriter writer) {
		super(nombreCoche,v,c,writer);
	}
	/**
	 *Método para calcular la velocidad real de un coche de manera especial en un coche rapido
	 *@param destreza destreza del piloto
	 *@param c circuito en el que corre
	 *@throws IOException
	 *@return veloReal*/

	@Override/* lo sobreescribimos para que se escriba el del hijo*/
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) throws IOException {
		double veloFinal=0;
		double veloReal=super.velocidadRealCoche(destreza, c);/*obtenemos la velocidad de un coche normal*/
		if(this.depositoN<=0) {/*si no tenemos deposito de nitro´la devolvemos tal cual*/
			return veloReal;
		}
		else {/*en caso contrario,se aplica la particularidad de este tipo de coche*/
			veloFinal=Math.round((veloReal*0.2)*100d)/100d;/*la velocidad final sera el 20% de la velocidad calculada anteriormente mas la anterior*/
			veloFinal= Math.round((veloFinal+veloReal)*100d)/100d;
			double diferencia=Math.round((veloFinal-veloReal)*100d)/100d;/*calculamos la diferencia entre la anterior y la nueva*/
			if(this.depositoN<diferencia) {/*si el deposito disponible es menor que la diferencia,solo aumentamos lo que quede de deposito y reescribimos el valor de la velocidad real*/
				veloFinal=Math.round((veloReal + this.depositoN)*100d)/100d;
				this.depositoN=0;/*en cualquier caso siempre sera 0 porque lo agotamos todo*/

			}
			else {
				this.depositoN=Math.round((this.depositoN-diferencia)*100d)/100d;/*si hay suficiente nitro,ponemos el valor del nitro restante*/			}

			System.out.println("+++"+getNombreCoche()+" usa "+ diferencia +" de nitro para alcanzar "+ veloFinal +" km/hora y el nitro restante es "+ getDepositoN() +"+++");
			writer.write("+++"+getNombreCoche()+" usa "+ diferencia +" de nitro para alcanzar "+ veloFinal +" km/hora y el nitro restante es "+ getDepositoN() +"+++\n");

		}
		return veloFinal; 
	}

	@Override/*imprime las características de un coche (con el tipo de clase coche rapido) mas la caracteristica especial del coche rapido del nitroPendiente*/
	public String toString() {

		return super.toString()+" <nitroPendiente: "+getDepositoN()+">";
	}

}
