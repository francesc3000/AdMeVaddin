package zone.adme.core.server.bbdd;

import static zone.adme.core.server.bbdd.OfyService.ofy;

import com.googlecode.objectify.Key;

import zone.adme.core.shared.modelo.ControlPuntuacion;
import zone.adme.core.shared.modelo.Propietario;
import zone.adme.core.shared.modelo.Puntuacion;
import zone.adme.core.shared.modelo.lista.ListaMensaje;

public class PropietarioBbdd {
	
	public PropietarioBbdd(){}
	
	public Key<ControlPuntuacion> creaControlPuntuacion(ControlPuntuacion controlPuntuacion){
		ofy().save().entity(controlPuntuacion).now();
		
		return Key.create(controlPuntuacion);
	}
	
	public Key<Puntuacion> creaPuntuacion(Puntuacion puntuacion){
		ofy().save().entity(puntuacion).now();
		
		return Key.create(puntuacion);
	}
	
	public ListaMensaje putPropietario(Propietario propietario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		ofy().save().entity(propietario).now();
		
		return listaMensaje;
	}
}