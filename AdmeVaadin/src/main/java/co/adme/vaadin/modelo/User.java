package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@Document(collection = "users")
public class User extends Owner{
	
	public final static String EMAIL_PROPERTYID = "email";

	private String email;	
	private String firstName; 	
	private String lastName;
	
	private List<String> groupIdList = new ArrayList<String>();
	private List<String> friendIdList = new ArrayList<String>();
	private List<String> favoritePubliIdList = new ArrayList<String>();
	private List<String> favoriteGroupIdList = new ArrayList<String>();
	
	@Transient
	private List<Group> groupList = new ArrayList<Group>();
	@Transient
	private List<User> friendList = new ArrayList<User>();
	@Transient
	private List<Publi> favoritePubliList = new ArrayList<Publi>();
	@Transient
	private List<Group> favoriteGroupList = new ArrayList<Group>();
	
	public User(String email, String name, String firstName, String lastName){
		super(name);
		this.email = email;
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
	
	public String getFullName(){
		return this.getName() + " " + this.getFirstName() + " " + this.getLastName();
	}
	
	public Boolean addGroup(Group group){
		if(this.addGroupId(group.getId()))
			return this.groupList.add(group);
			
		return false;
	}
	
	private Boolean addGroupId(String groupId){
		if(!this.groupIdList.contains(groupId))
			return this.groupIdList.add(groupId);
		
		return false;
	}
	
	public Boolean addGroupList(List<Group> groupList){
		for(Group group:groupList)
			this.addGroup(group);
			
		return true;
	}
	
	public List<Group> getGroupList(){
		if(this.groupList.isEmpty())
			this.groupList.addAll(this.getGroupListDB(this.getGroupIdList()));

		return this.groupList;
	}
	
	private List<String> getGroupIdList(){
		return this.groupIdList;
	}
	
	public Boolean addFriend(User friend){
		if(this.addFriendId(friend.getId())){
			friend.setUserService(this.getUserService());
			friend.setGroupService(this.getGroupService());
			friend.setPubliService(this.getPubliService());
			return this.friendList.add(friend);
		}
		
		return false;
	}
	
	private Boolean addFriendId(String friendId){
		if(!this.friendIdList.contains(friendId))
			return this.friendIdList.add(friendId);
		
		return false;
	}
	
	public Boolean addFriendList(List<User> friendList){
		for(User friend:friendList)
			this.addFriend(friend);
		
		return true;
	}
	
	public List<User> getFriendList(){
		if(this.friendList.isEmpty())
			this.friendList.addAll(this.getUserListDB(this.getFriendIdList()));
		
		return this.friendList;
	}
	
	private List<String> getFriendIdList(){
		return this.friendIdList;
	}
	
	public User findFriendByNameAndEmail(String concept) throws NotFound{
		List<User> friendList = this.getFriendList();
		for(User user:friendList){
			/*
			if(user.getName().contains(concept))
				return user;
			
			if(user.getEmail().contains(concept))
				return user;
			*/
			if(user.getLookup().contains(concept))
				return user;
		}
		throw new NotFound();
	}
	
	public Boolean addFavoritePubli(Publi favorite){
		if(this.addFavoritePubliId(favorite.getId()))
			return this.favoritePubliList.add(favorite);
		
		return false;
	}
	
	private Boolean addFavoritePubliId(String favoriteId){
		if(!this.favoritePubliIdList.contains(favoriteId))
			return this.favoritePubliIdList.add(favoriteId);
		
		return false;
	}
	
	public List<Publi> getFavoritePubliList(){
		if(this.favoritePubliList.isEmpty())
			this.favoritePubliList.addAll(getFavoritePubliListDB());
		
		return this.favoritePubliList;
	}
	
	private List<Publi> getFavoritePubliListDB(){
		return getPubliListDB(this.getFavoritePubliIdList());
	}
	
	private List<String> getFavoritePubliIdList(){
		return this.favoritePubliIdList;
	}
	
	public List<Publi> getGroupsPubliList() {
		List<Publi> publiList = new ArrayList<Publi>();

		for(Group group:this.getGroupList())
			publiList.addAll(group.getPubliList());
		
		return publiList;
	}
	
	public List<Publi> getUserAndGroupPubliList() {
		List<Publi> publiList = new ArrayList<Publi>();

		publiList.addAll(super.getPubliList());
		publiList.addAll(this.getGroupsPubliList());		
		
		return publiList;
	}
	
	public Boolean addFavoriteGroup(Group favorite){
		if(this.addFavoriteGroupId(favorite.getId()))
			return this.favoriteGroupList.add(favorite);
		
		return false;
	}
	
	private Boolean addFavoriteGroupId(String favoriteId){
		if(!this.favoriteGroupIdList.contains(favoriteId))
			return this.favoriteGroupIdList.add(favoriteId);
		
		return false;
	}
	
	public List<Group> getFavoriteGroupList(){
		if(this.favoriteGroupList.isEmpty())
			this.favoriteGroupList.addAll(getFavoriteGroupListDB());
		
		return this.favoriteGroupList;
	}
	
	private List<Group> getFavoriteGroupListDB(){
		return getGroupListDB(this.getFavoriteGroupIdList());
	}
	
	private List<String> getFavoriteGroupIdList(){
		return this.favoriteGroupIdList;
	}
	
	public List<Publi> getWinPubliList(){
		List<Publi> winPubliList = super.getWinPubliList();
		for(Group group:this.getGroupList())
			winPubliList.addAll(group.getWinPubliList());
		
		return winPubliList;
	}
	
	public Group newGroup(String name){
		return new Group(name, this);
	}
	
	public Boolean removeGroup(Group group){
		if(this.getGroupIdList().contains(group.getId())){
			if(this.removeGroupId(group.getId()))
				return this.getGroupList().remove(group);
		}
		
		return false;
			
	}
	
	private Boolean removeGroupId(String groupId){
		return this.getGroupIdList().remove(groupId);
	}
	
	public Boolean removeFavoritePubli(Publi favorite){
		if(this.removePubliIdFavorite(favorite.getId()))
			return this.favoritePubliList.remove(favorite);
		
		return false;
	}
	
	private Boolean removePubliIdFavorite(String publiId){
		return this.favoritePubliIdList.remove(publiId);
	}
	
	public Boolean removeFavoriteGroup(Group favorite){
		if(this.removeGroupIdFavorite(favorite.getId()))
			return this.favoriteGroupList.remove(favorite);
		
		return false;
	}
	
	private Boolean removeGroupIdFavorite(String groupId){
		return this.favoriteGroupIdList.remove(groupId);
	}
	
	public Boolean save(){
		return this.getUserService().save(this);
	}
	
	@Override
	public boolean equals(Object o){
		if(o!=null && o.getClass().equals(User.class))	{
			User user_o = (User) o;
			if(this.getEmail().equals(user_o.getEmail()))
				return true;
		}
		
		return false;
	}
	
	public String toString(){
		return this.getFullName();
	}
}
