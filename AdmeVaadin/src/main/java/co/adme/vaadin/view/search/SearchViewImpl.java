package co.adme.vaadin.view.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import co.adme.vaadin.db.PubliDBContainer;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.DefaultViewManager;

//pay attention to the order of annotations
@SuppressWarnings("serial")
@UIScope
@SpringView(name = SearchViewImpl.VIEW_NAME)
@VaadinSessionScope
public class SearchViewImpl extends VerticalLayout 
							implements View, SearchView, ItemClickListener, ClickListener {

	public static final String VIEW_NAME = "search";
	
	@Autowired
	private DefaultViewManager viewManager;
	
	@Autowired
	private PubliDBContainer publiDBContainer;
	
	private HorizontalLayout horizontal;
	private HorizontalLayout resultZone;
	private VerticalLayout filterZone;
	
	private TextField searchTerm;
	private TextField searchCity;
	private Button searchButton;
	
	private Table table;	
	/* Only the presenter registers one listener... */
	private List<SearchViewListener> listeners =
	        	new ArrayList<SearchViewListener>();
	 
	@PostConstruct
	void init() {
		setMargin(true);
	    setSpacing(true);
	    
	    horizontal = new HorizontalLayout();
        horizontal.setWidth("100%");
		addComponent(horizontal);
		
		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		horizontal.addComponent(navigationBar);
		
		searchTerm = new TextField( );
		searchTerm.setInputPrompt("Palabra Clave");
		navigationBar.addComponent(searchTerm);
		
		searchCity = new TextField( );
		searchCity.setValue("Barcelona");
		searchCity.setEnabled(false);
		navigationBar.addComponent(searchCity);
		
		searchButton = new Button("Buscar");
		searchButton.setData(SearchViewImpl.VIEW_NAME);
		searchButton.addClickListener(this);
		searchButton.setClickShortcut(KeyCode.ENTER);
		navigationBar.addComponent(searchButton);
		
		horizontal.setComponentAlignment(navigationBar, Alignment.MIDDLE_RIGHT);
		//horizontal.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		
		resultZone = new HorizontalLayout();
		resultZone.setVisible(false);
		addComponent(resultZone);
		
		filterZone = new VerticalLayout();
		resultZone.addComponent(filterZone);
		
		Panel viewContainer = new Panel();
		viewContainer.setSizeFull();
        viewContainer.setSizeUndefined();
        resultZone.addComponent(viewContainer);
        resultZone.setExpandRatio(viewContainer, 1.0f);
	    
	    table = new Table("Publicaciones");
	    table.setColumnExpandRatio(viewContainer,  1.0f);
	    table.setContainerDataSource(publiDBContainer);  
	    table.setVisibleColumns(PubliDBContainer.PROPERTIES);  
	    table.setColumnHeaders(PubliDBContainer.HEADERS);  
	    table.setSelectable(true);  
	    table.addItemClickListener(this);
	    table.setVisibleColumns(PubliDBContainer.VISIBLE);
	    
	    viewContainer.setContent(table);
	     
	    //Se configuran las views con sus presenters
	    viewManager.configure(this);
	 }
	
	 @Override
	 public void enter(ViewChangeEvent event) {
	     // the view is constructed in the init() method()
	 }
	
	@Override
	public void setSearchResult(List<Publi> publiList) {
		if(!resultZone.isVisible()){resultZone.setVisible(true);}
		publiDBContainer.removeAllItems();
		publiDBContainer.addAll(publiList);
		buildFilterZone(this.publiDBContainer, this.filterZone);
	}
	
	private void buildFilterZone(PubliDBContainer publiDBContainer, VerticalLayout filterZone){
		FilterOptionView filterOptionView = new FilterOptionView(publiDBContainer);
		
		filterZone.addComponent(filterOptionView);
	}
	
	@Override
	public void addSearchListener(SearchViewListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
	    for(SearchViewListener listener: listeners){
	    	listener.searchPubliList(searchTerm.getValue());
	    }
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		Object i = event.getItemId();
		Publi publi = publiDBContainer.getItem(i).getBean();
			
		// Call sub-window
		PubliDetailWindow publiDetailWindow = new PubliDetailWindow(publi);
		UI.getCurrent().addWindow(publiDetailWindow);
	}
}
