package com.luremesoftware.adme.modelo;

import com.luremesoftware.adme.modelo.gestor.GestorPubli;
import com.luremesoftware.adme.modelo.lista.ListaPubli;
import com.luremesoftware.adme.modelo.lista.ListaPuntuacion;

public class Propietario {
	private String id;
	private ListaPubli listaPubli = null;
	private ListaPuntuacion listaPuntuacion;
			
	public Propietario(String id){
		this.setId(id);
		//Se buscan los identificadores de las publicaciones del propietario
		this.listaPubli = new GestorPubli().getListaPubli(this);		
	}
	
	public String getId(){
		return this.id;
	}
	
	public ListaPubli getListaPubli(){
		return listaPubli;
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
		//TODO TRANSFORMAR STRING TO LONG
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
