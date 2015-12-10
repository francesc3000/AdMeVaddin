package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class Group extends MongoDBModelo{
	
	private String name;	 				

	private List<User> userList = new ArrayList<User>();
	
	public Group(String name, User user){
		this.name = name;
		addUser(user);
		this.setLookup(new String[]{getName()});
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean addUser(User user){
		return this.userList.add(user);
	}
	
	public List<User> getAllUser(){
		return this.userList;
	}
	
	public User getUser(String id){
		for(User user:this.userList){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
}
