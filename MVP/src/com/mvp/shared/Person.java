package com.mvp.shared;

public class Person {
	private String firstName;
	private String lastName;
	
	public Person(){
		this("Francesc","Munoz");
	}
	
	public Person(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFullName(){
		return this.getFirstName() + " " + this.getLastName();
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
}
