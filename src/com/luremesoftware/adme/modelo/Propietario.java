package com.luremesoftware.adme.modelo;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public abstract class Propietario{
	@Id
	protected String id;
	//private String video;
	//private Image avatar;
	@Load
	protected List<Ref<Publi>> listaPubliRef = new ArrayList<Ref<Publi>>();
	@Load
	protected Ref<ControlPuntuacion> controlPuntuacionRef = null;
	
	protected Propietario(){}
	
	public String getId(){
		return this.id;
	}
	
	public List<Publi> getListaPubli(){
		List<Publi> listaPubliNoRef = new ArrayList<Publi>();
		
		for(Ref<Publi> publi:this.listaPubliRef){
			listaPubliNoRef.add(publi.get());
		}
		return listaPubliNoRef;
	}

	public Puntuacion getAltaPuntuacion(){
		return this.controlPuntuacionRef.get().getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.controlPuntuacionRef.get().getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.controlPuntuacionRef.get().getPuntuacionPromedio();
	}
	
	public ControlPuntuacion getControlPuntuacion(){
		return this.controlPuntuacionRef.get();
	}

	public boolean setId(String id){
		this.id = id;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		return this.listaPubliRef.add(Ref.create(publi));
	}
	
	public boolean setListaPubli(List<Publi> listaPubli){
		
		for(Publi publi:listaPubli){
			this.setPubli(publi);
		}
		
		return true;
	}
}
