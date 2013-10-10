package com.luremesoftware.adme.modelo.gestor;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.luremesoftware.adme.bbdd.UsuarioBbdd;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.excepcion.MultipleUsuario;
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
		ListaMensaje listaMensaje = new ListaMensaje();
		
		listaMensaje.addAll(this.existeUsuario(usuario.getCorreo()));
		
		if(!listaMensaje.contieneErrores())
		{
			 if(checkparamObl(usuario)){
			   listaMensaje.addAll(this.usuarioBbdd.putUsuario(usuario));
			 }else{
				 listaMensaje.add(new Mensaje(TipoError.ERROR, "Complete todos los campos obligatorios"));
			 }
		}
		
		return listaMensaje;
	}

	public Usuario getUsuario(String correo) throws MultipleUsuario{
		ListaMetadato listaMetadato = new ListaMetadato();
		Usuario ret_usuario = null;
		ListaMensaje listaMensaje = new ListaMensaje();
		
		listaMensaje.addAll(this.existeUsuario(correo));
		
		if(!listaMensaje.contieneErrores()){
		
			listaMetadato.add( new Metadato(NombreTabla.USUARIO, ConstanteUsuario.CORREO, FilterOperator.EQUAL, correo));
			ListaUsuario listaUsuario = this.getListaUsuario(listaMetadato);
			
			if(!listaUsuario.isEmpty()){
				if(listaUsuario.size()>1){//Si encuentra mas de un usuario con el mismo correo
					throw new MultipleUsuario("Usuario duplicado en base de datos");
				}else{
					ret_usuario = listaUsuario.get(0);
				}
			}
		}else{
			ret_usuario = new Usuario(correo);
		}
		
		return ret_usuario;
	}
	
	public ListaUsuario getListaUsuarioXGrupo(String nombreGrupo){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		
		return listaUsuario;
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
	
	public ListaMensaje existeUsuario(String correo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		//TODO Meter en MemCache
		boolean existe = this.usuarioBbdd.existeUsuario(correo);
		
		if(existe){
			listaMensaje.add(new Mensaje(TipoError.ERROR,"El Usuario ya existe"));
		}
		
		return listaMensaje;
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
