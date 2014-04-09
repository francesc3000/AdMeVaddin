package zone.adme.core.modelo;

import java.io.Serializable;

import zone.adme.core.constantes.Constante;
import zone.adme.core.constantes.Constante.ConstanteOperador;
import zone.adme.core.constantes.Constante.Tabla;

public class Metadato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4051355469215817506L;

	
	private Tabla tabla;
	private Constante nombreMetadato;
    private ConstanteOperador operador;
    private Object valor;
    
    public Metadato(){}
    
	public Metadato(Tabla tabla, Constante nombreMetadato, ConstanteOperador operador, Object valor ) {
		this.tabla = tabla;
		this.nombreMetadato = nombreMetadato;
	    this.operador = operador;
	    this.valor = valor;
	}

	public Tabla getNombreTabla(){
		return this.tabla;
	}
	
	public Constante getNombreMetadato(){
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
	
	public ConstanteOperador getOperador(){
		return this.operador;
	}
	
	public Object getValor(){
		return this.valor;
	}
}