package zone.adme.core.modelo.gestor;

import zone.adme.core.bbdd.PropietarioBbdd;
import zone.adme.core.modelo.ControlPuntuacion;
import zone.adme.core.modelo.Propietario;

import com.googlecode.objectify.Key;
import zone.adme.core.modelo.lista.ListaMensaje;

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
