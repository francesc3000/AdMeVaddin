package co.adme.vaadin.modelo;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CheckBox;

public class FilterOption {
	private String title;
	private List<CheckBox> filterList = new ArrayList<CheckBox>();
	
	public FilterOption(String title){
		this.title = title;
	}
	
	public FilterOption(String title, CheckBox filter){
		this.title = title;
		this.filterList.add(filter);
	}
	
	public FilterOption(String title, List<CheckBox> filterList){
		this.title = title;
		this.filterList = filterList;
	}

	public String getTitle() {
		return title;
	}

	public List<CheckBox> getFilterList() {
		return filterList;
	}
	
	public void addFilter(String filterName){
		if(!exists(filterName)){
			this.filterList.add(new CheckBox(filterName));
		}
	}
	
	public void addFilter(String filterName, Boolean value){
		if(!exists(filterName)){
			this.filterList.add(new CheckBox(filterName, value));
		}
	}	
	
	private Boolean exists(String filterName){
		for(CheckBox checkBox:this.filterList){
			String caption = checkBox.getCaption();
			if(checkBox.getCaption().compareTo(filterName)==0){
				return true;
			}
		}
		return false;
	}
}
