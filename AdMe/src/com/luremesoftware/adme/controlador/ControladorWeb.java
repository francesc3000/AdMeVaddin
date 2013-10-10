package com.luremesoftware.adme.controlador;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.PuestoControl;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;
import com.luremesoftware.adme.vista.Acceder;

public class ControladorWeb{
	
	private GestorUsuario gestorUsuario = null;
	private GestorGrupo gestorGrupo = null;
	private GestorPubli gestorPubli = null;
	
	public ControladorWeb(){
		this.gestorUsuario = new GestorUsuario();
		this.gestorGrupo = new GestorGrupo();
		this.gestorPubli = new GestorPubli();
	}
	
	/**
	 * Se generan todas las clases asociadas al usuario y necesarias para el
	 * puesto de control
	 * @param usuario Usuario que se quiere recuperar su puesto de control
	 * @return Puesto de control
	 */
	public PuestoControl puestoControl(Usuario usuario){
		return new PuestoControl(usuario);
	}
	
	/**
	 * Se crea o modifica un usuario en base de datos
	 * 
	 * @param usuario Clase tipo usuario
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putUsuario(Usuario usuario){
		return gestorUsuario.putUsuario(usuario);
	}
	
	/**
	 * Crea o modifica un grupo
	 * @param usuario
	 * @param grupo
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putGrupo(Usuario usuario, Grupo grupo){
		return gestorGrupo.putGrupo(usuario, grupo);
	}

	/**
	 * Se crea o modifica una Publicación
	 * 
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putPubli(Publi publi){
		return gestorPubli.putPubli(publi);
	}
	
	public Usuario getUsuario(String correo){
		return gestorUsuario.getUsuario(correo);
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
	 * Si el usuario esta registrado se retornan los datos del usuario a 
	 * partir de su correo electronico
	 * @param correo
	 * @return Si el usuario no esta registrado se retorna null
	 * @throws IOException 
	 * @throws MultipleUsuario 
	 */
	public Usuario acceder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Acceder acceder = new Acceder(request, response);
		String correo = acceder.runAcceder();
		if(correo==null){
			return null;
		}
		Usuario usuario = this.getUsuario(correo);
		if(usuario==null){
			usuario = new Usuario(correo,null,null,null,null);
		}
		return usuario;
	}
}