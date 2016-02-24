package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

public abstract class Owner extends Modelo {
	public final static String NAME_PROPERTYID = "name";
	
	@NotNull
	//@Field(value="name")
	protected String name="";
	
	private String city;

	protected List<String> publiIdList = new ArrayList<String>();
	private List<String> sponsorPubliIdList = new ArrayList<String>();
	private List<String> winPubliIdList = new ArrayList<String>();
	
	@Transient
	protected List<Publi> publiList = new ArrayList<Publi>();
	@Transient
	private List<Publi> sponsorPubliList = new ArrayList<Publi>();
	@Transient
	private List<Publi> winPubliList = new ArrayList<Publi>();
	
	protected Owner(){}
	
	public Owner(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Boolean addPubli(Publi publi){
		if(this.addPubliId(publi.getId()))
			return this.publiList.add(publi);
		
		return false;
	}

	private List<String> getPubliIdList() {
		return publiIdList;
	}
	
	private Boolean addPubliId(String publiId) {
		if(!this.publiIdList.contains(publiId))
			return this.publiIdList.add(publiId);
		
		return false;
	}

	public List<Publi> getPubliList() {
		if(this.publiList.isEmpty())
			//Se inserta directamente en la tabla para saltar la validación
			this.publiList.addAll(getPubliListDB(this.getPubliIdList()));
		
		return this.publiList;
	}

	public Boolean addPubliList(List<Publi> publiList) {
		for(Publi publi:publiList)
			this.addPubli(publi);
		
		return true;
	}
	
	protected List<Group> getGroupListDB(List<String> groupIdList){
		List<Group> groupList = new ArrayList<Group>();
		
		for(String groupId:groupIdList){
			groupList.add(this.getGroupService().findOne(groupId));
		}
		
		return groupList;
	}
	
	protected List<Publi> getPubliListDB(List<String> publiIdList){
		List<Publi> publiList = new ArrayList<Publi>();
		
		for(String publiId:publiIdList){
			publiList.add(this.getPubliService().findOne(publiId));
		}
		
		return publiList;
	}
	
	public Boolean addSponsorPubli(Publi sponsored){
		if(this.addSponsorPubliId(sponsored.getId()))
			return this.sponsorPubliList.add(sponsored);
		
		return false;
	}
	
	public List<Publi> getSponsorPubliList(){
		if(this.sponsorPubliList.isEmpty())
			this.sponsorPubliList.addAll(getSponsorPubliListDB());
		
		return this.sponsorPubliList;
	}
	
	private Boolean addSponsorPubliId(String sponsoredId){
		if(!this.sponsorPubliIdList.contains(sponsoredId))
			return this.sponsorPubliIdList.add(sponsoredId);
		
		return false;
	}
	
	private List<Publi> getSponsorPubliListDB(){
		return getPubliListDB(this.getSponsorPubliIdList());
	}
	
	private List<String> getSponsorPubliIdList(){
		return this.sponsorPubliIdList;
	}
	
	public Boolean addWinPubli(Publi winPubli){
		if(this.addWinPubliId(winPubli.getId()))
			return this.winPubliList.add(winPubli);
		
		return false;
	}
	
	public List<Publi> getWinPubliList(){
		if(this.winPubliList.isEmpty())
			this.winPubliList.addAll(getWinPubliListDB());
		
		return this.winPubliList;
	}
	
	private Boolean addWinPubliId(String winPubliId){
		return this.winPubliIdList.add(winPubliId);
	}
	
	private List<Publi> getWinPubliListDB(){
		return getPubliListDB(this.getWinPubliIdList());
	}
	
	private List<String> getWinPubliIdList(){
		return this.winPubliIdList;
	}
	
	public Boolean removePubli(Publi publi){
		if(this.removePubliId(publi.getId()))
			return this.publiList.remove(publi);
		
		return false;
	}
	
	private Boolean removePubliId(String publiId) {
		if(this.publiIdList.contains(publiId))
			return this.publiIdList.remove(publiId);
		
		return false;
	}
	
	public Boolean removeSponsorPubli(Publi sponsored){
		if(this.removeSponsorPubliId(sponsored.getId())){
			this.sponsorPubliList.remove(sponsored);
			return true;
		}
		
		return false;
	}
	
	private Boolean removeSponsorPubliId(String sponsoredId){
		if(this.sponsorPubliIdList.contains(sponsoredId))
			return this.sponsorPubliIdList.remove(sponsoredId);
		
		return false;
	}
	
	public Boolean removeWinPubli(Publi winPubli){
		if(this.removeWinPubliId(winPubli.getId())){
			this.winPubliList.remove(winPubli);
			return true;
		}
		
		return false;
	}
	
	private Boolean removeWinPubliId(String winPubliId){
		if(this.winPubliIdList.contains(winPubliId))
			return this.winPubliIdList.remove(winPubliId);
		
		return false;
	}
	
	public Publi newPubli( ){
		return new Publi(this);
		
		}
	
	public Publi newPubli(String title, String description, 
						  String city, Float price ){
		return  new Publi(this, title, description, city, price);
	}
	
	public String toString(){
		return this.getName();
	}
}
