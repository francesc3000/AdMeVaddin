package com.luremesoftware.adme.modelo.gestor;

import com.luremesoftware.adme.bbdd.GrupoBbdd;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaPubli;

public class GestorGrupo {

	private GrupoBbdd grupoBbdd = null;
	
	public GestorGrupo(){
		this.grupoBbdd = new GrupoBbdd();
	}
	
	public ListaMensaje crearGrupo(Usuario usuario, Grupo grupo){
		return this.grupoBbdd.crearGrupo(usuario, grupo);
	}

	public ListaGrupo getListaGrupo(Usuario usuario){
		return this.grupoBbdd.getListaGrupo(usuario);
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
