package com.luremesoftware.adme.modelo.gestor;

import com.luremesoftware.adme.bbdd.PubliBbdd;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

public class GestorPubli {
	
	private PubliBbdd publiBbdd = null;
	
	public GestorPubli(){
		this.publiBbdd = new PubliBbdd();
	}
	
	/**
	 * Crea una publicación en el sistema
	 * 
	 * @return Retorna el identificador de la publicación
	 */
	public ListaMensaje putPubli(Publi publi){
		GestorUsuario gestorUsuario = new GestorUsuario();
		ListaMensaje listaMensaje = new ListaMensaje();
		listaMensaje.addAll(gestorUsuario.existeUsuario(publi.getPropietario().getId()));
		if(listaMensaje.contieneErrores()){
			listaMensaje.addAll(publiBbdd.putPublicacion(publi));
		}
		return listaMensaje;
	}
	
	/**
	 * Se retorna el listado de Publicaciones a partir de un Id
	 * @param id Identificador que se quiere buscar sus publicaciones
	 * @return Listado de Publicaciones
	 */
	public ListaPubli getListaPubli(Propietario propietario){
		return publiBbdd.getListaPubli(propietario);
	}
	
	/**
	 * Se retorna el listado de Pubicaciones a partir de una lista de Usuarios
	 * @param listaUsuario Listado de Usuario que se quieren buscar sus publicaciones
	 * @return Listado de Publicaciones
	 */
	public ListaPubli getListaPubli(ListaUsuario listaUsuario){
		ListaPubli listaPubli = new ListaPubli();
		
		for(Usuario usuario:listaUsuario){
			listaPubli.addAll(this.getListaPubli(usuario));
		}
		return listaPubli;
	}
	
	/**
	 * Se retorna el listado de Publicaciones a partir de un listado de Grupos
	 * @param listaGrupo Listado de grupos que se quieren buscar sus publicaciones
	 * @return Se retorna el listado de Publicaciones
	 */
	public ListaPubli getListaPubli(ListaGrupo listaGrupo){
		ListaPubli listaPubli = new ListaPubli();
		
		for(Grupo grupo:listaGrupo){
			listaPubli.addAll(this.getListaPubli(grupo));
		}
		return listaPubli;
	}
	
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		return this.publiBbdd.getListaPubli(listaMetadato);
	}
}
