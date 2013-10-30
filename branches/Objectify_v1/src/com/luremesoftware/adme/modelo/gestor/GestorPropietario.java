package com.luremesoftware.adme.modelo.gestor;

import com.googlecode.objectify.Key;
import com.luremesoftware.adme.bbdd.PropietarioBbdd;
import com.luremesoftware.adme.modelo.ControlPuntuacion;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;

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
