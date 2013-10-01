package com.luremesoftware.adme.controlador;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

public class ControladorWeb {
	
	private GestorUsuario gestorUsuario = null;
	private GestorGrupo gestorGrupo = null;
	private GestorPubli gestorPubli = null;
	
	public ControladorWeb(){
		this.gestorUsuario = new GestorUsuario();
		this.gestorGrupo = new GestorGrupo();
		this.gestorPubli = new GestorPubli();
	}
	
	/**
	 * Se crea un usuario en base de datos
	 * 
	 * @param usuario Clase tipo usuario
	 * @return Si todo va bien se retorna true
	 */
	public ListaMensaje crearUsuario(Usuario usuario){
		
		ListaUsuario listaUsuario = gestorUsuario.getListaUsuario(usuario);
		if(listaUsuario.isEmpty()==false){
			ListaMensaje listaMensaje = new ListaMensaje();
			listaMensaje.add(new Mensaje(Mensaje.ERROR,"El Usuario ya existe!"));
			return listaMensaje;
			}
		else{return gestorUsuario.crearUsuario(usuario);}
	}
	
	public ListaMensaje crearGrupo(Usuario usuario, Grupo grupo){
		return gestorGrupo.crearGrupo(usuario, grupo);
	}

	/**
	 * Se crea una Publicación
	 * 
	 * @param publi Clase Publicación
	 * @return Retorna true si la inserción ha sido satisfactoria
	 */
	public ListaMensaje crearPubli(Publi publi){
		return gestorPubli.crearPubli(publi);
	}

	/**
	 * Se realiza una consulta de Usuarios en base de datos
	 * 
	 * @param listaMetadato Clase tipo usuario
	 * @return Se retorna un listado de Usuarios
	 */
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		return gestorUsuario.getListaUsuario(listaMetadato);
	}
	
	/**
	 * Se realiza una consulta de Publicaciones en base de datos
	 * 
	 * @param listaMetadato 
	 * @return
	 */
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		return gestorPubli.getListaPubli(listaMetadato);
	}
}