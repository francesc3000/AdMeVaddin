package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

public class Event extends Publi {
	private List<Referee> refereeList = new ArrayList<Referee>();
	
	public Event(Owner owner, String title, String description, String city, Float price){
		super(owner, title, description, city, price);
	}
	
	public Event(Owner owner, String title, String description, String city, Float price, Referee referee){
		super(owner, title, description, city, price);
		this.addReferee(referee);
	}
	
	public Event(Owner owner, String title, String description, String city, Float price, List<Referee> refereeList){
		super(owner, title, description, city, price);
		this.addRefereeList(refereeList);
	}
	
	public Boolean addReferee(Referee referee){
		return this.refereeList.add(referee);
	}
	
	public Boolean addRefereeList(List<Referee> refereeList){
		return this.refereeList.addAll(refereeList);
	}
	
	public Boolean isReady(){
		if(!this.refereeList.isEmpty())
			return true;
		
		return false;
	}
}
