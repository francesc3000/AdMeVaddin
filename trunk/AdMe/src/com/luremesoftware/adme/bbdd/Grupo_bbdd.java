package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.luremesoftware.adme.constantes.MetadatosGrupo;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.ListaGrupo;
import com.luremesoftware.adme.modelo.ListaMensaje;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Usuario;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Grupo_bbdd {

	private DatastoreService datastore = null;
	private Query query_ug = null;
	private Query query = null;
	
	public Grupo_bbdd(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		query_ug = new Query(NombreTabla.USUARIOGRUPO.toString());
		query = new Query(NombreTabla.GRUPO.toString());
	}
	
	public ListaMensaje crearGrupo(Usuario usuario, Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		Entity entUsuarioGrupo = new Entity(NombreTabla.USUARIOGRUPO.toString());

		entUsuarioGrupo.setProperty(NombreTabla.USUARIO.toString(), usuario.getId());
		entUsuarioGrupo.setProperty(MetadatosGrupo.NOMBRE.toString(), grupo.getNombre());
			
		if(!grupo.existebbdd()){//Si el grupo no exsite en bbdd se guarda
		    //TODO Ir incluyendo propiedades del grupo
			Entity entGrupo = new Entity(NombreTabla.GRUPO.toString());
			
			entGrupo.setProperty(MetadatosGrupo.NOMBRE.toString(), grupo.getNombre());
			
			datastore.put(entGrupo);
		}	
		Key key = datastore.put(entUsuarioGrupo);
		
		if(key == null){
			listaMensaje.add(new Mensaje(Mensaje.ERROR,"No se pudo crear el grupo"));
		}else{
			listaMensaje.add(new Mensaje(Mensaje.OK,"Grupo creado"));
		}
		
		return listaMensaje;
	}
	
	public ListaGrupo getListaGrupo(Usuario usuario){
		ListaGrupo listaGrupo = new ListaGrupo();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		
		query_ug.setFilter(new FilterPredicate(NombreTabla.USUARIO.toString(),FilterOperator.EQUAL,usuario.getId()));
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq_ug = datastore.prepare(query_ug);

		for (Entity result_ug : pq_ug.asIterable()) {
			listaFiltros.add(
					new FilterPredicate(MetadatosGrupo.NOMBRE.toString(),FilterOperator.EQUAL,(String) result_ug.getProperty(MetadatosGrupo.NOMBRE.toString())));
			listaFiltros.add(
					new FilterPredicate(MetadatosGrupo.NOMBRE.toString(),FilterOperator.EQUAL,(String) result_ug.getProperty(MetadatosGrupo.NOMBRE.toString())));
		}
		
		if(!listaFiltros.isEmpty()){
		//try{
		Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
		//}catch{};
		query.setFilter(filtroCompuesto);
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(query);
		for (Entity result : pq.asIterable()) {
			   Grupo grupo = new Grupo(usuario,
			   (String) result.getProperty(MetadatosGrupo.NOMBRE.toString()));
			   listaGrupo.add(grupo);
			}
		}
		return listaGrupo; 
	}
}
