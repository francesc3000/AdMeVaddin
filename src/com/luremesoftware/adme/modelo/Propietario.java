package com.luremesoftware.adme.modelo;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public abstract class Propietario{
	@Id
	protected String id;
	//private String video;
	//private Image avatar;
	protected List<Publi> listaPubli = new ArrayList<Publi>();
	@Parent
	protected ControlPuntuacion controlPuntuacion;
	
	protected Propietario(){}
	
	public String getId(){
		return this.id;
	}
	
	public List<Publi> getListaPubli(){
		return this.listaPubli;
	}

	public Puntuacion getAltaPuntuacion(){
		return this.controlPuntuacion.getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.controlPuntuacion.getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.controlPuntuacion.getPuntuacionPromedio();
	}

	public boolean setId(String id){
		this.id = id;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		return this.listaPubli.add(publi);
	}
	
	public boolean setListaPubli(List<Publi> listaPubli){
		return this.listaPubli.addAll(listaPubli);
	}
}
