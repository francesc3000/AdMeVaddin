package com.luremesoftware.adme.modelo;

import java.util.ArrayList;

public class ListaUsuario extends ArrayList<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString(){
		String retorno = new String();
		
		for(Usuario usuario : this){
			retorno = retorno + usuario.toString();
			retorno = retorno +  "\t";
		}
		
		return retorno;
	}
}
