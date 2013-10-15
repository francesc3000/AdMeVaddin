package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.luremesoftware.adme.constantes.Constante.ConstanteGrupo;
import com.luremesoftware.adme.constantes.Constante.ConstanteUsuario;
import com.luremesoftware.adme.constantes.Constante.Tabla;
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

public class GrupoBbdd{
	
	public GrupoBbdd(){}
	
	public ListaMensaje putGrupo(Grupo grupo){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(grupo);
        } finally {
            pm.close();
        }

		return listaMensaje;
	}
	
	public Grupo getGrupo(String nombreGrupo){
		ListaUsuario listaUsuario = new ListaUsuario();
		//TODO hacer consulta de grupo por nombre de grupo
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
		
		return listaGrupo; 
	}
}
