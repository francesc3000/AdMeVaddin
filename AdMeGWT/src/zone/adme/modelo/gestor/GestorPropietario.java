package zone.adme.modelo.gestor;

import com.googlecode.objectify.Key;
import zone.adme.bbdd.PropietarioBbdd;
import zone.adme.modelo.ControlPuntuacion;
import zone.adme.modelo.Propietario;
import zone.adme.modelo.lista.ListaMensaje;

public class GestorPropietario {
	
	private PropietarioBbdd propietarioBbdd = new PropietarioBbdd();
	
	public GestorPropietario(){}
	
	public Key<ControlPuntuacion> creaControlPuntuacion(Propietario propietario){
		ControlPuntuacion controlPuntuacion = new ControlPuntuacion(Key.create(propietario));
		return this.propietarioBbdd.creaControlPuntuacion(controlPuntuacion);
	}
	
	public ListaMensaje putPropietario(Propietario propietario){
		return this.propietarioBbdd.putPropietario(propietario);
	}
}
