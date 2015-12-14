package co.adme.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import co.adme.vaadin.view.main.MainViewImpl;

@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
@Title(value = "AdMe")
public class AdMeVaadinUI extends UI {
	
	@Autowired
	SpringViewProvider viewProvider;
	
	//@Autowired
	//MainViewImpl mainView;
	 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	/*
    	setContent(mainView);
		
		Navigator navigator = new Navigator(this, mainView);
        navigator.addProvider(viewProvider);        
        navigator.addViewChangeListener(mainView);  
        */
    	
    	final Panel mainContainer = new Panel();
    	mainContainer.setSizeFull();
        setContent(mainContainer);
        
        Navigator navigator = new Navigator(this, mainContainer);
        navigator.addProvider(viewProvider);
        //navigator.addViewChangeListener(mainView);
        
        UI.getCurrent().getNavigator().navigateTo(MainViewImpl.VIEW_NAME);
    }    
}