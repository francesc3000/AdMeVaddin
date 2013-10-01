package com.luremesoftware.adme.modelo.gestor;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.bbdd.UsuarioBbdd;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

public class GestorUsuario {
	private UsuarioBbdd usuarioBbdd = null;
	private GestorPubli gestorPubli = null;
	
	public GestorUsuario(){
		this.usuarioBbdd = new UsuarioBbdd();
		this.gestorPubli = new GestorPubli();
	}
	
	/**
	 * Regista un usuario en el sistema
	 * 
	 * @return Se retorna un listado de mensajes del sistema
	 */	
	public ListaMensaje putUsuario(Usuario usuario){
		Usuario Usuario = this.getUsuario(usuario);
		if(Usuario!=null){
			ListaMensaje listaMensaje = new ListaMensaje();
			listaMensaje.add(new Mensaje(TipoError.ERROR,"El Usuario ya existe!"));
			return listaMensaje;
			}
		else{
			 if(checkparamObl(usuario)){
			   return this.usuarioBbdd.putUsuario(usuario);
			 }else{
				 ListaMensaje listaMensaje = new ListaMensaje();
				 listaMensaje.add(new Mensaje(TipoError.ERROR, "Complete todos los campos obligatorios"));
				 return listaMensaje;
			 }
		}
	}
	
	public Usuario getUsuario(String correo){
		ListaMetadato listaMetadato = new ListaMetadato();
		Usuario ret_usuario = null;
		
		listaMetadato.setMetadato(NombreTabla.USUARIO, ConstanteUsuario.CORREO, FilterOperator.EQUAL, correo);
		ListaUsuario listaUsuario = this.usuarioBbdd.getListaUsuario(listaMetadato);
		
		if(listaUsuario.size()>1){//Si encuentra mas de un usuario con el mismo correo
			//TODO Crear log
		}else{
			ret_usuario = listaUsuario.get(0);
		}
		
		return ret_usuario;
	}
	
	public Usuario getUsuario(Usuario usuario){
		
		ListaMetadato listaMetadato = new ListaMetadato();
		Usuario ret_usuario = null;
		
		listaMetadato.setMetadato(NombreTabla.USUARIO, ConstanteUsuario.CORREO, FilterOperator.EQUAL, usuario.getCorreo());
		
		ListaUsuario listaUsuario = this.usuarioBbdd.getListaUsuario(listaMetadato);
		
		if(listaUsuario.size()>1){//Si encuentra mas de un usuario con el mismo correo
			//TODO Crear log
		}else{
			ret_usuario = listaUsuario.get(0);
		}
		
		return ret_usuario;
	}
	
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		
		return this.usuarioBbdd.getListaUsuario(listaMetadato);
	}

	public ListaPubli getListaPubli(Usuario usuario){
	
	    if(usuario.getId() == null && checkparamObl(usuario)){
		  usuario.setListaPubli(gestorPubli.getListaPubli(usuario));
		}
	
		return usuario.getListaPubli();
	}

	public ListaPubli getListaPubli(ListaUsuario listaUsuario){
		
		ListaPubli listaPubli = new ListaPubli();

		for(Usuario usuario:listaUsuario){
			usuario.setListaPubli(this.getListaPubli(usuario));
			listaPubli.addAll(usuario.getListaPubli());
		}

		return listaPubli;
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
