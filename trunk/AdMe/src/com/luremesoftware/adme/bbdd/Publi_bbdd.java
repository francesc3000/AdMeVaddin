package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

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
import com.luremesoftware.adme.constantes.Constante.ConstantePubli;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.ListaMensaje;
import com.luremesoftware.adme.modelo.ListaMetadato;
import com.luremesoftware.adme.modelo.ListaPubli;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;

public class Publi_bbdd {

	private DatastoreService datastore = null;
	private Query query = null;
	
	public Publi_bbdd(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		query = new Query(NombreTabla.PUBLICACION.toString());
	}
	
	public ListaMensaje crearPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		Entity entPublicacion = new Entity(NombreTabla.PUBLICACION.toString());

		entPublicacion.setProperty(ConstantePubli.PROPIETARIO.toString(), publi.getPropietarioId());
		entPublicacion.setProperty(ConstantePubli.TITULO.toString(), publi.getTitulo());
		entPublicacion.setProperty(ConstantePubli.CIUDAD.toString(), publi.getCiudad());
		entPublicacion.setProperty(ConstantePubli.DESCRIPCION.toString(), publi.getDescripcion());
			
		Key key = datastore.put(entPublicacion);
		
		if(key == null){
			listaMensaje.add(new Mensaje(Mensaje.ERROR,"No se pudo crer la Publicación!"));
		}else{
			listaMensaje.add(new Mensaje(Mensaje.OK,"Publicación creada!"));
		}
		
		return listaMensaje;
	}
	
	public ListaPubli getListaPubli(Propietario propietario){
		ListaPubli listaPubli = new ListaPubli();
		
		query.setFilter(new FilterPredicate(ConstantePubli.PROPIETARIO.toString(),FilterOperator.EQUAL,propietario.getId()));
		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(query);
		
		for (Entity result : pq.asIterable()) {
		    listaPubli.add(rellenaPubli(result, propietario));
		}
		return listaPubli;
	}
	
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		ListaPubli listaPubli = new ListaPubli();
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
		
		PreparedQuery pq = datastore.prepare(query);
		
		Usuario_bbdd usuario_bbdd = new Usuario_bbdd();

		for (Entity result : pq.asIterable()) {	
			Propietario propietario = usuario_bbdd.getUsuario((String) result.getProperty(ConstanteUsuario.CORREO.toString()));
			
			if(propietario!=null){
				listaPubli.add(rellenaPubli(result, propietario));
			}
		}
		
		return listaPubli;
	}
	
	private Publi rellenaPubli(Entity result, Propietario propietario){

		    return new Publi(propietario,
				   			(String) result.getProperty(ConstantePubli.TITULO.toString()),
				   			(String) result.getProperty(ConstantePubli.CIUDAD.toString()),
				   			(String) result.getProperty(ConstantePubli.DESCRIPCION.toString()));
	}
}
