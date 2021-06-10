public abstract class CircuitoProyectoDecorado extends CircuitoProyecto  {
	protected CircuitoProyectoInterfaz cp;

	public CircuitoProyectoDecorado(CircuitoProyectoInterfaz cp) {
		super(cp.getComplejidadCircuito(),cp.getDistanciaCircuito(),cp.getNombreCircuito());
		this.cp=cp;
	}
	
	@Override
	public String getComplicaciones() {
		return	cp.getComplicaciones()+getClass().getName()+" ";

	}
	
}
