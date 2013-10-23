package com.luremesoftware.adme.modelo.gestor;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.bbdd.GrupoBbdd;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;

public class GestorGrupo {

	private GrupoBbdd grupoBbdd = null;
	
	public GestorGrupo(){
		this.grupoBbdd = new GrupoBbdd();
	}

	public List<Grupo> getListaGrupoByKey(List<Key> listaKey){
		return this.grupoBbdd.getListaGrupoByKey(listaKey);
	}

	public List<Publi> getListaPubli(Grupo grupo){
		
		GestorPubli gestorPubli = new GestorPubli();
		
		grupo.setListaPubli(gestorPubli.getListaPubli(grupo));

		return grupo.getListaPubli();
		
	}
	
	public ArrayList<Publi> getListaPubli(ArrayList<Grupo> listaGrupo){
		ArrayList<Publi> listaPubli = new ArrayList<Publi>();
		
		for(Grupo grupo:listaGrupo){
			grupo.setListaPubli(this.getListaPubli(grupo));
			listaPubli.addAll(grupo.getListaPubli());
		}
		
		return listaPubli;
	}

	/**
	 * Regista un grupo en el sistema
	 * 
	 * @return Se retorna un listado de mensajes del sistema
	 */	
	public ListaMensaje putGrupo(Grupo grupo){	
		return this.grupoBbdd.putGrupo(grupo);
	}

	public ListaMensaje borraGrupo(Grupo grupo){
		return this.grupoBbdd.borraGrupo(grupo);
	}
}
