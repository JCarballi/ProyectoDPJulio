public class CircuitoFrioProyecto extends CircuitoProyectoDecorado {
	private static final double complejiCirc=1.1;
	private static final double distancia=0.9;

	public CircuitoFrioProyecto(CircuitoProyectoInterfaz cpi) {
		super(cpi);
	}
	@Override
	public double getValorComplejidadCircuito() {
		return Math.round(cp.getValorComplejidadCircuito()*complejiCirc*100d)/100d;
	}

	@Override
	public double getValorDistanciaCircuito() {
		return Math.round(cp.getValorDistanciaCircuito()*distancia*100d)/100d;
	}

}