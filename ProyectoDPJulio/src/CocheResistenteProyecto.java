import java.io.BufferedWriter;
import java.io.IOException;

public class CocheResistenteProyecto extends CocheProyecto {
	private  BufferedWriter writer;
	private double reservaExtra=100.0;

	
	public double getReservaExtra() {
		return reservaExtra;
	}
	public CocheResistenteProyecto(String nombreCoche,Velocidad v,Combustible c,BufferedWriter writer) {
		super(nombreCoche,v,c, writer);
		
	}
	
	@Override
	public void reducirCombustible(double combustibleAct) {
		if(this.reservaExtra>0 && combustibleAct>super.getCombustibleAct()) {
			this.combustibleAct = Math.round((this.combustibleAct + reservaExtra - combustibleAct)*100d)/100d;
			this.reservaExtra=0;
			System.out.println("+++ El"+ getNombreCoche() +" tiene que recurrir al depósito de reserva para poder correr +++");
			try {
				writer.write("+++ El"+ getNombreCoche() +" tiene que recurrir al depósito de reserva para poder correr +++\n");
			} catch (IOException e) {
				System.out.println("Error imprimir reducirCombustible");
			}
		}else
			super.reducirCombustible(combustibleAct);

	}

	@Override
	public String toString() {
		return super.toString()+" <reserva: "+getReservaExtra()+">";
	}

}
