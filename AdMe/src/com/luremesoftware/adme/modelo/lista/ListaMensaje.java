package com.luremesoftware.adme.modelo.lista;

import java.util.List;

import com.luremesoftware.adme.modelo.Mensaje;
import com.luremesoftware.adme.modelo.Mensaje.TipoError;

public class ListaMensaje extends List<Mensaje> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Busca en la lista si existen algun mensaje de error
	 * @return
	 */
	public boolean contieneErrores(){
		for(Mensaje mensaje: this){
			if(mensaje.getTipo()==TipoError.ERROR){
				return true;
			}
		}
		return false;
	}

}
