package com.luremesoftware.adme.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.luremesoftware.adme.modelo.gestor.GestorUsuario;
import com.luremesoftware.adme.modelo.lista.ListaUsuario;

@PersistenceCapable
public class Grupo extends Propietario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent
	private String nombre = null;
	@Persistent
	private ArrayList<Usuario> listaUsuario = null;
	
	public Grupo(Usuario usuario, String nombre){
		super(nombre);
		this.nombre = nombre;
		this.listaUsuario = (ArrayList<Usuario>)new ListaUsuario();
		this.addUsuario(usuario);
	}
	
	public Grupo(ListaUsuario listausuario, String nombre){
		super(nombre);
		this.nombre = nombre;
		this.listaUsuario = new ListaUsuario();
		this.addListaUsuario(listausuario);
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
	
	public String toString(){
		return "Nombre del grupo: " + this.nombre;
	}

}
