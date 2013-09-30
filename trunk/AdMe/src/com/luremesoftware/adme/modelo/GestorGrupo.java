package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.bbdd.Grupo_bbdd;

public class GestorGrupo {

	private Grupo_bbdd grupo_bbdd = null;
	
	public GestorGrupo(){
		this.grupo_bbdd = new Grupo_bbdd();
	}
	
	public ListaMensaje crearGrupo(Usuario usuario, Grupo grupo){
		return this.grupo_bbdd.crearGrupo(usuario, grupo);
	}

	public ListaGrupo getListaGrupo(Usuario usuario){
		return this.grupo_bbdd.getListaGrupo(usuario);
	}

	public ListaPubli getListaPubli(Grupo grupo){
		
		GestorPubli gestorPubli = new GestorPubli();
		
		grupo.setListaPubli(gestorPubli.getListaPubli(grupo));

		return grupo.getListaPubli();
		
	}
	
	public ListaPubli getListaPubli(ListaGrupo listaGrupo){
		ListaPubli listaPubli = new ListaPubli();
		
		for(Grupo grupo:listaGrupo){
			grupo.setListaPubli(this.getListaPubli(grupo));
			listaPubli.addAll(grupo.getListaPubli());
		}
		
		return listaPubli;
	}
}
