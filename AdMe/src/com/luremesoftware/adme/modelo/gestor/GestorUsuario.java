package com.luremesoftware.adme.modelo.gestor;

import com.luremesoftware.adme.bbdd.UsuarioBbdd;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

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
	public ListaMensaje creaUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		if(checkparamObl(usuario)){
		  listaMensaje.addAll(this.usuarioBbdd.creaUsuario(usuario));
		}else{
		  listaMensaje.add(new Mensaje(TipoError.ERROR, "Complete todos los campos obligatorios"));
		}
		
		return listaMensaje;
	}
	
	public ListaMensaje actualizaUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		listaMensaje.addAll(this.usuarioBbdd.actualizaUsuario(usuario.getKey()));
		
		return listaMensaje;
	}
	
	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		listaMensaje.addAll(this.usuarioBbdd.borraUsuario(usuario));
		
		return listaMensaje;
	}

	public Usuario getUsuario(String correo){
		return this.usuarioBbdd.getUsuario(correo);
	}
	
	public ListaUsuario getListaUsuarioXGrupo(String nombreGrupo){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		
		return listaUsuario;
	}
	
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		
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
