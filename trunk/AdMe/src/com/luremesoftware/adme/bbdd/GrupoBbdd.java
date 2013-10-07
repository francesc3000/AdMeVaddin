package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.luremesoftware.adme.constantes.Constante.ConstanteGrupo;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Metadato;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.lista.ListaGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;
import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Transaction;

public class GrupoBbdd extends Bbdd{

	private Query query_ug = null;
	private Query query = null;
	
	public GrupoBbdd(){
		super();
		query_ug = new Query(NombreTabla.USUARIOGRUPO.toString());
		query = new Query(NombreTabla.GRUPO.toString());
	}
	
	public ListaMensaje putGrupo(Usuario usuario, Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		Entity entUsuarioGrupo = new Entity(NombreTabla.USUARIOGRUPO.toString());

		entUsuarioGrupo.setProperty(ConstanteUsuario.ID.toString(), usuario.getId());
		entUsuarioGrupo.setProperty(ConstanteGrupo.ID.toString(), grupo.getNombre());
		
		Entity entGrupo = new Entity(NombreTabla.GRUPO.toString());
		//TODO Ir incluyendo propiedades del grupo
		entGrupo.setProperty(ConstanteGrupo.NOMBRE.toString(), grupo.getNombre());

		Transaction txn = this.datastore.beginTransaction();
		try {            
            listaMensaje.addAll(this.putDatastore(entGrupo));
            listaMensaje.addAll(this.putDatastore(entUsuarioGrupo));
		} catch (DatastoreFailureException e) {
            // Log
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
		
		return listaMensaje;
	}
	
	public Grupo getGrupo(String nombreGrupo){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		//Se buscan los usuarios dentro del Grupo
		query.setFilter(new FilterPredicate(NombreTabla.GRUPO.toString(),FilterOperator.EQUAL,nombreGrupo));
		
		PreparedQuery pq = this.datastore.prepare(query);
		
		for (Entity result : pq.asIterable()) {
			listaUsuario.add(new Usuario((String) result.getProperty(NombreTabla.USUARIO.toString())));
		}
		
	    return new Grupo(listaUsuario,nombreGrupo);
	}
	
	public ListaGrupo getListaGrupo(Usuario usuario){
		return this.getListaGrupo(null, usuario);
	}
	
	public ListaGrupo getListaGrupo(ListaMetadato listaMetadato){
		return this.getListaGrupo(listaMetadato, null);
	}

	/**
	 * Este método busca 
	 * @param listaMetadato
	 * @param usuario
	 * @return
	 */
	public ListaGrupo getListaGrupo(ListaMetadato listaMetadato, Usuario usuario){
		ListaGrupo listaGrupo = new ListaGrupo();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		Usuario usuarioGrupo = null;
		ListaUsuario listaUsuario = new ListaUsuario();

		if(listaMetadato==null){
			listaMetadato = new ListaMetadato();
		}
		if(usuario!=null){
			listaMetadato.add(new Metadato(NombreTabla.USUARIOGRUPO,ConstanteUsuario.ID,FilterOperator.EQUAL,usuario.getId()));
		}
		
		/*
		 *Se buscan y se recogen todos los grupos en los que participa
		 *o participan los usuarios
		 */
		
		this.buildQuery(this.query_ug, listaMetadato, NombreTabla.USUARIOGRUPO);

		PreparedQuery pq_ug = this.datastore.prepare(query_ug);

		/*
		 * Se recogen los identificadores de los grupos y se introducen
		 *en el filtro
		*/
		for(Entity result_ug : pq_ug.asIterable()){
			listaFiltros.add(
					new FilterPredicate(ConstanteGrupo.ID.toString(),FilterOperator.EQUAL,(String) result_ug.getProperty(ConstanteGrupo.NOMBRE.toString())));
		}
		switch(listaFiltros.size()){
		case 1:
			query.setFilter(listaFiltros.get(0));
			break;
			
		case 2:
			Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
			query.setFilter(filtroCompuesto);
			break;
		}
		
		/*
		 * Se extraen los datos del grupo y se buscan sus usuarios o se le
		 * asigna el que le llega por parámetros
		 */
		PreparedQuery pq = this.datastore.prepare(query);
		for(Entity result : pq.asIterable()) {
			Grupo grupo = null;
			if(usuario==null){
				String nombreGrupo = (String)result.getProperty(ConstanteGrupo.ID.toString());
				for(Entity result_ug: pq_ug.asIterable()){
					String nombreGrupo_ug = (String)result_ug.getProperty(ConstanteGrupo.ID.toString());
					if(nombreGrupo.compareTo(nombreGrupo_ug)==0){
						String id = (String) result_ug.getProperty(ConstanteUsuario.ID.toString());
						usuarioGrupo = listaUsuario.getUsuarioById(id);
						if(usuarioGrupo == null){
							usuarioGrupo = new Usuario(id);
							listaUsuario.add(usuarioGrupo);
						}
					}
				}
			}else{
				grupo = new Grupo(usuario,
			    (String) result.getProperty(ConstanteGrupo.NOMBRE.toString()));
			}
		    listaGrupo.add(grupo);
		}
		return listaGrupo; 
	}
}
