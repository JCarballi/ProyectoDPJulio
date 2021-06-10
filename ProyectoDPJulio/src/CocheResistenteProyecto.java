public class CocheResistenteProyecto extends CocheProyecto {
	private double reservaExtra=100.0;
	
	public double getReservaExtra() {
		return reservaExtra;
	}
	public CocheResistenteProyecto(String nombreCoche,Velocidad v,Combustible c) {
		super(nombreCoche,v,c);
		
	}
	
	@Override
	public double combustibleFinaldeCoche(double valor) {
		if(this.reservaExtra>0 && valor>super.getCombustibleAct()) {
			valor -= reservaExtra;
			this.reservaExtra=0;
			System.out.println("+++ El"+ getNombreCoche() +"tiene que recurrir al depósito de reserva para poder correr +++");

		}
		return super.combustibleFinaldeCoche(valor);

	}

	@Override
	public String toString() {
		return super.toString()+" <reserva: "+getReservaExtra()+">";
	}

}
