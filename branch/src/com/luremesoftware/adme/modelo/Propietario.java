package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.Image;
import com.luremesoftware.adme.constantes.Constante.Tabla;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaPuntuacion;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Propietario{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;
	@NotPersistent
	protected String id;
	//private String video;
	//private Image avatar;
	//@Persistent
	protected ArrayList<Publi> listaPubli = new ArrayList<Publi>();
	//@Persistent
	protected ArrayList<Puntuacion> listaPuntuacion = null;
	
	public Propietario(){}
			
	public Propietario(String id){
		this.setId(id);	
	}
	
	public Key getKey(){
		if(this.key==null){
			this.setKey(KeyFactory.createKey(Tabla.USUARIO.toString(), this.getId()));
		}
		return this.key;
	}
	
	public String getId(){
		return this.id;
	}
	
	public ListaPubli getListaPubli(){
		if(this.listaPubli == null){
			this.listaPubli = new GestorPubli().getListaPubli(this);	
		}
		return (ListaPubli) this.listaPubli;
	}
/*
	public Puntuacion getAltaPuntuacion(){
		return this.listaPuntuacion.getAltaPuntuacion();
	}
	
	public Puntuacion getBajaPuntuacion(){
		return this.listaPuntuacion.getBajaPuntuacion();
	}
	
	public int getPuntuacionPromedio(){
		return this.listaPuntuacion.getPuntuacionPromedio();
	}
*/
	public boolean setKey(Key key){
		this.key = key;
		return true;
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