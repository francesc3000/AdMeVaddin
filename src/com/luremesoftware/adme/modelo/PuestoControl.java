package com.luremesoftware.adme.modelo;

import java.util.ArrayList;
import java.util.List;

public class PuestoControl {
	private Propietario usuario = null;
	private List<Publi> listaPubli = null;
	private List<Propietario> listaGrupo = null;
	private Puntuaciones puntuaciones = null;
	
	public PuestoControl(Propietario usuario){
		this.usuario = usuario;
		this.listaGrupo = usuario.getListaGrupo();
		
		//Se recuperan las publicaciones del Usuario 
		//y de los equipos en los que participa
		this.listaPubli = (usuario.getListaPubli());
		for(Propietario grupo:this.listaGrupo){
			this.listaPubli.addAll(grupo.getListaPubli());
		}
		
		this.puntuaciones = usuario.getControlPuntuacion();
	}
	
	public Propietario getUsuario(){
		return this.usuario;
	}
	
	public List<Publi> getListaPubli(){
		return this.listaPubli;
	}
	
	public List<Propietario> getListaGrupo(){
		return this.listaGrupo;
	}
	
	public int getPuntuacionPromedio(){
		return this.puntuaciones.getPuntuacionPromedio();
	}

}
