package zone.adme.core.shared.modelo;

import com.google.gwt.user.client.rpc.IsSerializable;

import zone.adme.core.shared.constantes.Constante.ConstanteOperador;

public class Metadato2 implements IsSerializable{
	
	private String tabla;
	private String nombreMetadato;
    private String operador;
    private Object valor;
    
    public Metadato2(String tabla, String nombreMetadato, String operador, Object valor ) {
		this.tabla = tabla;
		this.nombreMetadato = nombreMetadato;
	    this.operador = operador;
	    this.valor = valor;
	}

	public String getNombreTabla(){
		return this.tabla;
	}
	
	public String getNombreMetadato(){
		return this.nombreMetadato;
	}
	
	public String getOperatorFilter(){
		String retorno = null;
		if(this.operador.equals(ConstanteOperador.EQUAL)){
			retorno = this.operador.toString() + this.operador.toString();
		}else{
			retorno = this.operador.toString();
		}
		
		return retorno;
	}
	
	public String getOperador(){
		return this.operador;
	}
	
	public Object getValor(){
		return this.valor;
	}

}
