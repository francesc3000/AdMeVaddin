package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

public class PuestoControl {
	private Usuario usuario = null;
	private ArrayList<Publi> listaPubli = null;
	private ArrayList<Grupo> listaGrupo = null;
	private Puntuaciones puntuaciones = null;
	
	public PuestoControl(Usuario usuario){
		this.usuario = usuario;
		this.listaGrupo = usuario.getListaGrupo();
		
		//Se recuperan las publicaciones del Usuario 
		//y de los equipos en los que participa
		this.listaPubli = (usuario.getListaPubli());
		for(Grupo grupo:this.listaGrupo){
			this.listaPubli.addAll(grupo.getListaPubli());
		}
		
		this.puntuaciones = usuario.getControlPuntuacion();
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public ArrayList<Publi> getListaPubli(){
		return this.listaPubli;
	}
	
	public ArrayList<Grupo> getListaGrupo(){
		return this.listaGrupo;
	}
	
	public int getPuntuacionPromedio(){
		return this.puntuaciones.getPuntuacionPromedio();
	}

}
