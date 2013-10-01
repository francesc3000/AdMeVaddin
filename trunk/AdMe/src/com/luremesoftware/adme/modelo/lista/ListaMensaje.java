package com.luremesoftware.adme.modelo.lista;

import java.util.ArrayList;

import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;

public class ListaMensaje extends ArrayList<Mensaje> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean existeError(){
		for(Mensaje mensaje: this){
			if(mensaje.getTipo()==TipoError.ERROR){
				return true;
			}
		}
		return false;
	}

}
