package zone.adme.gwt.server;

import java.util.ArrayList;
import java.util.List;

import zone.adme.core.controlador.ControladorModelo;
import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Metadato;
import zone.adme.core.modelo.Publi;
import zone.adme.core.modelo.Usuario;
import zone.adme.core.modelo.lista.ListaMensaje;
import zone.adme.gwt.shared.PubliGWT;
import zone.adme.gwt.shared.UsuarioGWT;

public class ControladorCore {
	
	private ControladorModelo controladorModelo = new ControladorModelo();
	private Dto dto = new Dto();
	
	/**
	 * Se generan todas las clases asociadas al usuario y necesarias para el
	 * puesto de control
	 * @param usuario Usuario que se quiere recuperar su puesto de control
	 * @return Puesto de control
	 */
	/*public PuestoControlGWT puestoControl(UsuarioWeb usuarioGWT){
		
		return new PuestoControl(usuario);
	}*/
	
	/**
	 * Devuelve las estructuras del usuario 
	 * @param correo Identificativo del usuairo
	 * @return Retorna una clase Usuario
	 */
	public UsuarioGWT getUsuario(String correo){
		Usuario usuario = this.controladorModelo.getUsuario(correo);
		return this.dto.toUsuarioGWT(usuario);
	}
	
	/**
	 * Se realiza una consulta de Publicaciones en base de datos
	 * 
	 * @param listaMetadato 
	 * @return Se retorna un listado de Publicaciones
	 */
	public List<PubliGWT> getListaPubliGWT(List<Metadato> listaMetadato){
		/*List<Metadato> listaMetadato = new ArrayList<Metadato>();
		for(MetadatoGWT metadatoGWT:listaMetadatoGWT){
			Metadato metadato = new Metadato( metadatoGWT.getNombreTabla()
											, metadatoGWT.getNombreMetadato()
											, metadatoGWT.getOperador()
											, metadatoGWT.getValor());
			listaMetadato.add(metadato);
		}*/
		List<Publi> listaPubli = this.controladorModelo.getListaPubli(listaMetadato);
		return this.dto.toListaPubliGWT(listaPubli);
	}
	
	/**
	 * Se crea o modifica un usuario en base de datos
	 * 
	 * @param usuario Clase tipo usuario
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public boolean putUsuario(UsuarioGWT usuarioGWT){
		Usuario usuario = this.dto.toUsuario(usuarioGWT);
		ListaMensaje listaMensaje = this.controladorModelo.putUsuario(usuario);
		
		return !listaMensaje.contieneErrores();
	}
	
	/**
	 * Se crea el grupo y las relaciones entre grupo/usuario
	 * @param usuario
	 * @param grupo
	 * @return Se retorna una lista de Mensajes
	 */
	/*public ListaMensaje putGrupo(Usuario usuario, Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		listaMensaje.addAll(this.gestorGrupo.putGrupo(grupo));
		usuario.addGrupo(grupo);
		listaMensaje.addAll(this.gestorUsuario.putUsuario(usuario));
		
		return listaMensaje;
	}*/

	/**
	 * Se crea o modifica una Publicación
	 * 
	 * @param usuario Propietario de la publicacion
	 * @param publi Clase Publicación
	 * @return Se retorna un listado de mensajes del sistema
	 */
	public List<Mensaje> putPubli(PubliGWT publiGWT){
		
		Publi publi = dto.toPubli(publiGWT);
		
		ListaMensaje listaMensaje = this.controladorModelo.putPubli(publi.getPropietario(), publi);
		
		List<Mensaje> listaMensajeOut = new ArrayList<Mensaje>();
		listaMensajeOut.addAll(listaMensaje);
		
		return listaMensajeOut;
	}
	
	/**
	 * Se borra un Usuario de base de datos
	 * @param usuario Usuario a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	/*public ListaMensaje borraUsuario(Usuario usuario){
		return this.gestorUsuario.borraUsuario(usuario);
	}*/
	
	/**
	 * Se borra una Publicación de base de datos
	 * @param publi Publicación a borrar
	 * @return retorna listado de los errores ocurridos
	 */
	/*public ListaMensaje borraPubli(Publi publi){
		return this.gestorPubli.borraPubli(publi);
	}*/

}
