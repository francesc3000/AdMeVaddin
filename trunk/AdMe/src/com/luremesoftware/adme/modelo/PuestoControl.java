package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaPubli;

public class PuestoControl {
	private Usuario usuario = null;
	private ListaPubli listaPubli = null;
	private ListaGrupo listaGrupo = null;
	
	public PuestoControl(Usuario usuario){
		this.usuario = usuario;
		this.listaPubli = usuario.getListaPubli();
		this.listaGrupo = usuario.getListaGrupo();
	}
	
	public ListaPubli getListaPubli(){
		return this.listaPubli;
	}
	
	public ListaGrupo getListaGrupo(){
		return this.listaGrupo;
	}

}
