package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.bbdd.Publi_bbdd;

public class GestorPubli {
	
	private Publi_bbdd publi_bbdd = null;
	
	public GestorPubli(){
		this.publi_bbdd = new Publi_bbdd();
	}
	
	/**
	 * Crea una publicación en el sistema
	 * 
	 * @return Retorna el identificador de la publicación
	 */
	public ListaMensaje crearPubli(Publi publi){
		return publi_bbdd.crearPublicacion(publi);
	}
	
	/**
	 * Se retorna el listado de Publicaciones a partir de un Id
	 * @param id Identificador que se quiere buscar sus publicaciones
	 * @return Listado de Publicaciones
	 */
	public ListaPubli getListaPubli(Propietario propietario){
		return publi_bbdd.getListaPubli(propietario);
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
		
		return this.publi_bbdd.getListaPubli(listaMetadato);
	}
}
