/**
 * Clase CircuitoProyectoDecorado
 * Clase que implementa los diferentes decoradores para el proyecto(herencia).
 * @author Javier Santamaría Caballero
 * @author Juan José Carballo Pacheco
 */

/*Se trata de una clase abstracta porque no existen decoradores normales,solo de los 4 tipos establecidos*/
public abstract class CircuitoProyectoDecorado extends CircuitoProyecto  {
    protected CircuitoProyectoInterfaz cp;/*se utiliza para poder establecer las diferentes capas del patron para poder aplicarlos correctamente*/

    public CircuitoProyectoDecorado(CircuitoProyectoInterfaz cp) {
        super(cp.getComplejidadCircuito(),cp.getDistanciaCircuito(),cp.getNombreCircuito());
        this.cp=cp;
    }/**
     *Método que calcula las complicaciones a imprimir en el toString según el tipo de complicaciones que se presenten
     * @return complicaciones
     */
    /*se usa para sobreescribir las complicaciones segun el tipo de complicacion en el que nos encontremos(incluyendo posibles capas)*/
    @Override
    public String getComplicaciones() {
        return    cp.getComplicaciones()+getClass().getName()+" ";

    }

}
