package co.adme.vaadin.db;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.util.BeanContainer;

import co.adme.vaadin.modelo.Publi;

@SuppressWarnings("serial")
@Component  
@Scope("prototype")
public class PubliDBContainer extends BeanContainer<String, Publi> {	
	
	public static final String BEAN_ID = "id";
	  
    public static final Object[] PROPERTIES = {BEAN_ID, "title", "description", "city", "price"};
    
    public static final Object[] VISIBLE = {"title", "description", "city", "price"}; 
  
    public static final String[] HEADERS = {"Id", "Título", "Descripción", "Ciudad", "Aportación"};  
  
    public PubliDBContainer() {  
        super(Publi.class);  
        setBeanIdProperty(BEAN_ID);  
    } 
    
    public String getTitleHeader(){
    	return PubliDBContainer.HEADERS[1];
    }
    
    public String getDescriptioneHeader(){
    	return PubliDBContainer.HEADERS[2];
    }
    
    public String getCityHeader(){
    	return PubliDBContainer.HEADERS[3];
    }
    
    public String getPriceHeader(){
    	return PubliDBContainer.HEADERS[4];
    }

}
