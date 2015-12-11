package co.adme.vaadin.modelo;

import org.springframework.data.annotation.Id;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class MongoDBModelo {
	//Separator
	public final static String separator = "|";
	
	@Id
	protected String id;
	
	//Search assistant
	protected String lookup;
	
	public String getId(){
		return this.id;
	}
	
	protected void setLookup(String[] args ){
		for(String arg:args){
			this.lookup = this.lookup + arg.toUpperCase() + MongoDBModelo.separator;
		}
	}
	
	public String getLookup(){
		return this.lookup;
	}
}
