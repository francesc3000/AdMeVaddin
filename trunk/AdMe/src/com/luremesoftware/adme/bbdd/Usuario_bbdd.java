package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.luremesoftware.adme.constantes.MetadatosUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.ListaMensaje;
import com.luremesoftware.adme.modelo.ListaUsuario;
import com.luremesoftware.adme.modelo.Mensaje;
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
		

		entUsuario.setProperty(MetadatosUsuario.CORREO.toString(), usuario.getCorreo());
		entUsuario.setProperty(MetadatosUsuario.CONTRASENA.toString(), usuario.getContrasena());
		entUsuario.setProperty(MetadatosUsuario.NOMBRE.toString(), usuario.getNombre());
		entUsuario.setProperty(MetadatosUsuario.APELLIDO1.toString(), usuario.getApellido1());
		entUsuario.setProperty(MetadatosUsuario.APELLIDO2.toString(), usuario.getApellido2());
		
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
		query.setFilter(new FilterPredicate(MetadatosUsuario.CORREO.toString(),FilterOperator.EQUAL,f_correo));
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
		   usuario = new Usuario(
		   (String) result.getProperty(MetadatosUsuario.CORREO.toString()),
		   //usuario.setContrasena((String) result.getProperty(Contrasena));
		   " ",
		   (String) result.getProperty(MetadatosUsuario.NOMBRE.toString()),
		   (String) result.getProperty(MetadatosUsuario.APELLIDO1.toString()),
		   (String) result.getProperty(MetadatosUsuario.APELLIDO2.toString()));
		}
		
		return usuario;
	}

	public ListaUsuario getListaUsuario(String f_correo, String f_nombre, String f_apellido1, String f_apellido2){
		//TODO Integrar Clase ListaMetadato
		ListaUsuario listaUsuario = new ListaUsuario();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();

		buildFiltro(MetadatosUsuario.CORREO.toString(), FilterOperator.EQUAL, f_correo, listaFiltros);
		buildFiltro(MetadatosUsuario.NOMBRE.toString(), FilterOperator.EQUAL, f_nombre, listaFiltros);
		buildFiltro(MetadatosUsuario.APELLIDO1.toString(), FilterOperator.EQUAL, f_apellido1, listaFiltros);
		buildFiltro(MetadatosUsuario.APELLIDO2.toString(), FilterOperator.EQUAL, f_apellido2, listaFiltros);

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
		  (String) result.getProperty(MetadatosUsuario.CORREO.toString()),
		  (String) result.getProperty(MetadatosUsuario.CONTRASENA.toString()),
		  (String) result.getProperty(MetadatosUsuario.NOMBRE.toString()),
		  (String) result.getProperty(MetadatosUsuario.APELLIDO1.toString()),
		  (String) result.getProperty(MetadatosUsuario.APELLIDO2.toString()));
		  
		  listaUsuario.add(usuario);
		}
		
		return listaUsuario;
		
	}
	
	private boolean buildFiltro(String nombrePropiedad, FilterOperator operador, Object valor, ArrayList<Filter> listaFiltros){
		
		if(valor!=null){
			return listaFiltros.add(new FilterPredicate(nombrePropiedad,operador,valor));
		}else{return false;}
	
	}
}
