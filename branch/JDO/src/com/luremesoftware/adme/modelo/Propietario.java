package com.luremesoftware.adme.modelo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.images.Image;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaPuntuacion;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class Propietario {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	@NotPersistent
	private String video;
	@NotPersistent
	private Image avatar;
	@NotPersistent
	private ListaPubli listaPubli = null;
	@NotPersistent
	private ListaPuntuacion listaPuntuacion = null;
	
	public Propietario(){}
			
	public Propietario(String id){
		this.setId(id);	
	}
	
	public String getId(){
		return this.id;
	}
	
	public ListaPubli getListaPubli(){
		if(this.listaPubli == null){
			this.listaPubli = new GestorPubli().getListaPubli(this);	
		}
		return this.listaPubli;
	}

	public Puntuacion getAltaPuntuacion(){
		return this.listaPuntuacion.getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.listaPuntuacion.getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.listaPuntuacion.getPuntuacionPromedio();
	}
	
	public boolean setId(String id){
		this.id = id;
		return true;
	}
	
	public boolean setPubli(Publi publi){
		return this.listaPubli.add(publi);
	}
	
	public boolean setListaPubli(ListaPubli listaPubli){
		return this.listaPubli.addAll(listaPubli);
	}
	
}
