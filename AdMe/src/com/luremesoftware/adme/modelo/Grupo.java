package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;

@PersistenceCapable(detachable="true")
public class Grupo extends Propietario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent
	private String nombre = null;
	@Persistent
	private String descripcion = null;
	@Persistent
	private String ciudad = null;
	@Persistent
	private List<Key> listaUsuarioKey = new ArrayList<Key>();
	@NotPersistent
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	public Grupo(){};
	
	public Grupo(Usuario usuario, String nombre, String descripcion, String ciudad){
		super();
		this.buildKey(usuario.getKey().getName()+ "_" + nombre);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCiudad(ciudad);
		this.addUsuarioKey(usuario.getKey());
		this.listaUsuario.add(usuario);
	}
	
	private boolean buildKey(String id){
		return this.setKey(KeyFactory.createKey(Tabla.GRUPO.getSimpleName(), id));
	}
	
	private boolean addUsuarioKey(Key key){
		return this.listaUsuarioKey.add(key);
	}
	
	public boolean addUsuario(Usuario usuario){
		//Se introduce el usuario en el grupo
		this.addUsuarioKey(usuario.getKey());
		this.listaUsuario.add(usuario);
		
		//Se informa del grupo al usuario
		usuario.setGrupoKey(this.getKey());
		
		return true;
	}
	
	public boolean addListaUsuario(ArrayList<Usuario> listaUsuario){
		for(Usuario usuario:listaUsuario){
			this.addUsuarioKey(usuario.getKey());
		}
		return this.listaUsuario.addAll(listaUsuario);
	}
	
	public boolean borrarUsuario(Usuario usuario){
		return this.listaUsuario.remove(usuario);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public String getCiudad(){
		return this.ciudad;
	}
	
	public List<Usuario> getListaUsuario(){
		if(this.listaUsuario==null){
			this.listaUsuario = new GestorUsuario().getListaUsuarioByKey(this.listaUsuarioKey);
		}
		return this.listaUsuario;
	}
	
	public boolean setNombre(String nombre){
		this.nombre = nombre;
		return true;
	}
	
	public boolean setDescripcion(String descripcion){
		this.descripcion = descripcion;
		return true;
	}
	
	public boolean setCiudad(String ciudad){
		this.ciudad = ciudad;
		return true;
	}
	
	public String toString(){
		return "Nombre del grupo: " + this.nombre;
	}

}
