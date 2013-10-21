package com.luremesoftware.adme.modelo.gestor;

import java.util.ArrayList;

import com.luremesoftware.adme.bbdd.PubliBbdd;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

public class GestorPubli {
	
	private PubliBbdd publiBbdd = null;
	
	public GestorPubli(){
		this.publiBbdd = new PubliBbdd();
	}
	
	/**
	 * Se retorna el listado de Publicaciones a partir de un Id
	 * @param id Identificador que se quiere buscar sus publicaciones
	 * @return Listado de Publicaciones
	 */
	public ArrayList<Publi> getListaPubli(Propietario propietario){
		return publiBbdd.getListaPubli(propietario);
	}
	
	public ArrayList<Publi> getListaPubli(ListaMetadato listaMetadato){
		return this.publiBbdd.getListaPubli(listaMetadato);
	}

	/**
	 * Crea una publicación en el sistema
	 * 
	 * @return Retorna el identificador de la publicación
	 */
	public ListaMensaje putPubli(Publi publi){
		return publiBbdd.putPublicacion(publi);
	}
	
	public ListaMensaje borraPubli(Publi publi){
		return publiBbdd.borraPublicacion(publi);
	}
}
