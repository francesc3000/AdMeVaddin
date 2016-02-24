package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;

import co.adme.vaadin.db.controller.GroupController;
import co.adme.vaadin.db.controller.PubliController;
import co.adme.vaadin.db.controller.UserController;

@SpringComponent
@ViewScope
@Document(collection = "publis")
public class Publi extends Modelo{
	
	public final static String TITLE_PROPERTYID = "title";
	public final static String DESCRIPTION_PROPERTYID = "description";
	public final static String CITY_PROPERTYID = "city";
	public final static String PRICE_PROPERTYID = "price";
	public final static String OWNER = "owner";
	
	private String title="";	 		//Titulo de la publicacion
	private String description=""; 	//Descripción de la publicación
	private String city=""; 			//Ciudad donde se va mostrar el logotipo
	private Float price=new Float(0);
	private Owner owner;
	
	private List<String> sponsorIdList = new ArrayList<String>();
	@Transient
	private List<Owner> sponsorList = new ArrayList<Owner>();
	
	@SuppressWarnings("unused")
	private Publi(){}
	
	public Publi(Owner owner){
		this.owner = owner;
		loadDBServices(this.owner);
	}
	
	public Publi(Owner owner, String title, String description, String city, Float price){
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.city = city;
		this.price = price;
		this.setLookup(new String[]{getTitle(),getDescription()});
		loadDBServices(this.owner);
	}
	
	private void loadDBServices(Owner owner){
		this.setUserService(owner.getUserService());
		this.setGroupService(owner.getGroupService());
		this.setPubliService(owner.getPubliService());
	}
	
	public String getTitle(){
		return this.title;
	}

	public String getCity(){
		return this.city;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Float getPrice(){
		return this.price;
	}

	public Owner getOwner(){
		return this.owner;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setPrice(Float price){
		this.price = price;
	}
	
	public void setPrice(String price){
		this.price = new Float(price);
	}
	
	public void setOwner(Owner owner){
		this.owner = owner;
	}
	
	public Boolean addSponsor(Owner sponsor){
		if(this.addSponsorId(sponsor.getId()))
			return this.sponsorList.add(sponsor);
		
		return false;
	}
	
	private Boolean addSponsorId(String sponsorId){
		if(!this.sponsorIdList.contains(sponsorId))
			return this.sponsorIdList.add(sponsorId);
		
		return false;
		
	}
	
	public Boolean isSponsored(){
		return !this.sponsorIdList.isEmpty();
	}
	
	public Boolean isSponsor(Owner owner){
		return this.getSponsorList().contains(owner);
	}
	
	public List<Owner> getSponsorList(){
		if(this.sponsorList.isEmpty())
			this.sponsorList.addAll(this.getUserListDB(this.getSponsorIdList()));
		
		return this.sponsorList;
	}
	
	private List<String> getSponsorIdList(){
		return this.sponsorIdList;
	}
	
	public Owner getSponsor(int index){
		return this.getSponsorList().get(index);
	}
	
	public Boolean removeSponsor(Owner sponsor){
		if(this.removeSponsorId(sponsor.getId()))
			return this.sponsorList.remove(sponsor);
		
		return false;
	}
	
	private Boolean removeSponsorId(String sponsorId){
		if(this.sponsorIdList.contains(sponsorId))
			return this.sponsorIdList.remove(sponsorId);
		
		return false;
		
	}
	
	public Boolean save(){
		return this.getPubliService().save(this);
	}
	
	public boolean equals(Object o){
		if(o!=null && o.getClass().equals(Publi.class))	{
			Publi publi_o = (Publi) o;
			if(this.getId().equals(publi_o.getId()))
				return true;
		}
		
		return false;
	}
}
