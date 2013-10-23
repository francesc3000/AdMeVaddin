package com.luremesoftware.adme.bbdd;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.FetchGroup;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.Grupo;
import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Propietario;
import com.luremesoftware.adme.modelo.Publi;
import com.luremesoftware.adme.modelo.Puntuacion;
import com.luremesoftware.adme.modelo.ControlPuntuacion;
import com.luremesoftware.adme.modelo.Usuario;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;
import com.luremesoftware.adme.modelo.gestor.GestorGrupo;
import com.luremesoftware.adme.modelo.lista.ListaMensaje;
import com.luremesoftware.adme.modelo.lista.ListaMetadato;
import com.luremesoftware.adme.bbdd.PMF;
import com.luremesoftware.adme.constantes.Constante.Tabla;

public class UsuarioBbdd{
	
	private GestorGrupo gestorGrupo = new GestorGrupo();
	
	public UsuarioBbdd(){}
	
	public Usuario getUsuario(String correo){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Usuario usuario = null, detached = null;
		//FetchGroup fetchGroup = pm.getFetchGroup(ControlPuntuacion.class, "ControlPuntuacion");
		pm.getFetchPlan().addGroup(FetchGroup.ALL);
		pm.getFetchPlan().setMaxFetchDepth(-1);
		Query query = pm.newQuery(Usuario.class);
		query.setFilter("correo == :correo");
	    
	    try{
	    	@SuppressWarnings("unchecked")
	    	List<Usuario> listaUsuario = (List<Usuario>) pm.newQuery(query).execute(correo);
	    	if(!listaUsuario.isEmpty()){
	    		usuario = listaUsuario.get(0);
	    		detached = pm.detachCopy(usuario);
	    		if(detached.getControlPuntuacion()!=null){
	    			detached.getControlPuntuacion().getListaPuntuaciones().size();
	    		}
		    }
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    
	   /*if(detached!=null){
	    	detached.setPuntuacion(usuario,3);
	    	this.putUsuario(detached);
	    }*/
	    
	    /*if(detached!=null){
	    	Grupo grupo = new Grupo(detached,"titulo","","");
	    	detached.crearGrupo(grupo);
	    	this.putUsuario(detached);
	    }*/
	    
	    if(detached!=null){
	    	List<Grupo> listaGrupo = usuario.getListaGrupo();
	    	Grupo grupo = listaGrupo.get(0);
	    	if(grupo!=null){}
	    }
	    
	    return detached;
	}
	
	public Usuario getUsuarioByKey(Key key){
		Usuario usuario = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			usuario = (Usuario) pm.getObjectById(key);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuarioByKey(List<Key> listaKey){
		List<Usuario> listaUsuario = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			listaUsuario = (List<Usuario>) pm.getObjectsById(listaKey);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
		
		return listaUsuario;
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
		Transaction tx = pm.currentTransaction();
	    try {
	    	tx.begin();
	    	for(Grupo grupo:usuario.getListaGrupo()){
	    		this.gestorGrupo.putGrupo(grupo);
	    	}
	        pm.makePersistent(usuario);
	        tx.commit();
	    }catch (JDOObjectNotFoundException e) {
	    	listaMensaje.add(new Mensaje(TipoError.ERROR, e.getMessage()));
	    }finally {
	    	if (tx.isActive()) {
	            tx.rollback();
	        }
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
