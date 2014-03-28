package zone.adme.gwt.shared;

public class MensajeGWT {

	private String tipo;
	private String msg;

	public MensajeGWT(String tipo, String msg){
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
