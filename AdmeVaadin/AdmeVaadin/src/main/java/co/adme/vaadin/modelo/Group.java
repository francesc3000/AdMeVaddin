package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import co.adme.vaadin.db.GroupRepository;
import co.adme.vaadin.db.controller.UserController;

@Document(collection = "groups")
public class Group extends Owner{
	public final static String MEMBERLIST_PROPERTYID = "memeberList";
	
	@NotNull
	private List<String> memberIdList = new ArrayList<String>();
	private List<String> candidateList = new ArrayList<String>();
	
	@Transient
	private List<User> memeberList = new ArrayList<User>();

	@SuppressWarnings("unused")
	private Group(){
		super();
	}
	
	public Group(String name, User user){
		super(name);
		addMember(user);
		doLookup();
		loadDBServices(user);
	}
	
	private void doLookup(){
		this.clearLookup();
		this.setLookup(new String[]{getName()});
	}
	
	private void loadDBServices(User user){
		this.setUserService(user.getUserService());
		this.setGroupService(user.getGroupService());
		this.setPubliService(user.getPubliService());
	}
	
	public void setName(String name) {
		super.setName(name);
		doLookup();
	}
	
	public List<User> getMemberList() {
		if(this.memeberList.isEmpty())
			this.memeberList.addAll(this.getUserListDB(this.getMemberIdList()));
			
		return this.memeberList;
	}
	
	private List<String> getMemberIdList() {
		return this.memberIdList;
	}
	
	public List<String> getCandidateList() {
		return this.candidateList;
	}

	public void addCandidate(String candidate) {
		this.candidateList.add(candidate);
	}
	
	private Boolean addMemberId(String memberId){
		if(!this.memberIdList.contains(memberId))
			return this.memberIdList.add(memberId);
		
		return false;
	}
	
	public Boolean addMember(User user){
		if(this.addMemberId(user.getId()))
			return this.memeberList.add(user);

		return false;
	}
	
	public Boolean addMemberList(List<User> memberList){
		for(User user:memberList)
			this.addMember(user);
		
		return true;
	}
	
	public Boolean addCandidateList(List<String> candidateList){
		for(String candidate: this.candidateList)
			if(candidate.equals(candidateList))
				return false;
		
		return this.candidateList.addAll(candidateList);
	}
	
	public Boolean setMemberList(List<User> memberList){
		List<String> memberIdList = new ArrayList<String>();
		
		for(User member:memberList)
			memberIdList.add(member.getId());
		
		this.setMemberIdList(memberIdList);
		this.memeberList = memberList;
		return true;
	}
	
	private Boolean setMemberIdList(List<String> memberIdList){
		this.memberIdList = memberIdList;
		
		return true;
	}
	
	public Boolean setCandidateList(List<String> candidateList){
		this.candidateList = candidateList;

		return true;
	}
	
	public User getMember(String id){
		for(User user:this.memeberList){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
	
	public Boolean isUserInGroupByEmail(String email){
		List<User> userList = this.getMemberList();
		for(User user:userList){
			if(user.getEmail().contains(email))
				return true;
		}
		
		return false;
	}
	
	public Boolean save(){
		return this.getGroupService().save(this);
	}
}
