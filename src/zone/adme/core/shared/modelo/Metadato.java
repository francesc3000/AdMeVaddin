package zone.adme.core.shared.modelo;

import com.google.gwt.user.client.rpc.IsSerializable;

import zone.adme.core.shared.constantes.Constante;
import zone.adme.core.shared.constantes.Constante.ConstanteOperador;
import zone.adme.core.shared.constantes.Constante.Tabla;

public class Metadato implements IsSerializable{

	private Tabla tabla;
	private Constante nombreMetadato;
    private ConstanteOperador operador;
    private String valor;
        
    public Metadato(){}
    
	public Metadato(Tabla tabla, Constante nombreMetadato, ConstanteOperador operador, String valor ) {
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
	
	public String getValor(){
		return this.valor;
	}
	
}