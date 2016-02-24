package co.adme.vaadin.view.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.db.PubliDBContainer;
import co.adme.vaadin.modelo.Publi;

@SuppressWarnings("serial")
public class FilterOptionView extends VerticalLayout 
							  implements ValueChangeListener{
	
	private PubliDBContainer publiDBContainer;
	private List<OptionGroup> optionGroupList = new ArrayList<OptionGroup>();
	
	public FilterOptionView(PubliDBContainer publiDBContainer){
		this.publiDBContainer = publiDBContainer;	
		buildView();
	}
	
	public void addFilterOption(OptionGroup optionGroup){
		if(optionGroup!=null && !optionGroup.getItemIds().isEmpty()){
			this.optionGroupList.add(optionGroup);
		}
	}
	
	private void buildView(){
		generateFilterOptionList();
		rebuild();
	}
	
	private void generateFilterOptionList(){
		Collection<?> idList = this.publiDBContainer.getItemIds();
		Object[] headers = PubliDBContainer.VISIBLE;

		for(Object header: headers){
			OptionGroup optionGroup = new OptionGroup();
			optionGroup.addValueChangeListener(this);
			for(Object id:idList){
				Publi publi = publiDBContainer.getItem(id).getBean();
				switch(header.toString()){
					case "title":
						optionGroup.setCaption(this.publiDBContainer.getTitleHeader());
						optionGroup.addItem(publi.getTitle());
						optionGroup.setMultiSelect(true);
						optionGroup.setConverter(CheckBox.class);
						optionGroup.setData(header);
						break;
					case "city":
						optionGroup.setCaption(this.publiDBContainer.getCityHeader());
						optionGroup.addItem(publi.getCity());
						optionGroup.setMultiSelect(true);
						optionGroup.setNullSelectionAllowed(true);
						optionGroup.setData(header);
						break;
				}
				
			}			
			addFilterOption(optionGroup);
		}
	}
	
	private void build(){
		Panel panel = new Panel();
		Accordion accordion = new Accordion();
		accordion.setSizeFull();
		panel.setContent(accordion);
		
		if(optionGroupList!=null && !optionGroupList.isEmpty()){
			for(OptionGroup optionGroup:this.optionGroupList){
				accordion.addTab(optionGroup,optionGroup.getCaption());
			}
			
			addComponent(panel);
		}
	}
	
	public void rebuild(){
		removeAllComponents();
		build();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		OptionGroup optionGroup = (OptionGroup) event.getProperty();
		String values = (String) optionGroup.getValue().toString();
		String[] valueList = values.split(", ");
		String data = (String) optionGroup.getData();
		
		if(data!=null ){
			if(!data.isEmpty()){
				if(optionGroup.isMultiSelect()){
					this.publiDBContainer.removeContainerFilters(data);
				}
				List<SimpleStringFilter> simpleStringFilterList = new ArrayList<SimpleStringFilter>();
				for(String value:valueList){
					if(value.compareTo("[]")!=0 || value.isEmpty())	{
						String subvalue;
						if(value.contains("[")&&value.contains("]")){
							subvalue = value.substring(value.indexOf("[")+1, value.indexOf("]"));
						}else if(value.contains("[")){
							subvalue = value.substring(value.indexOf("[")+1, value.length());
						}else if(value.contains("]")){
							subvalue = value.substring(0, value.indexOf("]"));
						}else{
							subvalue = value;
						}
						subvalue.replaceAll("\\s+","");				
						simpleStringFilterList.add(new SimpleStringFilter(data,
																		subvalue, true, false));
						
					}
				}
				Or or = null;
				for(SimpleStringFilter simpleStringFilter:simpleStringFilterList){
					if(or==null){
						or = new Or(simpleStringFilter);
					}else{
						or = new Or(simpleStringFilter,or);
					}
				}
				if(or!=null){this.publiDBContainer.addContainerFilter(or);}
			}else{
				this.publiDBContainer.removeContainerFilters(data);
			}
		}
	}
}
