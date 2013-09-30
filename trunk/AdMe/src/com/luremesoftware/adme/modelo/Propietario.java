package com.luremesoftware.adme.modelo;

public class Propietario {
	private String id;
	private ListaPubli listaPubli = null;
			
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
