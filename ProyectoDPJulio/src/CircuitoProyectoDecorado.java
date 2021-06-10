public abstract class CircuitoProyectoDecorado extends CircuitoProyecto  {
	protected CircuitoProyecto cp;

	public CircuitoProyectoDecorado(CircuitoProyecto cp) {
		super(cp.getComplejidadCircuito(),cp.getDistanciaCircuito(),cp.getNombreCircuito());
		this.cp=cp;
	}
	
	@Override
	public String getComplicaciones() {
		return	cp.getComplicaciones()+getClass().getName()+" ";

	}
	
}
