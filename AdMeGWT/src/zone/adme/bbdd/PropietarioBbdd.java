package zone.adme.bbdd;

import static zone.adme.bbdd.OfyService.ofy;

import com.googlecode.objectify.Key;
import zone.adme.modelo.ControlPuntuacion;
import zone.adme.modelo.Propietario;
import zone.adme.modelo.Puntuacion;
import zone.adme.modelo.lista.ListaMensaje;

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