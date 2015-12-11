package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class Publi extends MongoDBModelo{
	
	private String title;	 		//Titulo de la publicacion
	private String description; 	//Descripción de la publicación
	private String city; 			//Ciudad donde se va mostrar el logotipo
	private Float price;
	//private Group owner;
	
	//private List<Group> sponsorList = new ArrayList<Group>();
	
	@SuppressWarnings("unused")
	private Publi(){}
	
	@Deprecated
	public Publi(String title, String description, String city, Float price){
		this.title = title;
		this.description = description;
		this.city = city;
		this.price = price;
		this.setLookup(new String[]{getTitle(),getDescription()});
		
	}
	
	public Publi(Group owner, String title, String description, String city, Float price){
		//this.owner = owner;
		this.title = title;
		this.description = description;
		this.city = city;
		this.price = price;
		this.setLookup(new String[]{getTitle(),getDescription()});
		
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
	/*
	public Group getOwner(){
		return this.owner;
	}
	
	public Boolean addSponsor(Group sponsor){
		return this.sponsorList.add(sponsor);
	}
	
	public List<Group> getAllSponsor(){
		return this.sponsorList;
	}
	
	public Group getSponsor(String id){
		for(Group sponsor:this.sponsorList){
			if(sponsor.getId()==id){
				return sponsor;
			}
		}
		return null;
	}
	*/
}
