package com.luremesoftware.adme.modelo.gestor;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.bbdd.PropietaroBbdd;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;

public class GestorPropietario {
	private PropietaroBbdd propietarioBbdd = null;
	private GestorPubli gestorPubli = null;
	
	public GestorPropietario(){
		this.propietarioBbdd = new PropietaroBbdd();
		this.gestorPubli = new GestorPubli();
	}
	
	public Propietario getPropietario(String id){
		return this.propietarioBbdd.getPropietario(id);
	}
	
	public Propietario getPropietarioByKey(Key key){
		return this.propietarioBbdd.getPropietarioByKey(key);
	}
	
	public List<Publi> getListaPubli(Propietario propietario){
		
	    if(propietario.getListaPubli().isEmpty()){
	    	propietario.setListaPubli(gestorPubli.getListaPubli(propietario));
		}
	
		return propietario.getListaPubli();
	}

	public List<Publi> getListaPubli(ArrayList<Propietario> listaPropietario){
		
		ArrayList<Publi> listaPubli = new ArrayList<Publi>();

		for(Propietario propietario:listaPropietario){
			propietario.setListaPubli(this.getListaPubli(propietario));
			listaPubli.addAll(propietario.getListaPubli());
		}

		return listaPubli;
	}
	
	public ListaMensaje putPropietario(Propietario propietario){
		return this.propietarioBbdd.putPropietario(propietario);
	}
}
