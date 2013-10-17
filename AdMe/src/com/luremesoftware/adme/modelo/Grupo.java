package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

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
	private ArrayList<Key> listaUsuarioKey = null;
	@NotPersistent
	private ListaUsuario listaUsuario = new ListaUsuario();
	
	public Grupo(){};
	
	public Grupo(Usuario usuario, String nombre, String descripcion, String ciudad){
		super();
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCiudad(ciudad);
		this.addUsuario(usuario);
	}
	
	public boolean addUsuario(Usuario usuario){
		return this.listaUsuario.add(usuario);
	}
	
	public boolean addListaUsuario(ListaUsuario listaUsuario){
		return this.listaUsuario.addAll(listaUsuario);
	}
	
	public boolean borrarUsario(Usuario usuario){
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
	
	public ListaUsuario getListaUsuario(){
		if(this.listaUsuario==null){
			this.listaUsuario = new GestorUsuario().getListaUsuarioXGrupo(this.getNombre());
		}
		return (ListaUsuario) this.listaUsuario;
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
