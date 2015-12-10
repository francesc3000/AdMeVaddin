package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class User extends MongoDBModelo{
	
	private String email;
	private String name;	 		
	private String firstName; 	
	private String lastName; 			
	private String city;
	
	private List<Group> groupList = new ArrayList<Group>();
	
	public User(String email, String name, String firstName, String lastName){
		this.email = email;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setLookup(new String[]{getEmail(),getName(),getFirstName(),getLastName()});
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Boolean addGroup(Group group){
		return this.groupList.add(group);
	}
	
	public List<Group> getAllGroup(){
		return this.groupList;
	}
	
	public Group getGroup(String id){
		for(Group group:this.groupList){
			if(group.getId()==id){
				return group;
			}
		}
		return null;
	}
}
