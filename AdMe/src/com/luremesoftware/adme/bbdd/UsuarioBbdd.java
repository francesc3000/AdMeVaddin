package com.luremesoftware.adme.bbdd;

import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;

public class UsuarioBbdd extends Bbdd{

	private Query query = null;
	
	public UsuarioBbdd(){
		super();
		query = new Query(NombreTabla.USUARIO.toString());
	}
	
	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		Entity entUsuario = new Entity(NombreTabla.USUARIO.toString(), usuario.getCorreo());
		

		entUsuario.setProperty(ConstanteUsuario.CORREO.toString(), usuario.getCorreo());
		entUsuario.setProperty(ConstanteUsuario.CONTRASENA.toString(), usuario.getContrasena());
		entUsuario.setProperty(ConstanteUsuario.NOMBRE.toString(), usuario.getNombre());
		entUsuario.setProperty(ConstanteUsuario.APELLIDO1.toString(), usuario.getApellido1());
		entUsuario.setProperty(ConstanteUsuario.APELLIDO2.toString(), usuario.getApellido2());
		
		listaMensaje.addAll(this.putDatastore(entUsuario));
		
		return listaMensaje;
	}

	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		this.buildQuery(this.query, listaMetadato);
		
		PreparedQuery pq = this.prepareDatastore(query);

		for (Entity result : pq.asIterable()) {			
		  Usuario usuario = new Usuario(
		  (String) result.getProperty(ConstanteUsuario.CORREO.toString()),
		  (String) result.getProperty(ConstanteUsuario.CONTRASENA.toString()),
		  (String) result.getProperty(ConstanteUsuario.NOMBRE.toString()),
		  (String) result.getProperty(ConstanteUsuario.APELLIDO1.toString()),
		  (String) result.getProperty(ConstanteUsuario.APELLIDO2.toString()));
		  
		  listaUsuario.add(usuario);
		}
		
		return listaUsuario;
		
	}
}
