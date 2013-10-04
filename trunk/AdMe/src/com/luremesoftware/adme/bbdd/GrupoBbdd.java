package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import com.luremesoftware.adme.constantes.Constante.ConstanteGrupo;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Grupo;
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

		entUsuarioGrupo.setProperty(NombreTabla.USUARIO.toString(), usuario.getId());
		entUsuarioGrupo.setProperty(ConstanteGrupo.NOMBRE.toString(), grupo.getNombre());
		
		//TODO Ejecutar en transacción o mirar de insertar como ancestros
		//TODO Ir incluyendo propiedades del grupo
		Entity entGrupo = new Entity(NombreTabla.GRUPO.toString());
			
		entGrupo.setProperty(ConstanteGrupo.NOMBRE.toString(), grupo.getNombre());
			
		listaMensaje.addAll(this.putDatastore(entGrupo));
		listaMensaje.addAll(this.putDatastore(entUsuarioGrupo));
		
		return listaMensaje;
	}
	
	public ListaGrupo getListaGrupo(ListaMetadato listaMetadato){
		//TODO Hacer
		
		return new ListaGrupo();
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
		ListaGrupo listaGrupo = new ListaGrupo();
		ArrayList<Filter> listaFiltros = new ArrayList<Filter>();
		
		query_ug.setFilter(new FilterPredicate(NombreTabla.USUARIO.toString(),FilterOperator.EQUAL,usuario.getId()));
		
		PreparedQuery pq_ug = this.prepareDatastore(query_ug);

		for (Entity result_ug : pq_ug.asIterable()) {
			listaFiltros.add(
					new FilterPredicate(ConstanteGrupo.NOMBRE.toString(),FilterOperator.EQUAL,(String) result_ug.getProperty(ConstanteGrupo.NOMBRE.toString())));
			listaFiltros.add(
					new FilterPredicate(ConstanteGrupo.NOMBRE.toString(),FilterOperator.EQUAL,(String) result_ug.getProperty(ConstanteGrupo.NOMBRE.toString())));
		}
		
		if(!listaFiltros.isEmpty()){
			Filter filtroCompuesto = CompositeFilterOperator.and(listaFiltros);
			query.setFilter(filtroCompuesto);
			
			PreparedQuery pq = this.prepareDatastore(query);
			for (Entity result : pq.asIterable()) {
			   Grupo grupo = new Grupo(usuario,
			   (String) result.getProperty(ConstanteGrupo.NOMBRE.toString()));
			   listaGrupo.add(grupo);
			}
		}
		return listaGrupo; 
	}
}
