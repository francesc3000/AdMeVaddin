package com.luremesoftware.adme.modelo;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.bbdd.Usuario_bbdd;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;

public class GestorUsuario {
	private Usuario_bbdd usuario_bbdd = null;
	private GestorPubli gestorPubli = null;
	
	public GestorUsuario(){
		this.usuario_bbdd = new Usuario_bbdd();
		this.gestorPubli = new GestorPubli();
	}
	
	/**
	 * Regista un usuario en el sistema
	 * 
	 * @return Si el registo es satisfactorio se retona OK
	 */	
	public ListaMensaje crearUsuario(Usuario usuario){
		
	  if(checkparamObl(usuario)){
	    return this.usuario_bbdd.crearUsuario(usuario);
	  }else{
		  ListaMensaje listaMensaje = new ListaMensaje();
		  listaMensaje.add(new Mensaje(Mensaje.ERROR, "Complete todos los campos obligatorios"));
		  return listaMensaje;
	  }
	}
	
	public Usuario getUsuario(Usuario usuario){
		
		String correo = usuario.getCorreo();//.toLowerCase();
		//String nombre = usuario.getNombre();//.toLowerCase();
		//String apellido1 = usuario.getApellido1();//.toLowerCase();
		//String apellido2 = usuario.getApellido2();//.toLowerCase();
		
		return this.usuario_bbdd.getUsuario(correo);
	}
	
	public ListaUsuario getListaUsuario(Usuario usuario){
		
		ListaMetadato listaMetadato = new ListaMetadato();

		listaMetadato.setMetadato(NombreTabla.USUARIO, ConstanteUsuario.CORREO, FilterOperator.EQUAL, usuario.getCorreo());
		
		return this.usuario_bbdd.getListaUsuario(listaMetadato);
	}
	
	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		
		return this.usuario_bbdd.getListaUsuario(listaMetadato);
	}

	public ListaGrupo getListaGrupo(Usuario usuario){
		return usuario.getListaGrupo();
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
