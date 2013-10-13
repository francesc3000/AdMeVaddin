package com.luremesoftware.adme.modelo.lista;

import java.util.ArrayList;

import com.luremesoftware.adme.modelo.Propietario;

public class ListaPropietario extends ArrayList<Propietario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean existeId(String id){
		for(Propietario propietario:this){
			if(id == propietario.getId()){
				return true;
			}
		}
		return false;
	}
	
	public Propietario getPropietarioById(String id){
		for(Propietario propietario:this){
			if(id.compareTo(propietario.getId()) == 0){
				return propietario;
			}
		}
		return null;
	}

}
