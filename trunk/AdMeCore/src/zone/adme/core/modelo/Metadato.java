package zone.adme.core.modelo;

import zone.adme.core.constantes.Constante;
import zone.adme.core.constantes.Constante.Tabla;

import com.google.appengine.api.datastore.Query.FilterOperator;

public class Metadato {

	private Tabla tabla;
	private Constante nombreMetadato;
    private FilterOperator operador;
    private Object valor;
	
	public Metadato(Tabla tabla, Constante nombreMetadato, FilterOperator operador, Object valor ) {
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
		if(this.operador.equals(FilterOperator.EQUAL)){
			retorno = this.operador.toString() + this.operador.toString();
		}else{
			retorno = this.operador.toString();
		}
		
		return retorno;
	}
	
	public FilterOperator getOperador(){
		return this.operador;
	}
	
	public Object getValor(){
		return this.valor;
	}

}
