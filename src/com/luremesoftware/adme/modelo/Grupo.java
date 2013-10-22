package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;

@PersistenceCapable(detachable = "true")
public class Grupo implements Serializable{

	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;
	@Persistent
	private String nombreGrupo = null;
	@Persistent
	private String descripcion = null;
	@Persistent
	private String ciudad = null;
	@Persistent
	private List<Key> listaUsuarioKey = null;
	@NotPersistent
	private List<Propietario> listaUsuario = null;
	
	
	public Grupo(){};
	
	public Grupo(Propietario usuario, String nombre, String descripcion, String ciudad){
		//super();
		//this.buildKey(usuario.getKey()+nombre, Tabla.GRUPO.getSimpleName());
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCiudad(ciudad);
		this.addUsuario(usuario);
	}
	
	public boolean addUsuario(Propietario usuario){
		if(this.listaUsuario==null){
			this.listaUsuario = new ArrayList<Propietario>();
		}
		return this.listaUsuario.add(usuario);
	}
	
	public boolean addListaUsuario(List<Propietario> listaUsuario){
		return this.listaUsuario.addAll(listaUsuario);
	}
	
	public boolean borrarUsario(Usuario usuario){
		return this.listaUsuario.remove(usuario);
	}
	
	public String getNombre(){
		return this.nombreGrupo;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public String getCiudad(){
		return this.ciudad;
	}
	
	public List<Propietario> getListaUsuario(){
		if(this.listaUsuario==null){
			this.listaUsuario = new GestorUsuario().getListaUsuarioXGrupo(this.getNombre());
		}
		return this.listaUsuario;
	}
	
	public boolean setNombre(String nombre){
		this.nombreGrupo = nombre;
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
		return "Nombre del grupo: " + this.nombreGrupo;
	}

}
