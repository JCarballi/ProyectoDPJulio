public class CocheResistenteProyecto extends CocheProyecto {
	private double reservaExtra=100.0;
	
	public double getReservaExtra() {
		return reservaExtra;
	}
	public CocheResistenteProyecto(String nombreCoche,Velocidad v,Combustible c) {
		super(nombreCoche,v,c);
		
	}
	
	@Override
	public void reducirCombustible(double combustibleAct) {
		if(this.reservaExtra>0 && combustibleAct>super.getCombustibleAct()) {
			this.combustibleAct = Math.round((this.combustibleAct + reservaExtra - combustibleAct)*100d)/100d;
			this.reservaExtra=0;
			System.out.println("+++ El"+ getNombreCoche() +" tiene que recurrir al depósito de reserva para poder correr +++");
		}else
			super.reducirCombustible(combustibleAct);

	}

	@Override
	public String toString() {
		return super.toString()+" <reserva: "+getReservaExtra()+">";
	}

}
