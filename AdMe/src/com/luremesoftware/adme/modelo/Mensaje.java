package com.luremesoftware.adme.modelo;

public class Mensaje {
	
	public static String OK			= "S";
	public static String WARNING 	= "W";
	public static String ERROR		= "E";

	private String tipo;
	private String msg;

	public Mensaje(String tipo, String msg){
		this.tipo = tipo;
		this.msg = msg;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public String getMensaje(){
		return this.msg;
	}
}
