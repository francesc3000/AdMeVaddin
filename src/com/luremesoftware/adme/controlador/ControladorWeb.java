package com.luremesoftware.adme.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luremesoftware.adme.constantes.Constante.ConstanteSession;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.PuestoControl;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.vista.UtilidadesVista;

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
	 * Si el usuario esta registrado se retornan los datos del usuario a 
	 * partir de su correo electronico
	 * @param correo
	 * @return Si el usuario no esta registrado se retorna null
	 * @throws IOException 
	 */
	public Usuario acceder(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		UtilidadesVista utilidadesVista = new UtilidadesVista(request, response, session);
		Usuario usuario = null;
		
		String correo = utilidadesVista.acceder();
		
		if(correo!=null){
			usuario = this.getUsuario(correo);
			if(usuario==null){
				utilidadesVista.setSessionAttribute(ConstanteSession.USUARIOMAIL, correo);
				utilidadesVista.sendRedirect("Registro.jsp");
			}else{
				utilidadesVista.setSessionAttribute(ConstanteSession.USUARIO,usuario);
				//utilidadesVista.sendRedirect("Inicio.jsp");
			}		
		}
		
		return usuario;
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
	 * Devuelve las estructuras del usuario 
	 * @param correo Identificativo del usuairo
	 * @return Retorna una clase Usuario
	 */
	public Usuario getUsuario(String correo){
		return gestorUsuario.getUsuario(correo);
	}
	
	/**
	 * Se realiza una consulta de Publicaciones en base de datos
	 * 
	 * @param listaMetadato 
	 * @return Se retorna un listado de Publicaciones
	 */
	public List<Publi> getListaPubli(ListaMetadato listaMetadato){
		return gestorPubli.getListaPubli(listaMetadato);
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
	 * Se crea o modifica una Publicación
	 * 
	 * @param usuario Propietario de la publicacion
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putPubli(Usuario usuario, Publi publi){
		ListaMensaje listaMensaje = null;
		if(usuario.setPubli(publi)){
			listaMensaje = this.gestorUsuario.putUsuario(usuario);
		}
		return listaMensaje;
	}
	
	/**
	 * Se crea o modifica una Publicación
	 * 
	 * @param grupo Propietario de la publicacion
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putPubli(Grupo grupo, Publi publi){
		ListaMensaje listaMensaje = null;
		if(grupo.setPubli(publi)){
			listaMensaje = this.gestorGrupo.putGrupo(grupo);
		}
		return listaMensaje;
	}
	
	/**
	 * Se borra un Usuario de base de datos
	 * @param usuario Usuario a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	public ListaMensaje borraUsuario(Usuario usuario){
		return this.gestorUsuario.borraUsuario(usuario);
	}
	
	/**
	 * Se borra una Publicación de base de datos
	 * @param publi Publicación a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	public ListaMensaje borraPubli(Publi publi){
		return this.gestorPubli.borraPubli(publi);
	}
}