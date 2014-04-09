package zone.adme.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PubliGWT implements IsSerializable {
	
	private UsuarioGWT usuarioGWT=null;
	private String titulo;
	private String texto;
	
	
	public UsuarioGWT getUsuarioGWT() {
		return usuarioGWT;
	}
	public void setUsuarioGWT(UsuarioGWT usuarioGWT) {
		this.usuarioGWT = usuarioGWT;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}	

}
