package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import co.adme.vaadin.db.controller.GroupController;
import co.adme.vaadin.db.controller.PubliController;
import co.adme.vaadin.db.controller.UserController;

@Document
@SpringComponent
@UIScope
public abstract class Modelo {
	public final static String ID_PROPERTYID = "name";

	@Autowired
	private UserController userService;

	@Autowired
	private GroupController groupService;

	@Autowired
	private PubliController publiService;
	
	//Separator
	public final static String separator = "|";
	
	@Id
	protected String id;
	
	//Search assistant
	protected String lookup="";
	
	private Rating rating = new Rating();
	
	protected Modelo(){
		
		ApplicationContext applicationContext = UI.getCurrent().getSession().getAttribute(ApplicationContext.class);
		if(applicationContext!=null){
			this.setUserService(applicationContext.getBean(UserController.class));
			this.setGroupService(applicationContext.getBean(GroupController.class));
			this.setPubliService(applicationContext.getBean(PubliController.class));
		}
		
	}
	
	public String getId(){
		return this.id;
	}
	
	protected void setLookup(String[] args ){
		for(String arg:args){
			if(this.lookup.isEmpty())
				this.lookup = arg.toUpperCase();
			else
				this.lookup += arg.toUpperCase() + Modelo.separator;
		}
	}
	
	public String getLookup(){
		return this.lookup;
	}
	
	protected void clearLookup(){
		this.lookup = "";
	}
	
	public double getRating() {
		return rating.getRating();
	}

	public void setRating(Modelo rater, double rating) {
		this.rating.addRating(rater, rating);
	}

	public UserController getUserService(){
		return userService;
	}

	public GroupController getGroupService(){
		return groupService;
	}
	
	public PubliController getPubliService(){
		return publiService;
	}
	
	protected void setUserService(UserController userService){
		if(this.userService==null){
			this.userService = userService;
		}
	}
	
	protected void setGroupService(GroupController groupService){
		if(this.groupService==null)
			this.groupService = groupService;
	}
	
	protected void setPubliService(PubliController publiService){
		if(this.publiService==null){
			this.publiService = publiService;
		}
	}
	
	protected List<User> getUserListDB(List<String> userIdList){
		List<User> userList = new ArrayList<User>();
		
		for(String userId:userIdList){
			User user = this.getUserService().findOne(userId);
			user.setUserService(this.getUserService());
			user.setGroupService(this.getGroupService());
			user.setPubliService(this.getPubliService());
			userList.add(user);
		}
		
		return userList;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o!=null && o instanceof Modelo)	{
			Modelo modelo_o = (Modelo) o;
			if(this.getId().equals(modelo_o.getId()))
				return true;
		}
		
		return false;
	}
}
