package com.luremesoftware.adme.controlador;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaClases;
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
	 * Si el usuario esta registrado se retornan los datos del usuario a 
	 * partir de su correo electronico
	 * @param correo
	 * @return Si el usuario no esta registrado se retorna null
	 */
	public Usuario acceder(String correo){
		return gestorUsuario.acceder(correo);
	}
	
	public ListaClases puestoControl(Usuario usuario){
		ListaClases listaClases = new ListaClases();
		ListaMetadato listaMetadato = new ListaMetadato();
		listaMetadato.add(new Metadato(NombreTabla.PUBLICACION,ConstanteUsuario.CORREO,FilterOperator.EQUAL,usuario.getCorreo()));
		
		listaClases.setListaPubli(this.getListaPubli(listaMetadato));
		
		return listaClases;
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
}