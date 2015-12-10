package co.adme.vaadin.modelo;

import org.springframework.data.annotation.Id;

public class MongoDBModelo {
	//Separator
	public final static String separator = "|";
	
	@Id
	private String id;
	
	//Search assistant
	private String lookup;
	
	public String getId(){
		return this.id;
	}
	
	protected void setLookup(String[] args ){
		for(String arg:args){
			this.lookup = arg.toUpperCase() + MongoDBModelo.separator;
		}
	}
	
	public String getLookup(){
		return this.lookup;
	}
}
