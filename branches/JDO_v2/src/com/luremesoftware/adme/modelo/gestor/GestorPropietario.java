package com.luremesoftware.adme.modelo.gestor;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.bbdd.PropietaroBbdd;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;

public class GestorPropietario {
	private PropietaroBbdd propietarioBbdd = null;
	private GestorPubli gestorPubli = null;
	
	public GestorPropietario(){
		this.propietarioBbdd = new PropietaroBbdd();
		this.gestorPubli = new GestorPubli();
	}
	
	public Propietario getPropietarioByKey(Key key){
		return this.propietarioBbdd.getPropietarioByKey(key);
	}
	
	public ArrayList<Publi> getListaPubli(Propietario propietario){
		
	    if(propietario.getListaPubli().isEmpty()){
	    	propietario.setListaPubli(gestorPubli.getListaPubli(propietario));
		}
	
		return propietario.getListaPubli();
	}

	public ArrayList<Publi> getListaPubli(ArrayList<Propietario> listaPropietario){
		
		ArrayList<Publi> listaPubli = new ArrayList<Publi>();

		for(Propietario propietario:listaPropietario){
			propietario.setListaPubli(this.getListaPubli(propietario));
			listaPubli.addAll(propietario.getListaPubli());
		}

		return listaPubli;
	}
}
