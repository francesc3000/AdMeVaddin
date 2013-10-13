package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaPubli;

public class PuestoControl {
	private Usuario usuario = null;
	private ListaPubli listaPubli = null;
	private ListaGrupo listaGrupo = null;
	
	public PuestoControl(Usuario usuario){
		this.usuario = usuario;
		this.listaGrupo = new ListaGrupo();
		this.listaGrupo.addAll(usuario.getListaGrupo());
		
		//Se recuperan las publicaciones del Usuario 
		//y de los equipos en los que participa
		this.listaPubli = new ListaPubli();
		this.listaPubli.addAll(usuario.getListaPubli());
		for(Grupo grupo:this.listaGrupo){
			this.listaPubli.addAll(grupo.getListaPubli());
		}
	}
	
	public ListaPubli getListaPubli(){
		return this.listaPubli;
	}
	
	public ListaGrupo getListaGrupo(){
		return this.listaGrupo;
	}

}
