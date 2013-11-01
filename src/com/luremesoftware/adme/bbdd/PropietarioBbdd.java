package com.luremesoftware.adme.bbdd;

import static com.luremesoftware.adme.bbdd.OfyService.ofy;

import com.googlecode.objectify.Key;
import com.luremesoftware.adme.modelo.ControlPuntuacion;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Puntuacion;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;

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