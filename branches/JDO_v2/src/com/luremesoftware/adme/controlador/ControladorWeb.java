package com.luremesoftware.adme.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.luremesoftware.adme.constantes.Constante.ConstanteSession;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.PuestoControl;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorPropietario;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.vista.UtilidadesVista;

public class ControladorWeb{
	
	private GestorPropietario gestorPropietario = null;
	private GestorUsuario gestorUsuario = null;
	private GestorGrupo gestorGrupo = null;
	private GestorPubli gestorPubli = null;
	
	public ControladorWeb(){
		this.gestorPropietario = new GestorPropietario();
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
	
	public Usuario getUsuario(String correo){
		return gestorUsuario.getUsuario(correo);
	}
	
	public Grupo getGrupo(String titulo){
		return gestorGrupo.getGrupo(titulo);
	}

	/**
	 * Se realiza una consulta de Usuarios en base de datos
	 * 
	 * @param listaMetadato Clase tipo usuario
	 * @return Se retorna un listado de Usuarios
	 */
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		return gestorUsuario.getListaUsuario(listaMetadato);
	}
	
	/**
	 * Se realiza una consulta de Publicaciones en base de datos
	 * 
	 * @param listaMetadato 
	 * @return Se retorna un listado de Publicaciones
	 */
	public ArrayList<Publi> getListaPubli(ListaMetadato listaMetadato){
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
	 * Se crea o modifica un grupo
	 * @param usuario
	 * @param grupo
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putGrupo(Grupo grupo){
		return gestorGrupo.putGrupo(grupo);
	}

	/**
	 * Se crea o modifica una Publicación
	 * 
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putPubli(Publi publi){
		ListaMensaje listaMensaje = gestorPubli.putPubli(publi);
		if(!listaMensaje.contieneErrores()){
			Propietario propietario = publi.getPropietario();
			if(propietario.setPubli(publi)){
				this.gestorUsuario.putUsuario((Usuario)propietario);
			}
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
	 * Se borra un Grupo de base de datos
	 * @param grupo Grupo a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	public ListaMensaje borraGrupo(Grupo grupo){
		return this.gestorGrupo.borraGrupo(grupo);
	}
	
	/**
	 * Se borra una Publicación de base de datos
	 * @param publi Publicación a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	public ListaMensaje borraPubli(Publi publi){
		return this.gestorPubli.borraPubli(publi);
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
			}		}
		
		return usuario;
	}
}