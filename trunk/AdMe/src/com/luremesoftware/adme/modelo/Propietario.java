package com.luremesoftware.adme.modelo;

import com.google.appengine.api.images.Image;
import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaPuntuacion;

public class Propietario {
	private String id;
	private String video;
	private Image avatar;
	private ListaPubli listaPubli = null;
	private ListaPuntuacion listaPuntuacion;
	
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
