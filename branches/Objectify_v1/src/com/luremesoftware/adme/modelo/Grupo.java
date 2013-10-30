package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

@EntitySubclass(index=true)
public class Grupo extends Propietario implements Serializable{

	/**
	 * 
	 */
	@Ignore
	private static final long serialVersionUID = 1L;
	private String nombre = null;
	private String descripcion = null;
	private String ciudad = null;
	@Load
	private List<Ref<Usuario>> listaUsuarioRef = new ArrayList<Ref<Usuario>>();

	@SuppressWarnings("unused")
	private Grupo(){};
	
	public Grupo(Usuario usuario, String nombre, String descripcion, String ciudad){
		super();
		this.setId(usuario.getId() + "_" + nombre);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCiudad(ciudad);
		this.addUsuarioRef(usuario);
	}
	
	private boolean addUsuarioRef(Usuario usuario){
		return this.listaUsuarioRef.add(Ref.create(usuario));
	}
	
	public boolean addUsuario(Usuario usuario){
		//Se introduce el usuario en el grupo
		return this.addUsuarioRef(usuario);
	}
	
	public boolean addListaUsuario(List<Usuario> listaUsuario){
		for(Usuario usuario:listaUsuario){
			this.addUsuarioRef(usuario);
		}
		return true;
	}
	
	public boolean borrarUsuario(Usuario usuario){
		return this.listaUsuarioRef.remove(usuario);
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
		List<Usuario> listaUsuarioNoRef = new ArrayList<Usuario>();
		
		for(Ref<Usuario> usuario:this.listaUsuarioRef){
			listaUsuarioNoRef.add(usuario.get());
		}
		return listaUsuarioNoRef;
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
