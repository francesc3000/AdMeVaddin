package com.luremesoftware.adme.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
//@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
@Inheritance(customStrategy = "complete-table")
public abstract class Propietario{
	@PrimaryKey
	@Persistent
	protected Key key;
	//private String video;
	//private Image avatar;
	@Persistent(mappedBy = "propietario", defaultFetchGroup = "true")
	@Element(dependent = "true")
	protected List<Publi> listaPubli = new ArrayList<Publi>();
	@Persistent(dependent = "true", defaultFetchGroup = "true")
	protected ControlPuntuacion controlPuntuacion;
	
	public Propietario(){}
	
	public Key getKey(){
		return this.key;
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

	public boolean setKey(Key key){
		this.key = key;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		return this.listaPubli.add(publi);
	}
	
	public boolean setListaPubli(List<Publi> listaPubli){
		return this.listaPubli.addAll(listaPubli);
	}
}
