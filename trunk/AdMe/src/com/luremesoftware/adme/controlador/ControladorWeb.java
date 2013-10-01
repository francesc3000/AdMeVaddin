package com.luremesoftware.adme.controlador;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
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
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje crearUsuario(Usuario usuario){
		
		ListaUsuario listaUsuario = gestorUsuario.getListaUsuario(usuario);
		if(listaUsuario.isEmpty()==false){
			ListaMensaje listaMensaje = new ListaMensaje();
			listaMensaje.add(new Mensaje(TipoError.ERROR,"El Usuario ya existe!"));
			return listaMensaje;
			}
		else{return gestorUsuario.crearUsuario(usuario);}
	}
	
	/**
	 * Crea un nuevo grupo
	 * @param usuario
	 * @param grupo
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje crearGrupo(Usuario usuario, Grupo grupo){
		return gestorGrupo.crearGrupo(usuario, grupo);
	}

	/**
	 * Se crea una Publicación
	 * 
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
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
	 * @return Se retorna un listado de Publicaciones
	 */
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		return gestorPubli.getListaPubli(listaMetadato);
	}
	
	/**
	 * Se modifica un Usuario ya existente en base de datos
	 * @param usuario
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje modUsuario(Usuario usuario){
		return this.gestorUsuario.modUsuario(usuario);
	}
}