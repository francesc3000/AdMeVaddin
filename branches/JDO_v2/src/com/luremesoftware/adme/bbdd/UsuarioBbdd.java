package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.bbdd.PMF;
import com.luremesoftware.adme.constantes.Constante.Tabla;

public class UsuarioBbdd{
	
	public UsuarioBbdd(){}
	
	public Usuario getUsuario(String correo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Usuario usuario = null;
		Extent extent = pm.getExtent(Usuario.class, true);
		Query query = pm.newQuery(extent);
		query.setFilter("correo == :correo");
	    
	    
	    try{
	    	@SuppressWarnings("unchecked")
	    	List<Usuario> listaUsuario = (List<Usuario>) pm.newQuery(query).execute(correo);
	    	if(!listaUsuario.isEmpty()){
	    		usuario = listaUsuario.get(0);
		    }
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	    /*List<Publi> listaPubli = null;
	    if(usuario!=null){
	    	listaPubli = usuario.getListaPubli();
	    }*/
	    
	    return usuario;
		
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		tx.begin();
		Extent e = pm.getExtent(Propietario.class, true);
		Query  q = pm.newQuery(e);
		@SuppressWarnings("unchecked")
		Collection c=(Collection)q.execute();
		tx.commit();
		
		return new Usuario();
		*/
	}
	
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato){
		return this.getListaUsuario(listaMetadato, null, null);
	}
	
	public ArrayList<Usuario> getListaUsuario(ListaMetadato listaMetadato, ArrayList<Grupo> listaGrupo, ArrayList<Publi> listaPubli){
		ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		return listaUsuario;
	}

	public ListaMensaje putUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        pm.makePersistent(usuario);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    }finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}

	public ListaMensaje borraUsuario(Usuario usuario){
		ListaMensaje listaMensaje = new ListaMensaje();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.deletePersistent(usuario);
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaMensaje;
	}
}