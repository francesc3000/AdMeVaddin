package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.modelo.lista.ListaUsuario;

public class Grupo extends Propietario{

	private String nombre = null;
	private ListaUsuario listaUsuario = null;
	private boolean existebbdd = false; //Indica si el grupo ya existe en BBDD
	
	public Grupo(Usuario usuario, String nombre){
		super(nombre);
		this.nombre = nombre;
		this.listaUsuario = new ListaUsuario();
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
	
	public ListaUsuario getListaUsuario(){
		return listaUsuario;
	}
	
	public boolean existebbdd(){
		return this.existebbdd;
	}
	
	public String toString(){
		return "Nombre del grupo: " + this.nombre;
	}

}
