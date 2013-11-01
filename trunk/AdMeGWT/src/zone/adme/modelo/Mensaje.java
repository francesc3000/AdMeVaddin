package zone.adme.modelo;

public class Mensaje {
	
	public enum TipoError {
		OK("S"),
		WARNING("W"),
		ERROR("E");
		
		private final String tipoError;
		
		private TipoError(String tipoError){
			this.tipoError = tipoError;
		}
		
		public String toString(){
			return this.tipoError;
		}
	}

	private TipoError tipo;
	private String msg;

	public Mensaje(TipoError tipo, String msg){
		this.tipo = tipo;
		this.msg = msg;
	}
	
	public TipoError getTipo(){
		return this.tipo;
	}
	
	public String getMensaje(){
		return this.msg;
	}
}
