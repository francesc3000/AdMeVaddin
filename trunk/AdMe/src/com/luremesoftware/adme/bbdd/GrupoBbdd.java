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
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

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
		
		//TODO Ejecutar en transacci�n o mirar de insertar como ancestros
		listaMensaje.addAll(this.putDatastore(entGrupo));
		listaMensaje.addAll(this.putDatastore(entUsuarioGrupo));
		
		return listaMensaje;
	}
	
	public Grupo getGrupo(String nombreGrupo){
		ListaUsuario listaUsuario = new ListaUsuario();
		
		//Se buscan los usuarios dentro del Grupo
		query.setFilter(new FilterPredicate(NombreTabla.GRUPO.toString(),FilterOperator.EQUAL,nombreGrupo));
		
		PreparedQuery pq = this.prepareDatastore(query);
		
		for (Entity result : pq.asIterable()) {
			listaUsuario.add(new Usuario((String) result.getProperty(NombreTabla.USUARIO.toString())));
		}
		
	    return new Grupo(listaUsuario,nombreGrupo);
	}
	
	public ListaGrupo getListaGrupo(Usuario usuario){
		ListaMetadato listaMetadato = new ListaMetadato();
		listaMetadato.add(new Metadato(NombreTabla.USUARIOGRUPO,ConstanteUsuario.ID,FilterOperator.EQUAL,usuario.getId()));
		return this.getListaGrupo(listaMetadato);
	}

	public ListaGrupo getListaGrupo(ListaMetadato listaMetadato){
		ListaGrupo listaGrupo = new ListaGrupo();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		Usuario usuarioGrupo = null;
		ListaUsuario listaUsuario = new ListaUsuario();
		
		/*
		 *Se buscan y se recogen todos los grupos en los que participa
		 */
		
		this.buildQuery(this.query_ug, listaMetadato, NombreTabla.USUARIOGRUPO);

		PreparedQuery pq_ug = this.prepareDatastore(query_ug);

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
		/*if(usuario==null){
			String id = (String) result_ug.getProperty(ConstanteUsuario.ID.toString());
			usuarioGrupo = listaUsuario.getUsuarioById(id);
			if(usuarioGrupo == null){
				usuarioGrupo = new Usuario(id, );
				listaUsuario.add(usuarioGrupo);
			}
		}*/
		PreparedQuery pq = this.prepareDatastore(query);
		for(Entity result : pq.asIterable()) {
			if(usuario==null){
				for(Entity result_ug: pq_ug.asIterable()){
					
				}
			}else{
				Grupo grupo = new Grupo(usuario_grupo,
			    (String) result.getProperty(ConstanteGrupo.NOMBRE.toString()));
			}
		    listaGrupo.add(grupo);
		}
		return listaGrupo; 
	}
}