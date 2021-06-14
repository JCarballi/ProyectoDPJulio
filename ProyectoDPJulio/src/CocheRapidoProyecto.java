import java.io.BufferedWriter;
import java.io.IOException;

public class CocheRapidoProyecto extends CocheProyecto{
	private double depositoN=80.0;
	public  double getDepositoN() {
		return depositoN;
	}
	public CocheRapidoProyecto(String nombreCoche,Velocidad v,Combustible c,BufferedWriter writer) {
		super(nombreCoche,v,c,writer);
	}

	@Override
	public double velocidadRealCoche(double destreza, CircuitoProyectoInterfaz c) throws IOException {
		double veloFinal=0;
		double veloReal=super.velocidadRealCoche(destreza, c);
		if(this.depositoN<=0) {
			return veloReal;
		}
		else {
			veloFinal=Math.round((veloReal*0.2)*100d)/100d;
			veloFinal= Math.round((veloFinal+veloReal)*100d)/100d;
			double diferencia=Math.round((veloFinal-veloReal)*100d)/100d;
			if(this.depositoN<diferencia) {
				veloFinal=Math.round((veloReal + this.depositoN)*100d)/100d;
				this.depositoN=0;

			}
			else {
				this.depositoN=Math.round((this.depositoN-diferencia)*100d)/100d; //aqui decremento el deposito final(es interno al estar como atributo de la clase)
			}

			System.out.println("+++"+getNombreCoche()+" usa "+ diferencia +" de nitro para alcanzar "+ veloFinal +" km/hora y el nitro restante es "+ getDepositoN() +"+++");
			writer.write("+++"+getNombreCoche()+" usa "+ diferencia +" de nitro para alcanzar "+ veloFinal +" km/hora y el nitro restante es "+ getDepositoN() +"+++\n");

		}
		return veloFinal; 
	}

	@Override
	public String toString() {
		
		return super.toString()+" <nitroPendiente: "+getDepositoN()+">";
	}

}
