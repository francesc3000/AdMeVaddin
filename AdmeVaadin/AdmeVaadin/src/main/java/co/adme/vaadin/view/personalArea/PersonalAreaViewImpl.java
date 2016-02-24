package co.adme.vaadin.view.personalArea;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.scrollablepanel.ScrollablePanel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import co.adme.vaadin.view.DefaultViewManager;
import co.adme.vaadin.view.group.GroupViewImpl;
import co.adme.vaadin.view.publi.PubliViewImpl;
	
//pay attention to the order of annotations
@SuppressWarnings("serial")
@UIScope
@SpringView(name = PersonalAreaViewImpl.VIEW_NAME)
@VaadinSessionScope
public class PersonalAreaViewImpl extends VerticalLayout 
								  implements View, PersonalAreaView, 
								  			 MenuBar.Command {

	public static final String VIEW_NAME = "personalArea";
	
	@Autowired
	private DefaultViewManager viewManager;

	@Autowired
	private PubliViewImpl publiViewImpl;
	
	@Autowired
	private GroupViewImpl groupViewImpl;
	
	private ScrollablePanel viewContent;
	
	/* Only the presenter registers one listener... */
	private PersonalAreaViewListener listener;
	 
	@PostConstruct
	void init() {
	    //Se configuran las views con sus presenters
	    viewManager.configure(this);
	    
	    HorizontalLayout main = new HorizontalLayout();
	    main.setSizeFull();
	    addComponent(main);
	    
	    MenuBar menubar = new MenuBar();
	    main.addComponent(menubar);
	    
	    MenuBar.MenuItem mainItem = menubar.addItem("",this);
	    mainItem.addItem("Home", this);
	    mainItem.addItem("Publis", this);
	    mainItem.addItem("Grupos", this);
	    menubar.setAutoOpen(true);
	    
	    groupViewImpl.load();
	    publiViewImpl.load();
	    
	    viewContent = new ScrollablePanel();
	    viewContent.setSizeUndefined();
	    viewContent.setContent(publiViewImpl);
	    main.addComponent(viewContent);
	    
	    main.setExpandRatio(menubar, new Float("0.06"));
	    main.setExpandRatio(viewContent, 2);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	@Override
	public void addPersonalAreaListener(PersonalAreaViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		switch(selectedItem.getText()){
		case "Home":
			UI.getCurrent().getNavigator().navigateTo("");
			break;
		case "Publis":
			publiViewImpl.load();
			this.viewContent.setContent(publiViewImpl);
			break;
		case "Grupos":
			groupViewImpl.load();
			this.viewContent.setContent(groupViewImpl);
			break; 
		}
	}
}