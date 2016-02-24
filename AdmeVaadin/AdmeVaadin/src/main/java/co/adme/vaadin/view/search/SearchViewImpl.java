package co.adme.vaadin.view.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.vaadin.LazyList;
import org.vaadin.LazyList.LazyItemFetcher;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.Renderer;
import com.vaadin.ui.themes.ValoTheme;

import co.adme.vaadin.AdMeVaadinUI;
import co.adme.vaadin.db.PubliDBContainer;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.view.ButtonData;
import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.publi.PubliSummaryView;
import de.datenhahn.vaadin.componentrenderer.grid.ComponentGrid;

//pay attention to the order of annotations
@SuppressWarnings("serial")
@UIScope
@SpringView(name = SearchViewImpl.VIEW_NAME)
@VaadinSessionScope
public class SearchViewImpl extends VerticalLayout 
							implements View, SearchView, ItemClickListener, ClickListener, 
								       SelectionListener{

	public static final String VIEW_NAME = "search";
	
	@Autowired
	private DefaultViewManager viewManager;
	
	@Autowired
	private PubliDBContainer publiDBContainer;
	
	 @Autowired
	 private ApplicationContext applicationContext;
	 
	 @Inject
	    EventBus.UIEventBus eventBus;
	
	private HorizontalLayout horizontal;
	private HorizontalLayout resultZone;
	private VerticalLayout filterZone;
	
	private TextField searchTerm;
	private ComboBox searchCity;
	private Button searchButton;
	
	private Table table;	
	private LazyList lazylist;
	private ListSelect listSelect;
	private ComponentGrid<Publi> grid;
	/* Only the presenter registers one listener... */
	private SearchViewListener listener;
	
	private Boolean newSearch = false;
	private List<Publi> publiList = new ArrayList<Publi>();
	 
	@PostConstruct
	void init() {
		setMargin(true);
	    setSpacing(true);
	    
	    eventBus.subscribe(this);
	    
	    //Se configuran las views con sus presenters
	    viewManager.configure(this);
	    
	    horizontal = new HorizontalLayout();
        horizontal.setSizeFull();
		addComponent(horizontal);
		
		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		horizontal.addComponent(navigationBar);
		
		searchTerm = new TextField( );
		searchTerm.setInputPrompt("Palabra Clave");
		navigationBar.addComponent(searchTerm);
		
		searchCity = new ComboBox( );
		searchCity.setNullSelectionAllowed(false);
		getAvailableCities();
		navigationBar.addComponent(searchCity);
		
		searchButton = new Button("Buscar");
		searchButton.setData(SearchViewImpl.VIEW_NAME);
		searchButton.addClickListener(this);
		searchButton.setClickShortcut(KeyCode.ENTER);
		searchButton.setData("search");
		navigationBar.addComponent(searchButton);
		
		//horizontal.setComponentAlignment(navigationBar, Alignment.MIDDLE_RIGHT);
		horizontal.setComponentAlignment(navigationBar, Alignment.MIDDLE_CENTER);
		
		resultZone = new HorizontalLayout();
		//resultZone.setSizeFull();
		resultZone.setVisible(false);
		addComponent(resultZone);
		
		filterZone = new VerticalLayout();
		//resultZone.addComponent(filterZone);
		
		Panel viewContainer = new Panel();
        //resultZone.addComponent(viewContainer);
	    
	    table = new Table("Publicaciones");
	    //table.setColumnExpandRatio(viewContainer,  1.0f);
	    table.setContainerDataSource(publiDBContainer);  
	    table.setVisibleColumns(PubliDBContainer.PROPERTIES);  
	    table.setColumnHeaders(PubliDBContainer.HEADERS);  
	    table.setSelectable(true);  
	    table.addItemClickListener(this);
	    table.setVisibleColumns(PubliDBContainer.VISIBLE);
	    
	    //viewContainer.setContent(table);
	    
	    
	    //---------------------------------------------------------
	    /*
	    LazyItemFetcher itemFetcher = new LazyItemFetcher() {
	        @Override
	        public List<Component> getMoreItems() {
	            return fetchMorePublis(false);
	        }
	    };
	    lazylist = new LazyList(itemFetcher);
	    lazylist.setSizeFull();
	    //viewContainer.setContent(lazylist);
	    resultZone.addComponent(lazylist);
	    */
	    
	    //---------------------------------------------------------
	    
	    listSelect = new ListSelect();
	    //resultZone.addComponent(listSelect);
	    
	  //---------------------------------------------------------
	 }
	
	private void getAvailableCities(){
		List<String> availableCities = new ArrayList<String>();
		availableCities.addAll(listener.getAvailableCities());
		
		searchCity.addItems(availableCities);
		
		searchCity.setValue(availableCities.get(0));
	}
	
	private SearchViewListener getMainListener(){
		return this.listener;
	}
	
	 @Override
	 public void enter(ViewChangeEvent event) {
	     // the view is constructed in the init() method()
	 }
	
	@Override
	public void setSearchResult(List<Publi> publiList) {
		if(!resultZone.isVisible()){resultZone.setVisible(true);}
		publiDBContainer.removeAllItems();
		publiDBContainer.removeAllContainerFilters();
		publiDBContainer.addAll(publiList);
		buildFilterZone(this.publiDBContainer, this.filterZone);
	}
	
	private void buildFilterZone(PubliDBContainer publiDBContainer, VerticalLayout filterZone){
		FilterOptionView filterOptionView = new FilterOptionView(publiDBContainer);
		
		filterZone.removeAllComponents();
		filterZone.addComponent(filterOptionView);
	}
	
	public void setSearchResultPage() {
		if(!resultZone.isVisible()){resultZone.setVisible(true);}
	    LazyItemFetcher itemFetcher = new LazyItemFetcher() {
	        @Override
	        public List<Component> getMoreItems() {
	            return fetchMorePublis();
	        }
	    };
	    resultZone.removeAllComponents();
	    lazylist = new LazyList(itemFetcher);
	    //lazylist.setSizeUndefined();
	    lazylist.setWidth(100, Unit.PERCENTAGE);
	    
	    resultZone.addComponent(lazylist);
	    
	    //listSelect.addItems(publiList);
	    
	    
	    grid = new ComponentGrid<>(Publi.class);
	    grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(70, Unit.PERCENTAGE);

        grid.setRows(publiList);
	    
        
	    grid.setDetailsGenerator(rowReference  -> {
            VerticalLayout layout = new VerticalLayout();
            layout.setHeight(300, Unit.PIXELS);
            layout.addComponent((createPubliView((Publi)rowReference.getItemId())));
            return layout;

        });
	    //grid.addItemClickListener(this);
	    
	    grid.addSelectionListener(this);
	    
	    grid.addComponentColumn(Publi.TITLE_PROPERTYID, publi -> new Label(publi.getTitle()));
	    
	    grid.setColumns(Publi.TITLE_PROPERTYID);

	    //resultZone.addComponent(grid);
	}
	
	public List<Component> fetchMorePublis(){
		List<Component> publiViews = new LinkedList<Component>();

		for (Publi publi : this.getMainListener().searchPubliListPage(searchTerm.getValue(), searchCity.getValue().toString(), newSearch))
			publiViews.add(createPubliView(publi));

		return publiViews;
	}
	
	private Component createPubliView(Publi publi) {
		Panel panel = new Panel();
		CssLayout cssLayout = new CssLayout();
		panel.setContent(cssLayout);
		//cssLayout.addStyleName("publi");
		
		this.publiList.add(publi);
		
		//PubliSummaryView publiSumanyView = new PubliSummaryView(publi);
		
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		PubliSummaryView publiSummaryView = factory.createBean(PubliSummaryView.class);
		publiSummaryView.setContent(publi);
		cssLayout.addComponent(publiSummaryView);
		
		/*
		BeanItem<Publi> publiItem = new BeanItem<Publi>(publi);
		FieldGroup binder = new FieldGroup(publiItem);
		 
		cssLayout.addComponent(binder.buildAndBind(Publi.TITLE_PROPERTYID));
		cssLayout.addComponent(binder.buildAndBind(Publi.CITY_PROPERTYID));
		cssLayout.addComponent(binder.buildAndBind(Publi.DESCRIPTION_PROPERTYID));
		cssLayout.addComponent(binder.buildAndBind(Publi.PRICE_PROPERTYID));
		*/

			/*
		Label title = new Label(publi.getTitle());
		title.setSizeUndefined();
		//title.addStyleName("Título");
		Label city = new Label(publi.getCity());
		city.setSizeUndefined();
		//city.addStyleName("Ciudad");
		Label description = new Label(publi.getDescription());
		description.setSizeUndefined();
		//description.addStyleName("Description");

		cssLayout.addComponent(title);
		cssLayout.addComponent(city);
		cssLayout.addComponent(description);
		 */
		 
		return panel;
	}
	
	@Override
	public void addSearchListener(SearchViewListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String option = (String)event.getButton().getData();
		
		switch(option){
		case "search":
			listener.searchPubliList(searchTerm.getValue(), searchCity.getValue().toString());

		    //List<Publi> publiList = listener.searchPubliListPage(searchTerm.getValue(), searchCity.getValue().toString(), true);
		    this.newSearch = true;
		    setSearchResultPage();
		    this.newSearch = false;
		default:
		}
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		/*
		Object i = event.getItemId();
		Publi publi = publiDBContainer.getItem(i).getBean();
		//publiDetailWindow.setPubliDetails(publi);
		
		switch(event.getButton()){
		case LEFT:
			// Call sub-window
			PubliDetailWindow publiDetailWindow = new PubliDetailWindow(publi);
			UI.getCurrent().addWindow(publiDetailWindow);
			break;
		case MIDDLE:
			//UI.getCurrent().getNavigator().navigateTo(PubliDetailView.VIEW_NAME);
			//BrowserWindowOpener open = new BrowserWindowOpener(PubliDetailView.VIEW_NAME);

			UI.getCurrent().getPage().open(PubliDetailView.VIEW_NAME, "_blank");
			break;
		default:
			break;
		}
		*/
		Publi publi = (Publi) event.getItemId();
		grid.setDetailsVisible(publi, true);
	}

	@Override
	public void select(SelectionEvent event) {
		Publi publi = (Publi) event.getSelected();
		grid.setDetailsVisible(publi, true);
	}
	
	@EventBusListenerMethod
    public void onEvent(Event event) {
		Logger log = LoggerFactory.getLogger(SearchViewImpl.class);
		log.debug("Event received: {}", event.getSource().toString());
    }
}
