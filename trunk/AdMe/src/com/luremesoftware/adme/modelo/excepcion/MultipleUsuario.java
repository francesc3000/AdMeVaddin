package com.luremesoftware.adme.modelo.excepcion;

public class MultipleUsuario extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleUsuario(String msg){
		super(msg);
	}
}
