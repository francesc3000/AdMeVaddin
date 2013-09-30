package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.ListaMensaje;
import com.luremesoftware.adme.modelo.ListaMetadato;
import com.luremesoftware.adme.modelo.ListaUsuario;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Usuario;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;

public class Usuario_bbdd {
	
	private DatastoreService datastore = null;
	private Query query = null;
	
	public Usuario_bbdd(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		query = new Query(NombreTabla.USUARIO.toString());
	}
	
	public ListaMensaje crearUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();		
		Entity entUsuario = new Entity(NombreTabla.USUARIO.toString(), usuario.getCorreo());
		

		entUsuario.setProperty(ConstanteUsuario.CORREO.toString(), usuario.getCorreo());
		entUsuario.setProperty(ConstanteUsuario.CONTRASENA.toString(), usuario.getContrasena());
		entUsuario.setProperty(ConstanteUsuario.NOMBRE.toString(), usuario.getNombre());
		entUsuario.setProperty(ConstanteUsuario.APELLIDO1.toString(), usuario.getApellido1());
		entUsuario.setProperty(ConstanteUsuario.APELLIDO2.toString(), usuario.getApellido2());
		
		Key key = datastore.put(entUsuario);
		if(key==null){
			//TODO Si falla la creación introducir en LOG
			listaMensaje.add(new Mensaje(Mensaje.ERROR,"No ha sido posible crear el Usuario"));
		}else{
			listaMensaje.add(new Mensaje(Mensaje.OK,"Usuario Creado"));
		}
		
		
		return listaMensaje;
	}
	
	public Usuario getUsuario(String f_correo)
	{
		Usuario usuario = null;
		query.setFilter(new FilterPredicate(ConstanteUsuario.CORREO.toString(),FilterOperator.EQUAL,f_correo));
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
		   usuario = new Usuario(
		   (String) result.getProperty(ConstanteUsuario.CORREO.toString()),
		   //usuario.setContrasena((String) result.getProperty(Contrasena));
		   " ",
		   (String) result.getProperty(ConstanteUsuario.NOMBRE.toString()),
		   (String) result.getProperty(ConstanteUsuario.APELLIDO1.toString()),
		   (String) result.getProperty(ConstanteUsuario.APELLIDO2.toString()));
		}
		
		return usuario;
	}

	public ListaUsuario getListaUsuario(ListaMetadato listaMetadato){
		ListaUsuario listaUsuario = new ListaUsuario();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		
		if(listaMetadato.size() == 1){
			query.setFilter(listaMetadato.get(0).getlikeFilterPredicate());
		}
		else{
			for(Metadato metadato:listaMetadato){
				listaFiltros.add(metadato.getlikeFilterPredicate());
			}
			Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
			query.setFilter(filtroCompuesto);
		}

		//Use CompositeFilter to combine multiple filters
		if(listaFiltros.size() == 1){
			query.setFilter(listaFiltros.get(0));
		}else{
			Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
			query.setFilter(filtroCompuesto);
		}
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(query);

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
