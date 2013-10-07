package com.luremesoftware.adme.modelo.lista;

import java.util.ArrayList;

import com.luremesoftware.adme.modelo.Usuario;

public class ListaUsuario extends ArrayList<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean existeId(String id){
		for(Usuario Usuario:this){
			if(id == Usuario.getId()){
				return true;
			}
		}
		return false;
	}
	
	public Usuario getUsuarioById(String id){
		for(Usuario usuario:this){
			if(id.compareTo(usuario.getId()) == 0){
				return usuario;
			}
		}
		return null;
	}

	public String toString(){
		String retorno = new String();
		
		for(Usuario usuario : this){
			retorno = retorno + usuario.toString();
			retorno = retorno +  "\t";
		}
		
		return retorno;
	}
}
