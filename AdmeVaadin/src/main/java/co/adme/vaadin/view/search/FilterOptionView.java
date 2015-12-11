package co.adme.vaadin.view.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.db.PubliDBContainer;
import co.adme.vaadin.modelo.FilterOption;
import co.adme.vaadin.modelo.Publi;

@SuppressWarnings("serial")
public class FilterOptionView extends VerticalLayout 
							  implements ValueChangeListener{
	
	private PubliDBContainer publiDBContainer;
	private List<FilterOption> filterOptionList = new ArrayList<FilterOption>();
	
	public FilterOptionView(PubliDBContainer publiDBContainer){
		this.publiDBContainer = publiDBContainer;	
		buildView();
	}
	
	public void addFilterOption(FilterOption filterOption){
		if(!filterOption.getFilterList().isEmpty()){
			this.filterOptionList.add(filterOption);
		}
	}
	
	private void buildView(){
		generateFilterOptionList();
		build();
	}
	
	private void generateFilterOptionList(){
		Collection<?> idList = this.publiDBContainer.getItemIds();
		Object[] headers = PubliDBContainer.VISIBLE;

		for(Object header: headers){
			FilterOption filterOption = new FilterOption(header.toString());
			for(Object id:idList){
				Publi publi = publiDBContainer.getItem(id).getBean();
				switch(header.toString()){
					case "title": filterOption.addFilter(publi.getTitle());
						break;
					case "city": filterOption.addFilter(publi.getCity());
						break;
				}
				
			}			
			addFilterOption(filterOption);
		}
	}
	
	private void build(){
		Panel panel = new Panel();
		for(FilterOption filter:this.filterOptionList){
			panel.setContent(new Label(filter.getTitle()));
			for(CheckBox checkBox:filter.getFilterList()){
				checkBox.addValueChangeListener(this);
				panel.setContent(checkBox);
			}
		}
		
		addComponent(panel);
	}
	
	public void rebuild(){
		removeAllComponents();
		build();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		CheckBox checkbox= (CheckBox) event.getProperty();
	}
}
