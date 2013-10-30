package com.luremesoftware.adme.modelo;

import java.util.List;

public class PuestoControl {
	private Usuario usuario = null;
	private List<Publi> listaPubli = null;
	private List<Grupo> listaGrupo = null;
	private ControlPuntuacion controlPuntuacion = null;
	
	public PuestoControl(Usuario usuario){
		this.usuario = usuario;
		this.listaGrupo = usuario.getListaGrupo();
		
		//Se recuperan las publicaciones del Usuario 
		//y de los equipos en los que participa
		this.listaPubli = (usuario.getListaPubli());
		for(Grupo grupo:this.listaGrupo){
			this.listaPubli.addAll(grupo.getListaPubli());
		}
		
		this.controlPuntuacion = usuario.getControlPuntuacion();
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public List<Publi> getListaPubli(){
		return this.listaPubli;
	}
	
	public List<Grupo> getListaGrupo(){
		return this.listaGrupo;
	}
	
	public int getPuntuacionPromedio(){
		return this.controlPuntuacion.getPuntuacionPromedio();
	}

}
