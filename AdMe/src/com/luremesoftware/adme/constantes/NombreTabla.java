package com.luremesoftware.adme.constantes;

public enum NombreTabla {
	USUARIO("Usuario"),
	GRUPO("Grupo"),
	PUBLICACION("Publicacion"),
	USUARIOGRUPO("UsuarioGrupo");
	
	private final String nombreTabla;
	
	private NombreTabla(String nombreTabla){
		this.nombreTabla = nombreTabla;
	}
	
	public String toString(){
		return this.nombreTabla;
	}
}
