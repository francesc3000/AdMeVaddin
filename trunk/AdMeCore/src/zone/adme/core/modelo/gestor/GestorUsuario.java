package zone.adme.core.modelo.gestor;

import java.util.List;

import zone.adme.core.bbdd.UsuarioBbdd;
import zone.adme.core.modelo.Mensaje;
import zone.adme.core.modelo.Usuario;
import zone.adme.core.modelo.Mensaje.TipoError;

import com.googlecode.objectify.Key;
import zone.adme.core.modelo.lista.ListaMensaje;
import zone.adme.core.modelo.lista.ListaMetadato;

public class GestorUsuario {
	private UsuarioBbdd usuarioBbdd = null;
	
	
	public GestorUsuario(){
		this.usuarioBbdd = new UsuarioBbdd();
	}
	
	/**
	 * Regista un usuario en el sistema
	 * 
	 * @return Se retorna un listado de mensajes del sistema
	 */	
	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		if(checkparamObl(usuario)){
		  listaMensaje.addAll(this.usuarioBbdd.putUsuario(usuario));
		}else{
		  listaMensaje.add(new Mensaje(TipoError.ERROR, "Complete todos los campos obligatorios"));
		}
		
		return listaMensaje;
	}
	
	public ListaMensaje borraUsuario(Usuario usuario){
		return this.usuarioBbdd.borraUsuario(usuario);
	}

	public Usuario getUsuario(String correo){
		return this.usuarioBbdd.getUsuario(correo);
	}
	
	public Usuario getUsuarioByKey(Key<Usuario> key){
		return this.usuarioBbdd.getUsuarioByKey(key);
	}
	
	public List<Usuario> getListaUsuarioByKey(List<Key<Usuario>> listaKey){
		return this.usuarioBbdd.getListaUsuarioByKey(listaKey);
	}
	
	public List<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		
		return this.usuarioBbdd.getListaUsuario(listaMetadato);
	}
	
	private boolean checkparamObl(Usuario usuario){
		boolean result = false;
		if( usuario.getNombre()!=null
		 && usuario.getCorreo()!=null
		 && usuario.getApellido1()!=null){
			result = true;
		}
		
		return result;
	}
}
