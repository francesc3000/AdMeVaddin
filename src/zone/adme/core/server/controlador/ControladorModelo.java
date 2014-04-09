package zone.adme.core.server.controlador;

import java.util.List;

import zone.adme.core.shared.modelo.Grupo;
import zone.adme.core.shared.modelo.Metadato;
import zone.adme.core.shared.modelo.Propietario;
import zone.adme.core.shared.modelo.PuestoControl;
import zone.adme.core.shared.modelo.Publi;
import zone.adme.core.shared.modelo.Usuario;
import zone.adme.core.shared.modelo.gestor.GestorGrupo;
import zone.adme.core.shared.modelo.gestor.GestorPropietario;
import zone.adme.core.shared.modelo.gestor.GestorPubli;
import zone.adme.core.shared.modelo.gestor.GestorUsuario;
import zone.adme.core.shared.modelo.lista.ListaMensaje;

public class ControladorModelo{
	
	private GestorPropietario gestorPropietario = null;
	private GestorUsuario gestorUsuario = null;
	private GestorGrupo gestorGrupo = null;
	private GestorPubli gestorPubli = null;
	
	public ControladorModelo(){
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
	public List<Publi> getListaPubli(List<Metadato> listaMetadato){
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