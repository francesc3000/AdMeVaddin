package zone.adme.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zone.adme.constantes.Constante.ConstanteSession;
import zone.adme.modelo.Grupo;
import zone.adme.modelo.Propietario;
import zone.adme.modelo.PuestoControl;
import zone.adme.modelo.Publi;
import zone.adme.modelo.Usuario;
import zone.adme.modelo.gestor.GestorGrupo;
import zone.adme.modelo.gestor.GestorPropietario;
import zone.adme.modelo.gestor.GestorPubli;
import zone.adme.modelo.gestor.GestorUsuario;
import zone.adme.modelo.lista.ListaMensaje;
import zone.adme.modelo.lista.ListaMetadato;
import zone.adme.vista.UtilidadesVista;

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
	 * Se crea el grupo y las relaciones entre grupo/usuario
	 * @param usuario
	 * @param grupo
	 * @return Se retorna una lista de Mensajes
	 */
	public ListaMensaje putGrupo(Usuario usuario, Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		listaMensaje.addAll(this.gestorGrupo.putGrupo(grupo));
		usuario.addGrupo(grupo);
		listaMensaje.addAll(this.gestorUsuario.putUsuario(usuario));
		
		return listaMensaje;
	}

	/**
	 * Se crea o modifica una Publicación
	 * 
	 * @param usuario Propietario de la publicacion
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public ListaMensaje putPubli(Propietario propietario, Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		listaMensaje.addAll(this.gestorPubli.putPubli(publi));
		propietario.setPubli(publi);
		listaMensaje.addAll(this.gestorPropietario.putPropietario(propietario));
		
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