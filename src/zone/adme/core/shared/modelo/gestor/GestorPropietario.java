package zone.adme.core.shared.modelo.gestor;

import zone.adme.core.server.bbdd.PropietarioBbdd;
import zone.adme.core.shared.modelo.ControlPuntuacion;
import zone.adme.core.shared.modelo.Propietario;

import com.googlecode.objectify.Key;
import zone.adme.core.shared.modelo.lista.ListaMensaje;

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
