package com.luremesoftware.adme.bbdd;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.luremesoftware.adme.constantes.Constante.ConstantePropietario;
import com.luremesoftware.adme.constantes.Constante.ConstantePubli;
import com.luremesoftware.adme.constantes.NombreTabla;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.excepcion.MultipleUsuario;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.modelo.lista.ListaPropietario;
import com.luremesoftware.adme.modelo.lista.ListaPubli;

public class PubliBbdd extends Bbdd{

	private Query query = null;
	
	public PubliBbdd(){
		super();
		query = new Query(NombreTabla.PUBLICACION.toString());
	}
	
	public ListaMensaje putPublicacion(Publi publi){
		ListaMensaje listaMensaje = new ListaMensaje();
		Entity entPublicacion = new Entity(NombreTabla.PUBLICACION.toString());
		
		String clase = publi.getPropietario().getClass().toString();
		int ultimoPunto = clase.lastIndexOf(".");
		clase = clase.substring(ultimoPunto + 1);

		entPublicacion.setProperty(ConstantePubli.PROPIETARIO.toString(), publi.getPropietarioId());
		entPublicacion.setProperty(ConstantePubli.CLASE.toString(), clase);
		entPublicacion.setProperty(ConstantePubli.TITULO.toString(), publi.getTitulo());
		entPublicacion.setProperty(ConstantePubli.CIUDAD.toString(), publi.getCiudad());
		entPublicacion.setProperty(ConstantePubli.DESCRIPCION.toString(), publi.getDescripcion());
		
		listaMensaje.addAll(this.putDatastore(entPublicacion));
		
		return listaMensaje;
	}
	
	public ListaPubli getListaPubli(Propietario propietario){
		ListaPubli listaPubli = new ListaPubli();
		
		query.setFilter(new FilterPredicate(ConstantePubli.PROPIETARIO.toString(),FilterOperator.EQUAL,propietario.getId()));
		
		PreparedQuery pq = this.prepareDatastore(query);
		
		for (Entity result : pq.asIterable()) {
		    listaPubli.add(rellenaPubli(result, propietario));
		}
		return listaPubli;
	}
	
	public ListaPubli getListaPubli(ListaMetadato listaMetadato){
		ListaPubli listaPubli = new ListaPubli();
		
		this.buildQuery(this.query, listaMetadato);
		
		PreparedQuery pq = this.prepareDatastore(query);
		
		for (Entity result : pq.asIterable()) {
			String class_string = (String) result.getProperty(ConstantePropietario.CLASE.toString());
			Propietario propietario = null;
			ListaPropietario listaPropietario = new ListaPropietario();
			String id = null;
			
			//TODO Hacer que enum funcione en switch
			if(class_string.compareTo(ConstantePropietario.USUARIO.toString()) == 0){
				id = (String) result.getProperty(ConstantePubli.PROPIETARIO.toString());
				
				propietario = listaPropietario.getPropietarioById(id);
				if(propietario == null){
					//Se busca en BBDD
					GestorUsuario gestorUsuario = new GestorUsuario();
					try{
						propietario = gestorUsuario.getUsuario(id);
					}catch(MultipleUsuario mu){
						//Nothing to do
					}
				}
			}
			else if(class_string == ConstantePropietario.GRUPO.toString()){
				GestorGrupo gestorGrupo = new GestorGrupo();
				id = (String) result.getProperty(ConstantePubli.PROPIETARIO.toString());
				propietario = listaPropietario.getPropietarioById(id);
				if(propietario == null){
					//Se busca en BBDD
					propietario = gestorGrupo.getGrupo(id);
				}
			}

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
