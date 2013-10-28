package com.luremesoftware.adme.modelo.gestor;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.luremesoftware.adme.bbdd.UsuarioBbdd;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;

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
	
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		
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
